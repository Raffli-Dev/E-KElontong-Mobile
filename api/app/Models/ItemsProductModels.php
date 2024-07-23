<?php

namespace App\Models;

use App\Exception\ValidationException;
use CodeIgniter\Model;

class ItemsProductModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_items';
    protected $allowedFields      = ['item_code', 'custom_barcode', 'item_name', 'description', 'category_id', 'sku', 'hsn', 'unit_id',
        'alert_qty','brand_id','lot_number','expire_date','price','tax_id','purchase_price','tax_type','profit_margin','sales_price',
        'final_price','stock',
        'item_image','system_ip','system_name','created_date','created_time','created_by','company_id','status','discount_type',
        'discount'];

    public function total($company_id)
    {
        try {
            $result = $this->where('company_id', $company_id)->countAllResults();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function saveWithReturnId($data) {
        if (!$this->save($data)) {
            return -1;
        }
        if ($data[$this->primaryKey] && $data[$this->primaryKey] != "") {
            return (int) $data[$this->primaryKey];
        }
        return $this->getInsertID();
    }

}