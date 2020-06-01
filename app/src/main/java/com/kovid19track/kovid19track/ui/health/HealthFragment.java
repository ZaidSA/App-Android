package com.kovid19track.kovid19track.ui.health;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kovid19track.kovid19track.R;
import com.kovid19track.kovid19track.ui.MainActivity;
import com.kovid19track.kovid19track.utils.Constants;

public class HealthFragment extends Fragment {

    private HealthPageAdapter adapter;
    private Context context;

    public Boolean isSympton = true;

    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.health_main, container, false);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getActivity().getColor(R.color.white));
        }

//        if (Constants.menu != null && Constants.menu.findItem(R.id.mybutton) != null) {
//            Constants.menu.findItem(R.id.mybutton).setVisible(true);
//        }
//        ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getActivity().getColor(R.color.white)));
//        ((MainActivity) getActivity()).getSupportActionBar().setShowHideAnimationEnabled(false);
//        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        ((MainActivity) getActivity()).getSupportActionBar().show();
//
//        String header_str;
//        if(isSympton){
//             header_str = getActivity().getString(R.string.symptoms_header_text);
//
//        }else {
//             header_str = getActivity().getString(R.string.diagnosis_header_text);
//        }
////        if (Constants.PUBLIC_DEMO) {
////            header_str = getActivity().getString(R.string.health_header_text_demo);
////        }
//        ((MainActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml(header_str));
//

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.symptoms_text));


        Constants.healthViewPager = view.findViewById(R.id.pager);
        adapter = new HealthPageAdapter(getChildFragmentManager(), getContext(), isSympton);

        Constants.healthViewPager.setAdapter(adapter);

        Constants.healthViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.e("time","onpagescrolled "+position);
                if (isSympton) {
                    if (Constants.menu != null && Constants.menu.findItem(R.id.mybutton) != null) {
                        Constants.menu.findItem(R.id.mybutton).setVisible(true);
                    }
                } else {
                    if (Constants.menu != null && Constants.menu.findItem(R.id.mybutton) != null) {
                        Constants.menu.findItem(R.id.mybutton).setVisible(false);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
//                Log.e("time","onpageselected "+position);
                if (isSympton) {
                    if (Constants.menu != null && Constants.menu.findItem(R.id.mybutton) != null) {
                        Constants.menu.findItem(R.id.mybutton).setVisible(true);
                    }
                } else {
                    if (Constants.menu != null && Constants.menu.findItem(R.id.mybutton) != null) {
                        Constants.menu.findItem(R.id.mybutton).setVisible(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.e("time","scrollstatechanged "+state);
            }
        });

        tabLayout.setupWithViewPager(Constants.healthViewPager);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        Constants.HealthFragment = this;
        Constants.CurrentFragment = this;

        Log.e("health", "onresume " + Constants.HealthFragmentState.toString());
    }
}
