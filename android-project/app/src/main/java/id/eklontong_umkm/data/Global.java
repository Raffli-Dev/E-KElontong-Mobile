package id.eklontong_umkm.data;

import android.content.Context;


import java.util.HashMap;
import java.util.Map;

import id.eklontong_umkm.model.Config;
import id.eklontong_umkm.model.User;

public class Global {
    private Context context;
//    private EntityCity city = null;
    private String tempEmail = "";
    private User user;
//    private FirebaseAnalytics firebaseAnalytics;
//    private GoogleSignInClient googleSignInClient;
    private Map<String, String> order_by;
//    private NotificationEntity notification;

    public Global(Context context) {
        this.context = context;
        this.user = DataApp.pref().getUser();

        initConfig();
        initFirebase();
        initGoogleSignIn();
        initOrderBy();

        // Init Firebase Notification and One Signal
//        NotificationHelper.init(context);
    }

    private void initGoogleSignIn() {
        // Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();

//        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }


    private void initConfig() {
        Config config = DataApp.pref().getConfig();
        if (config != null) setServerConfig(config);
    }

    private void initFirebase() {
        // Obtain the Firebase Analytics.
//        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    private void initOrderBy() {
        order_by = new HashMap<>();
        order_by.put("new_old", "created_at|desc");
        order_by.put("old_new", "created_at|asc");
        order_by.put("high_rating", "rating_avg|desc");
        order_by.put("low_rating", "rating_avg|asc");
        order_by.put("most_review", "review_count|desc");
        order_by.put("less_review", "review_count|asc");
    }

    public String getOrderBy(String key) {
        return order_by.get(key);
    }

    public void setServerConfig(Config config) {
        DataApp.pref().saveConfig(config);
    }

    public Config getServerConfig() {
        return DataApp.pref().getConfig();
    }

    // user data
    public User getUser() {
        if (user == null) user = DataApp.pref().getUser();
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        DataApp.pref().saveUser(user);
    }

    public void logout() {
        user = null;
        DataApp.pref().clearUser();
//        if (googleSignInClient != null) {
//            googleSignInClient.signOut();
//        }
    }

    public boolean isLogin() {
        return user != null;
    }

//    public EntityCity getCity() {
//        city = DataApp.dao().getCity(DataApp.pref().getCityId());
//        if (city == null) {
//            city = DataApp.dao().getDefaultCity();
//        }
//        return city;
//    }

//    public void setCity(EntityCity city) {
//        this.city = city;
//        DataApp.pref().setCityId(city.getId());
//    }

    public String getTempEmail() {
        return tempEmail;
    }

    public void setTempEmail(String tempEmail) {
        this.tempEmail = tempEmail;
    }

//    public GoogleSignInClient getGoogleSignInClient() {
//        return googleSignInClient;
//    }

//    public NotificationEntity getNotification() {
//        return notification;
//    }
//
//    public void setNotification(NotificationEntity notification) {
//        this.notification = notification;
//    }

}
