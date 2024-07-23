<?php

namespace App\Models;

use CodeIgniter\Model;

class KategoriBarangModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_category';
    protected $allowedFields      = ["category_code","category_name","description","company_id","status"];

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