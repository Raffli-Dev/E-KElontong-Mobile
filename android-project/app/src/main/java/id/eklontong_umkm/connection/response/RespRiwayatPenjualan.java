package id.eklontong_umkm.connection.response;

import java.io.Serializable;
import java.util.List;

import id.eklontong_umkm.model.ProductList;
import id.eklontong_umkm.model.Sales;

public class RespRiwayatPenjualan implements Serializable {
    public int status;
    public int count;
    public int count_total;
    public int page;
    public String messages;
    public List<Sales> list;
}
