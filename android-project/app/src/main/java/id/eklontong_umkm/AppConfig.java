package id.eklontong_umkm;

import androidx.multidex.BuildConfig;

import java.io.Serializable;
import java.util.Locale;

import id.eklontong_umkm.data.AdNetworkType;
import id.eklontong_umkm.utils.AppConfigExt;

public class AppConfig extends AppConfigExt implements Serializable {
    /* -------------------------------------- INSTRUCTION : ----------------------------------------
     * This is config file used for this app, you can configure Ads, Notification, and General data from this file
     * some values are not explained and can be understood easily according to the variable name
     * value can change remotely (optional), please read documentation to follow instruction
     *
     * variable with UPPERCASE name will NOT fetch / replace with remote config
     * variable with LOWERCASE name will fetch / replace with remote config
     * See video Remote Config tutorial https://www.youtube.com/watch?v=tOKXwOTqOzA
     ---------------------------------------------------------------------------------------------- */
    public static final boolean ENABLE_GDPR = true;

    // force rtl layout direction
    public static final boolean RTL_LAYOUT = false;

    // notification topic for FCM
    public static final String NOTIFICATION_TOPIC = "ALL-DEVICE";

    // flag for enable/disable all ads
    private static final boolean ADS_ENABLE = true;

    // flag for display ads (change true & false ant the end only )

    public static final boolean ADS_SPLASH_OPEN_APP = ADS_ENABLE && true;
    public static final boolean ADS_GLOBAL_OPEN_APP = ADS_ENABLE && true;

    public static final boolean ADS_MAIN_BANNER = ADS_ENABLE && true;
    public static final boolean ADS_MAIN_INTERS = ADS_ENABLE && true;

    public static final boolean ADS_DETAILS_LISTING_BANNER = ADS_ENABLE && true;
    public static final boolean ADS_DETAILS_LISTING_NATIVE = ADS_ENABLE && true;
    public static final boolean ADS_DETAILS_NEWS_NATIVE = ADS_ENABLE && true;

    public static final boolean ADS_SEARCH_BANNER = ADS_ENABLE && true;
    public static final boolean ADS_SEARCH_INTERS = ADS_ENABLE && true;
    public static final boolean ADS_SEARCH_NATIVE = ADS_ENABLE && true;

    public static final boolean ADS_COMMENT_BANNER = ADS_ENABLE && true;

    public static final boolean ADS_SETTINGS_BANNER = ADS_ENABLE && true;

    /* set true for fetch config with firebase remote config, */
    public static final boolean USE_REMOTE_CONFIG = false;


    /* config for General Application */
    public static class General {

        /* Edit WEB_URL with your url. Make sure you have backslash('/') in the end url */
        public String admin_panel_url = "http://demo.dream-space.web.id/markeet_panel/";

        /* [ IMPORTANT ] be careful when edit this security code */
        /* This string must be same with security code at Server, if its different android unable to submit order */
        public String security_code = "8V06LupAaMBLtQqyqTxmcN42nn27FlejvaoSM3zXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

        // tinting category icon
        public boolean tint_category_icon = true;

        /* Locale.US        -> 2,365.12
         * Locale.GERMANY   -> 2.365,12 */
        public Locale price_local_format = Locale.US;

        /* true     -> 2.365,12
         * false    -> 2.365 */
        public boolean price_with_decimal = true;

        /* true     -> 2.365,12 USD
         * false    -> USD 2.365 */
        public boolean price_currency_in_end = true;

        // this limit value used for give pagination (request and display) to decrease payload
        public int news_per_request = 10;
        public int product_per_request = 6;
        public int notification_page = 20;
        public int wishlist_page = 20;
        public int order_history_page = 10;

        /* 3 links below will use on setting page */
        public String privacy_policy_url = "https://dream-space.web.id/privacy-policy";
        public String contact_us_url = "https://dream-space.web.id/contact-us";
        public String update_app_url = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;
    }

    /* config for Ad Network */
    public static class Ads {

        /* enable disable ads */
        public boolean ad_enable = true;

        /* MULTI Ad network selection,
         * Fill this array to enable ad backup flow, left this empty to use single ad_network above
         * app will try show sequentially from this array
         * example flow ADMOB > FAN > IRONSOURCE
         *
         * OPTION :
         * ADMOB,MANAGER, FAN, UNITY, IRONSOURCE, APPLOVIN, APPLOVIN_MAX, APPLOVIN_DISCOVERY,
         * STARTAPP, WORTISE, FAN_BIDDING_ADMOB, FAN_BIDDING_AD_MANAGER, FAN_BIDDING_APPLOVIN_MAX,
         * FAN_BIDDING_IRONSOURCE
         * */
        public AdNetworkType[] ad_networks = {
                AdNetworkType.ADMOB,
                AdNetworkType.FAN,
                AdNetworkType.IRONSOURCE,
        };

        /* ad backup flow retry attempt cycle */
        public Integer retry_from_start_max = 2;

        public boolean ad_enable_gdpr = true;

        /* disable enable ads each page */
        public boolean ad_main_banner = true;
        public boolean ad_main_interstitial = true;
        public boolean ad_news_info_details = true;
        public boolean ad_product_details = true;

        public boolean ad_splash_open_app = true;
        public boolean ad_global_open_app = false;

        /* when ad networks not supported open app format, it will replace with interstitial format
         * for placement after plash screen only */
        public boolean ad_replace_unsupported_open_app_with_interstitial_on_splash = true;

        /* maximum load time in second for open app ads */
        public Integer limit_time_open_app_loading = 10;

        /* show interstitial after several action, this value for action counter */
        public Integer ad_inters_interval = 5;

        /* ad unit for ADMOB */
        public String ad_admob_publisher_id = "pub-3940256099942544";
        public String ad_admob_banner_unit_id = "ca-app-pub-3940256099942544/6300978111";
        public String ad_admob_interstitial_unit_id = "ca-app-pub-3940256099942544/1033173712";
        public String ad_admob_rewarded_unit_id = "ca-app-pub-3940256099942544/5224354917";
        public String ad_admob_open_app_unit_id = "ca-app-pub-3940256099942544/9257395921";

        /* ad unit for Google Ad Manager */
        public String ad_manager_banner_unit_id = "/6499/example/banner";
        public String ad_manager_interstitial_unit_id = "/6499/example/interstitial";
        public String ad_manager_rewarded_unit_id = "/6499/example/rewarded";
        public String ad_manager_open_app_unit_id = "/6499/example/app-open";

        /* ad unit for FAN */
        public String ad_fan_banner_unit_id = "YOUR_PLACEMENT_ID";
        public String ad_fan_interstitial_unit_id = "YOUR_PLACEMENT_ID";
        public String ad_fan_rewarded_unit_id = "VID_HD_9_16_39S_APP_INSTALL";

        /* ad unit for IRON SOURCE */
        public String ad_ironsource_app_key = "170112cfd";
        public String ad_ironsource_banner_unit_id = "DefaultBanner";
        public String ad_ironsource_rewarded_unit_id = "DefaultRewardedVideo";
        public String ad_ironsource_interstitial_unit_id = "DefaultInterstitial";

        /* ad unit for UNITY */
        public String ad_unity_game_id = "4988568";
        public String ad_unity_banner_unit_id = "Banner_Android";
        public String ad_unity_rewarded_unit_id = "Rewarded_Android";
        public String ad_unity_interstitial_unit_id = "Interstitial_Android";

        /* ad unit for APPLOVIN MAX */
        public String ad_applovin_banner_unit_id = "a3a3a5b44c763304";
        public String ad_applovin_interstitial_unit_id = "35f9c01af124fcb9";
        public String ad_applovin_rewarded_unit_id = "21dba76a66f7c9fe";
        public String ad_applovin_open_app_unit_id = "7c3fcecd43d3f90c";

        /* ad unit for APPLOVIN DISCOVERY */
        public String ad_applovin_banner_zone_id = "df40a31072feccab";
        public String ad_applovin_interstitial_zone_id = "d0eea040d4bd561e";
        public String ad_applovin_rewarded_zone_id = "5d799aeefef733a1";

        /* ad unit for STARTAPP */
        public String ad_startapp_app_id = "0";

        /* ad unit for WORTISE */
        public String ad_wortise_app_id = "test-app-id";
        public String ad_wortise_banner_unit_id = "test-banner";
        public String ad_wortise_interstitial_unit_id = "test-interstitial";
        public String ad_wortise_rewarded_unit_id = "test-rewarded";
        public String ad_wortise_open_app_unit_id = "test-app-open";
    }
}
