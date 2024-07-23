package id.eklontong_umkm.connection.response;

import java.io.Serializable;

import id.eklontong_umkm.model.Dashboard;
import id.eklontong_umkm.model.User;

public class RespDashboard implements Serializable {
    public int status;
    public String messages;
    public Dashboard data;
}
