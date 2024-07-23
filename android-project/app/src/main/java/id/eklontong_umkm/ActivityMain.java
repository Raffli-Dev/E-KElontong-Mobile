package id.eklontong_umkm;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.data.DatabaseHandler;
import id.eklontong_umkm.data.SharedPref;
import id.eklontong_umkm.model.User;
import id.eklontong_umkm.utils.Tools;

public class ActivityMain extends AppCompatActivity {

    private FragmentHome fragmentHome;
    private FragmentRiwayatPenjualan fragmentRiwayatPenjualan;
    private FragmentUntungRugiV2 fragmentUntungRugi;
    private FragmentProfile fragmentProfile;
    private FragmentNeraca fragmentNeraca;

    private Fragment selectedFragment;
    private FragmentManager fm;

    private SharedPref sharedPref;
    private Dialog dialog_failed = null;
    public boolean category_load = false, news_load = false;

    static ActivityMain activityMain;

    private BottomNavigationView navigation;
    private AppBarLayout appbar_layout;
    private LinearLayout lyt_bar_content, lyt_bar;
    private DrawerLayout drawer_layout;
    private TextView txt_title;
    public static ActivityMain getInstance() {
        return activityMain;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        validateUserSession();
        // launch instruction when first launch
        if (DataApp.pref().isFirstLaunch()) {
            DataApp.pref().setFirstLaunch(false);
        }

        initToolbar();
        initComponent();


        checkNewVersion();;
        Tools.RTLMode(getWindow());

        // set default fragment
        selectedFragment = fragmentHome;
        displayFragment(R.id.nav_home, getString(R.string.menu_home));

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        Tools.setSystemBarColor(this, R.color.primary_register);
    }

    private void initComponent() {
        fm = getSupportFragmentManager();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        appbar_layout = (AppBarLayout) findViewById(R.id.appbar_layout);
        lyt_bar_content = (LinearLayout) findViewById(R.id.lyt_bar_content);
        lyt_bar = (LinearLayout) findViewById(R.id.lyt_bar);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        txt_title = (TextView) findViewById(R.id.title);

        fragmentHome = FragmentHome.instance();
        fragmentRiwayatPenjualan = FragmentRiwayatPenjualan.instance();
        fragmentUntungRugi = FragmentUntungRugiV2.instance();
        fragmentProfile = FragmentProfile.instance();
        fragmentNeraca = FragmentNeraca.instance();

        fm.beginTransaction().add(R.id.frame_layout, fragmentHome, getString(R.string.menu_home)).commit();
        fm.beginTransaction().add(R.id.frame_layout, fragmentRiwayatPenjualan, getString(R.string.menu_riwayat_penjualan)).hide(fragmentRiwayatPenjualan).commit();
        fm.beginTransaction().add(R.id.frame_layout, fragmentUntungRugi, getString(R.string.menu_untung_rugi)).hide(fragmentUntungRugi).commit();
        fm.beginTransaction().add(R.id.frame_layout, fragmentProfile, getString(R.string.menu_profile)).hide(fragmentProfile).commit();
        fm.beginTransaction().add(R.id.frame_layout, fragmentNeraca, getString(R.string.menu_neraca)).hide(fragmentNeraca).commit();

        navigation.setOnItemSelectedListener(item -> {
            displayFragment(item.getItemId(), item.getTitle().toString());
            return true;
        });

        // listener for hide toolbar and bottom nav
        appbar_layout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            int offset = Math.abs(verticalOffset), range = appBarLayout.getTotalScrollRange();
            int heightMax = lyt_bar_content.getHeight();
            if (range <= 0) return;
            int translationY = (offset * heightMax) / range;
            lyt_bar.setTranslationY(translationY);
        });


    }
    public void displayFragment(int id, String title) {
        if (id == R.id.nav_home) {
            fm.beginTransaction().hide(selectedFragment).show(fragmentHome).commit();
            selectedFragment = fragmentHome;
        } else if (id == R.id.nav_categories) {
            fm.beginTransaction().hide(selectedFragment).show(fragmentRiwayatPenjualan).commit();
            selectedFragment = fragmentRiwayatPenjualan;
        } else if (id == R.id.nav_news_info) {
            fm.beginTransaction().hide(selectedFragment).show(fragmentUntungRugi).commit();
            selectedFragment = fragmentUntungRugi;
        } else if (id == R.id.nav_profile) {
            fm.beginTransaction().hide(selectedFragment).show(fragmentProfile).commit();
            selectedFragment = fragmentProfile;
        } else if (id == R.id.nav_neraca) {
            fm.beginTransaction().hide(selectedFragment).show(fragmentNeraca).commit();
            selectedFragment = fragmentNeraca;
        }
        txt_title.setText(title);
        navigation.getMenu().findItem(id).setChecked(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        validateSession();

    }

//    static boolean active = false;

    @Override
    public void onStart() {
        super.onStart();
//        active = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        active = false;
    }

    public void checkNewVersion() {
        if (DataApp.pref().getAppStatus().equals("SUGGEST-UPDATE")) {
            showNewVersion();
        }
    }
    private void showNewVersion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_info);
        builder.setMessage(R.string.msg_app_new_version);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.UPDATE, (dialog, which) -> {
            dialog.dismiss();
            Tools.rateAction(ActivityMain.this);
            finish();
        });
        builder.setNegativeButton(R.string.Skip, null);
        builder.show();
    }
    public void validateSession() {
        if (DataApp.global().isLogin()) {
            User user = DataApp.global().getUser();
        } else {
            validateUserSession();
        }

    }
    public void validateUserSession() {
        boolean is_login = DataApp.global().isLogin();
        User user = DataApp.global().getUser();
        if (is_login) {

        } else {
            Intent i = new Intent(ActivityMain.this, AuthActivity.class);
            startActivity(i);
            finish();
        }
    }
}
