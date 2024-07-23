<?php

namespace App\Repository;

use App\Exception\ValidationException;
use App\Models\ItemsProductModels;
use App\Models\PengeluaranModels;
use App\Models\SalesModels;

class NeracaRepository
{
    function __construct()
    {
        $this->saledModels = new SalesModels();
        $this->itemsProductModels = new ItemsProductModels();
        $this->pengeluaranModels = new PengeluaranModels();
    }
    public function total_saled_ammount(string $company_id, string $from_date, string $to_date)
    {
        try {
            $this->saledModels->select("
                COALESCE(sum(grand_total),0) AS tot_sal_grand_total
            ");
            $this->saledModels->where('sales_status', 'Final');
            $this->saledModels->where("sales_date >= ", $from_date);
            $this->saledModels->where("sales_date <= ", $to_date);
            $this->saledModels->where('company_id', $company_id);
            $result = $this->saledModels->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function total_persediaal_barang(string $company_id)
    {
        try {
            $this->itemsProductModels->select("
                COALESCE(SUM(b.qty*db_items.purchase_price),0) AS  opening_stock_price
            ");
            $this->itemsProductModels->join("db_stockentry b", "db_items.id=b.item_id", "left");
            $this->itemsProductModels->where('db_items.company_id', $company_id);
            $result = $this->itemsProductModels->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function total_pengeluaran(string $company_id, string $from_date, string $to_date)
    {
        try {
            $this->pengeluaranModels->select("
                COALESCE(SUM(expense_amt),0) AS exp_total
            ");
            $this->pengeluaranModels->where("expense_date >= ", $from_date);
            $this->pengeluaranModels->where("expense_date <= ", $to_date);
            $this->pengeluaranModels->where("company_id", $company_id);
            $result = $this->pengeluaranModels->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}