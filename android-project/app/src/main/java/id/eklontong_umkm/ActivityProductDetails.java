package id.eklontong_umkm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityProductDetails extends AppCompatActivity {
    private static final String EXTRA_OBJECT_ID = "key.EXTRA_OBJECT_ID";
    private static final String EXTRA_FROM_NOTIF = "key.EXTRA_FROM_NOTIF";

    // activity transition
    public static void navigate(Activity activity, Long id, Boolean from_notif) {
        Intent i = navigateBase(activity, id, from_notif);
        activity.startActivity(i);
    }
    public static Intent navigateBase(Context context, Long id, Boolean from_notif) {
        Intent i = new Intent(context, ActivityProductDetails.class);
        i.putExtra(EXTRA_OBJECT_ID, id);
        i.putExtra(EXTRA_FROM_NOTIF, from_notif);
        return i;
    }
}
