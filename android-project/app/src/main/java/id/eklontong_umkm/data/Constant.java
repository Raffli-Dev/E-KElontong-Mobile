package id.eklontong_umkm.data;

public class Constant {
    /**
     * -------------------- EDIT THIS WITH YOURS -------------------------------------------------
     */

    // Edit WEB_URL with your url. Make sure you have backslash('/') in the end url
    public static String BASE_URL = "https://admintiket.kahyang.site/eklontong-unmer/api/public/";
//    public static String BASE_URL = "http://192.168.110.246:8080/";

    /* [ IMPORTANT ] be careful when edit this security code */
    /* This string must be same with security code at Server, if its different android unable to submit data */
    public static final String SECURITY_CODE = "eShw8MN1uLQs0eCFSLbCW17ran8gE01WELn5rT1IHP2TWEHwfGdw2K4DfdjvUOc2q0vBJFWcupzB6zHWMlv4yX2ntPqiOq1nCrfX";

    public static Integer SAVED_PAGE = 20;
    public static Integer NEWS_EVENT_PAGE = 20;
    public static Integer LISTING_PAGE = 20;
    public static Integer REVIEW_PAGE = 20;
    public static Integer NOTIFICATION_PAGE = 20;


    // retry load image notification
    public static int LOAD_IMAGE_NOTIF_RETRY = 4;
}
