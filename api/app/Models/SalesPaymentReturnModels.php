<?php

namespace App\Models;

use App\Exception\ValidationException;
use CodeIgniter\Model;

class SalesPaymentReturnModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_salespaymentsreturn';
    protected $allowedFields      = ['sales_id', 'return_id', 'payment_date', 'payment_type', 'payment',
        'payment_note', 'change_return', 'system_ip',
        'system_name','created_time','created_date',
        'created_by','status'];

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