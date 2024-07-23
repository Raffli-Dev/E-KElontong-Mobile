package id.eklontong_umkm.connection.response;

import java.io.Serializable;

import id.eklontong_umkm.model.User;

public class RespUser implements Serializable {
    public int status;
    public String messages;
    public User data;
}
