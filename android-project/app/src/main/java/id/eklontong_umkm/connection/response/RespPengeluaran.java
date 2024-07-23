package id.eklontong_umkm.connection.response;

import java.io.Serializable;
import java.util.List;

import id.eklontong_umkm.model.Pengeluaran;
import id.eklontong_umkm.model.Product;
import id.eklontong_umkm.model.ProductList;

public class RespPengeluaran implements Serializable {
    public int status;
    public int count;
    public int count_total;
    public int page;
    public String messages;
    public List<Pengeluaran> list;
}
