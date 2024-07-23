<?php

namespace App\Models;

use CodeIgniter\Model;

class NotifDeviceModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'notif_device';
    protected $useTimestamps      = true;
    protected $allowedFields      = ['device_id', 'device_name', 'client', 'client_version', 'app_version', 'fcm_regid', 'os_regid', 'user_app'];
    protected $createdField       = 'created_at';

    public function __construct() {
        parent::__construct();
    }

    public function getAllByUserApp($user_app){
        return $this->where(['user_app' => $user_app])->findAll();
    }

    public function getByDeviceId($device_id){
        return $this->where(['device_id' => $device_id])->first();
    }

    public function getByFcmRegId($fcm_regid){
        return $this->where(['fcm_regid' => $fcm_regid])->first();
    }
}