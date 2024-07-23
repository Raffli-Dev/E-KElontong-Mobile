package id.eklontong_umkm.connection.response;

import java.io.Serializable;
import java.util.List;

import id.eklontong_umkm.model.Category;
import id.eklontong_umkm.model.UnitSatuan;

public class RespListUnitSatuan implements Serializable {
    public int status;
    public int count;
    public int count_total;
    public int page;
    public String messages;
    public List<UnitSatuan> list;
}
