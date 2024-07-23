<?php

namespace App\Repository;

use App\Exception\ValidationException;
use App\Models\SatuanBarangModels;

class SatuanBarangRepository
{
    function __construct()
    {
        $this->satuanBarangModels = new SatuanBarangModels();
    }
    public function show_all(string $company_id)
    {
        try {

            $result = $this->satuanBarangModels->where('company_id', $company_id)->where('status', 1)->findAll();
            $data = [];
            foreach ($result as $value) {
                $row['id'] = $value['id'];
                $row['unit_name'] = $value['unit_name'];
                $row['description'] = $value['description'];
                $row['company_id'] = $value['company_id'];
                $row['status'] = $value['status'];
                $data[] = $row;
            }
            return $data;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function update(array $data, string $id)
    {
        try {
            $this->satuanBarangModels->set('status', $data['status']);
            $this->satuanBarangModels->where('id', $id);
            $this->satuanBarangModels->update();
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}