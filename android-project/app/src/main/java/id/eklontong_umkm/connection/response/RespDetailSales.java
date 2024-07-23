package id.eklontong_umkm.connection.response;

import java.io.Serializable;

import id.eklontong_umkm.model.Sales;

public class RespDetailSales implements Serializable {
    public int status;
    public String messages;
    public Sales data;
}
