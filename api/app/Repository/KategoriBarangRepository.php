<?php

namespace App\Repository;

use App\Exception\ValidationException;
use App\Models\KategoriBarangModels;

class KategoriBarangRepository
{
    function __construct()
    {
        $this->kategoriBarangModels = new KategoriBarangModels();
    }
    public function show_all(string $company_id)
    {
        try {

            $result = $this->kategoriBarangModels->where('company_id', $company_id)->where('status', 1)->findAll();
            $data = [];
            foreach ($result as $value) {
                $row['id'] = $value['id'];
                $row['category_code'] = $value['category_code'];
                $row['category_name'] = $value['category_name'];
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
    public function maxid()
    {
        try {
            $result = $this->kategoriBarangModels->select("coalesce(max(id),0)+1 as maxid")->orderBy('id', 'desc')->limit(1)->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function update(array $data, string $id)
    {
        try {
            $this->kategoriBarangModels->set('status', $data['status']);
            $this->kategoriBarangModels->where('id', $id);
            $this->kategoriBarangModels->update();
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}