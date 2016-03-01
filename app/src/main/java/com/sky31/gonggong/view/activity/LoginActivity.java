package com.sky31.gonggong.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.sky31.gonggong.R;
import com.sky31.gonggong.view.fragment.LoginFragment;

public class LoginActivity extends FragmentActivity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState != null) {
            return;
        }
        context = this;

        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.login_fragment, loginFragment);
        transaction.commit();
    }
}
