package id.eklontong_umkm.model;

import java.io.Serializable;

public class UnitSatuan implements Serializable {
    public Long id;
    public String unit_name;
    public String description;
    public String company_id;
    public String status;

    public UnitSatuan() {
    }

    public UnitSatuan(Long id,
                    String unit_name,
                    String description,
                    String company_id,
                    String status
    ) {
        this.id = id;
        this.unit_name = unit_name;
        this.description = description;
        this.company_id = company_id;
        this.status = status;
    }
}
