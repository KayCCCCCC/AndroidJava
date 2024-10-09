package com.example.authenform;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eUsername;
    private EditText ePassword;
    private EditText eConfirmPassword;
    public TextView tvAlreadyAccount;
    public Button btnSignUp;

    private final String REQUIRE = "Require";
    private final String PASSWORD_MISMATCH = "Passwords do not match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        eUsername = findViewById(R.id.usernameSignUp);
        ePassword = findViewById(R.id.passwordSignUp);
        eConfirmPassword = findViewById(R.id.confirmPasswordSignUp);
        tvAlreadyAccount = findViewById(R.id.alreadyAccount);
        btnSignUp = findViewById(R.id.signUpButton);

        tvAlreadyAccount.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(eUsername.getText().toString())) {
            eUsername.setError(REQUIRE);
            return false;
        }

        if (TextUtils.isEmpty(ePassword.getText().toString())) {
            ePassword.setError(REQUIRE);
            return false;
        }

        if (TextUtils.isEmpty(eConfirmPassword.getText().toString())) {
            eConfirmPassword.setError(REQUIRE);
            return false;
        }

        if (!ePassword.getText().toString().equals(eConfirmPassword.getText().toString())) {
            eConfirmPassword.setError(PASSWORD_MISMATCH);
            return false;
        }

        return true;
    }

    private void signUp() {
        if (!checkInput()) {
            return;
        }
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    private void redirectToSignIn() {
        // Chuyển hướng về màn hình đăng nhập
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.signUpButton) {
            signUp();
        } else if (id == R.id.alreadyAccount) {
            redirectToSignIn();
        }
    }
}
