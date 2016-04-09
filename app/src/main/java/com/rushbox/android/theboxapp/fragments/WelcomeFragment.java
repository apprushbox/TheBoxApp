package com.rushbox.android.theboxapp.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rushbox.android.theboxapp.utils.Fuentes;
import com.rushbox.android.theboxapp.LoginActivity;
import com.rushbox.android.theboxapp.R;
import com.rushbox.android.theboxapp.RegisterActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class WelcomeFragment extends Fragment implements View.OnClickListener {

    public WelcomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button btnSignIn = (Button) view.findViewById(R.id.signin_btn);
        Button btnSignUp = (Button) view.findViewById(R.id.signup_btn);
        TextView txtWelcome = (TextView) view.findViewById(R.id.welcome_txt);
        btnSignIn.setTypeface(Fuentes.getFuenteRobotoMedium(this.getContext()));
        btnSignUp.setTypeface(Fuentes.getFuenteRobotoBlack(this.getContext()));
        txtWelcome.setTypeface(Fuentes.getFuenteRobotoLight(this.getContext()));

        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.signin_btn:
                intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.signup_btn:
                intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
