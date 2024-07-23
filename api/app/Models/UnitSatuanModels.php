<?php

namespace App\Models;

use CodeIgniter\Model;

class UnitSatuanModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_units';
    protected $allowedFields      = ['unit_name', 'description', 'company_id', 'status'];
}