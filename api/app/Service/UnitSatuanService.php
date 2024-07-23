<?php

namespace App\Service;

use App\Exception\ValidationException;
use App\Repository\UnitSatuanRepository;

class UnitSatuanService
{
    function __construct()
    {
        $this->unitSatuanRepository = new UnitSatuanRepository();
    }
    public function showAll($request)
    {
        try {
            $category = $this->unitSatuanRepository->showAll();
            return $category;

        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}