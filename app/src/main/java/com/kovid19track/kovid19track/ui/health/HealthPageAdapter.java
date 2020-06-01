package com.kovid19track.kovid19track.ui.health;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kovid19track.kovid19track.R;
import com.kovid19track.kovid19track.utils.Constants;

public class HealthPageAdapter extends FragmentPagerAdapter {
    Context cxt;
    Boolean isSympton;

    public HealthPageAdapter(@NonNull FragmentManager fm, Context cxt, Boolean isSympton) {
        super(fm);
        this.cxt = cxt;
        this.isSympton = isSympton;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (isSympton) {
            return Constants.SymptomTrackerFragment;
        } else  {
            return Constants.DiagnosisFragment;
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (isSympton) {
            return cxt.getString(R.string.symptoms_text);
        } else if (position == 1) {
            return cxt.getString(R.string.diagnosis_text);
        }
        return "";
    }
}
