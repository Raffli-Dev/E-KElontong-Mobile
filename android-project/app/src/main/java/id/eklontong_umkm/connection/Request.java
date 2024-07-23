package id.eklontong_umkm.connection;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import id.eklontong_umkm.connection.response.ParamUpdateCompany;
import id.eklontong_umkm.connection.response.RespCompanyDetail;
import id.eklontong_umkm.connection.response.RespDashboard;
import id.eklontong_umkm.connection.response.RespDeleteBarang;
import id.eklontong_umkm.connection.response.RespDeleteKategoriBarang;
import id.eklontong_umkm.connection.response.RespDeletePengeluaran;
import id.eklontong_umkm.connection.response.RespDeleteSatuanBarang;
import id.eklontong_umkm.connection.response.RespDetailSales;
import id.eklontong_umkm.connection.response.RespDetailSalesItem;
import id.eklontong_umkm.connection.response.RespInfo;
import id.eklontong_umkm.connection.response.RespKategoriBarang;
import id.eklontong_umkm.connection.response.RespListCategory;
import id.eklontong_umkm.connection.response.RespListProduct;
import id.eklontong_umkm.connection.response.RespListUnitSatuan;
import id.eklontong_umkm.connection.response.RespNeraca;
import id.eklontong_umkm.connection.response.RespPengeluaran;
import id.eklontong_umkm.connection.response.RespProduct;
import id.eklontong_umkm.connection.response.RespRiwayatPenjualan;
import id.eklontong_umkm.connection.response.RespSatuanBarang;
import id.eklontong_umkm.connection.response.RespUntungRugi;
import id.eklontong_umkm.connection.response.RespUser;
import id.eklontong_umkm.model.param.ParamDashboard;
import id.eklontong_umkm.model.param.ParamKategoriBarang;
import id.eklontong_umkm.model.param.ParamList;
import id.eklontong_umkm.model.param.ParamLogin;
import id.eklontong_umkm.model.param.ParamPengeluaran;
import id.eklontong_umkm.model.param.ParamProcust;
import id.eklontong_umkm.model.param.ParamSalesDetail;
import id.eklontong_umkm.model.param.ParamSatuanBarang;
import id.eklontong_umkm.model.param.ParamUpdateProfile;
import id.eklontong_umkm.model.param.ParamUserRegister;
import id.eklontong_umkm.utils.AvatarUtils;
import id.eklontong_umkm.utils.Tools;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Request {
    private Context context;
    private API api = RestAdapter.createAPI();

    public Request(Context context) {
        this.context = context;
    }
    public Call<RespInfo> info(RequestListener<RespInfo> listener) {
        Call<RespInfo> callbackCall = api.info(Tools.getVersionCode(this.context), "ANDROID");
        callbackCall.enqueue(new Callback<RespInfo>() {
            @Override
            public void onResponse(Call<RespInfo> call, Response<RespInfo> response) {
                RespInfo resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespInfo> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }
    public Call<RespUser> userLogin(ParamLogin params, RequestListener<RespUser> listener) {
        Call<RespUser> callbackCall = api.userLogin(params);
        callbackCall.enqueue(new Callback<RespUser>() {
            @Override
            public void onResponse(Call<RespUser> call, Response<RespUser> response) {
                RespUser resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespUser> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespUser> userRegister(ParamUserRegister params, RequestListener<RespUser> listener) {
        Call<RespUser> callbackCall = api.userRegister(params);
        callbackCall.enqueue(new Callback<RespUser>() {
            @Override
            public void onResponse(Call<RespUser> call, Response<RespUser> response) {
                RespUser resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespUser> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }
    public Call<RespDashboard> showDashboard(ParamDashboard params, RequestListener<RespDashboard> listener) {
        Call<RespDashboard> callbackCall = api.showDashboard(params.company_id, params.users_id, params.tanggal_mulai, params.tanggal_selesai);
        callbackCall.enqueue(new Callback<RespDashboard>() {
            @Override
            public void onResponse(Call<RespDashboard> call, Response<RespDashboard> response) {
                RespDashboard resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespDashboard> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespListCategory> categories(ParamList params, RequestListener<RespListCategory> listener) {
        params.column = "priority";
        params.order = "asc";
        Call<RespListCategory> callbackCall = api.categories(params.getParams());
        callbackCall.enqueue(new Callback<RespListCategory>() {
            @Override
            public void onResponse(Call<RespListCategory> call, Response<RespListCategory> response) {
                RespListCategory resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespListCategory> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespListUnitSatuan> unitSatuan(ParamList params, RequestListener<RespListUnitSatuan> listener) {
        params.column = "priority";
        params.order = "asc";
        Call<RespListUnitSatuan> callbackCall = api.unitSatuan(params.getParams());
        callbackCall.enqueue(new Callback<RespListUnitSatuan>() {
            @Override
            public void onResponse(Call<RespListUnitSatuan> call, Response<RespListUnitSatuan> response) {
                RespListUnitSatuan resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespListUnitSatuan> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespProduct> productSave(Bitmap bitmap, ParamProcust params, RequestListener<RespProduct> listener) {
        MultipartBody.Part image = null;
        if (bitmap != null) {
            File file = AvatarUtils.createTempFile(context, bitmap);
            RequestBody reqFile = RequestBody.create(file, MediaType.parse("image/*"));
            image = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
        }
        Call<RespProduct> callbackCall = api.saveProduct(image, createPart(params.getParams()));
        callbackCall.enqueue(new Callback<RespProduct>() {
            @Override
            public void onResponse(Call<RespProduct> call, Response<RespProduct> response) {
                RespProduct resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespProduct> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespPengeluaran> pengeluaranList(ParamList params, RequestListener<RespPengeluaran> listener) {
        Call<RespPengeluaran> callbackCall = api.pengeluaranList(params.getParams());
        callbackCall.enqueue(new Callback<RespPengeluaran>() {
            @Override
            public void onResponse(Call<RespPengeluaran> call, Response<RespPengeluaran> response) {
                RespPengeluaran resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespPengeluaran> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespProduct> productDetails(String id, RequestListener<RespProduct> listener) {
        Call<RespProduct> callbackCall = api.productDetails(id);
        callbackCall.enqueue(new Callback<RespProduct>() {
            @Override
            public void onResponse(Call<RespProduct> call, Response<RespProduct> response) {
                RespProduct resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespProduct> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespProduct> userRegister(ParamProcust params, RequestListener<RespProduct> listener) {
        Call<RespProduct> callbackCall = api.updateStock(params);
        callbackCall.enqueue(new Callback<RespProduct>() {
            @Override
            public void onResponse(Call<RespProduct> call, Response<RespProduct> response) {
                RespProduct resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespProduct> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespDetailSales> salesDetail(ParamSalesDetail param, RequestListener<RespDetailSales> listener) {
        Call<RespDetailSales> callbackCall = api.salesDetail(param.id_sales);
        callbackCall.enqueue(new Callback<RespDetailSales>() {
            @Override
            public void onResponse(Call<RespDetailSales> call, Response<RespDetailSales> response) {
                RespDetailSales resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespDetailSales> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespDetailSalesItem> salesItems(ParamList params, RequestListener<RespDetailSalesItem> listener) {
        Call<RespDetailSalesItem> callbackCall = api.salesItems(params.getParams());
        callbackCall.enqueue(new Callback<RespDetailSalesItem>() {
            @Override
            public void onResponse(Call<RespDetailSalesItem> call, Response<RespDetailSalesItem> response) {
                RespDetailSalesItem resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespDetailSalesItem> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespListProduct> productList(ParamList params, RequestListener<RespListProduct> listener) {
        Call<RespListProduct> callbackCall = api.productList(params.getParams());
        callbackCall.enqueue(new Callback<RespListProduct>() {
            @Override
            public void onResponse(Call<RespListProduct> call, Response<RespListProduct> response) {
                RespListProduct resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespListProduct> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespPengeluaran> pengeluaranSave(ParamPengeluaran params, RequestListener<RespPengeluaran> listener) {
        Call<RespPengeluaran> callbackCall = api.pengeluaranSave(params);
        callbackCall.enqueue(new Callback<RespPengeluaran>() {
            @Override
            public void onResponse(Call<RespPengeluaran> call, Response<RespPengeluaran> response) {
                RespPengeluaran resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespPengeluaran> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }


    public Call<RespDeletePengeluaran> delete_pengeluaran(String id, RequestListener<RespDeletePengeluaran> listener) {
        Call<RespDeletePengeluaran> callbackCall = api.delete_pengeluaran(id);
        callbackCall.enqueue(new Callback<RespDeletePengeluaran>() {
            @Override
            public void onResponse(Call<RespDeletePengeluaran> call, Response<RespDeletePengeluaran> response) {
                RespDeletePengeluaran resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespDeletePengeluaran> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespRiwayatPenjualan> riwayatPenjualan(ParamList params, RequestListener<RespRiwayatPenjualan> listener) {
        Call<RespRiwayatPenjualan> callbackCall = api.riwayatPenjualan(params.getParams());
        callbackCall.enqueue(new Callback<RespRiwayatPenjualan>() {
            @Override
            public void onResponse(Call<RespRiwayatPenjualan> call, Response<RespRiwayatPenjualan> response) {
                RespRiwayatPenjualan resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespRiwayatPenjualan> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }


    public Call<RespUntungRugi> showUntungRugi(ParamDashboard params, RequestListener<RespUntungRugi> listener) {
        Call<RespUntungRugi> callbackCall = api.showUntungRugi(params.company_id, params.users_id, params.tanggal_mulai, params.tanggal_selesai);
        callbackCall.enqueue(new Callback<RespUntungRugi>() {
            @Override
            public void onResponse(Call<RespUntungRugi> call, Response<RespUntungRugi> response) {
                RespUntungRugi resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespUntungRugi> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }
    public Call<RespUser> updateProfile(ParamUpdateProfile params, RequestListener<RespUser> listener) {
        Call<RespUser> callbackCall = api.updateProfile(params);
        callbackCall.enqueue(new Callback<RespUser>() {
            @Override
            public void onResponse(Call<RespUser> call, Response<RespUser> response) {
                RespUser resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespUser> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespCompanyDetail> showCompanyDetail(ParamDashboard params, RequestListener<RespCompanyDetail> listener) {
        Call<RespCompanyDetail> callbackCall = api.showCompanyDetail(params.company_id, params.users_id);
        callbackCall.enqueue(new Callback<RespCompanyDetail>() {
            @Override
            public void onResponse(Call<RespCompanyDetail> call, Response<RespCompanyDetail> response) {
                RespCompanyDetail resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespCompanyDetail> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespCompanyDetail> updateCompany(ParamUpdateCompany params, RequestListener<RespCompanyDetail> listener) {
        Call<RespCompanyDetail> callbackCall = api.updateCompany(params);
        callbackCall.enqueue(new Callback<RespCompanyDetail>() {
            @Override
            public void onResponse(Call<RespCompanyDetail> call, Response<RespCompanyDetail> response) {
                RespCompanyDetail resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespCompanyDetail> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespKategoriBarang> kategoriBarangList(ParamList params, RequestListener<RespKategoriBarang> listener) {
        Call<RespKategoriBarang> callbackCall = api.kategoriBarangList(params.getParams());
        callbackCall.enqueue(new Callback<RespKategoriBarang>() {
            @Override
            public void onResponse(Call<RespKategoriBarang> call, Response<RespKategoriBarang> response) {
                RespKategoriBarang resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespKategoriBarang> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespDeleteKategoriBarang> delete_kategori_barang(String id, RequestListener<RespDeleteKategoriBarang> listener) {
        Call<RespDeleteKategoriBarang> callbackCall = api.delete_kategori_barang(id);
        callbackCall.enqueue(new Callback<RespDeleteKategoriBarang>() {
            @Override
            public void onResponse(Call<RespDeleteKategoriBarang> call, Response<RespDeleteKategoriBarang> response) {
                RespDeleteKategoriBarang resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespDeleteKategoriBarang> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespKategoriBarang> kategoriBarangSave(ParamKategoriBarang params, RequestListener<RespKategoriBarang> listener) {
        Call<RespKategoriBarang> callbackCall = api.kategoriBarangSave(params);
        callbackCall.enqueue(new Callback<RespKategoriBarang>() {
            @Override
            public void onResponse(Call<RespKategoriBarang> call, Response<RespKategoriBarang> response) {
                RespKategoriBarang resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespKategoriBarang> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespSatuanBarang> satuanBarangList(ParamList params, RequestListener<RespSatuanBarang> listener) {
        Call<RespSatuanBarang> callbackCall = api.satuanBarangList(params.getParams());
        callbackCall.enqueue(new Callback<RespSatuanBarang>() {
            @Override
            public void onResponse(Call<RespSatuanBarang> call, Response<RespSatuanBarang> response) {
                RespSatuanBarang resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespSatuanBarang> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespDeleteSatuanBarang> delete_satuan_barang(String id, RequestListener<RespDeleteSatuanBarang> listener) {
        Call<RespDeleteSatuanBarang> callbackCall = api.delete_satuan_barang(id);
        callbackCall.enqueue(new Callback<RespDeleteSatuanBarang>() {
            @Override
            public void onResponse(Call<RespDeleteSatuanBarang> call, Response<RespDeleteSatuanBarang> response) {
                RespDeleteSatuanBarang resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespDeleteSatuanBarang> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }
    public Call<RespSatuanBarang> satuanBarangSave(ParamSatuanBarang params, RequestListener<RespSatuanBarang> listener) {
        Call<RespSatuanBarang> callbackCall = api.satuanBarangSave(params);
        callbackCall.enqueue(new Callback<RespSatuanBarang>() {
            @Override
            public void onResponse(Call<RespSatuanBarang> call, Response<RespSatuanBarang> response) {
                RespSatuanBarang resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespSatuanBarang> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespDeleteBarang> delete_barang(String id, RequestListener<RespDeleteBarang> listener) {
        Call<RespDeleteBarang> callbackCall = api.delete_barang(id);
        callbackCall.enqueue(new Callback<RespDeleteBarang>() {
            @Override
            public void onResponse(Call<RespDeleteBarang> call, Response<RespDeleteBarang> response) {
                RespDeleteBarang resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespDeleteBarang> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    public Call<RespListProduct> productListSalesRetur(ParamList params, RequestListener<RespListProduct> listener) {
        Call<RespListProduct> callbackCall = api.productListSalesRetur(params.getParams());
        callbackCall.enqueue(new Callback<RespListProduct>() {
            @Override
            public void onResponse(Call<RespListProduct> call, Response<RespListProduct> response) {
                RespListProduct resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespListProduct> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }


    public Call<RespNeraca> showNeraca(ParamDashboard params, RequestListener<RespNeraca> listener) {
        Call<RespNeraca> callbackCall = api.showNeraca(params.company_id, params.users_id, params.tanggal_mulai, params.tanggal_selesai);
        callbackCall.enqueue(new Callback<RespNeraca>() {
            @Override
            public void onResponse(Call<RespNeraca> call, Response<RespNeraca> response) {
                RespNeraca resp = response.body();
                listener.onFinish();
                if (resp == null) {
                    listener.onFailed(null);
                } else {
                    if (resp.status != 200) {
                        listener.onFailed(resp.messages);
                    } else {
                        listener.onSuccess(resp);
                    }
                }
            }

            @Override
            public void onFailure(Call<RespNeraca> call, Throwable t) {
                listener.onFinish();
                listener.onFailed(t.getMessage());
            }
        });
        return callbackCall;
    }

    private Map<String, RequestBody> createPart(Map<String, String> params) {
        Map<String, RequestBody> paramsBody = new HashMap<>();
        for (Map.Entry<String, String> e : params.entrySet()) {
            paramsBody.put(e.getKey(), getRequestBody(e.getValue()));
        }
        return paramsBody;
    }

    private RequestBody getRequestBody(String value) {
        return RequestBody.create(value, okhttp3.MultipartBody.FORM);
    }

}
