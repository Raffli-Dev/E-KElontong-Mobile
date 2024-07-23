<?php

namespace App\Controllers;

use App\Interfaces\ApiInterface;
use App\Models\AppVersionModels;
use App\Models\CompanyModels;
use App\Models\CustomerModels;
use App\Models\ItemsProductModels;
use App\Models\KategoriBarangModels;
use App\Models\NotifDeviceModels;
use App\Models\PengeluaranModels;
use App\Models\PosModels;
use App\Models\SalesDetailModels;
use App\Models\SalesItemReturnModels;
use App\Models\SalesModels;
use App\Models\SalesPaymentReturnModels;
use App\Models\SalesReturnModels;
use App\Models\SatuanBarangModels;
use App\Models\StockEntryModels;
use App\Models\UsersAppModels;
use App\Repository\CategoryRepository;
use App\Repository\ItemsProductRepository;
use App\Repository\KategoriBarangRepository;
use App\Repository\PengeluaranRepository;
use App\Repository\PosRepository;
use App\Repository\SalesItemRepository;
use App\Repository\SalesRepository;
use App\Repository\SatuanBarangRepository;
use App\Repository\UnitSatuanRepository;
use App\Service\CategoryService;
use App\Service\DashboardService;
use App\Service\NeracaService;
use App\Service\UnitSatuanService;
use CodeIgniter\API\ResponseTrait;
use CodeIgniter\Database\Exceptions\DatabaseException;
use Config\Database;

class ApiController extends BaseController implements ApiInterface
{
    use ResponseTrait;
    function __construct()
    {
        $this->appVersionModels = new AppVersionModels();
        $this->usersAppModels = new UsersAppModels();
        $this->notifDeviceModels = new NotifDeviceModels();
        $this->companyModels = new CompanyModels();
        $this->dashboardService = new DashboardService();
        $this->categoryService = new CategoryService();
        $this->unitSatuanService = new UnitSatuanService();
        $this->itemsProductRepository = new ItemsProductRepository();
        $this->categoryRepository = new CategoryRepository();
        $this->unitSatuanRepository = new UnitSatuanRepository();
        $this->itemsProductModels = new ItemsProductModels();
        $this->stockEntryModels = new StockEntryModels();
        $this->posRepository = new PosRepository();
        $this->posModels = new SalesModels();
        $this->salesDetailModels = new SalesDetailModels();
        $this->pengeluaranRepository = new PengeluaranRepository();
        $this->pengeluaranModels = new PengeluaranModels();
        $this->salesRepository = new SalesRepository();
        $this->kategoriBarangRepository = new KategoriBarangRepository();
        $this->kategoriBarangModels = new KategoriBarangModels();
        $this->satuanBarangModels = new SatuanBarangModels();
        $this->satuanBarangRepository = new SatuanBarangRepository();
        $this->salesReturnModels = new SalesReturnModels();
        $this->salesItemReturnModels = new SalesItemReturnModels();
        $this->salesPaymentReturnModels = new SalesPaymentReturnModels();
        $this->customerModels = new CustomerModels();
        $this->salesItemRepository = new SalesItemRepository();
        $this->neracaService = new NeracaService();

    }
    protected $USER_UPLOAD_PATH = './uploads/items/';


    public function check()
    {
        try {
            $connection = Database::connect()->persistentConnect();
            $status = ($connection->ping() ? "SUCCESS" : "FAILED");
            return "DB connection : " . $status;
        } catch (\Throwable $th) {
            return $th->getMessage();
        }
        return $this->respondSuccess($connection);
    }

    public function info()
    {
        $validation_arr = [
            'version' => 'required|integer',
            'type' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }

        $version = (int) $this->request->getVar('version');
        $type = $this->request->getVar('type');
        $appVersion = $this->appVersionModels->getOne(['version_code' => $version, 'type' => $type]);

        $resp['status'] = 200;
        if (!$appVersion) {
            $resp['app_status'] = 'FORCE-UPDATE';
        } else {
            $resp['app_status'] = $appVersion['status'];
        }

        return $this->respond($resp);

    }

    protected function getPlainMessage($errors) {
        if (!is_array($errors)) return $errors;
        return str_replace('.,', ',', implode(', ', array_values($errors)));
    }
    protected function respondFail($messages) {
        if (is_array($messages)) {
            $messages = $this->getPlainMessage($messages);
        }
        $resp = ['status' => 400, 'messages' => $messages];
        return $this->respond($resp, 200);
    }
    protected function respondSuccess($messages, $data = null) {
        $resp = ['status' => 200, 'messages' => $messages];
        if ($data) $resp['data'] = $data;
        return $this->respond($resp);
    }
    protected function respondObject($data) {
        $resp = ['status' => 200];
        foreach ($data as $key => $value) {
            $resp[$key] = $value;
        }
        return $this->respond($resp);
    }
    protected function respondList($total, $page, $list) {
        $resp = [
            'status' => 200,
            'count' => sizeof($list),
            'count_total' => $total,
            'page' => (int) $page,
            'list' => $list
        ];
        return $this->respond($resp);
    }


    public function login()
    {
        $validation_arr = [
            'email' => [
                'rules' => 'required|valid_email|is_not_unique[user_app.email]',
                'errors' => [
                    'is_not_unique' => 'Email not found, please sign up before sign in',
                ]
            ],
            'password' => 'required'
        ];

        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }

        $email = $this->request->getVar('email');
        $password = $this->request->getVar('password');
        $device_id = $this->request->getVar('device_id') ? $this->request->getVar('device_id') : null;

        $user = $this->usersAppModels->getOneByEmail($email);
        if (!password_verify($password, $user['password'])) {
            return $this->respondFail('Password do not match');
        }

        $device = $this->notifDeviceModels->getByDeviceId($device_id);

        if (!$user) {
            return $this->respondFail('User not found');
        } else if ($user['status'] == 'INACTIVE') {
            return $this->respondFail('Access denied');
        }

        if ($device) {
            $device['user_app'] = $user['id'];
            $this->notifDeviceModels->update($device['id'], $device);
        }

        unset($user['password'], $user['reset_code']);

        return $this->respondSuccess('Login successfully', $user);
    }

    public function register()
    {
        $user = null;
        $id = $this->request->getVar('id');
        if ($id) $user = $this->usersAppModels->find($id);
        $is_new = ($user == null);

        $validation_arr = [
            'email' => [
                'rules' => 'required|valid_email|is_unique[user_app.email]',
                'errors' => [
                    'is_unique' => 'Email is available, please use another email',
                    'valid_email' => 'The email writing format must be correct',
                ]
            ],
            'phone' => [
                'rules' => 'required|is_unique[user_app.phone]',
                'errors' => [
                    'is_unique' => 'Email is available, please use another email',
                ]
            ],
            'password' => 'required',
            'owner' => 'required',
            'company_name' => 'required',
            'address' => 'required',
        ];

        if (!$is_new) {
            $old_user = $user;
            if ($old_user['email'] == $this->request->getVar('email')) {
                $validation_arr['email'] = 'required';
            }
            $validation_arr['password'] = 'permit_empty';
        }

        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }

        $dataCompany = [
            'id' => '',
            'owner' => $this->request->getVar('name'),
            'company_name' => $this->request->getVar('company_name'),
            'mobile' => $this->request->getVar('phone'),
            'phone' => $this->request->getVar('phone'),
            'email' => $this->request->getVar('email'),
            'logo' => 'logo-0.png',
            'show_signature' => '0',
            'country' => 'Indonesia',
            'address' => $this->request->getVar('address'),
            'cid' => 1,
            'category_init' => 'CT',
            'item_init' => 'IT',
            'supplier_init' => 'SP',
            'purchase_init' => 'PU',
            'purchase_return_init' => 'PR',
            'customer_init' => 'CU',
            'sales_init' => 'SL',
            'sales_return_init' => 'PR',
            'expense_init' => 'EX',
            'invoice_view' => '1',
            'status' => '1',
            'sms_status' => '0',
        ];
        $resultIdCompany = $this->companyModels->saveWithReturnId($dataCompany);
        $data = array(
            'id' => $is_new ? '' : $user['id'],
            'name' => $this->request->getVar('name'),
            'email' => $this->request->getVar('email'),
            'phone' => $this->request->getVar('phone'),
            'status' => 'ACTIVE',
            'company_id' => $resultIdCompany,
        );
        if ($this->request->getVar('password')) {
            $data['password'] = password_hash($this->request->getVar('password'), PASSWORD_DEFAULT);
        }

        $device_id = $this->request->getVar('device_id');
        $fcm_regid = $this->request->getVar('fcm_regid');

        $device = $this->notifDeviceModels->getByDeviceId($device_id);
        if (!$device && $fcm_regid) {
            $device = $this->notifDeviceModels->getByFcmRegId($fcm_regid);
        }

        if (!$is_new) {

        }
        // update notif
        $resultId = $this->usersAppModels->saveWithReturnId($data);
        if ($resultId != -1) {  // when succcess
            if ($device) {
                $device['user_app'] = $resultId;
                $this->notifDeviceModels->update($device['id'], $device);
            }
        } else {
            return $this->respondFail(($is_new ? 'Register' : 'Update') . ' user failed');
        }

        $newUser = $this->usersAppModels->find($resultId);
        unset($newUser['password'], $newUser['reset_code']);

        return $this->respondSuccess(($is_new ? 'Register' : 'Update') . ' user successfully', $newUser);
    }

    public function show_dashboard()
    {
        $validation_arr = [
            'company_id' => 'required',
            'users_id' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $company_id = $this->request->getVar('company_id');
        $users_id = $this->request->getVar('users_id');
        $tanggal_mulai = $this->request->getVar('tanggal_mulai');
        $tanggal_selesai = $this->request->getVar('tanggal_selesai');

        $companyModels = new CompanyModels();
        if ($companyModels->check_count($company_id) < 1) {
            return $this->respondFail("Company not found");
        }
        $findCompany = $companyModels->find_by_id($company_id);
        $usersModels = new UsersAppModels();
        if ($usersModels->check_count($users_id) < 1) {
            return $this->respondFail("Users not found");
        }


        $result = $this->dashboardService->show($company_id, $users_id, $findCompany, $tanggal_mulai, $tanggal_selesai);

        $response['status'] = 200;
        $response['messages'] = "loaded dashboard";
        $response['data'] = $result;

        return $this->respond($response);
    }

    public function show_all_category()
    {
        $validation_arr = [
            'count' => 'permit_empty|if_exist|greater_than[0]',
            'page' => 'permit_empty|if_exist|greater_than[0]',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $list = $this->categoryService->showAll($this->request);
        return $this->respondList(1000, 1, $list);
    }

    public function show_all_unit_satuan()
    {
        $validation_arr = [
            'count' => 'permit_empty|if_exist|greater_than[0]',
            'page' => 'permit_empty|if_exist|greater_than[0]',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $list = $this->unitSatuanService->showAll($this->request);
        return $this->respondList(1000, 1, $list);
    }

    public function save_product()
    {
        try {

            $this->itemsProductModels->db->transException(true)->transStart();
            $this->stockEntryModels->db->transException(true)->transStart();

            $products = null;
            $image_url = null;
            $id = $this->request->getVar('id');
            if ($id) $products = $this->itemsProductRepository->findById($id);
            $image_url = $this->request->getVar('image_url');
            $is_new = ($products == null);

            $validation_arr = [
                'item_name' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Nama Barang Harus Diisi',
                    ]
                ],
                'company_id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Company Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                    ]
                ],
                'users_id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Users Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                    ]
                ],
                'category_id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Katehgori Harus Diisi',
                    ]
                ],
                'unit_id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Satuan Harus Diisi',
                    ]
                ],

                'alert_qty' => [
                    'rules' => 'required|is_natural_no_zero',
                    'errors' => [
                        'required' => 'Kuantiti Minimal Barang Harus Diisi',
                        'is_natural_no_zero' => 'alert_qty Harus Diisi Dengan Angka Tidak Boleh Kosong',
                    ]
                ],
                'purchase_price' => [
                    'rules' => 'required|is_natural_no_zero',
                    'errors' => [
                        'required' => 'Harga Pembelian Harus Diisi',
                        'is_natural_no_zero' => 'purchase_price Harus Diisi Dengan Angka Tidak Boleh Kosong',
                    ]
                ],
                'sales_price' => [
                    'rules' => 'required|is_natural_no_zero',
                    'errors' => [
                        'required' => 'Harga Jual Harus Diisi',
                        'is_natural_no_zero' => 'sales_price Harus Diisi Dengan Angka Tidak Boleh Kosong',
                    ]
                ],
            ];

            if ($is_new) {
                $validation_arr = [
                    'new_opening_stock' => [
                        'rules' => 'required|is_natural_no_zero',
                        'errors' => [
                            'required' => 'Stok Harus Diisi',
                            'is_natural_no_zero' => 'Harus Diisi Dengan Angka Tidak Boleh Kosong',
                        ]
                    ],
                ];
            }

            // get image
            $imageFile = $this->request->getFile('image');

            if($imageFile != null){
                $validation_arr['image'] = 'uploaded[image]|is_image[image]|max_size[image,1024]|mime_in[image,image/jpg,image/jpeg,image/png]';
            }
            $imageName = $is_new ? null : $products['item_image'];


            // when param error
            if (!$this->validate($validation_arr)) {
                return $this->respondFail($this->validator->getErrors());
            }


            if ($imageFile && $imageFile->getError() == 0) {
                $oldImageName = $imageName;
                // generate file name random
                $imageName = $imageFile->getRandomName();

                // move file to folder uplads
                $imageFile->move($this->USER_UPLOAD_PATH, $imageName);

                // delete old image
                if ($oldImageName && file_exists($this->USER_UPLOAD_PATH . $oldImageName)) {
                    unlink($this->USER_UPLOAD_PATH . $oldImageName);
                }
            }


            $item_name = $this->request->getVar('item_name');
            $alert_qty = $this->request->getVar('alert_qty');
            $purchase_price = $this->request->getVar('purchase_price');
            $sales_price = $this->request->getVar('sales_price');
            $new_opening_stock = $this->request->getVar('new_opening_stock');
            $category_id = $this->request->getVar('category_id');
            $unit_id = $this->request->getVar('unit_id');
            $custom_barcode = $this->request->getVar('custom_barcode');
            $expire_date = $this->request->getVar('expire_date');
            $description = $this->request->getVar('description');
            $company_id = $this->request->getVar('company_id');
            $users_id = $this->request->getVar('users_id');

            $findCompany = $this->companyModels->find_by_id($company_id);
            $totalProductItems = $this->itemsProductRepository->total($company_id);
            $item_init = $findCompany['item_init'];
            $itemCode = '0';
            if ($totalProductItems < 1) {
                $itemCode = $item_init.$company_id."0001";
            }
            $maxId = $this->itemsProductRepository->maxid();
            $itemCode = $item_init.$company_id.str_pad($maxId, 4, '0', STR_PAD_LEFT);
            $category_id = explode("-", $category_id);
            $findCategory  = $this->categoryRepository->findByCode($category_id[0]);
            $findUnitSatuanByName = $this->unitSatuanRepository->findByName($unit_id);
            $findUsers = $this->usersAppModels->findById($users_id);

            if (!$is_new) {
                $old_products = $products;
                $itemCode = $old_products['item_code'];
            }

            if ($expire_date != "") {
                $expire_date = date('Y-m-d', strtotime($expire_date));
            }

            $data = [
                'id' => $is_new ? '' : $products['id'],
                'description' => $description,
                'item_code' => $itemCode,
                'item_name' => $item_name,
                'brand_id' => '0',
                'category_id' => $findCategory['id'],
                'sku' => '-',
                'hsn' => '-',
                'unit_id' => $findUnitSatuanByName['id'],
                'alert_qty' => $alert_qty,
                'lot_number' => "0",
                'expire_date' => $expire_date,
                'price' => $purchase_price,
                'tax_id' => "0",
                'purchase_price' => $purchase_price,
                'tax_type' => "0",
                'profit_margin' => "0",
                'sales_price' => $sales_price,
                'custom_barcode' => $custom_barcode,
                'final_price' => $sales_price,
                'system_ip' => "-",
                'system_name' => 'AndroidApp',
                'created_date' => date('Y-m-d'),
                'created_time' => date('H:i:s'),
                'created_by' => $findUsers['name'],
                'status' => '1',
                'discount_type' => "0",
                'discount' => '0',
                'company_id' => $company_id,
                'item_image' => $image_url ? $image_url : $imageName,
            ];
            $resultId = $this->itemsProductModels->saveWithReturnId($data);
            if ($resultId != -1) {

                if ($is_new) {
                    $dataStockEntry = [
                        'id' => '',
                        'entry_date' => date('Y-m-d'),
                        'item_id' => $resultId,
                        'qty' => $new_opening_stock,
                        'note' => $description,
                        'status' => '1',
                    ];
                    $this->stockEntryModels->saveWithReturnId($dataStockEntry);

                    //UPDATE itemS QUANTITY IN itemS TABLE
                    $this->posRepository->update_items_quantity($resultId);
                }


            } else {
                return $this->respondFail(($is_new ? 'Register' : 'Update') . ' user failed');
            }
            $newProducts = $this->itemsProductModels->find($resultId);
            if($newProducts['item_image']){
                $newProducts['item_image'] = base_url($this->USER_UPLOAD_PATH) .'/'. $newProducts['item_image'];
            }

            $this->stockEntryModels->db->transComplete();
            $this->itemsProductModels->db->transComplete();

            return $this->respondSuccess(($is_new ? 'Register' : 'Update') . ' Product successfully', $newProducts);
        } catch (DatabaseException $exception) {
            return $this->respondFail($exception->getMessage());
        }
    }

    public function show_all_product()
    {
        $validation_arr = [

            'company_id' => [
                'rules' => 'required',
                'errors' => [
                    'required' => 'Company Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                ]
            ],
            'users_id' => [
                'rules' => 'required',
                'errors' => [
                    'required' => 'Users Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                ]
            ],
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }

        $company_id = $this->request->getVar('company_id');
        $users_id = $this->request->getVar('users_id');
        $search = ($this->request->getVar('search') == "") ? "" : $this->request->getVar('search');

        $list = [];
        $result = $this->itemsProductRepository->show_list_product($company_id, $search);
        foreach ($result as $value) {
            $row = [];
            $row['id'] = $value->id;
            $row['item_image'] = base_url('uploads/items/'.$value->item_image);
            $row['item_code'] = $value->item_code;
            $row['item_name'] = strtoupper($value->item_name);
            $row['category_name'] = $value->category_name;
            $row['unit_name'] = $value->unit_name;
            $row['stock'] = (float) $value->stock;
            $row['alert_qty'] = (float) $value->alert_qty;
            $row['purchase_price'] = (float) $value->purchase_price;
            $row['final_price'] = (float) $value->final_price;
            $row['tax_name'] = $value->tax_name;
            $row['tax'] = $value->tax;
            $row['status'] = $value->status;
            $row['brand_name'] = $value->brand_name;
            $row['tax_type'] = $value->tax_type;
            $row['hsn'] = $value->hsn;
            $row['sku'] = $value->sku;
            $row['brand_name'] = $value->brand_name;
            $list[] = $row;
        }
        return $this->respondList(1000,1, $list);
    }

    public function productDetails(string $id)
    {
        $product = $this->itemsProductRepository->find($id);

        $findCategory  = $this->categoryRepository->findId($product['category_id']);
        $findUnit  = $this->unitSatuanRepository->findId($product['unit_id']);

        $product['item_image'] = base_url('uploads/items/'.$product['item_image']);
        $product['category_id'] = $findCategory['category_code']."-".$findCategory['category_name'];
        $product['unit_id'] = $findUnit['unit_name'];
        $product['purchase_price'] = (float)$product['purchase_price'];
        $product['price'] = (float)$product['price'];
        $product['sales_price'] = (float)$product['sales_price'];
        $product['final_price'] = (float)$product['final_price'];
        $product['stock'] = (float)$product['stock'];

        if ($product['expire_date'] != "") {
            $product['expire_date'] = date("F d, Y", strtotime($product['expire_date']));
        } else {
            $product['expire_date'] = "";
        }

        return $this->respondSuccess('success', $product);
    }

    public function updateManajemenStok(){
        try {

            $this->stockEntryModels->db->transException(true)->transStart();



            $validation_arr = [
                'id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'ID Barang Harus Diisi',
                    ]
                ],
                'item_name' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Nama Barang Harus Diisi',
                    ]
                ],
                'company_id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Company Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                    ]
                ],
                'users_id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Users Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                    ]
                ],
                'new_opening_stock' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Stok Harus Diisi',
                    ]
                ],

            ];

            // when param error
            if (!$this->validate($validation_arr)) {
                return $this->respondFail($this->validator->getErrors());
            }

            $products = null;
            $id = $this->request->getVar('id');
            $products = $this->itemsProductRepository->findById($id);

            $item_name = $this->request->getVar('item_name');
            $new_opening_stock = $this->request->getVar('new_opening_stock');
            $company_id = $this->request->getVar('company_id');
            $users_id = $this->request->getVar('users_id');
            $type = $this->request->getVar('type');

            if ($type == "Penambahan (+)") {
                $type_pengurangan_penambahan = "+";
            } else if ($type == "Pengurangan (-)") {
                $type_pengurangan_penambahan = "-";
            }
            $new_opening_stock = $type_pengurangan_penambahan."".$new_opening_stock;



            if(!empty($new_opening_stock) && $new_opening_stock!=0){
                $dataStockEntry = [
                    'id' => '',
                    'entry_date' => date('Y-m-d'),
                    'item_id' => $id,
                    'qty' => $new_opening_stock,
                    'note' => '',
                    'status' => '1',
                ];
                $this->stockEntryModels->saveWithReturnId($dataStockEntry);

                //UPDATE itemS QUANTITY IN itemS TABLE
                $this->posRepository->update_items_quantity($id);
            }

            $this->stockEntryModels->db->transComplete();

            return $this->respondSuccess( 'Update Product successfully', $products);
        } catch (DatabaseException $exception) {
            return $this->respondFail($exception->getMessage());
        }
    }

    public function savePayment()
    {
        try {

            $this->posModels->db->transException(true)->transStart();
            $this->salesDetailModels->db->transException(true)->transStart();

            $data = json_decode(file_get_contents("php://input"), true);
            if(!isset($data) || !isset($data['product_order']) || !isset($data['product_order_detail'])) return $this->respondFail($this->validator->getErrors());

            // check stock product
            $order_detail = $data['product_order_detail'];
            $resp_stock = $this->itemsProductRepository->checkAvailableProductOrderDetail($order_detail);

            // submit order
            $sales_date  = date('Y-m-d');
            $discount_input = 'NULL';
            $tot_disc= 'NULL';
            $tot_grand = 'NULL';
            $round_off = 0;

            $userFindById = $this->usersAppModels->findById($data['product_order']['users_id']);
            $customer_name = $userFindById['name'];
            $mobile = $userFindById['phone'];

            $company_detail = $this->companyModels->find_by_id($data['product_order']['company_id']);

            $maxid = $this->posModels->maxid();
            $sales_code=$company_detail['sales_init'].str_pad($maxid['maxid'], 4, '0', STR_PAD_LEFT);

            $sales_entry = [
                'id'                        => '',
                'sales_code' 				=> $sales_code,
                'sales_date' 				=> $sales_date,
                'sales_status' 				=> 'Final',
                'customer_id' 				=> $userFindById['id'],
                'discount_to_all_input' 	=> $discount_input,
                'discount_to_all_type' 		=> "in_percentage",
                'tot_discount_to_all_amt' 	=> $tot_disc,
                'other_charges_input' 		=> "0",
                'other_charges_amt' 		=> "0",
                'subtotal' 					=> "0",
                'round_off' 				=> "0",
                'grand_total' 				=> $data['product_order']['total_fees'],
                'created_date' 				=> $sales_date,
                'created_time' 				=> date("H;i:s"),
                'created_by' 				=> $customer_name,
                'system_ip' 				=> null,
                'system_name' 				=> "ANDROID",
                'pos' 						=> 1,
                'status' 					=> 1,
                'company_id'                => $data['product_order']['company_id']
            ];
            $result_insert = $this->posModels->saveWithReturnId($sales_entry);

            //save items
            for ($i = 0; $i < count($order_detail); $i++) {

                $findDetailBarang = $this->itemsProductRepository->findById($order_detail[$i]['product_id']);

                $total_cost = $order_detail[$i]['price_item'] * $order_detail[$i]['amount'];
                $salesitems_entry = [
                    'id' 			=> '',
                    'sales_id' 			=> $result_insert,
                    'sales_status'		=> 'Final',
                    'item_id' 			=>  $order_detail[$i]['product_id'],
                    'description' 		=> '',
                    'sales_qty' 		=> $order_detail[$i]['amount'],
                    'price_per_unit' 	=> $order_detail[$i]['price_item'],
                    'tax_id' 			=> 1,
                    'tax_amt' 			=> null,
                    'tax_type' 			=> 'Exclusive',
                    'discount_type' 	=> 'Percentage',
                    'discount_input' 	=> 0,
                    'discount_amt' 		=> 0,
                    'unit_total_cost' 	=> $order_detail[$i]['price_item'],
                    'total_cost' 		=> $total_cost,
                    'purchase_price' 		=> $findDetailBarang['purchase_price'],
                    'status'	 		=> 1,
                ];
                $this->salesDetailModels->saveWithReturnId($salesitems_entry);
                $this->posRepository->update_items_quantity($order_detail[$i]['product_id']);
            }
            $this->salesDetailModels->db->transComplete();
            $this->posModels->db->transComplete();
            $m = array('status' => "success", 'msg' => "Success submit ", 'data' => [
                "id" => $result_insert,
                "code" => 200,
                "total_fees" => $data['product_order']['total_fees'],
            ]);
            return $this->respond($m, 200);

        } catch (DatabaseException $exception) {
            return $this->respondFail($exception->getMessage());
        }


    }

    public function detailSales()
    {
        $validation_arr = [
            'id_sales' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $id = $this->request->getVar('id_sales');
        $findSales = $this->posModels->where('id', $id)->orderBy('id', 'desc')->first();
        $findSalesDetail = $this->salesDetailModels->where('sales_id', $id)->findAll();

        $data = [
            'sales' => $findSales,
            'sales_detail' => $findSalesDetail,
        ];
        $findSales['created_date'] = date('d F Y', strtotime($findSales['created_date']))." ".$findSales['created_time'];
        return $this->respondSuccess("data loaded", $findSales);

    }

    public function detailSalesItems()
    {
        $validation_arr = [
            'id_sales' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $id = $this->request->getVar('id_sales');
        $findSales = $this->posModels->where('id', $id)->orderBy('id', 'desc')->first();
        $findSalesDetail = $this->salesDetailModels->where('sales_id', $id)->findAll();

        $data = [];

        foreach ($findSalesDetail as $value) {
            $name  = $this->itemsProductRepository->findById($value['item_id']);
            $row['id'] =  $value['id'];
            $row['sales_id'] =  $value['sales_id'];
            $row['sales_status'] =  $value['sales_status'];
            $row['item_id'] =  $value['item_id'];
            $row['description'] =  $value['description'];
            $row['sales_qty'] =  $value['sales_qty'];
            $row['price_per_unit'] =  $value['price_per_unit'];
            $row['tax_type'] =  $value['tax_type'];
            $row['tax_id'] =  $value['tax_id'];
            $row['tax_amt'] =  $value['tax_amt'];
            $row['discount_type'] =  $value['discount_type'];
            $row['discount_input'] =  $value['discount_input'];
            $row['discount_amt'] =  $value['discount_amt'];
            $row['unit_total_cost'] =  $value['unit_total_cost'];
            $row['total_cost'] =  $value['total_cost'];
            $row['status'] =  $value['status'];
            $row['purchase_price'] =  $value['purchase_price'];
            $row['item_name'] =  $name['item_name'];
            $data[] = $row;
        }

        $findSales['created_date'] = date('d F Y', strtotime($findSales['created_date']))." ".$findSales['created_time'];
        return $this->respondList(1000, 1, $data);
    }

    public function show_all_pengeluaran()
    {
        $validation_arr = [
            'users_id' => 'required',
            'company_id' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $users_id = $this->request->getVar('users_id');
        $company_id = $this->request->getVar('company_id');
        $date = $this->request->getVar('date');

        $result = $this->pengeluaranRepository->show_all($company_id, $date);
        return $this->respondList(1000, 1, $result);

    }

    public function save_pengeluaran()
    {
        try {

//            $this->stockEntryModels->db->transException(true)->transStart();

            $validation_arr = [
                'expense_date' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Tanggal Pengeluaran',
                    ]
                ],
                'expense_for' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Pengeluaran Untuk',
                    ]
                ],
                'expense_amt' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Jumlah Pengeluaran',
                    ]
                ],
                'note' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Catatan',
                    ]
                ],
                'company_id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Company Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                    ]
                ],
                'users_id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Users Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                    ]
                ],

            ];

            // when param error
            if (!$this->validate($validation_arr)) {
                return $this->respondFail($this->validator->getErrors());
            }

            $expense_date = $this->request->getVar('expense_date');
            $reference_no = $this->request->getVar('reference_no');
            $expense_for = $this->request->getVar('expense_for');
            $expense_amt = $this->request->getVar('expense_amt');
            $note = $this->request->getVar('note');
            $company_id = $this->request->getVar('company_id');
            $users_id = $this->request->getVar('users_id');

            $company_detail = $this->companyModels->find_by_id($company_id);
            $expense_init = $company_detail['expense_init'];
            $maxid = $this->pengeluaranRepository->maxid();
            $expense_code=$expense_init.str_pad($maxid['maxid'], 4, '0', STR_PAD_LEFT);

            $users = $this->usersAppModels->findById($users_id);

            $data = [
                "id" => "",
                "expense_code" => $expense_code,
                "category_id" => 1,
                "expense_for" => $expense_for,
                "expense_amt" => $expense_amt,
                "reference_no" => $reference_no,
                "note" => $note,
                "created_date" => date("Y-m-d", strtotime($expense_date)),
                "created_time" => date("H:i:s"),
                "created_by" => $users['name'],
                "status" => 1,
                "system_ip" => null,
                "system_name" => "android",
                "expense_date" => date("Y-m-d", strtotime($expense_date)),
                "company_id" => $company_id,
            ];

            $this->pengeluaranModels->saveWithReturnId($data);

            return $this->respondSuccess('successfully', $data);
        } catch (DatabaseException $exception) {
            return $this->respondFail($exception->getMessage());
        }
    }

    public function delete_pengeluaran()
    {
        $validation_arr = [
            'id' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $id = $this->request->getVar('id');
        $countPengeluaran = $this->pengeluaranModels->where('id', $id)->countAllResults();
        if ($countPengeluaran < 1) {
            return $this->respondFail("Data Tidak Tersedia");
        }
        $result = $this->pengeluaranModels->where('id', $id)->first();

        $this->pengeluaranModels->delete($id);
        return $this->respondSuccess('delete successfully', $result);
    }

    public function show_all_riwayat_penjualan()
    {
        $validation_arr = [
            'company_id' => 'required',
            'users_id' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $company_id = $this->request->getVar('company_id');
        $users_id = $this->request->getVar('users_id');
        $tanggal_mulai = $this->request->getVar('tanggal_mulai');
        $tanggal_selesai = $this->request->getVar('tanggal_selesai');
        $result = $this->salesRepository->show_all_riwayat_penjualan($company_id, $tanggal_mulai, $tanggal_selesai);
        return $this->respondList(1000,1, $result);

    }

    public function show_untung_rugi()
    {
        $validation_arr = [
            'company_id' => 'required',
            'users_id' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $company_id = $this->request->getVar('company_id');
        $users_id = $this->request->getVar('users_id');
        $tanggal_mulai = $this->request->getVar('tanggal_mulai');
        $tanggal_selesai = $this->request->getVar('tanggal_selesai');

        $companyModels = new CompanyModels();
        if ($companyModels->check_count($company_id) < 1) {
            return $this->respondFail("Company not found");
        }
        $findCompany = $companyModels->find_by_id($company_id);
        $usersModels = new UsersAppModels();
        if ($usersModels->check_count($users_id) < 1) {
            return $this->respondFail("Users not found");
        }


        $result = $this->dashboardService->show_untung_rugi($company_id, $users_id, $findCompany, $tanggal_mulai, $tanggal_selesai);

        $response['status'] = 200;
        $response['messages'] = "loaded dashboard";
        $response['data'] = $result;

        return $this->respond($response);
    }

    public function update_profile()
    {
        $user = null;
        $id = $this->request->getVar('id');
        $user = $this->usersAppModels->find($id);

        $validation_arr = [
            'id' => 'required',
            'name' => 'required',
            'phone' => 'required',
        ];

        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }

        $data = array(
            'id' => $id,
            'name' => $this->request->getVar('name'),
            'email' => $this->request->getVar('email'),
            'phone' => $this->request->getVar('phone'),
        );
        if ($this->request->getVar('password')) {
            $data['password'] = password_hash($this->request->getVar('password'), PASSWORD_DEFAULT);
        }

        $resultId = $this->usersAppModels->saveWithReturnId($data);

        return $this->respondSuccess('Update user successfully', $user);
    }

    public function company_detail()
    {
        $validation_arr = [
            'company_id' => 'required',
            'users_id' => 'required',
        ];

        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $id = $this->request->getVar('company_id');
        $companyDetail = $this->companyModels->where('id', $id)->first();
        return $this->respondSuccess('Loaded', $companyDetail);

    }

    public function company_update()
    {
        $validation_arr = [
            'company_id' => 'required',
            'users_id' => 'required',
            'owner' => 'required',
            'company_name' => 'required',
            'mobile' => 'required',
            'address' => 'required',
            'email' => 'required',
        ];

        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }

        $dataCompany = [
            'id' => $this->request->getVar('company_id'),
            'owner' => $this->request->getVar('owner'),
            'company_name' => $this->request->getVar('company_name'),
            'mobile' => $this->request->getVar('mobile'),
            'address' => $this->request->getVar('address'),
            'email' => $this->request->getVar('email'),
        ];
        $this->companyModels->saveWithReturnId($dataCompany);
        return $this->respondSuccess('Loaded', $dataCompany);
    }

    public function show_all_kategori_barang()
    {
        $validation_arr = [
            'users_id' => 'required',
            'company_id' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $users_id = $this->request->getVar('users_id');
        $company_id = $this->request->getVar('company_id');

        $result = $this->kategoriBarangRepository->show_all($company_id);
        return $this->respondList(1000, 1, $result);
    }

    public function delete_kategori_barang()
    {
        $validation_arr = [
            'id' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $id = $this->request->getVar('id');
        $countPengeluaran = $this->kategoriBarangModels->where('id', $id)->countAllResults();
        if ($countPengeluaran < 1) {
            return $this->respondFail("Data Tidak Tersedia");
        }
        $result = $this->kategoriBarangModels->where('id', $id)->first();

        $data_update = [
            "status" => 0
        ];
        $this->kategoriBarangModels->update($id, $data_update);
        return $this->respondSuccess('delete successfully', $result);
    }

    public function save_kategori_barang()
    {
        try {

//            $this->stockEntryModels->db->transException(true)->transStart();

            $validation_arr = [
                'category_name' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Nama Kategori Harus Diisi',
                    ]
                ],
                'company_id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Company Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                    ]
                ],
                'users_id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Users Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                    ]
                ],

            ];

            // when param error
            if (!$this->validate($validation_arr)) {
                return $this->respondFail($this->validator->getErrors());
            }

            $category_name = $this->request->getVar('category_name');
            $description = $this->request->getVar('description');
            $company_id = $this->request->getVar('company_id');
            $users_id = $this->request->getVar('users_id');
            $id = $this->request->getVar('id');

            $company_detail = $this->companyModels->find_by_id($company_id);
            $expense_init = $company_detail['category_init'];
            $maxid = $this->kategoriBarangRepository->maxid();
            $category_code=$expense_init.str_pad($maxid['maxid'], 4, '0', STR_PAD_LEFT);

            $users = $this->usersAppModels->findById($users_id);

            $data = [
                "id" => $id,
                "category_code" => $category_code,
                "category_name" => $category_name,
                "description" => $description,
                "company_id" => $company_id,
                "status" => 1,
            ];

            $this->kategoriBarangModels->saveWithReturnId($data);

            return $this->respondSuccess('successfully', $data);
        } catch (DatabaseException $exception) {
            return $this->respondFail($exception->getMessage());
        }
    }

    public function show_all_satuan_barang()
    {
        $validation_arr = [
            'users_id' => 'required',
            'company_id' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $users_id = $this->request->getVar('users_id');
        $company_id = $this->request->getVar('company_id');

        $result = $this->satuanBarangRepository->show_all($company_id);
        return $this->respondList(1000, 1, $result);
    }

    public function delete_satuan_barang()
    {
        $validation_arr = [
            'id' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $id = $this->request->getVar('id');
        $countPengeluaran = $this->satuanBarangModels->where('id', $id)->countAllResults();
        if ($countPengeluaran < 1) {
            return $this->respondFail("Data Tidak Tersedia");
        }
        $result = $this->satuanBarangModels->where('id', $id)->first();

        $data_update = [
            "status" => 0
        ];
        $this->satuanBarangModels->update($id, $data_update);
        return $this->respondSuccess('delete successfully', $result);
    }

    public function save_satuan_barang()
    {
        try {
            $validation_arr = [
                'unit_name' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Nama Satuan Barang Harus Diisi',
                    ]
                ],
                'company_id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Company Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                    ]
                ],
                'users_id' => [
                    'rules' => 'required',
                    'errors' => [
                        'required' => 'Users Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                    ]
                ],

            ];

            // when param error
            if (!$this->validate($validation_arr)) {
                return $this->respondFail($this->validator->getErrors());
            }

            $unit_name = $this->request->getVar('unit_name');
            $description = $this->request->getVar('description');
            $company_id = $this->request->getVar('company_id');
            $users_id = $this->request->getVar('users_id');
            $id = $this->request->getVar('id');

            $users = $this->usersAppModels->findById($users_id);

            $data = [
                "id" => $id,
                "unit_name" => $unit_name,
                "description" => $description,
                "company_id" => $company_id,
                "status" => 1,
            ];

            $this->satuanBarangModels->saveWithReturnId($data);

            return $this->respondSuccess('successfully', $data);
        } catch (DatabaseException $exception) {
            return $this->respondFail($exception->getMessage());
        }
    }

    public function delete_barang()
    {
        $validation_arr = [
            'id' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $id = $this->request->getVar('id');
        $countProduct = $this->itemsProductModels->where('id', $id)->countAllResults();
        if ($countProduct < 1) {
            return $this->respondFail("Data Tidak Tersedia");
        }
        $result = $this->itemsProductModels->where('id', $id)->first();

        $data_update = [
            "status" => 0
        ];
        $this->itemsProductModels->update($id, $data_update);
        return $this->respondSuccess('delete successfully', $result);
    }

    public function show_all_product_retur_sales()
    {
        $validation_arr = [

            'company_id' => [
                'rules' => 'required',
                'errors' => [
                    'required' => 'Company Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                ]
            ],
            'users_id' => [
                'rules' => 'required',
                'errors' => [
                    'required' => 'Users Harus Diisi, Atau Silahkan Logout Kemudian Login Lagi',
                ]
            ],
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }

        $company_id = $this->request->getVar('company_id');
        $users_id = $this->request->getVar('users_id');
        $search = ($this->request->getVar('search') == "") ? "" : $this->request->getVar('search');

        $list = [];
        $result = $this->salesItemRepository->show_list_product($company_id, $search);
        foreach ($result as $value) {
            $row = [];
            $row['id'] = $value->id;
            $row['item_image'] = base_url('uploads/items/'.$value->item_image);
            $row['item_code'] = $value->item_code;
            $row['item_name'] = strtoupper($value->item_name);
            $row['category_name'] = $value->category_name;
            $row['unit_name'] = $value->unit_name;
            $row['stock'] = 10000;
            $row['alert_qty'] = (float) $value->alert_qty;
            $row['purchase_price'] = (float) $value->purchase_price;
            $row['final_price'] = (float) $value->final_price;
            $row['tax_name'] = $value->tax_name;
            $row['tax'] = $value->tax;
            $row['status'] = $value->status;
            $row['brand_name'] = $value->brand_name;
            $row['tax_type'] = $value->tax_type;
            $row['hsn'] = $value->hsn;
            $row['sku'] = $value->sku;
            $row['brand_name'] = $value->brand_name;
            $row['sales_id'] = (float)$value->sales_id;
            $row['sales_qty'] = (float)$value->sales_qty;
            $list[] = $row;
        }
        return $this->respondList(1000,1, $list);
    }

    public function save_retur_penjualan()
    {
        try {
            $this->salesReturnModels->db->transException(true)->transStart();
            $this->salesItemReturnModels->db->transException(true)->transStart();

            $data = json_decode(file_get_contents("php://input"), true);
            if(!isset($data) || !isset($data['product_order']) || !isset($data['product_order_detail'])) return $this->respondFail($this->validator->getErrors());

            $order_detail = $data['product_order_detail'];

            $company_detail = $this->companyModels->find_by_id($data['product_order']['company_id']);
            $maxid = $this->salesReturnModels->maxid();
            $return_code=$company_detail['sales_return_init'].str_pad($maxid['maxid'], 4, '0', STR_PAD_LEFT);

            $userFindById = $this->usersAppModels->findById($data['product_order']['users_id']);
            $customer_name = $userFindById['name'];


            $sales_entry = array(
                "id"                        => "",
                'sales_id' 		            => null,
                'return_code' 				=> $return_code,
                'reference_no' 				=> "",
                'return_date' 				=> date("Y-m-d"),
                'return_status' 			=> "Return",
                'customer_id' 				=> "1",
                /*'warehouse_id' 				=> $warehouse_id,*/
                /*Other Charges*/
                'other_charges_input' 		=> null,
                'other_charges_tax_id' 		=> null,
                'other_charges_amt' 		=> null,
                /*Discount*/
                'discount_to_all_input' 	=> null,
                'discount_to_all_type' 		=> "in_percentage",
                'tot_discount_to_all_amt' 	=> null,
                /*Subtotal & Total */
                'subtotal' 					=> $data['product_order']['total_fees'],
                'round_off' 				=> null,
                'grand_total' 				=> $data['product_order']['total_fees'],
                'return_note' 				=> "",
                /*System Info*/
                'created_date' 				=> date("Y-m-d"),
                'created_time' 				=> date("H:i:s"),
                'created_by' 				=> $customer_name,
                'system_ip' 				=> null,
                'system_name' 				=> "ANDROID",
                'status' 					=> 1,
                'company_id'                => $data['product_order']['company_id'],
            );

            $q1 = $this->salesReturnModels->saveWithReturnId($sales_entry);
            $return_id = $q1;

            //Import post data from form
            //Import post data from form
            $amount = $data['product_order']['total_fees'];
            for ($i = 0; $i < count($order_detail); $i++) {

                $findDetailBarang = $this->itemsProductRepository->findById($order_detail[$i]['product_id']);

                $total_cost = $order_detail[$i]['price_item'] * $order_detail[$i]['amount'];
//                return $this->respondFail("Sales Id : ");
                $sales_id = ($order_detail[$i]['sales_id'] == "") ? null : $order_detail[$i]['sales_id'];

                $salesitems_entry = array(
                    'id'            => '',
                    'sales_id' 		=> $sales_id,
                    'return_id' 			=> $return_id,
                    'return_status'		=> "Return",
                    'item_id' 			=> $order_detail[$i]['product_id'],
                    'description' 		=> '',
                    'return_qty' 		=> $order_detail[$i]['amount'],
                    'price_per_unit' 	=> $order_detail[$i]['price_item'],
                    'tax_id' 			=> 1,
                    'tax_amt' 			=> null,
                    'tax_type' 			=> 'Exclusive',
                    'discount_input' 	=> null,
                    'discount_amt' 		=> 0,
                    'discount_type' 	=> 'Percentage',
                    'unit_total_cost' 	=> $order_detail[$i]['price_item'],
                    'total_cost' 		=> $total_cost,
                    'purchase_price' 	=> $findDetailBarang['purchase_price'],
                    'status'	 		=> 1,

                );

                $q2 = $this->salesItemReturnModels->saveWithReturnId($salesitems_entry);

                /*Find the Item Exist in sales entry or not*/
                //$this->adjust_sales_item($sales_id,$item_id,$return_qty,$command);

                //UPDATE itemS QUANTITY IN itemS TABLE

                $q6=$this->posRepository->update_items_quantity($order_detail[$i]['product_id']);
                if(!$q6){
                    return $this->respondFail("Failed Retur Sales");
                }

            }//for end


            if($amount=='' || $amount==0){$amount=null;}
            if($amount>0 ){
                $salespayments_entry = array(
                    'id'                => '',
                    'sales_id' 			=> null,
                    'return_id' 		=> $return_id,
                    'payment_date'		=> date("Y-m-d"),//Current Payment with sales entry
                    'payment_type' 		=> "Cash",
                    'payment' 			=> $amount,
                    'payment_note' 		=> "",
                    'created_date' 		=> date("Y-m-d"),
                    'created_time' 		=> date("H:i:s"),
                    'created_by' 		=> $customer_name,
                    'system_ip' 		=> null,
                    'system_name' 		=> "ANDROID",
                    'status' 			=> 1,
                );

                $q3 = $this->salesPaymentReturnModels->saveWithReturnId($salespayments_entry);

            }

            $data_update_sales_payment_status_by_sales_id = array(
                'id' => $return_id,
                'payment_status' => 'Paid',
                'paid_amount' => $data['product_order']['total_fees'],
            );
            $q7 = $this->salesReturnModels->saveWithReturnId($data_update_sales_payment_status_by_sales_id);

            $totals_sales_return = $this->salesReturnModels->totals("1");

            $data_update_customers = array(
                'id' => "1",
                'sales_return_due' => $totals_sales_return
            );
            $q12 = $this->customerModels->saveWithReturnId($data_update_customers);


            $this->salesItemReturnModels->db->transComplete();
            $this->salesReturnModels->db->transComplete();

            $m = array('status' => "success", 'msg' => "Success submit ", 'data' => [
                "id" => $return_id,
                "code" => 200,
                "total_fees" => $data['product_order']['total_fees'],
            ]);
            return $this->respond($m, 200);

        } catch (DatabaseException $exception) {
            return $this->respondFail($exception->getMessage());
        }
    }

    public function show_neraca()
    {
        $validation_arr = [
            'company_id' => 'required',
            'users_id' => 'required',
        ];
        // when param error
        if (!$this->validate($validation_arr)) {
            return $this->respondFail($this->validator->getErrors());
        }
        $company_id = $this->request->getVar('company_id');
        $users_id = $this->request->getVar('users_id');
        $tanggal_mulai = $this->request->getVar('tanggal_mulai');
        $tanggal_selesai = $this->request->getVar('tanggal_selesai');

        $companyModels = new CompanyModels();
        if ($companyModels->check_count($company_id) < 1) {
            return $this->respondFail("Company not found");
        }
        $findCompany = $companyModels->find_by_id($company_id);
        $usersModels = new UsersAppModels();
        if ($usersModels->check_count($users_id) < 1) {
            return $this->respondFail("Users not found");
        }


        $result = $this->neracaService->show_data($company_id, $tanggal_mulai, $tanggal_selesai);

        $response['status'] = 200;
        $response['messages'] = "loaded dashboard";
        $response['data'] = $result;

        return $this->respond($response);
    }
}