package id.eklontong_umkm.model.param;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ParamUpdateProfile implements Serializable {
    public String id;
    public String name;
    public String email;
    public String phone;
    public String password;

    public ParamUpdateProfile() {
    }

    public ParamUpdateProfile(
            String id,
            String name,
            String email,
            String phone,
            String password
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Map<String, String> getParams(){
        Map<String, String> param = new HashMap<>();
        if(!TextUtils.isEmpty(id)) param.put("id", id);
        if(!TextUtils.isEmpty(name)) param.put("name", name);
        if(!TextUtils.isEmpty(email)) param.put("email", email);
        if(!TextUtils.isEmpty(phone)) param.put("phone", phone);
        if(!TextUtils.isEmpty(password)) param.put("password", password);

        return param;
    }
}
