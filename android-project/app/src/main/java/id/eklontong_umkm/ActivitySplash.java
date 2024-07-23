package id.eklontong_umkm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import id.eklontong_umkm.connection.Request;
import id.eklontong_umkm.connection.RequestListener;
import id.eklontong_umkm.connection.response.RespInfo;
import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.utils.Tools;

public class ActivitySplash extends AppCompatActivity {
    // activity transition
    public static void navigate(Activity activity) {
        Intent i = new Intent(activity, ActivitySplash.class);
        activity.startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Tools.RTLMode(getWindow());
    }

    @Override
    protected void onResume() {
        super.onResume();
        startProcess();
    }

    private void startProcess() {
        if (!Tools.isConnect(this)) {
            dialogFailed(getString(R.string.no_internet_connection));
        } else {
            requestInfo();
        }
    }

    private void requestInfo() {
        new Request(this).info(new RequestListener<RespInfo>() {
            @Override
            public void onSuccess(RespInfo resp) {
                DataApp.global().setServerConfig(resp.config);
                DataApp.get().initAdNetwork();
                checkAppVersion(resp);
            }

            @Override
            public void onFailed(String messages) {
                dialogFailed(getString(R.string.unable_connect_server));
            }
        });
    }

    private void checkAppVersion(RespInfo resp) {
        DataApp.pref().setAppStatus(resp.app_status);
        if (resp.app_status.equals("ACTIVE") || resp.app_status.equals("SUGGEST-UPDATE")) {
            startActivityMainDelay();
        } else if (resp.app_status.equals("FORCE-UPDATE")) {
            dialogOutDate();
        }
    }

    public void dialogOutDate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_info);
        builder.setMessage(R.string.msg_app_out_date);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.UPDATE, (dialog, which) -> {
            dialog.dismiss();
            Tools.rateAction(ActivitySplash.this);
            finish();
        });
        builder.setNegativeButton(R.string.CLOSE, (dialog, which) -> {
            dialog.dismiss();
            finish();
        });
        builder.show();
    }

    public void dialogFailed(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.failed);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.RETRY, (dialog, which) -> {
            dialog.dismiss();
            retryOpenApplication();
        });
        builder.show();
    }

    // make a delay to start next activity
    private void retryOpenApplication() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> startProcess(), 1000);
    }

    private void startActivityMainDelay() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> startActivityOrShowOpenAppAds(), 500);
    }

    private void startActivityOrShowOpenAppAds() {
        Intent i = new Intent(ActivitySplash.this, ActivityMain.class);
        startActivity(i);
        finish();
    }
}
