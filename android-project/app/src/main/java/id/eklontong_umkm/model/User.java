package id.eklontong_umkm.model;

import java.io.Serializable;

public class User implements Serializable {
    public Long id = -1L;
    public String name = "";
    public String email = "";
    public String phone = "";
    public String company_id = "";
    public String password = "";
    public String image = "";
    public String provider = "";
}
