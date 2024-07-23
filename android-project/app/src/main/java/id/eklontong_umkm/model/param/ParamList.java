package id.eklontong_umkm.model.param;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class ParamList {
    public String count;
    public String page;
    public String keywords;
    public String category;
    public String city;
    public String listing;
    public String type;
    public String featured;
    public String column;
    public String order;
    public String company_id;
    public String users_id;
    public String search;
    public String id_sales;
    public String date;
    public String tanggal_mulai;
    public String tanggal_selesai;


    public ParamList() {
    }

    public Map<String, String> getParams(){
        Map<String, String> param = new HashMap<>();
        if(!TextUtils.isEmpty(count)) param.put("count", count);
        if(!TextUtils.isEmpty(page)) param.put("page", page);
        if(!TextUtils.isEmpty(keywords)) param.put("keywords", keywords);
        if(!TextUtils.isEmpty(category)) param.put("category", category);
        if(!TextUtils.isEmpty(city)) param.put("city", city);
        if(!TextUtils.isEmpty(listing)) param.put("listing", listing);
        if(!TextUtils.isEmpty(type)) param.put("type", type);
        if(!TextUtils.isEmpty(featured)) param.put("featured", featured);
        if(!TextUtils.isEmpty(column)) param.put("column", column);
        if(!TextUtils.isEmpty(order)) param.put("order", order);
        if(!TextUtils.isEmpty(company_id)) param.put("company_id", company_id);
        if(!TextUtils.isEmpty(users_id)) param.put("users_id", users_id);
        if(!TextUtils.isEmpty(search)) param.put("search", search);
        if(!TextUtils.isEmpty(id_sales)) param.put("id_sales", id_sales);
        if(!TextUtils.isEmpty(date)) param.put("date", date);
        if(!TextUtils.isEmpty(tanggal_mulai)) param.put("tanggal_mulai", tanggal_mulai);
        if(!TextUtils.isEmpty(tanggal_selesai)) param.put("tanggal_selesai", tanggal_selesai);
        return param;
    }
}
