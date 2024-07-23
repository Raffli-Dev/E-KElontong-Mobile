package id.eklontong_umkm.connection.response;

import java.io.Serializable;

import id.eklontong_umkm.model.Dashboard;
import id.eklontong_umkm.model.UntungRugi;

public class RespUntungRugi implements Serializable {
    public int status;
    public String messages;
    public UntungRugi data;
}
