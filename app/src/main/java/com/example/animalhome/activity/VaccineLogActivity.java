package com.example.animalhome.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalhome.R;
import com.example.animalhome.adapter.VaccineLogAdapter;
import com.example.animalhome.db.BusinessResult;
import com.example.animalhome.db.VaccineLogDB;
import com.example.animalhome.entity.VaccineLog;
import com.example.animalhome.utils.CurrentUserUtils;

import java.util.List;

public class VaccineLogActivity extends AppCompatActivity {

    private RecyclerView rvVaccine;

    private TextView tvAdd;

    private ImageView ivBack;

    private VaccineLogAdapter adapter;

    /**
     * 输入对话框
     */
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_log);
        //绑定控件
        bindView();
        //初始化数据
        initData();
        //初始化控件
        initView();
    }

    private void bindView() {
        rvVaccine = findViewById(R.id.rv_vaccine);
        tvAdd = findViewById(R.id.tv_add);
        ivBack = findViewById(R.id.iv_back);
    }

    private void initData() {
        adapter = new VaccineLogAdapter();
    }

    private void initView() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rvVaccine.setAdapter(adapter);
        rvVaccine.setLayoutManager(new LinearLayoutManager(this));
        List<VaccineLog> vaccineLogs = VaccineLogDB.listByUserId(CurrentUserUtils.getCurrentUser().getId()).getData();
        adapter.setList(vaccineLogs);
        adapter.setOnItemDeleteClickListener(new VaccineLogAdapter.OnItemDeleteClickListener() {
            @Override
            public void onDelete(VaccineLog vaccineLog) {
                BusinessResult<Void> result = VaccineLogDB.delete(vaccineLog.getId());
                Toast.makeText(VaccineLogActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if (!result.isSuccess()) {
                    return;
                }
                adapter.remove(vaccineLog);
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAlertDialog(VaccineLogActivity.this, new OnInputConfirmedListener() {
                    @Override
                    public void onInputConfirmed(String inputText) {
                        if (inputText.isEmpty()) {
                            Toast.makeText(VaccineLogActivity.this, "请输入疫苗记录", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        alertDialog.dismiss();
                        VaccineLog vaccineLog = new VaccineLog();
                        vaccineLog.setContent(inputText);
                        vaccineLog.setUserId(CurrentUserUtils.getCurrentUser().getId());
                        vaccineLog.setCreateTime(DateFormat.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis()).toString());
                        BusinessResult<Void> result = VaccineLogDB.insert(vaccineLog);
                        Toast.makeText(VaccineLogActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                        if (!result.isSuccess()) {
                            return;
                        }
                        adapter.add(vaccineLog);
                    }
                }).show();
            }
        });
    }

    /**
     * 创建输入对话框
     *
     * @param context  上下文
     * @param listener 确认监听
     * @return 对话框
     */
    public AlertDialog getAlertDialog(Context context, OnInputConfirmedListener listener) {
        if (alertDialog != null) {
            return alertDialog;
        }
        LinearLayout layout = new LinearLayout(context);
        int padding = dp2px(20);
        layout.setPadding(padding, 0, padding, 0);
        final EditText editText = new EditText(context);
        editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        editText.setHint("请输入疫苗记录");
        editText.setTextSize(14);
        layout.addView(editText);

        AlertDialog.Builder inputDialog = new AlertDialog.Builder(context);
        inputDialog.setTitle("添加");
        inputDialog.setMessage("添加疫苗记录");
        inputDialog.setView(layout);
        inputDialog.setNegativeButton("确定", null);
        inputDialog.setPositiveButton("取消", null);
        alertDialog = inputDialog.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                setClick(alertDialog, editText, listener);
            }
        });
        return alertDialog;
    }


    /**
     * 设置点击事件
     */
    private void setClick(AlertDialog alertDialog, EditText editText, OnInputConfirmedListener listener) {
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.main));
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if (listener != null) {
                    listener.onInputConfirmed(editText.getText().toString());
                }
            }
        });
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.hint));
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                alertDialog.dismiss();
            }
        });
    }

    /**
     * dp转px
     */
    public int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public interface OnInputConfirmedListener {
        void onInputConfirmed(String inputText);
    }

}
