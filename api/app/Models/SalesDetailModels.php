<?php

namespace App\Models;

use App\Exception\ValidationException;
use CodeIgniter\Model;

class SalesDetailModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_salesitems';
    protected $allowedFields      = ["sales_id","sales_status","item_id","description","sales_qty","price_per_unit",
        "tax_type","tax_id","tax_amt","discount_type","discount_input","discount_amt",
        "unit_total_cost","total_cost","status","purchase_price"];

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