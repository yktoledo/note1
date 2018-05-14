package com.example.yendry.room1.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yendry.room1.R;

import static com.example.yendry.room1.util.Constants.ADD_FRAGMENT_TAG;
import static com.example.yendry.room1.util.Constants.HOME_FRAGMENT_TAG;

/**
 * Created by yendry on 5/11/18.
 */

public abstract class ActivityBase extends AppCompatActivity{
    private static final String TAG = ActivityBase.class.getSimpleName();
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportFragmentManager = getSupportFragmentManager();
    }

    public void addFragment(Fragment fragment, String tag) {

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment, tag);

        if (tag.equalsIgnoreCase(HOME_FRAGMENT_TAG)){
            supportFragmentManager.popBackStack(HOME_FRAGMENT_TAG, 0);
        }
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (supportFragmentManager.getBackStackEntryCount()>0) {
            if (supportFragmentManager.getBackStackEntryAt(supportFragmentManager.getBackStackEntryCount() - 1).getName().equalsIgnoreCase(HOME_FRAGMENT_TAG)){
                finish();
            }else {
                supportFragmentManager.popBackStack();
            }
        }
    }
}
