package id.eklontong_umkm.connection.response;

import java.io.Serializable;

import id.eklontong_umkm.model.Product;

public class RespProduct implements Serializable {
    public int status;
    public String messages;
    public Product data;
}
