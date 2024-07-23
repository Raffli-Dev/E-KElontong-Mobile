<?php

namespace App\Service;

use App\Exception\ValidationException;
use App\Models\CategoryModels;
use App\Repository\CategoryRepository;

class CategoryService
{
    function __construct()
    {
        $this->categoryRepository = new CategoryRepository();
        $this->categoryModels = new CategoryModels();
    }
    public function showAll($request)
    {
        try {
            $category = $this->categoryRepository->showAll();
            return $category;
            
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}