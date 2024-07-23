package id.eklontong_umkm.connection;

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
import id.eklontong_umkm.connection.response.RespOrder;
import id.eklontong_umkm.connection.response.RespOrderReturSales;
import id.eklontong_umkm.connection.response.RespPengeluaran;
import id.eklontong_umkm.connection.response.RespProduct;
import id.eklontong_umkm.connection.response.RespRiwayatPenjualan;
import id.eklontong_umkm.connection.response.RespSatuanBarang;
import id.eklontong_umkm.connection.response.RespUntungRugi;
import id.eklontong_umkm.connection.response.RespUser;
import id.eklontong_umkm.data.Constant;
import id.eklontong_umkm.model.Checkout;
import id.eklontong_umkm.model.Sales;
import id.eklontong_umkm.model.param.ParamKategoriBarang;
import id.eklontong_umkm.model.param.ParamLogin;
import id.eklontong_umkm.model.param.ParamPengeluaran;
import id.eklontong_umkm.model.param.ParamProcust;
import id.eklontong_umkm.model.param.ParamSatuanBarang;
import id.eklontong_umkm.model.param.ParamUpdateProfile;
import id.eklontong_umkm.model.param.ParamUserRegister;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface API {
    String CACHE = "Cache-Control: max-age=0";
    String AGENT = "User-Agent: Traver.";
    String SECURITY = "Security: " + Constant.SECURITY_CODE;

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/info")
    Call<RespInfo> info(
            @Query("version") Integer version,
            @Query("type") String type
    );

    @Headers({CACHE, AGENT, SECURITY})
    @POST("api/user/login")
    Call<RespUser> userLogin(@Body ParamLogin login);

    @Headers({CACHE, AGENT, SECURITY})
    @POST("api/user/register")
    Call<RespUser> userRegister(@Body ParamUserRegister login);

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/dashboard")
    Call<RespDashboard> showDashboard(
            @Query("company_id") String company_id,
            @Query("users_id") Long users_id,
            @Query("tanggal_mulai") String tanggal_mulai,
            @Query("tanggal_selesai") String tanggal_selesai
    );
    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/category")
    Call<RespListCategory> categories(@QueryMap Map<String, String> param);

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/unit-satuan")
    Call<RespListUnitSatuan> unitSatuan(@QueryMap Map<String, String> param);

    @Headers({CACHE, AGENT, SECURITY})
    @Multipart
    @POST("api/product/save")
    Call<RespProduct> saveProduct(
            @Part MultipartBody.Part image,
            @PartMap Map<String, RequestBody> data
    );

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/product/show-all")
    Call<RespListProduct> productList(@QueryMap Map<String, String> param);

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/product-sales-retur/show-all")
    Call<RespListProduct> productListSalesRetur(@QueryMap Map<String, String> param);

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/product/{id}")
    Call<RespProduct> productDetails(@Path("id") String id);

    @Headers({CACHE, AGENT, SECURITY})
    @POST("api/user/update-manajemen-stok")
    Call<RespProduct> updateStock(@Body ParamProcust login);

    @Headers({CACHE, AGENT})
    @POST("api/payment")
    Call<RespOrder> submitProductOrder(
            @Header("Security") String security,
            @Body Checkout checkout
    );

    @Headers({CACHE, AGENT})
    @POST("api/retur-sales")
    Call<RespOrderReturSales> submitProductOrderSalesRetur(
            @Header("Security") String security,
            @Body Checkout checkout
    );

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/sales-detail")
    Call<RespDetailSales> salesDetail(
            @Query("id_sales") String version
    );

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/sales-detail-items")
    Call<RespDetailSalesItem> salesItems(@QueryMap Map<String, String> param);

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/pengeluaran")
    Call<RespPengeluaran> pengeluaranList(@QueryMap Map<String, String> param);

    @Headers({CACHE, AGENT, SECURITY})
    @POST("api/pengeluaran")
    Call<RespPengeluaran> pengeluaranSave(@Body ParamPengeluaran login);

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/pengeluaran/delete")
    Call<RespDeletePengeluaran> delete_pengeluaran(
            @Query("id") String id
    );

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/riwayat/penjualan")
    Call<RespRiwayatPenjualan> riwayatPenjualan(@QueryMap Map<String, String> param);


    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/untung-rugi")
    Call<RespUntungRugi> showUntungRugi(
            @Query("company_id") String company_id,
            @Query("users_id") Long users_id,
            @Query("tanggal_mulai") String tanggal_mulai,
            @Query("tanggal_selesai") String tanggal_selesai
    );
    @Headers({CACHE, AGENT, SECURITY})
    @POST("api/update-profile")
    Call<RespUser> updateProfile(@Body ParamUpdateProfile login);

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/company-detail")
    Call<RespCompanyDetail> showCompanyDetail(
            @Query("company_id") String company_id,
            @Query("users_id") Long users_id
    );

    @Headers({CACHE, AGENT, SECURITY})
    @POST("api/company-update")
    Call<RespCompanyDetail> updateCompany(@Body ParamUpdateCompany login);

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/kategori-barang")
    Call<RespKategoriBarang> kategoriBarangList(@QueryMap Map<String, String> param);

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/kategori-barang/delete")
    Call<RespDeleteKategoriBarang> delete_kategori_barang(
            @Query("id") String id
    );

    @Headers({CACHE, AGENT, SECURITY})
    @POST("api/kategori-barang")
    Call<RespKategoriBarang> kategoriBarangSave(@Body ParamKategoriBarang login);


    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/satuan-barang")
    Call<RespSatuanBarang> satuanBarangList(@QueryMap Map<String, String> param);

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/satuan-barang/delete")
    Call<RespDeleteSatuanBarang> delete_satuan_barang(
            @Query("id") String id
    );

    @Headers({CACHE, AGENT, SECURITY})
    @POST("api/satuan-barang")
    Call<RespSatuanBarang> satuanBarangSave(@Body ParamSatuanBarang login);


    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/product/delete")
    Call<RespDeleteBarang> delete_barang(
            @Query("id") String id
    );

    @Headers({CACHE, AGENT, SECURITY})
    @GET("api/show-neraca")
    Call<RespNeraca> showNeraca(
            @Query("company_id") String company_id,
            @Query("users_id") Long users_id,
            @Query("tanggal_mulai") String tanggal_mulai,
            @Query("tanggal_selesai") String tanggal_selesai
    );

}
