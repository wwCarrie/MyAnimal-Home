package com.example.animalhome.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.animalhome.R;
import com.example.animalhome.db.BusinessResult;
import com.example.animalhome.db.UserDB;
import com.example.animalhome.entity.User;

public class RegisterActivity extends Activity {

    private ImageView ivPasswordShow, ivPasswordShowAgain;

    private EditText etUsername, etPassword, etPasswordAgain;

    private ImageView ivBack;

    private Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ivPasswordShow = findViewById(R.id.iv_password_show);
        ivPasswordShowAgain = findViewById(R.id.iv_password_show_again);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etPasswordAgain = findViewById(R.id.et_password_again);
        ivBack = findViewById(R.id.iv_back);
        btnRegister = findViewById(R.id.btn_register);

        initView();

        initListener();
    }

    private void initView() {
        ivPasswordShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransformationMethod method = etPassword.getTransformationMethod();
                if (method.equals(HideReturnsTransformationMethod.getInstance())) {
                    ivPasswordShow.setImageResource(R.drawable.ic_password_close);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    ivPasswordShow.setImageResource(R.drawable.ic_password_open);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        ivPasswordShowAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransformationMethod method = etPasswordAgain.getTransformationMethod();
                if (method.equals(HideReturnsTransformationMethod.getInstance())) {
                    ivPasswordShowAgain.setImageResource(R.drawable.ic_password_close);
                    etPasswordAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    ivPasswordShowAgain.setImageResource(R.drawable.ic_password_open);
                    etPasswordAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String passwordAgain = etPasswordAgain.getText().toString();
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);

                BusinessResult<User> result = UserDB.register(user, passwordAgain);
                Toast.makeText(RegisterActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if (result.isSuccess()) {
                    // 注册成功，返回登录界面，并传递用户信息
                    Intent intent = new Intent();
                    intent.putExtra("user", user);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

}
