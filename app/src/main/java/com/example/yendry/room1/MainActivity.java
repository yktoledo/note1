package com.example.yendry.room1;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.yendry.room1.base.ActivityBase;
import com.example.yendry.room1.util.OnFragmentInteractionListener;
import com.example.yendry.room1.view.AddFragment;
import com.example.yendry.room1.view.DetailFragment;
import com.example.yendry.room1.view.HomeFragment;

import javax.annotation.Nullable;

import static com.example.yendry.room1.util.Constants.ADD_FRAGMENT_TAG;
import static com.example.yendry.room1.util.Constants.DETAIL_FRAGMENT_TAG;
import static com.example.yendry.room1.util.Constants.HOME_FRAGMENT_TAG;

public class MainActivity extends ActivityBase implements OnFragmentInteractionListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();

        HomeFragment fragment = (HomeFragment) manager.findFragmentByTag(HOME_FRAGMENT_TAG);
        if (fragment == null) {
            fragment = HomeFragment.newInstance();
        }
        addFragment(fragment, HOME_FRAGMENT_TAG);

    }

    @Override
    public void onFragmentInteraction(String tag) {
        startFragmentByTag(tag, null);

    }

    @Override
    public void onItemClicked(String id) {

        DetailFragment fragment = DetailFragment.newInstance(id);

        addFragment(fragment, DETAIL_FRAGMENT_TAG);

    }

    public void startFragmentByTag(String tag, @Nullable View view) {
        hideKeyBoard();

        Fragment fragment = null;
         if (tag.equalsIgnoreCase(ADD_FRAGMENT_TAG)){
            fragment = manager.findFragmentByTag(ADD_FRAGMENT_TAG);
            if (fragment == null) {

                fragment = AddFragment.newInstance();
            }
        }else if (tag.equalsIgnoreCase(HOME_FRAGMENT_TAG)){
            fragment = manager.findFragmentByTag(HOME_FRAGMENT_TAG);
            if (fragment == null) {
                fragment = HomeFragment.newInstance();
            }

        }
        if (fragment==null){
            Toast.makeText(this, "No fragment", Toast.LENGTH_SHORT).show();
        }else {
            addFragment(fragment, tag);
        }
    }

    private void hideKeyBoard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
