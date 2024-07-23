package id.eklontong_umkm.model.param;

import java.io.Serializable;

public class ParamRiwayatPenjualan implements Serializable {
    public String company_id;
    public Long users_id;
    public String tanggal_mulai;
    public String tanggal_selesai;

    public ParamRiwayatPenjualan() {
    }

    public ParamRiwayatPenjualan(String company_id, Long users_id, String tanggal_mulai, String tanggal_selesai) {
        this.company_id = company_id;
        this.users_id = users_id;
        this.tanggal_mulai = tanggal_mulai;
        this.tanggal_selesai = tanggal_selesai;
    }
}
