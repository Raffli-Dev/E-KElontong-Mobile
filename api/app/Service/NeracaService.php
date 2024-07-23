<?php

namespace App\Service;

use App\Repository\NeracaRepository;

class NeracaService
{
    function __construct()
    {
        $this->neracaRepository = new NeracaRepository();
    }
    public function show_data (string $company_id, string $tgl_mulai, string $tgl_selesai)
    {
        if ($tgl_mulai != 'TGL.Mulai' && $tgl_selesai != 'TGL.Selesai') {
            $tanggal_mulai = date("Y-m-d", strtotime($tgl_mulai));
            $tanggal_selesai = date("Y-m-d", strtotime($tgl_selesai));
            $from_date = $tanggal_mulai;
            $to_date = $tanggal_selesai;
        } else {
            $from_date = date("Y")."-".date("m")."-01";
            $to_date = date("Y-m-d", strtotime('+ 1days'));
        }

        $total_saled_ammount =  $this->neracaRepository->total_saled_ammount($company_id, $from_date, $to_date);
        $total_persediaal_barang = $this->neracaRepository->total_persediaal_barang($company_id);
        $total_pengeluaran = $this->neracaRepository->total_pengeluaran($company_id, $from_date, $to_date);

        $aset_lancar_kas = number_format($total_saled_ammount['tot_sal_grand_total'], 2);
        $aset_lancar_bank = 0;
        $aset_lancar_piutang_usaha = 0;
        $aset_lancar_persediaan_barang_dagang = number_format($total_persediaal_barang['opening_stock_price'], 2);
        $aset_lancar_uang_muka_biaya = 0;
        $aset_tetap_tanah = 0;
        $aset_tetap_bangunan = 0;
        $aset_tetap_peralatan = 0;

        $kewajiban_beban_yang_harus_dibayar = number_format($total_pengeluaran['exp_total'], 2);
        $kewajiban_hutang_pajak = 0;
        $kewajiban_hutang_usaha = 0;
        $kewajiban_hutang_bank = 0;
        $modal_pemilik = 0;
        $modal_laba_berjalan = 0;
        $total_aset = $total_saled_ammount['tot_sal_grand_total'] + $aset_lancar_bank + $total_persediaal_barang['opening_stock_price'] + $aset_lancar_piutang_usaha +  $aset_lancar_uang_muka_biaya + $aset_tetap_tanah + $aset_tetap_bangunan + $aset_tetap_peralatan;

        $total_kewajiban_dan_modal = $total_pengeluaran['exp_total'] + $kewajiban_hutang_pajak + $kewajiban_hutang_usaha + $kewajiban_hutang_bank + $modal_pemilik + $modal_laba_berjalan;

        $data = [
            "aset_lancar_kas" => $aset_lancar_kas,
            "aset_lancar_bank" => $aset_lancar_bank,
            "aset_lancar_piutang_usaha" => $aset_lancar_piutang_usaha,
            "aset_lancar_persediaan_barang_dagang" => $aset_lancar_persediaan_barang_dagang,
            "aset_lancar_uang_muka_biaya" => $aset_lancar_uang_muka_biaya,
            "aset_tetap_tanah" => $aset_tetap_tanah,
            "aset_tetap_bangunan" => $aset_tetap_bangunan,
            "aset_tetap_peralatan" => $aset_tetap_peralatan,
            "kewajiban_beban_yang_harus_dibayar" => $kewajiban_beban_yang_harus_dibayar,
            "kewajiban_hutang_pajak" => $kewajiban_hutang_pajak,
            "kewajiban_hutang_usaha" => $kewajiban_hutang_usaha,
            "kewajiban_hutang_bank" => $kewajiban_hutang_bank,
            "modal_pemilik" => $modal_pemilik,
            "modal_laba_berjalan" => $modal_laba_berjalan,
            "total_aset" => number_format($total_aset, 2),
            "total_kewajiban_dan_modal" => number_format($total_kewajiban_dan_modal, 2),
        ];
        return $data;
    }
}