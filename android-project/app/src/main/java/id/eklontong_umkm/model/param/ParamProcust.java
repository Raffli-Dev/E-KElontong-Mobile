package id.eklontong_umkm.model.param;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class ParamProcust {
    public String id;
    public String item_name;
    public String alert_qty;
    public String purchase_price;
    public String sales_price;
    public String new_opening_stock;
    public String category_id;
    public String unit_id;
    public String custom_barcode;
    public String expire_date;
    public String description;
    public String company_id;
    public String users_id;
    public String image_url;
    public String type;

    public ParamProcust() {
    }

    public ParamProcust(
            String item_name,
            String alert_qty,
            String purchase_price,
            String sales_price,
            String new_opening_stock,
            String category_id,
            String unit_id,
            String custom_barcode,
            String expire_date,
            String description,
            String company_id,
            String users_id,
            String type
    ) {
        this.item_name = item_name;
        this.alert_qty = alert_qty;
        this.purchase_price = purchase_price;
        this.sales_price = sales_price;
        this.new_opening_stock = new_opening_stock;
        this.category_id = category_id;
        this.unit_id = unit_id;
        this.custom_barcode = custom_barcode;
        this.expire_date = expire_date;
        this.description = description;
        this.company_id = company_id;
        this.users_id = users_id;
        this.type = type;
    }

    public Map<String, String> getParams(){
        Map<String, String> param = new HashMap<>();
        if(!TextUtils.isEmpty(id)) param.put("id", id);
        if(!TextUtils.isEmpty(item_name)) param.put("item_name", item_name);
        if(!TextUtils.isEmpty(alert_qty)) param.put("alert_qty", alert_qty);
        if(!TextUtils.isEmpty(purchase_price)) param.put("purchase_price", purchase_price);
        if(!TextUtils.isEmpty(sales_price)) param.put("sales_price", sales_price);
        if(!TextUtils.isEmpty(new_opening_stock)) param.put("new_opening_stock", new_opening_stock);
        if(!TextUtils.isEmpty(category_id)) param.put("category_id", category_id);
        if(!TextUtils.isEmpty(unit_id)) param.put("unit_id", unit_id);
        if(!TextUtils.isEmpty(custom_barcode)) param.put("custom_barcode", custom_barcode);
        if(!TextUtils.isEmpty(expire_date)) param.put("expire_date", expire_date);
        if(!TextUtils.isEmpty(description)) param.put("description", description);
        if(!TextUtils.isEmpty(company_id)) param.put("company_id", company_id);
        if(!TextUtils.isEmpty(users_id)) param.put("users_id", users_id);
        if(!TextUtils.isEmpty(image_url)) param.put("image_url", image_url);
        if(!TextUtils.isEmpty(type)) param.put("image_url", type);
        return param;
    }
}
