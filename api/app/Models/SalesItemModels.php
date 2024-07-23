<?php

namespace App\Models;

use CodeIgniter\Model;

class SalesItemModels extends Model
{
    protected $primaryKey         = 'id';
    protected $table              = 'db_salesitems';
    protected $allowedFields      = ['sales_id', 'sales_status', 'item_id', 'description', 'sales_qty',
        'price_per_unit', 'tax_type', 'tax_id',
        'tax_amt','discount_type','discount_input','discount_amt','unit_total_cost','total_cost',
        'status','purchase_price'];
}