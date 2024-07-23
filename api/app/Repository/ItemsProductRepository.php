<?php

namespace App\Repository;

use App\Exception\ValidationException;
use App\Models\ItemsProductModels;

class ItemsProductRepository
{
    function __construct()
    {
        $this->itemsProductModels = new ItemsProductModels();
    }
    public function total_produk(string $company_id)
    {
        try {
            $this->itemsProductModels->select("COALESCE(COUNT(*),0) AS total_produk");
            $this->itemsProductModels->where('company_id', $company_id)->where('status', 1);
            $result = $this->itemsProductModels->first();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function total(string $company_id)
    {
        try {
            $result = $this->itemsProductModels->total($company_id);
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function maxid()
    {
        try {
            $this->itemsProductModels->orderBy('id', 'DESC');
            $this->itemsProductModels->limit(1);
            $result = $this->itemsProductModels->first();
            $maxnumber = $result['id'];
            $maxnumber = $maxnumber+1;
            return $maxnumber;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function findById(string $id)
    {
        try {
            $result = $this->itemsProductModels->find($id);
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }

    public function show_list_product(string $company_id, string $search = "")
    {
        try {
            $this->itemsProductModels->select("
               db_items.id,db_items.item_image, db_items.item_code, db_items.item_name, b.category_name, c.unit_name,
               db_items.custom_barcode,
               db_items.stock, db_items.alert_qty, db_items.purchase_price, db_items.final_price, d.tax_name,
               d.tax, db_items.status, e.brand_name, db_items.tax_type, db_items.hsn, db_items.sku,
               CASE WHEN e.brand_name IS NULL THEN '' ELSE e.brand_name END AS brand_name
            ");
            if ($search != "") {
                $this->itemsProductModels->like('db_items.item_name', $search, 'both');
            }
            $this->itemsProductModels->join("db_brands as e", "e.id=db_items.brand_id", "left");
            $this->itemsProductModels->join("db_category as b", "b.id=db_items.category_id", "left");
            $this->itemsProductModels->join("db_units as c", "c.id=db_items.unit_id", "left");
            $this->itemsProductModels->join("db_tax as d", "d.id=db_items.tax_id", "left");
            $this->itemsProductModels->where("db_items.company_id", $company_id);
            $this->itemsProductModels->where('db_items.status', 1);
            $result = $this->itemsProductModels->getWhere("db_items.id != '0'")->getResult();
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
    public function find(string $id)
    {
        try {
            $result = $this->itemsProductModels->find($id);
            return $result;
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }

    public function checkAvailableProductOrderDetail($order_detail){
        try {
            // find and check available each product
            foreach($order_detail as $od) {
                $status_item = ['product_id' => $od['product_id'], 'stock' => 0, 'amount' => 0, 'product_name' => $od['product_name'], 'msg' => 'OK'];
                $product_id = $od['product_id'];
                $resultIdCount = $this->itemsProductModels->where('id', $status_item['product_id'])->countAllResults();
                if ($resultIdCount < 1) {
                    throw new ValidationException("Data Produkct Id Dengan Nama ".$status_item['product_name']." Tidak Ditemukan");
                }
                $resultId = $this->itemsProductModels->where('id', $status_item['product_id'])->getWhere("id != '0'")->getFirstRow();
                return $resultId;

            }
        } catch (\Exception $exception) {
            throw new ValidationException($exception->getMessage());
        }
    }
}