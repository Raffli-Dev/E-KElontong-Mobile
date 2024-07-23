package id.eklontong_umkm.model;

import java.io.Serializable;

public class Category implements Serializable {
    public Long id;
    public String category_code;
    public String category_name;
    public String description;
    public String company_id;
    public String status;

    public Category() {
    }

    public Category(Long id,
                    String category_code,
                    String category_name,
                    String description,
                    String company_id,
                    String status
    ) {
        this.id = id;
        this.category_code = category_code;
        this.category_name = category_name;
        this.description = description;
        this.company_id = company_id;
        this.status = status;
    }
}
