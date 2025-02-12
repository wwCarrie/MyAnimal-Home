package com.example.animalhome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.animalhome.R;
import com.example.animalhome.db.BusinessResult;
import com.example.animalhome.db.UserDB;
import com.example.animalhome.entity.User;
import com.example.animalhome.utils.AppDataUtils;
import com.example.animalhome.utils.CurrentAnimalUtils;
import com.example.animalhome.utils.CurrentUserUtils;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private ImageView ivPasswordShow;

    private EditText etUsername, etPassword;

    private Button btnLogin;

    private TextView tvRegister;

    /**
     * 记住密码复选框
     */
    private CheckBox cbRemember;

    /**
     * 界面跳转回调
     */
    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        XXPermissions.with(this)
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                .permission(Permission.READ_MEDIA_IMAGES)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
                        //初始化数据
                        AppDataUtils.init();
                    }
                });


        //当注册成功返回时，接收返回的用户名和密码
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    // 注册成功
                    User user = (User) result.getData().getSerializableExtra("user");
                    etUsername.setText(user.getUsername());
                    etPassword.setText(user.getPassword());
                }
            }
        });
        ivPasswordShow = findViewById(R.id.iv_password_show);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        cbRemember = findViewById(R.id.cb_remember);

        initView();
    }

    private void initView() {
        // 获取当前用户
        User currentUser = CurrentUserUtils.getCurrentUser();
        if (currentUser.getRemember()) {
            // 如果用户选择了记住密码，则填充用户名和密码
            etUsername.setText(currentUser.getUsername());
            etPassword.setText(currentUser.getPassword());
            cbRemember.setChecked(true);
        } else {
            // 否则只填充用户名
            etUsername.setText(currentUser.getUsername());
            cbRemember.setChecked(false);
        }
        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 记住密码
                User user = CurrentUserUtils.getCurrentUser();
                user.setRemember(b);
                CurrentUserUtils.setCurrentUser(user);
            }
        });
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                BusinessResult<User> login = UserDB.login(user);
                if (login.isSuccess()) {
                    // 登录成功
                    login.getData().setRemember(cbRemember.isChecked());
                    // 保存用户信息
                    CurrentUserUtils.setCurrentUser(login.getData());
                    CurrentAnimalUtils.init(LoginActivity.this);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // 登录失败
                    Toast.makeText(LoginActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                activityResultLauncher.launch(intent);
            }
        });
    }

}
