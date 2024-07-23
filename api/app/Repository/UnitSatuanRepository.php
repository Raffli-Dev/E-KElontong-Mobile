<?php

namespace App\Repository;

use App\Exception\ValidationException;
use App\Models\UnitSatuanModels;

class UnitSatuanRepository
{
    function __construct()
    {
        $this->unitSatuanModels = new UnitSatuanModels();
    }
    public function showAll()
    {
        try {
            $this->unitSatuanModels->orderBy('unit_name', 'ASC');
            $result = $this->unitSatuanModels->getWhere("id != '0'")->getResult();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function findByName(string $name)
    {
        try {
            $find = $this->unitSatuanModels->where('unit_name', $name)->first();
            return $find;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function findId(string $id)
    {
        try {
            $find = $this->unitSatuanModels->where('id', $id)->first();
            return $find;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}