<?php

namespace App\Models;

use App\Exception\ValidationException;
use CodeIgniter\Model;

class CompanyModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_company';
    protected $allowedFields      = ['company_code', 'owner', 'company_name', 'company_website', 'mobile', 'phone', 'email', 'website',
        'company_logo','logo','upi_id','upi_code','signature','show_signature','country','state','city','address','postcode','gst_no',
        'vat_no','pan_no','bank_details','cid','category_init','item_init','supplier_init','purchase_init','purchase_return_init',
        'customer_init','sales_init','sales_return_init','expense_init','invoice_view','status','sms_status','sales_terms_and_conditions'];

    public function __construct() {
        parent::__construct();
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
    public function check_count(string $id)
    {
        try {
            $result = $this->where('id', $id)->countAllResults();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function find_by_id(string $id)
    {
        try {
            $result = $this->where('id', $id)->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}