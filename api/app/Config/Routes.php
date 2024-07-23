<?php

use CodeIgniter\Router\RouteCollection;

/**
 * @var RouteCollection $routes
 */
$routes->get('/', 'Home::index');

$routes->get('/api/check', 'ApiController::check');
$routes->get('/api/info', 'ApiController::info');

$routes->post('/api/user/login', 'ApiController::login', ['filter' => 'securityfilter']);
$routes->post('/api/user/register', 'ApiController::register', ['filter' => 'securityfilter']);

$routes->post('/api/user/update-manajemen-stok', 'ApiController::updateManajemenStok', ['filter' => 'securityfilter']);

$routes->get('/api/dashboard', 'ApiController::show_dashboard', ['filter' => 'securityfilter']);
$routes->get('/api/category', 'ApiController::show_all_category', ['filter' => 'securityfilter']);
$routes->get('/api/unit-satuan', 'ApiController::show_all_unit_satuan', ['filter' => 'securityfilter']);
$routes->post('/api/product/save', 'ApiController::save_product', ['filter' => 'securityfilter']);
$routes->get('/api/product/show-all', 'ApiController::show_all_product', ['filter' => 'securityfilter']);
$routes->get('/api/product/show-all', 'ApiController::show_all_product', ['filter' => 'securityfilter']);
$routes->get('/api/product-sales-retur/show-all', 'ApiController::show_all_product_retur_sales', ['filter' => 'securityfilter']);
$routes->get('/api/product/(:num)', 'ApiController::productDetails/$1');
$routes->get('/api/product/delete', 'ApiController::delete_barang');

$routes->post('/api/payment', 'ApiController::savePayment');

$routes->get('/api/sales-detail', 'ApiController::detailSales');
$routes->get('/api/sales-detail-items', 'ApiController::detailSalesItems');
$routes->get('/api/pengeluaran', 'ApiController::show_all_pengeluaran');
$routes->post('/api/pengeluaran', 'ApiController::save_pengeluaran');
$routes->get('/api/pengeluaran/delete', 'ApiController::delete_pengeluaran');
$routes->get('/api/riwayat/penjualan', 'ApiController::show_all_riwayat_penjualan');
$routes->get('/api/untung-rugi', 'ApiController::show_untung_rugi');

$routes->get('/api/show-neraca', 'ApiController::show_neraca');

$routes->post('/api/update-profile', 'ApiController::update_profile');
$routes->get('/api/company-detail', 'ApiController::company_detail');
$routes->post('/api/company-update', 'ApiController::company_update');

$routes->get('/api/kategori-barang', 'ApiController::show_all_kategori_barang');
$routes->get('/api/kategori-barang/delete', 'ApiController::delete_kategori_barang');
$routes->post('/api/kategori-barang', 'ApiController::save_kategori_barang');

$routes->get('/api/satuan-barang', 'ApiController::show_all_satuan_barang');
$routes->get('/api/satuan-barang/delete', 'ApiController::delete_satuan_barang');

$routes->post('/api/satuan-barang', 'ApiController::save_satuan_barang');

$routes->post('/api/retur-sales', 'ApiController::save_retur_penjualan');