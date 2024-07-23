<?php

namespace App\Service;

use App\Exception\ValidationException;
use App\Repository\ItemsProductRepository;
use App\Repository\PengeluaranRepository;
use App\Repository\SalesRepository;
use App\Repository\SalesReturnRepository;

class DashboardService
{
    function __construct()
    {
        $this->salesRepository = new SalesRepository();
        $this->salesReturnRepository = new SalesReturnRepository();
        $this->itemsProductRepository = new ItemsProductRepository();
        $this->pengeluaranRepository = new PengeluaranRepository();
    }
    public function show(string $company_id, string $users_id, $company, string $tgl_mulai, string $tgl_selesai): array
    {
        date_default_timezone_set('Asia/Jakarta');
        try {
            $resultTotalPenjualanHariIni = $this->salesRepository->total_penjualan_hari_ini($tgl_mulai, $tgl_selesai, $company_id);
            $resultTotalPenjualanReturHariIni = $this->salesReturnRepository->total_pengembalian_hari_ini($company_id, $tgl_mulai, $tgl_selesai);
            $resultTotalProduk = $this->itemsProductRepository->total_produk($company_id);
            $resultJumlahPenjualanTotal = $this->salesRepository->jumlah_penjualan_total($company_id, $tgl_mulai, $tgl_selesai);
            $resultJumlahPengembalianTotal = $this->salesReturnRepository->total_pengembalian($company_id, $tgl_selesai, $tgl_selesai);
            $resultPengeluaranHariIni = $this->pengeluaranRepository->total_pengeluaran_hari_ini($company_id, $tgl_mulai, $tgl_selesai);
            $resultTotalTransaksiPenjualanHariIni = $this->salesRepository->total_transaksi_penjualan_hari_ini($company_id, $tgl_mulai, $tgl_selesai);

            $tanggal_mulai = date("Y-m-d");
            $tanggal_selesai = date("Y-m-d");
            if ($tgl_mulai != 'TGL.Mulai' && $tgl_selesai != 'TGL.Selesai') {
                $tanggal_mulai = date("Y-m-d", strtotime($tgl_mulai));
                $tanggal_selesai = date("Y-m-d", strtotime($tgl_selesai));
                $from_date = $tanggal_mulai;
                $to_date = $tanggal_selesai;
            } else {
                $from_date = date("Y")."-".date("m")."-01";
                $to_date = date("Y-m-d", strtotime('+ 1days'));
            }




            $resultUntungRugi = $this->get_untung_rugi($from_date, $to_date, $company_id);
            $resultUntungRugi = json_decode($resultUntungRugi, true);

//            $response['total_penjualan_hari_ini'] = $resultTotalPenjualanHariIni['todays_total_sales'] - $resultTotalPenjualanReturHariIni['todays_total_sales_return'];
            $response['total_penjualan_hari_ini'] = (float)$resultTotalPenjualanHariIni['todays_total_sales'];
            $response['total_produk'] = (Int)$resultTotalProduk['total_produk'];
            $response['jumlah_penjualan_total'] = ($resultJumlahPenjualanTotal['tot_sal_grand_total'] - $resultJumlahPengembalianTotal['tot_sales_return']);
            $response['total_pengeluaran_hari_ini'] = (Int)$resultPengeluaranHariIni['todays_total_expense'];
            $response['total_transaksi_penjualan_hari_ini'] = $resultTotalTransaksiPenjualanHariIni['total_transaksi'];
//            $response['tanggal_update_terakhir'] = date("d F Y H:i:s", strtotime(date("Y-m-d H:i:s")));
            $response['tanggal_update_terakhir'] = date("d F Y", strtotime($tanggal_mulai)) . " s/d ".date("d F Y", strtotime($tanggal_selesai));
            $response['company_name'] = strtoupper($company['company_name']);
            $response['laba_kotor'] = $resultUntungRugi['gross_profit'];
            $response['laba_bersih'] = $resultUntungRugi['tot_net_profit'];

            return $response;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }

    private function get_untung_rugi(string $from_date, string $to_date, string $company_id)
    {
        try {
            $client = \Config\Services::curlrequest();
            $response = $client->request('GET', env('app.thirdparty.api-untung-rugi').'?from_date='.$from_date.'&to_date='.$to_date.'&company_id='.$company_id, [
                'headers' => [
                    'User-Agent' => 'wsantrian/1.0',
                    'Accept'     => 'application/json',

                ],
            ]);
            $result = $response->getBody();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }

    public function show_untung_rugi(string $company_id, string $users_id, $company, string $tgl_mulai, string $tgl_selesai): array
    {
        date_default_timezone_set('Asia/Jakarta');
        try {

            $tanggal_mulai = date("Y-m-d");
            $tanggal_selesai = date("Y-m-d");
            if ($tgl_mulai != 'TGL.Mulai' && $tgl_selesai != 'TGL.Selesai') {
                $tanggal_mulai = date("Y-m-d", strtotime($tgl_mulai));
                $tanggal_selesai = date("Y-m-d", strtotime($tgl_selesai));
                $from_date = $tanggal_mulai;
                $to_date = $tanggal_selesai;
            } else {
                $from_date = date("Y")."-".date("m")."-01";
                $to_date = date("Y-m-d", strtotime('+ 1days'));
            }




            $resultUntungRugi = $this->get_untung_rugi($from_date, $to_date, $company_id);
            $resultUntungRugi = json_decode($resultUntungRugi, true);

            return $resultUntungRugi;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}