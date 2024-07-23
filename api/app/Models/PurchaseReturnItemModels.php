<?php

namespace App\Models;

use CodeIgniter\Model;

class PurchaseReturnItemModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_purchaseitemsreturn';
    protected $allowedFields      = ['purchase_id', 'return_id', 'return_status', 'item_id', 'return_qty',
        'price_per_unit', 'tax_id', 'tax_amt',
        'tax_type','unit_discount_per','discount_amt','unit_total_cost','total_cost','profit_margin_per',
        'unit_sales_price','status','description','discount_type','discount_input'];
}