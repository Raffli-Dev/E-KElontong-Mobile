package id.eklontong_umkm.connection.response;

import java.io.Serializable;
import java.util.List;

import id.eklontong_umkm.model.Config;

public class RespInfo implements Serializable {
    public int status;
    public String messages;
    public String app_status;
    public Config config;
}
