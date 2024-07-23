package id.eklontong_umkm.connection.response;

import java.io.Serializable;

import id.eklontong_umkm.model.Company;
import id.eklontong_umkm.model.Dashboard;

public class RespCompanyDetail implements Serializable {
    public int status;
    public String messages;
    public Company data;
}
