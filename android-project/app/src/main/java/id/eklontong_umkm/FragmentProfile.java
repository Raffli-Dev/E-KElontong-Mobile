package id.eklontong_umkm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import id.eklontong_umkm.data.DataApp;
import id.eklontong_umkm.model.UntungRugi;
import id.eklontong_umkm.model.User;

public class FragmentProfile extends Fragment {
    private View rootView;
    private LinearLayout action_edit_profil, action_edit_usaha,action_shopping_cart,
            action_history_penjualan, action_history_pengeluaran, action_logout;


    public static FragmentProfile instance() {
        return new FragmentProfile();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        // launch instruction when first launch
        if (DataApp.pref().isFirstLaunch()) {
            DataApp.pref().setFirstLaunch(false);
        }

        initComponent();
//
//        checkNewVersion();
//        init_data();
        return rootView;
    }

    private void initComponent() {
        action_edit_profil = rootView.findViewById(R.id.action_edit_profil);
        action_edit_usaha = rootView.findViewById(R.id.action_edit_usaha);
        action_shopping_cart = rootView.findViewById(R.id.action_shopping_cart);
        action_history_penjualan = rootView.findViewById(R.id.action_history_penjualan);
        action_history_pengeluaran = rootView.findViewById(R.id.action_history_pengeluaran);
        action_logout = rootView.findViewById(R.id.action_logout);

        action_edit_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityUpdateProfile.class);
                getActivity().startActivity(intent);
            }
        });
        action_edit_usaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityCompanyUpdate.class);
                getActivity().startActivity(intent);
            }
        });
        action_shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityShoppingCart.class);
                getActivity().startActivity(intent);
            }
        });
        action_history_penjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityMain.class);
                getActivity().startActivity(intent);
            }
        });
        action_history_pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityPengeluaran.class);
                getActivity().startActivity(intent);
            }
        });
        action_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogLogout();
            }
        });
    }

    private void showDialogLogout() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.confirmation);
        dialog.setMessage(R.string.logout_confirmation_text);
        dialog.setNegativeButton(R.string.CANCEL, null);
        dialog.setPositiveButton(R.string.YES, (dialog1, i) -> {
            DataApp.global().logout();
            ((ActivityMain) getActivity()).validateSession();

            validateUserSession();
        });
        dialog.setCancelable(false);
        dialog.show();
    }
    public void validateUserSession() {
        boolean is_login = DataApp.global().isLogin();
        User user = DataApp.global().getUser();
        if (is_login) {

        } else {
            Intent i = new Intent(getActivity(), AuthActivity.class);
            startActivity(i);
            getActivity().finish();
        }
    }
}
