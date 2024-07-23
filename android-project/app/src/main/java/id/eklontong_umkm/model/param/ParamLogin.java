package id.eklontong_umkm.model.param;

import java.io.Serializable;

public class ParamLogin implements Serializable {
    public String email;
    public String password;
    public String device_id;
    public String fcm_regid;

    public ParamLogin() {
    }

    public ParamLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
