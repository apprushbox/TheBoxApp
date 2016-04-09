package com.rushbox.android.theboxapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.rushbox.android.theboxapp.fragments.PrincipalFragment;
import com.rushbox.android.theboxapp.fragments.WelcomeFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            Fragment firstFragment = null;
            if (1 == 1) {
                // Create an instance of ExampleFragment
                firstFragment = new WelcomeFragment();
            } else {
                firstFragment = new PrincipalFragment();
            }      // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            //getSupportFragmentManager().beginTransaction()
            //.add(R.id.fragment_container, secondFragment).commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

}
