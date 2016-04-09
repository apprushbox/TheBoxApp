package com.rushbox.android.theboxapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;
import com.rushbox.android.theboxapp.connections.ResponseOperation;
import com.rushbox.android.theboxapp.connections.TheBoxAppClient;
import com.rushbox.android.theboxapp.model.User;
import com.rushbox.android.theboxapp.utils.Fuentes;
import com.rushbox.android.theboxapp.utils.Utility;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtLastName, edtPhoneNumber, edtEmail,
            edtConfirmEmail, edtUserName, edtPassword, edtConfirmPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextInputLayout txtNameLy = (TextInputLayout) findViewById(R.id.name_edt_ly);
        TextInputLayout txtLastNameLy = (TextInputLayout) findViewById(R.id.lastname_edt_ly);
        TextInputLayout txtPhoneNumberLy = (TextInputLayout) findViewById(R.id.phonenumber_edt_ly);
        TextInputLayout txtEmailLy = (TextInputLayout) findViewById(R.id.email_edt_ly);
        TextInputLayout txtConfirmEmailLy = (TextInputLayout) findViewById(R.id.confirmemail_edt_ly);
        TextInputLayout txtUserNameLy = (TextInputLayout) findViewById(R.id.username_edt_ly);
        TextInputLayout txtPasswordLy = (TextInputLayout) findViewById(R.id.password_edt_ly);
        TextInputLayout txtConfirmPasswordLy = (TextInputLayout) findViewById(R.id.confirmemail_edt_ly);

        edtName = (EditText) findViewById(R.id.name_edt);
        edtLastName = (EditText) findViewById(R.id.lastname_edt);
        edtPhoneNumber = (EditText) findViewById(R.id.phonenumber_edt);
        edtEmail = (EditText) findViewById(R.id.email_edt);
        edtConfirmEmail = (EditText) findViewById(R.id.confirmemail_edt);
        edtUserName = (EditText) findViewById(R.id.username_edt);
        edtPassword = (EditText) findViewById(R.id.password_edt);
        edtConfirmPassword = (EditText) findViewById(R.id.confirmpassword_edt);

        TextView txtSignUpName = (TextView) findViewById(R.id.signup_txt);
        final Button btnSignUp = (Button) findViewById(R.id.sign_up_btn);

        txtNameLy.setTypeface(Fuentes.getFuenteRobotoLight(this));
        txtLastNameLy.setTypeface(Fuentes.getFuenteRobotoLight(this));
        txtPhoneNumberLy.setTypeface(Fuentes.getFuenteRobotoLight(this));
        txtEmailLy.setTypeface(Fuentes.getFuenteRobotoLight(this));
        txtConfirmEmailLy.setTypeface(Fuentes.getFuenteRobotoLight(this));
        txtUserNameLy.setTypeface(Fuentes.getFuenteRobotoLight(this));
        txtPasswordLy.setTypeface(Fuentes.getFuenteRobotoLight(this));
        txtConfirmPasswordLy.setTypeface(Fuentes.getFuenteRobotoLight(this));

        edtName.setTypeface(Fuentes.getFuenteRobotoLight(this));
        edtLastName.setTypeface(Fuentes.getFuenteRobotoLight(this));
        edtPhoneNumber.setTypeface(Fuentes.getFuenteRobotoLight(this));
        edtEmail.setTypeface(Fuentes.getFuenteRobotoLight(this));
        edtConfirmEmail.setTypeface(Fuentes.getFuenteRobotoLight(this));
        edtUserName.setTypeface(Fuentes.getFuenteRobotoLight(this));
        edtPassword.setTypeface(Fuentes.getFuenteRobotoLight(this));
        edtConfirmPassword.setTypeface(Fuentes.getFuenteRobotoLight(this));

        txtSignUpName.setTypeface(Fuentes.getFuenteRobotoLight(this));
        btnSignUp.setTypeface(Fuentes.getFuenteRobotoMedium(this));

        btnSignUp.setOnClickListener(this);

        edtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                TheBoxAppClient.get(TheBoxAppClient.USERSERVICE_BASE_URL + "FindUser/" + editable.toString().trim(), null, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        btnSignUp.setEnabled(false);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        if (responseString.equals("")) {
                            btnSignUp.setEnabled(true);
                        } else {
                            edtUserName.setError("This username already exists!");
                            btnSignUp.setEnabled(false);
                        }
                    }
                });
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_btn:
                if (validateFields()) {
                    registerUser();
                }
                break;
        }
    }

    private void registerUser() {
        User user = new User(edtName.getText().toString().trim(), edtLastName.getText().toString().trim(),
                edtEmail.getText().toString().trim(), edtUserName.getText().toString().trim(),
                edtPassword.getText().toString().trim(), edtPhoneNumber.getText().toString());
        String jsonUser = new Gson().toJson(user);
        progressDialog = ProgressDialog.show(RegisterActivity.this, "", "Signing Up...");
        try {
            StringEntity stringEntity = new StringEntity(jsonUser);
            stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            TheBoxAppClient.post(getApplicationContext(), TheBoxAppClient.USERSERVICE_BASE_URL + "Create", stringEntity,
                    stringEntity.getContentType().getValue(), new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            progressDialog.dismiss();
                            ResponseOperation responseOperation = Utility.JSONObjectToResponseOperation(response);
                            if (responseOperation.isResultOperation()) {
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Snackbar.make(edtEmail, "Error signing up, please try again!", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                            progressDialog.dismiss();
                            Snackbar.make(edtEmail, "Error signing up, please try again!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }


                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateFields() {
        boolean result = false;
        if (edtName.getText().toString().length() > 0) {
            if (edtLastName.getText().toString().length() > 0) {
                if (edtPhoneNumber.getText().toString().length() > 0) {
                    if (Utility.isValid(Utility.PATTERN_PHONE, edtPhoneNumber.getText().toString())) {
                        if (edtEmail.getText().toString().length() > 0) {
                            if (Utility.isValid(Utility.PATTERN_EMAIL, edtEmail.getText().toString())) {
                                if (edtConfirmEmail.getText().toString().equals(edtEmail.getText().toString())) {
                                    if (edtUserName.getText().toString().length() > 0) {
                                        if (edtPassword.getText().toString().length() > 0) {
                                            if (Utility.isValid(Utility.PATTERN_PASSWORD, edtPassword.getText().toString())) {
                                                if (edtConfirmPassword.getText().toString().equals(edtPassword.getText().toString())) {
                                                    result = true;
                                                } else {
                                                    edtConfirmPassword.setError(getResources().getString(R.string.error_password_confirmation_no_the_same));
                                                }
                                            } else {
                                                edtPassword.setError(getResources().getString(R.string.error_password_validation));
                                            }
                                        } else {
                                            edtPassword.setError(getResources().getString(R.string.error_field_required));
                                        }
                                    } else {
                                        edtUserName.setError(getResources().getString(R.string.error_field_required));
                                    }
                                } else {
                                    edtConfirmEmail.setError(getResources().getString(R.string.error_mail_confirmation_no_the_same));
                                }
                            } else {
                                edtEmail.setError(getResources().getString(R.string.error_invalid_email));
                            }
                        } else {
                            edtEmail.setError(getResources().getString(R.string.error_field_required));
                        }
                    } else {
                        edtPhoneNumber.setError(getResources().getString(R.string.error_invalid_phone));
                    }
                } else {
                    edtPhoneNumber.setError(getResources().getString(R.string.error_field_required));
                }
            } else {
                edtLastName.setError(getResources().getString(R.string.error_field_required));
            }
        } else {
            edtName.setError(getResources().getString(R.string.error_field_required));
        }
        return result;
    }

}