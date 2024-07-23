package id.eklontong_umkm.connection.response;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ParamUpdateCompany implements Serializable {
    public String id;
    public String owner;
    public String company_name;
    public String mobile;
    public String address;
    public String email;
    public String company_id;
    public String users_id;

    public ParamUpdateCompany() {
    }

    public ParamUpdateCompany(
            String id,
            String owner,
            String company_name,
            String mobile,
            String address,
            String email,
            String company_id,
            String users_id
    ) {
        this.owner = owner;
        this.company_name = company_name;
        this.mobile = mobile;
        this.address = address;
        this.email = email;
        this.company_id = company_id;
        this.users_id = users_id;
    }

    public Map<String, String> getParams(){
        Map<String, String> param = new HashMap<>();
        if(!TextUtils.isEmpty(id)) param.put("id", id);
        if(!TextUtils.isEmpty(owner)) param.put("owner", owner);
        if(!TextUtils.isEmpty(company_name)) param.put("company_name", company_name);
        if(!TextUtils.isEmpty(mobile)) param.put("mobile", mobile);
        if(!TextUtils.isEmpty(address)) param.put("address", address);
        if(!TextUtils.isEmpty(email)) param.put("email", email);
        if(!TextUtils.isEmpty(company_id)) param.put("company_id", company_id);
        if(!TextUtils.isEmpty(users_id)) param.put("users_id", users_id);

        return param;
    }
}
