package id.eklontong_umkm.model.param;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class ParamUserRegister {
    public String id;
    public String name;
    public String email;
    public String phone;
    public String password;
    public String provider = "EMAIL";
    public String image_url;
    public String device_id;
    public String owner;
    public String company_name;
    public String address;

    public ParamUserRegister() {
    }

    public ParamUserRegister(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Map<String, String> getParams(){
        Map<String, String> param = new HashMap<>();
        if(!TextUtils.isEmpty(id)) param.put("id", id);
        if(!TextUtils.isEmpty(name)) param.put("name", name);
        if(!TextUtils.isEmpty(email)) param.put("email", email);
        if(!TextUtils.isEmpty(provider)) param.put("provider", provider);
        if(!TextUtils.isEmpty(password)) param.put("password", password);
        if(!TextUtils.isEmpty(image_url)) param.put("image_url", image_url);
        if(!TextUtils.isEmpty(device_id)) param.put("device_id", device_id);
        if(!TextUtils.isEmpty(phone)) param.put("phone", phone);
        if(!TextUtils.isEmpty(owner)) param.put("owner", owner);
        if(!TextUtils.isEmpty(company_name)) param.put("owner", company_name);
        if(!TextUtils.isEmpty(address)) param.put("owner", address);
        return param;
    }
}
