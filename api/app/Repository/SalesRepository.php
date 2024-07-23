<?php

namespace App\Repository;

use App\Exception\ValidationException;
use App\Models\SalesModels;

class SalesRepository
{
    function __construct()
    {
        $this->salesModels = new SalesModels();
    }
    public function total_penjualan_hari_ini(string $tanggal_mulai, string $tanggal_selesai, string $company_id)
    {
        try {


            if ($tanggal_mulai != 'TGL.Mulai' && $tanggal_selesai != 'TGL.Selesai') {
                $tanggal_mulai = date("Y-m-d", strtotime($tanggal_mulai));
                $tanggal_selesai = date("Y-m-d", strtotime($tanggal_selesai));

                $this->salesModels->where('sales_date >= ', $tanggal_mulai);
                $this->salesModels->where('sales_date <= ', $tanggal_selesai);
            } else {
                $this->salesModels->where('sales_date', date("Y-m-d"));
            }
            $this->salesModels->select("COALESCE(sum(grand_total),0) AS todays_total_sales");
            $this->salesModels->where('sales_status', 'Final')->where('company_id', $company_id);
            $result = $this->salesModels->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function jumlah_penjualan_total(string $company_id, string $tanggal_mulai, string $tanggal_selesai)
    {
        try {
            if ($tanggal_mulai != 'TGL.Mulai' && $tanggal_selesai != 'TGL.Selesai') {
                $tanggal_mulai = date("Y-m-d", strtotime($tanggal_mulai));
                $tanggal_selesai = date("Y-m-d", strtotime($tanggal_selesai));
                $this->salesModels->where('sales_date >= ', $tanggal_mulai);
                $this->salesModels->where('sales_date <= ', $tanggal_selesai);

            } else {
                $this->salesModels->where('sales_date', date("Y-m-d"));
            }
            $this->salesModels->select("COALESCE(sum(grand_total),0) AS tot_sal_grand_total");
            $this->salesModels->where('sales_status', 'Final')->where('company_id', $company_id);
            $result = $this->salesModels->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function total_transaksi_penjualan_hari_ini(string $company_id, string $tanggal_mulai, string $tanggal_selesai)
    {
        try {
            if ($tanggal_mulai != 'TGL.Mulai' && $tanggal_selesai != 'TGL.Selesai') {
                $tanggal_mulai = date("Y-m-d", strtotime($tanggal_mulai));
                $tanggal_selesai = date("Y-m-d", strtotime($tanggal_selesai));
                $this->salesModels->where('sales_date >= ', $tanggal_mulai);
                $this->salesModels->where('sales_date <= ', $tanggal_selesai);
            } else {
                $this->salesModels->where('sales_date', date("Y-m-d"));
            }
            $this->salesModels->select("COALESCE(COUNT(*),0) AS total_transaksi");
            $this->salesModels->where('sales_status', 'Final')->where('company_id', $company_id);
            $result = $this->salesModels->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }

    public function show_all_riwayat_penjualan(string $company_id, string $tanggal_mulai, string $tanggal_selesai)
    {
        try {
            if ($tanggal_mulai != 'TGL.Mulai' && $tanggal_selesai != 'TGL.Selesai') {
                $tanggal_mulai = date("Y-m-d", strtotime($tanggal_mulai));
                $tanggal_selesai = date("Y-m-d", strtotime($tanggal_selesai));
                $this->salesModels->where('sales_date >= ', $tanggal_mulai);
                $this->salesModels->where('sales_date <= ', $tanggal_selesai);
            }
            $this->salesModels->where('sales_status', 'Final')->where('company_id', $company_id);
            $result = $this->salesModels->findAll();
            $data = [];
            foreach ($result as $value) {
                $row['id'] = $value['id'];
                $row['sales_code'] = $value['sales_code'];
                $row['reference_no'] = $value['reference_no'];
                $row['sales_date'] = date('d F Y', strtotime($value['sales_date']))." ".$value['created_time'];
                $row['sales_status'] = $value['sales_status'];
                $row['customer_id'] = $value['customer_id'];
                $row['warehouse_id'] = $value['warehouse_id'];
                $row['other_charges_input'] = $value['other_charges_input'];
                $row['other_charges_tax_id'] = $value['other_charges_tax_id'];
                $row['other_charges_amt'] = $value['other_charges_amt'];
                $row['discount_to_all_input'] = $value['discount_to_all_input'];
                $row['discount_to_all_type'] = $value['discount_to_all_type'];
                $row['tot_discount_to_all_amt'] = $value['tot_discount_to_all_amt'];
                $row['subtotal'] = $value['subtotal'];
                $row['round_off'] = $value['round_off'];
                $row['biaya_pajak'] = $value['biaya_pajak'];
                $row['biaya_pajak_total'] = $value['biaya_pajak_total'];
                $row['grand_total'] = number_format($value['grand_total']);
                $row['sales_note'] = $value['sales_note'];
                $row['payment_status'] = $value['payment_status'];
                $row['paid_amount'] = $value['paid_amount'];
                $row['created_date'] = $value['created_date'];
                $row['created_time'] = $value['created_time'];
                $row['created_by'] = $value['created_by'];
                $row['system_ip'] = $value['system_ip'];
                $row['system_name'] = $value['system_name'];
                $row['company_id'] = $value['company_id'];
                $row['pos'] = $value['pos'];
                $row['status'] = $value['status'];
                $row['return_bit'] = $value['return_bit'];
                $data[] = $row;
            }
            return $data;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }

}