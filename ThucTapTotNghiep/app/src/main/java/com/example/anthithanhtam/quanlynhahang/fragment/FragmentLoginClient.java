package com.example.anthithanhtam.quanlynhahang.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ClientActivity;
import com.example.anthithanhtam.quanlynhahang.activity.SwichBeginAppActivity;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.model.Employee;
import com.example.anthithanhtam.quanlynhahang.sharepreferences.ShareConstand;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLoginClient extends BaseFragment {

    @BindView(R.id.edtUser)
    TextInputEditText edtUser;
    @BindView(R.id.edtPass)
    TextInputEditText edtPass;
    @BindView(R.id.ckNhoMatKhau)
    CheckBox ckNhoMatKhau;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    Unbinder unbinder;
    private String MY_PREFS_NAME = "oderfood";
    private SharedPreferences.Editor editor;
    private SwichBeginAppActivity swichBeginAppActivity;


    @Override
    protected void initView() {
        this.swichBeginAppActivity = (SwichBeginAppActivity) mActivity;
        SharedPreferences prefs = mActivity.getSharedPreferences(MY_PREFS_NAME, mActivity.MODE_PRIVATE);
        edtUser.setText(prefs.getString("Taikhoan", ""));
        edtPass.setText(prefs.getString("Matkhau", ""));
        SharedPreferences sharedPreferences1 = mActivity.getSharedPreferences(MY_PREFS_NAME, mActivity.MODE_PRIVATE);
        boolean checkRadio = sharedPreferences1.getBoolean("checked", false);
        if (checkRadio) {
            ckNhoMatKhau.setChecked(true);
        } else {
            ckNhoMatKhau.setChecked(false);
        }
        addEvent();
    }

    @Override
    protected int layoutID() {
        return R.layout.layout_login_client_fragment;
    }

    private void addEvent() {
        ckNhoMatKhau.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (ckNhoMatKhau.isChecked()) {
                    Toast.makeText(mActivity, getString(R.string.remember_pass), Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences1 = mActivity.getSharedPreferences(MY_PREFS_NAME, mActivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putBoolean("checked", true);
                    editor.commit();
                } else {
                    Toast.makeText(mActivity, getString(R.string.no_remember_pass), Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences1 = mActivity.getSharedPreferences(MY_PREFS_NAME, mActivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putBoolean("checked", false);
                    editor.commit();
                }
            }
        });

        ckNhoMatKhau.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor = mActivity.getSharedPreferences(MY_PREFS_NAME, mActivity.MODE_PRIVATE).edit();
                    editor.putString("Taikhoan", edtUser.getText() + "");
                    editor.putString("Matkhau", edtPass.getText() + "");
                    editor.apply();
                } else {
                    editor = mActivity.getSharedPreferences(MY_PREFS_NAME, mActivity.MODE_PRIVATE).edit();
                    editor.putString("Taikhoan", edtUser.getText() + "");
                    editor.putString("Matkhau", "");
                    editor.apply();

                }

            }
        });
    }


    public void setData(String userName, String pass) {
        edtUser.setText(userName);
        edtPass.setText(pass);
    }


    @OnClick({R.id.btnLogin, R.id.btnRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                final String user = edtUser.getText().toString().trim();
                final String pass = edtPass.getText().toString().trim();
                if (user.isEmpty()) {
                    edtUser.setError(getString(R.string.no_enter_acount));
                } else if (pass.isEmpty()) {
                    edtPass.setError(getString(R.string.no_enter_pass));
                } else {
                    if (ckNhoMatKhau.isChecked() == true) {
                        editor = mActivity.getSharedPreferences(MY_PREFS_NAME, mActivity.MODE_PRIVATE).edit();
                        editor.putString("Taikhoan", edtUser.getText() + "");
                        editor.putString("Matkhau", edtPass.getText() + "");
                        editor.apply();
                    } else {
                        editor = mActivity.getSharedPreferences(MY_PREFS_NAME, mActivity.MODE_PRIVATE).edit();
                        editor.putString("Taikhoan", edtUser.getText() + "");
                        editor.putString("Matkhau", "");
                        editor.apply();
                    }
                    swichBeginAppActivity.getProgressBarSwitchBegin().setVisibility(View.VISIBLE);
                    soService.checkLogin(user, pass).enqueue(new Callback<List<Employee>>() {
                        @Override
                        public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                            if (response.body().size() == 0) {
                                Toast.makeText(mActivity, getString(R.string.pass_false), Toast.LENGTH_SHORT).show();
                            } else {
                                ShareConstand.setEmployee(mActivity, response.body().get(0));
                                //Toast.makeText(context, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                                Utils.toastMessage(swichBeginAppActivity, getString(R.string.success));
                                Intent i = new Intent(mActivity, ClientActivity.class);
                                startActivity(i);
                            }
                            swichBeginAppActivity.getProgressBarSwitchBegin().setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<List<Employee>> call, Throwable t) {
                            call.clone().enqueue(this);
                        }
                    });
                }
                break;
            case R.id.btnRegister:
                //swichBeginAppActivity.switchFragment(swichBeginAppActivity.getFragmentRegister());
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }
}
