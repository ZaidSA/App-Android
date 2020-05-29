package com.kovid19track.kovid19track.ui.onboarding;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kovid19track.kovid19track.R;

import com.kovid19track.kovid19track.preferences.AppPreferencesHelper;
import com.kovid19track.kovid19track.ui.MainActivity;
import com.kovid19track.kovid19track.ui.settings.MoreRecyclerViewAdapter;
import com.kovid19track.kovid19track.ui.settings.PermissionsRecyclerViewAdapter;
import com.kovid19track.kovid19track.utils.Constants;
import com.kovid19track.kovid19track.utils.Utils;

public class PermissionFragment extends Fragment {

    View view;

    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.onboarding_permissions, container, false);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getActivity().getColor(R.color.white));
        }

        ((OnboardingActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getActivity().getColor(R.color.white)));
        ((OnboardingActivity) getActivity()).getSupportActionBar().setShowHideAnimationEnabled(false);
        ((OnboardingActivity) getActivity()).getSupportActionBar().show();
        ((OnboardingActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((OnboardingActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml(getActivity().getString(R.string.permissions_header_text)));
        ((OnboardingActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(getActivity().getDrawable(R.drawable.ic_arrow_back_black_24dp));

        RecyclerView rview3 = view.findViewById(R.id.recyclerViewPerms);
        PermissionsRecyclerViewAdapter adapter3 = new PermissionsRecyclerViewAdapter(getContext(),getActivity(), view);
        rview3.setAdapter(adapter3);
        rview3.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerView rview4 = view.findViewById(R.id.recyclerViewPerms2);
        MoreRecyclerViewAdapter adapter4 = new MoreRecyclerViewAdapter(getContext(),getActivity(), 0);
        rview4.setAdapter(adapter4);
        rview4.setLayoutManager(new LinearLayoutManager(getActivity()));

        Button nextButton = (Button) view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        TextView privacy = view.findViewById(R.id.privacyText);
        Utils.linkify(privacy,getString(R.string.privacyLink1));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("state","permission fragment on resume");
        Constants.CurrentFragment = this;
        Constants.PermissionsFragment = this;

        Log.e("perms","should update switch states? "+Constants.SuppressSwitchStateCheck);
        if (Constants.SuppressSwitchStateCheck) {
            Constants.SuppressSwitchStateCheck = false;
        }
        else {
            Utils.updateSwitchStates(getActivity());
        }
    }
}
