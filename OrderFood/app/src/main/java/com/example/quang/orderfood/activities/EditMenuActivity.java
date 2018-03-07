package com.example.quang.orderfood.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quang.orderfood.R;
import com.github.nkzawa.emitter.Emitter;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import consts.Constants;
import singleton.Singleton;

/**
 * Created by Administrator on 3/7/2018.
 */

public class EditMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtEditTenMonAn, edtEditGiaMonAn;
    private ImageView imgAnhMonAn;
    private int PICK_IMAGE=1;
    private Animation animationButton;
    private byte[] aByte;
    String anhMonAn, tenMonAn,giaMonAn;
    private Button btnHuy, btnXong;
    private int THUMBNAIL_SIZE=300;
    private String CLIENT_SEND_IMAGE_STAFF="CLIENT_SEND_IMAGE_STAFF";
    private Emitter.Listener onResult, onResultEditMenu;
    private String SERVER_SEND_RESULT_UP_IMAGE="SERVER_SEND_RESULT_UP_IMAGE";
    private String CLIENT_REQUEST_EDIT_MENU="CLIENT_REQUEST_EDIT_MENU";
    private String SERVER_SEND_RESULT_EDIT_MENU="SEVER_SEND_RESULT_EDIT_MENU";

    {
        onResult=new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getResult(args[0]);
            }
        };
        onResultEditMenu=new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getResultEditMenu(args[0]);
            }
        };
    }

    private void getResultEditMenu(final Object arg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String kp = (String)arg;
                Toast.makeText(EditMenuActivity.this,"Tải món ăn thành công!"+kp,Toast.LENGTH_SHORT).show();
                if (kp.split("").equals("true")){

                    finish();
                }
            }
        });
    }

    private void getResult(final Object args) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String kp = (String)args;
                editMenu(kp);
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_menu_dialog);
        initSocket();
        getNameImage();
        initView();
        //imageViewToByte();
        addEvent();
    }

    private void imageViewToByte() {
        Bitmap bitmap = ((BitmapDrawable) imgAnhMonAn.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        aByte = baos.toByteArray();
    }

    private void getNameImage() {
        Intent intent=getIntent();
        anhMonAn=intent.getStringExtra("getImage");
        tenMonAn=intent.getStringExtra("getName");
        giaMonAn=intent.getStringExtra("getPrice");
    }

    private void initSocket() {
        Singleton.Instance().getmSocket().on(SERVER_SEND_RESULT_UP_IMAGE,onResult);
        Singleton.Instance().getmSocket().on(SERVER_SEND_RESULT_EDIT_MENU,onResultEditMenu);
    }

    private void addEvent() {
        btnXong.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
        imgAnhMonAn.setOnClickListener(this);
    }

    private void initView() {
        edtEditGiaMonAn=findViewById(R.id.edtEditGiaMonAn);
        edtEditTenMonAn=findViewById(R.id.edtEditTenMonAn);
        imgAnhMonAn=findViewById(R.id.imgEditHinhMonAn);
        btnHuy=findViewById(R.id.btnHuyEditMenu);
        btnXong=findViewById(R.id.btnDongYEditMenu);
        animationButton= AnimationUtils.loadAnimation(EditMenuActivity.this,R.anim.button_apha);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHuyEditMenu:
                btnHuy.startAnimation(animationButton);
                finish();
                break;
            case R.id.btnDongYEditMenu:
                btnXong.startAnimation(animationButton);
                upLoadHinh();
                break;
            case R.id.imgEditHinhMonAn:
                imgAnhMonAn.startAnimation(animationButton);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                break;
        }
    }

    private void editMenu(String anhMonAnAn) {
        String tenMonAn=edtEditTenMonAn.getText().toString();
        String giaMonAn =edtEditGiaMonAn.getText().toString();
        String query="UPDATE `danhsachmonan` SET `tenMonAn`='"+tenMonAn+"',`gia`='"+giaMonAn+"',`anhMonAn`='"+anhMonAnAn+"' WHERE `anhMonAn`='"+anhMonAn+"'";
        Singleton.Instance().getmSocket().emit(CLIENT_REQUEST_EDIT_MENU,query);
    }

    private void upLoadHinh(){
        String tenMonAn=edtEditTenMonAn.getText().toString();
        String giaMonAn =edtEditGiaMonAn.getText().toString();
        if (tenMonAn.isEmpty()||giaMonAn.isEmpty()){
            Snackbar snackbar = Snackbar
                    .make(btnXong, "Bạn chưa nhập đủ thông tin!", Snackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.DKGRAY);
            snackbar.show();
        }else {
            Singleton.Instance().getmSocket().emit(CLIENT_SEND_IMAGE_STAFF,aByte);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE &&resultCode==RESULT_OK){
            Uri uriImage = data.getData();
            try {
                Bitmap bitmap1=getThumbnail(uriImage);
                aByte=getByteArrayFromBitmap(bitmap1);
                imgAnhMonAn.setImageBitmap(bitmap1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getByteArrayFromBitmap(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public Bitmap getThumbnail(Uri uri) throws FileNotFoundException, IOException{
        InputStream input = this.getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither=true;//optional
        onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();

        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) {
            return null;
        }

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > THUMBNAIL_SIZE) ? (originalSize / THUMBNAIL_SIZE) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true; //optional
        bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//
        input = this.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio){
        int k = Integer.highestOneBit((int)Math.floor(ratio));
        if(k==0) return 1;
        else return k;
    }
}
