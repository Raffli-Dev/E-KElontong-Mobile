package id.eklontong_umkm.data;

import android.app.Application;

public class DataApp extends Application {
    private static DataApp mInstance;
    private static Global global;
    private static SharedPref sharedPref;
//    private static DAO dao;

    public static synchronized DataApp get() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        sharedPref = new SharedPref(this);
//        dao = AppDatabase.getDb(this).get();
        global = new Global(this);
    }


    public void initAdNetwork() {
        // Init ADS Admob
//        AdNetworkHelper.init(this);
//        AdNetworkHelper.initActivityListener(this);
    }

    public static synchronized SharedPref pref() {
        return sharedPref;
    }

    public static synchronized Global global() {
        return global;
    }

//    public static synchronized DAO dao() {
//        return dao;
//    }
}
