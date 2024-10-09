package com.example.authenform;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eUsername;
    private EditText ePassword;
    private TextView tvNotAccountYet;
    private Button btnSignIn;

    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        eUsername = findViewById(R.id.username);
        ePassword = findViewById(R.id.password);
        tvNotAccountYet = findViewById(R.id.createAccount);
        btnSignIn = findViewById(R.id.signInButton);

        tvNotAccountYet.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
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
        return true;
    }

    private void signIn() {
        if (!checkInput()) {
            return;
        }
        // Start MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUpForm() {
        // Redirect SignUpActivity
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.signInButton) {
            signIn();
        } else if (id == R.id.createAccount) {
            signUpForm();
        }
    }
}
