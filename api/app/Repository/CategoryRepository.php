<?php

namespace App\Repository;

use App\Exception\ValidationException;
use App\Models\CategoryModels;

class CategoryRepository
{
    function __construct()
    {
        $this->categoryModels = new CategoryModels();
    }
    public function showAll()
    {
        try {
            $this->categoryModels->orderBy('category_name', 'ASC');
            $result = $this->categoryModels->getWhere("id != '0'")->getResult();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function findByCode($category_code)
    {
        try {
            $findCode = $this->categoryModels->where('category_code', $category_code)->first();
            return $findCode;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function findId($id)
    {
        try {
            $findCode = $this->categoryModels->where('id', $id)->first();
            return $findCode;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }

}