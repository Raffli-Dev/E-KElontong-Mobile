<?php

namespace App\Repository;

use App\Exception\ValidationException;
use App\Models\PengeluaranModels;

class PengeluaranRepository
{
    function __construct()
    {
        $this->pengeluaranModels = new PengeluaranModels();
    }
    public function total_pengeluaran_hari_ini(string $company_id, string $tanggal_mulai, $tanggal_selesai)
    {
        try {
            if ($tanggal_mulai != 'TGL.Mulai' && $tanggal_selesai != 'TGL.Selesai') {
                $tanggal_mulai = date("Y-m-d", strtotime($tanggal_mulai));
                $tanggal_selesai = date("Y-m-d", strtotime($tanggal_selesai));
                $this->pengeluaranModels->where('expense_date >= ', $tanggal_mulai);
                $this->pengeluaranModels->where('expense_date <= ', $tanggal_selesai);
            } else {
                $this->pengeluaranModels->where('expense_date', date("Y-m-d"));
            }
            $this->pengeluaranModels->select("COALESCE(sum(expense_amt),0) AS todays_total_expense");
            $this->pengeluaranModels->where('company_id', $company_id);
            $result = $this->pengeluaranModels->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function show_all(string $company_id, string $start_date = null)
    {
        try {
            if ($start_date != "") {
                $this->pengeluaranModels->where('expense_date', date('Y-m-d', strtotime($start_date)));
            }

            $result = $this->pengeluaranModels->where('company_id', $company_id)->findAll();
            $data = [];
            foreach ($result as $value) {
                $row['id'] = $value['id'];
                $row['expense_code'] = $value['expense_code'];
                $row['category_id'] = $value['category_id'];
                $row['expense_date'] = date('d F Y', strtotime($value['expense_date']));
                $row['reference_no'] = $value['reference_no'];
                $row['expense_for'] = $value['expense_for'];
                $row['expense_amt'] = number_format($value['expense_amt']);
                $row['note'] = $value['note'];
                $row['created_by'] = $value['created_by'];
                $row['created_date'] = $value['created_date'];
                $row['created_time'] = $value['created_time'];
                $row['system_ip'] = $value['system_ip'];
                $row['system_name'] = $value['system_name'];
                $row['status'] = $value['status'];
                $row['company_id'] = $value['company_id'];
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
            $result = $this->pengeluaranModels->select("coalesce(max(id),0)+1 as maxid")->orderBy('id', 'desc')->limit(1)->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}