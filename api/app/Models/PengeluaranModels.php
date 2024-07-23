<?php

namespace App\Models;

use CodeIgniter\Model;

class PengeluaranModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_expense';
    protected $allowedFields      = ["expense_code","category_id","expense_date","reference_no","expense_for","expense_amt",
        "note","created_by","created_date","created_time","system_ip","system_name","status","company_id"];

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