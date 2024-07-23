<?php

namespace App\Models;

use CodeIgniter\Model;

class AppVersionModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'app_version';
    protected $useTimestamps      = true;
    protected $allowedFields      = ['version_code', 'version_name', 'type', 'status'];
    protected $createdField       = 'created_at';
    protected $updatedField       = 'updated_at';

    public function __construct() {
        parent::__construct();
    }

    public function getOne($params){
        return $this->where($params)->first();
    }
}