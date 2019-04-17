package com.example.anthithanhtam.quanlynhahang.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.SwichBeginAppActivity;
import com.example.anthithanhtam.quanlynhahang.constant.Constant;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.model.Message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentRegister extends BaseFragment {
    @BindView(R.id.edtUserUser)
    TextInputEditText edtUserUser;
    @BindView(R.id.edtPassWord)
    TextInputEditText edtPassWord;
    @BindView(R.id.edtUserName)
    TextInputEditText edtUserName;
    @BindView(R.id.edtAddress)
    TextInputEditText edtAddress;
    @BindView(R.id.edtPhone)
    TextInputEditText edtPhone;
    @BindView(R.id.edtEmail)
    TextInputEditText edtEmail;
    @BindView(R.id.imgAnhDaiDien)
    ImageView imgAnhDaiDien;
    @BindView(R.id.edtTitle)
    TextInputEditText edtTitle;

    private SwichBeginAppActivity swichBeginAppActivity;
    private String nameCheck = "";
    private final int REQUEST_CHOOSE_PICTURE = 321;

    private String imageCode;


    private void xacNhanDangKi() {
        final String userUserName = edtUserUser.getText().toString();
        final String userPass = edtPassWord.getText().toString();
        final String userName = edtUserName.getText().toString();
        final String userAddress = edtAddress.getText().toString();
        final String userPhone = edtPhone.getText().toString();

        final String userEmail = edtEmail.getText().toString();
        String userTitle = edtTitle.getText().toString();

        if (userUserName.isEmpty() && userPass.isEmpty() && userName.isEmpty() && userAddress.isEmpty() && userPhone.isEmpty() && userEmail.isEmpty() && userTitle.isEmpty()) {
            edtUserUser.setError(getString(R.string.no_enter_infomation));
            edtPassWord.setError(getString(R.string.no_enter_infomation));
            edtUserName.setError(getString(R.string.no_enter_infomation));
            edtAddress.setError(getString(R.string.no_enter_infomation));
            edtPhone.setError(getString(R.string.no_enter_infomation));
            edtEmail.setError(getString(R.string.no_enter_infomation));
            edtTitle.setError(getString(R.string.no_enter_infomation));
        } else {
            if (!imageCode.isEmpty()) {
                Date date = new Date();
                String[] chuoi = {"t", "e", "a", "g", "k", "l", "c", "m"};
                StringBuilder builder = new StringBuilder();
                Random random = new Random();
                for (int i = 0; i < 7; i++) {
                    builder.append(chuoi[random.nextInt(7)]);
                }

                final String name = String.valueOf(date.getTime()) + builder + "imagesges.jpg";
                if (nameCheck.isEmpty()) {
                    swichBeginAppActivity.getProgressBarSwitchBegin().setVisibility(View.VISIBLE);
                    soService.uploadImage(imageCode, name).enqueue(new Callback<Message>() {
                        @Override
                        public void onResponse(Call<Message> call, Response<Message> response) {
                            swichBeginAppActivity.getProgressBarSwitchBegin().setVisibility(View.GONE);
                            if (response.body() != null) {
                                nameCheck = name;
                                if (response.body().getMessage().equals("Success")) {
                                    insertEmployee(userUserName, userPass, userName, name, userPhone, userAddress, userEmail);
                                } else {
                                    Toast.makeText(mActivity, getString(R.string.error_upload_image), Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<Message> call, Throwable t) {
                            call.clone();
                        }
                    });
                } else {
                    insertEmployee(userUserName, userPass, userName, nameCheck, userPhone, userAddress, userEmail);
                }
            } else {
                Toast.makeText(mActivity, getString(R.string.error_select_image), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void insertEmployee(final String userUserName, final String userPass, String userName, String imageName, String userPhone, String userAddress, String userEmail) {
        swichBeginAppActivity.getProgressBarSwitchBegin().setVisibility(View.VISIBLE);
        soService.insertEmployee(userUserName, userPass, userName, imageName, userPhone, userAddress, userEmail, "2019-03-21", "1", "90000", "2019-03-21", "2019-03-21")
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body() != null) {
                            if (response.body().equals("1")) {
                                nameCheck = "";
                                Utils.toastMessage(swichBeginAppActivity, getString(R.string.success));
                                swichBeginAppActivity.setData(userUserName, userPass);
                                swichBeginAppActivity.switchFragment(swichBeginAppActivity.getFragmentLoginClient());
                                cleanText();
                            } else if (response.body().equals("2")) {
                                Toast.makeText(mActivity, getString(R.string.exist_acount), Toast.LENGTH_SHORT).show();
                            }
                        }
                        swichBeginAppActivity.getProgressBarSwitchBegin().setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        call.clone().enqueue(this);
                    }
                });
    }

    private void cleanText() {
        edtAddress.setText("");
        edtEmail.setText("");
        edtUserUser.setText("");
        edtPassWord.setText("");
        edtPhone.setText("");
        edtTitle.setText("");
        edtUserName.setText("");
        imageCode = "";
        nameCheck = "";
        imgAnhDaiDien.setVisibility(View.GONE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(mActivity, getString(R.string.no_permistion_storage), Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHOOSE_PICTURE && resultCode == mActivity.RESULT_OK) {
            Uri imageURI = data.getData();
            try {
                Bitmap bitmap = getThumbnail(imageURI);
                imageCode = Utils.getBitMap(bitmap);
                imgAnhDaiDien.setImageBitmap(bitmap);
                imgAnhDaiDien.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public Bitmap getThumbnail(Uri uri) throws FileNotFoundException, IOException {
        InputStream input = mActivity.getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();

        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) {
            return null;
        }

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > Constant.THUMBNAIL_SIZE) ? (originalSize / Constant.THUMBNAIL_SIZE) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true; //optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//
        input = mActivity.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0) return 1;
        else return k;
    }

    @Override
    protected void initView() {
        this.swichBeginAppActivity = (SwichBeginAppActivity) mActivity;
    }

    @Override
    protected int layoutID() {
        return R.layout.layout_register_fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }


    @OnClick({R.id.btnSelectImage, R.id.btnXacNhan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSelectImage:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CHOOSE_PICTURE);
                break;
            case R.id.btnXacNhan:
                xacNhanDangKi();
                break;
        }
    }
}
