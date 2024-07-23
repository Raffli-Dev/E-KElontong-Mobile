package id.eklontong_umkm.connection.response;

import java.io.Serializable;

import id.eklontong_umkm.model.Neraca;
import id.eklontong_umkm.model.UntungRugi;

public class RespNeraca implements Serializable {
    public int status;
    public String messages;
    public Neraca data;
}
