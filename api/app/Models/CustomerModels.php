<?php

namespace App\Models;

use App\Exception\ValidationException;
use CodeIgniter\Model;

class CustomerModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_customers';
    protected $allowedFields      = ['customer_code', 'customer_name', 'mobile', 'phone', 'email',
        'gstin', 'tax_number', 'vatin',
        'opening_balance','sales_due','sales_return_due',
        'country_id','state_id','city','postcode','address','system_ip','system_name','created_date',
        'created_time','created_by','company_id','status'];

    public function maxid()
    {
        try {
            $result = $this->select("COALESCE(MAX(id),0)+1 AS maxid")->orderBy('id', 'DESC')->limit(1)->first();
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