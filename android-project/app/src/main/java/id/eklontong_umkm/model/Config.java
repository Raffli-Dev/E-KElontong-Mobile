package id.eklontong_umkm.model;

import java.io.Serializable;

public class Config implements Serializable {
    public String general_contact_us;
    public String general_more_app_url;

    public String notif_one_signal_appid;

    public boolean ad_enable;
    public String ad_network;
    public String ad_networks;
    public int ad_inters_interval;

    public String ad_admob_banner_unit_id;
    public String ad_admob_interstitial_unit_id;
    public String ad_admob_native_unit_id;
    public String ad_admob_open_app_unit_id;
    public String ad_admob_publisher_id;

    public String ad_fan_banner_unit_id;
    public String ad_fan_interstitial_unit_id;
    public String ad_fan_native_unit_id;

    public String ad_ironsource_app_key;
    public String ad_ironsource_banner_unit_id;
    public String ad_ironsource_interstitial_unit_id;

    public String ad_applovin_banner_unit_id;
    public String ad_applovin_interstitial_unit_id;
    public String ad_applovin_mrec_unit_id;

    public String ad_unity_banner_unit_id;
    public String ad_unity_game_id;
    public String ad_unity_interstitial_unit_id;
}
