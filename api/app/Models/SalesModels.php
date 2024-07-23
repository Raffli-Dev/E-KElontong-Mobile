<?php

namespace App\Models;

use App\Exception\ValidationException;
use CodeIgniter\Model;

class SalesModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_sales';
    protected $allowedFields      = ["sales_code","reference_no","sales_date","sales_status","customer_id","warehouse_id",
        "other_charges_input","other_charges_tax_id","other_charges_amt","discount_to_all_input","discount_to_all_type","tot_discount_to_all_amt",
        "subtotal","round_off","biaya_pajak","biaya_pajak_total","grand_total","sales_note","payment_status",
        "paid_amount","created_date","created_time","created_by","system_ip","system_name","company_id","pos","status","return_bit"];

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