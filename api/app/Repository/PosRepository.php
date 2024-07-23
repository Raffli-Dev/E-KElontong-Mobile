<?php

namespace App\Repository;

use App\Exception\ValidationException;
use App\Models\ItemsProductModels;
use App\Models\PurchaseItemModels;
use App\Models\PurchaseReturnItemModels;
use App\Models\SalesItemModels;
use App\Models\SalesItemReturnModels;
use App\Models\StockEntryModels;

class PosRepository
{
    function __construct()
    {
        $this->db_stockentry = new StockEntryModels();
        $this->db_purchaseitems = new PurchaseItemModels();
        $this->db_salesitems = new SalesItemModels();
        $this->db_purchaseitemsreturn = new PurchaseReturnItemModels();
        $this->db_salesitemsreturn = new SalesItemReturnModels();
        $this->itemsProductModels = new ItemsProductModels();
    }
    public function update_items_quantity($item_id)
    {
        try {
            $q7 = $this->db_stockentry->select("COALESCE(SUM(qty),0) as stock_qty")->where('item_id', $item_id)->first();
            $stock_qty = $q7['stock_qty'];

            $q8 = $this->db_purchaseitems->select("COALESCE(SUM(purchase_qty),0) as pu_tot_qty")->where('item_id', $item_id)->where('purchase_status', 'Received')->first();
            $pu_tot_qty = $q8['pu_tot_qty'];

            $q9 = $this->db_salesitems->select("coalesce(SUM(sales_qty),0) as sl_tot_qty")->where('item_id', $item_id)->where('sales_status', 'Final')->first();
            $sl_tot_qty = $q9['sl_tot_qty'];

            /*Fid Return Items Count*/
            $q6 = $this->db_purchaseitemsreturn->select("COALESCE(SUM(return_qty),0) as pu_return_tot_qty")->where('item_id', $item_id)->first();
            $pu_return_tot_qty = $q6['pu_return_tot_qty'];

            /*Fid Return Items Count*/
            $q6=$this->db_salesitemsreturn->select("COALESCE(SUM(return_qty),0) as sl_return_tot_qty")->where('item_id', $item_id)->first();
            $sl_return_tot_qty=$q6['sl_return_tot_qty'];

            $stock=((($stock_qty+$pu_tot_qty)-$sl_tot_qty)+$sl_return_tot_qty)-$pu_return_tot_qty;
            $q7 = $this->itemsProductModels->set('stock', $stock)->where('id', $item_id)->update();
            if ($q7) {
                return true;
            } else {
                return false;
            }
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}