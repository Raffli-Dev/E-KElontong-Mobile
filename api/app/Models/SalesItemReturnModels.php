<?php

namespace App\Models;

use App\Exception\ValidationException;
use CodeIgniter\Model;

class SalesItemReturnModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_salesitemsreturn';
    protected $allowedFields      = ['sales_id', 'return_id', 'return_status', 'item_id', 'description',
        'return_qty', 'price_per_unit', 'tax_id',
        'tax_amt','tax_type','discount_input','discount_amt','discount_type','unit_total_cost',
        'total_cost','status','purchase_price'];

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