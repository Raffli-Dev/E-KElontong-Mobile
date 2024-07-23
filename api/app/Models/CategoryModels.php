<?php

namespace App\Models;

use CodeIgniter\Model;

class CategoryModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_category';
    protected $allowedFields      = ['category_code', 'category_name', 'description', 'company_id', 'status'];


}