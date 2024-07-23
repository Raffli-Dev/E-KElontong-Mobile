<?php

namespace App\Models;

use App\Exception\ValidationException;
use CodeIgniter\Model;

class UsersAppModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'user_app';
    protected $useTimestamps      = true;
    protected $allowedFields      = ['name', 'email', 'password', 'image', 'status', 'provider', 'reset_code', 'phone', 'company_id'];
    protected $createdField       = 'created_at';
    protected $updatedField       = 'updated_at';

    public function __construct() {
        parent::__construct();
    }

    public function getOneByEmail($email) {
        return $this->where(['email' => $email])->first();
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
    public function check_count(string $id)
    {
        try {
            $result = $this->where('id', $id)->countAllResults();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function findById(string $id)
    {
        try {
            $result = $this->where('id', $id)->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}