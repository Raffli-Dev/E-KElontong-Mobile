package id.eklontong_umkm.model.param;

import java.io.Serializable;

public class ParamSalesDetail implements Serializable {
    public String id_sales;

    public ParamSalesDetail() {
    }

    public ParamSalesDetail(String id_sales) {
        this.id_sales = id_sales;
    }
}
