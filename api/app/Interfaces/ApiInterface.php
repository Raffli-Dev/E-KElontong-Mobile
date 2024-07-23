<?php

namespace App\Interfaces;

interface ApiInterface
{
    public function check();
    public function info();
    public function login();
    public function register();
    public function show_dashboard();
    public function show_all_category();
    public function show_all_unit_satuan();
    public function save_product();
    public function show_all_product();
    public function productDetails(string $id);
    public function updateManajemenStok();
    public function savePayment();
    public function detailSales();
    public function detailSalesItems();
    public function show_all_pengeluaran();
    public function save_pengeluaran();
    public function delete_pengeluaran();
    public function show_all_riwayat_penjualan();
    public function show_untung_rugi();
    public function update_profile();
    public function company_detail();
    public function company_update();
    public function show_all_kategori_barang();
    public function delete_kategori_barang();
    public function save_kategori_barang();
    public function show_all_satuan_barang();
    public function delete_satuan_barang();
    public function save_satuan_barang();
    public function delete_barang();
    public function show_all_product_retur_sales();
    public function save_retur_penjualan();
    public function show_neraca();
}