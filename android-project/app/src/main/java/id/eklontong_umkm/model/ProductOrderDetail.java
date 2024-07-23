package id.eklontong_umkm.model;

import java.io.Serializable;

public class ProductOrderDetail implements Serializable {
    public Long product_id;
    public String product_name;
    public Integer amount;
    public Double price_item;
    public Long created_at = System.currentTimeMillis();
    public Long last_update = System.currentTimeMillis();
    public Long sales_id;
    public Long sales_qty;

    public ProductOrderDetail() {
    }

    public ProductOrderDetail(Long product_id, String product_name, Integer amount, Double price_item, Long sales_id, Long sales_qty) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.amount = amount;
        this.price_item = price_item;
        this.sales_id = sales_id;
        this.sales_qty = sales_qty;
    }
}
