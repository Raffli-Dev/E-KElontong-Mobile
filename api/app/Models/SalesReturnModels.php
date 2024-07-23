<?php

namespace App\Models;

use App\Exception\ValidationException;
use CodeIgniter\Model;

class SalesReturnModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_salesreturn';
    protected $allowedFields      = ["sales_id","return_code","reference_no","return_date","return_status","customer_id",
        "warehouse_id","other_charges_input","other_charges_tax_id","other_charges_amt","discount_to_all_input",
        "discount_to_all_type","tot_discount_to_all_amt","subtotal","round_off","grand_total","return_note",
        "payment_status","paid_amount","created_date","created_time","created_by","system_ip","system_name",
        "company_id","pos","status","return_bit"];

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

    public function totals(string $customer_id)
    {
        try {
            $this->select("COALESCE(SUM(grand_total),0)-COALESCE(SUM(paid_amount),0) as total");
            $this->where('customer_id', $customer_id);
            $result = $this->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}