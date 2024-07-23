<?php

namespace App\Models;

use CodeIgniter\Model;

class StockEntryModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_stockentry';
    protected $allowedFields      = ['entry_date', 'item_id', 'qty', 'note', 'status'];

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