<?php

namespace App\Repository;

use App\Exception\ValidationException;
use App\Models\SalesReturnModels;

class SalesReturnRepository
{
    function __construct()
    {
        $this->salesReturnModels = new SalesReturnModels();
    }
    public function total_pengembalian_hari_ini(string $company_id, string $tanggal_mulai, string $tanggal_selesai)
    {
        try {

            if ($tanggal_mulai != 'TGL.Mulai' && $tanggal_selesai != 'TGL.Selesai') {
                $tanggal_mulai = date("Y-m-d", strtotime($tanggal_mulai));
                $tanggal_selesai = date("Y-m-d", strtotime($tanggal_selesai));
                $this->salesReturnModels->where('return_date >=', $tanggal_mulai);
                $this->salesReturnModels->where('return_date <=', $tanggal_selesai);
            } else {
                $this->salesReturnModels->where("return_date", date("Y-m-d"));
            }
            $this->salesReturnModels->select("COALESCE(sum(grand_total),0) AS todays_total_sales_return");
            $this->salesReturnModels->where('company_id', $company_id);
            $result = $this->salesReturnModels->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function total_pengembalian(string $company_id, string $tanggal_mulai, string $tanggal_selesai)
    {
        try {
            if ($tanggal_mulai != 'TGL.Mulai' && $tanggal_selesai != 'TGL.Selesai') {
                $tanggal_mulai = date("Y-m-d", strtotime($tanggal_mulai));
                $tanggal_selesai = date("Y-m-d", strtotime($tanggal_selesai));
                $this->salesReturnModels->where('return_date >=', $tanggal_mulai);
                $this->salesReturnModels->where('return_date <=', $tanggal_selesai);

            } else {
                $this->salesReturnModels->where('return_date >=', date("Y-m-d"));
            }
            $this->salesReturnModels->select("COALESCE(sum(grand_total),0) AS tot_sales_return");
            $this->salesReturnModels->where('company_id', $company_id);
            $result = $this->salesReturnModels->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}