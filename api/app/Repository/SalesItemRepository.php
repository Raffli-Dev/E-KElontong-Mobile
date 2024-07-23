<?php

namespace App\Repository;

use App\Exception\ValidationException;
use App\Models\SalesItemModels;

class SalesItemRepository
{
    function __construct()
    {
        $this->salesItemModels = new SalesItemModels();
    }
    public function show_list_product(string $company_id, string $search = "")
    {
        try {
            $this->salesItemModels->select("
               db_salesitems.sales_id,db_salesitems.sales_qty,
               db_items.id,db_items.item_image, db_items.item_code, db_items.item_name, b.category_name, c.unit_name,
               db_items.custom_barcode,
               db_items.stock, db_items.alert_qty, db_items.purchase_price, db_items.final_price, d.tax_name,
               d.tax, db_items.status, e.brand_name, db_items.tax_type, db_items.hsn, db_items.sku,
               CASE WHEN e.brand_name IS NULL THEN '' ELSE e.brand_name END AS brand_name
            ");
            if ($search != "") {
                $this->salesItemModels->like('db_items.item_name', $search, 'both');
            }
            $this->salesItemModels->join("db_items", "db_items.id=db_salesitems.item_id", "left");
            $this->salesItemModels->join("db_brands as e", "e.id=db_items.brand_id", "left");
            $this->salesItemModels->join("db_category as b", "b.id=db_items.category_id", "left");
            $this->salesItemModels->join("db_units as c", "c.id=db_items.unit_id", "left");
            $this->salesItemModels->join("db_tax as d", "d.id=db_items.tax_id", "left");
            $this->salesItemModels->where("db_items.company_id", $company_id);
            $this->salesItemModels->where('db_items.status', 1);
            $result = $this->salesItemModels->getWhere("db_items.id != '0'")->getResult();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}