package id.eklontong_umkm.model.param;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ParamSatuanBarang implements Serializable {
    public String id;
    public String unit_name;
    public String description;
    public String company_id;
    public String users_id;

    public ParamSatuanBarang() {
    }

    public ParamSatuanBarang(
            String unit_name,
            String description,
            String company_id,
            String users_id
    ) {
        this.unit_name = unit_name;
        this.description = description;
        this.company_id = company_id;
        this.users_id = users_id;
    }

    public Map<String, String> getParams(){
        Map<String, String> param = new HashMap<>();
        if(!TextUtils.isEmpty(id)) param.put("id", id);
        if(!TextUtils.isEmpty(unit_name)) param.put("unit_name", unit_name);
        if(!TextUtils.isEmpty(description)) param.put("description", description);
        if(!TextUtils.isEmpty(company_id)) param.put("company_id", company_id);
        if(!TextUtils.isEmpty(users_id)) param.put("users_id", users_id);
        return param;
    }
}
