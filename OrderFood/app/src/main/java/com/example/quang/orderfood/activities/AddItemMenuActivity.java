package com.example.quang.orderfood.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quang.orderfood.R;
import com.github.nkzawa.emitter.Emitter;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import singleton.Singleton;

public class AddItemMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private  EditText edtTenMonAn, edtGiaMonAn;
    private Button btnHuy, btnXong;
    private ImageView imgAnhMonAn;
    private Spinner spLoaiMonAn, spDonViTinh;
    private ArrayList<String> arrSpLoaiMonAn,arrSpDonViTinh;
    private Animation animationButton;
    private int PICK_IMAGE=1;
    private boolean ktTaiLen=false;
    private int THUMBNAIL_SIZE=300;
    private byte[] aByte=new byte[]{};
    private String CLIENT_SEND_IMAGE_STAFF="CLIENT_SEND_IMAGE_STAFF";
    private Emitter.Listener onResult, onResultAddMenu;
    private String SERVER_SEND_RESULT_UP_IMAGE="SERVER_SEND_RESULT_UP_IMAGE";
    private String loaiMonAn, donViTinh;
    private String CLIENT_REQUEST_ADD_MENU="CLIENT_REQUEST_ADD_MENU";
    private String SEVER_SEND_RESULT_ADD_MENU="SEVER_SEND_RESULT_ADD_MENU";

    {
        onResult=new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getResult(args[0]);
            }
        };
        onResultAddMenu =new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getResultAddMenu(args[0]);
            }
        };
    }

    private void getResultAddMenu(final  Object arg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String kp =(String)arg;
                if (kp.split("").equals("1")){
                   return;

                }else {
                    Toast.makeText(AddItemMenuActivity.this,"Thêm món ăn thành công",Toast.LENGTH_SHORT).show();
                    CountDownTimer countDownTimer=new CountDownTimer(1000,1000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            finish();
                        }
                    }.start();
                }
            }
        });
    }

    private void getResult(final Object arg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String kp = (String)arg;
                if (ktTaiLen==false){
                    ktTaiLen=true;
                    editMenu(kp);
                }else {
                    return;
                }


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_menu);
        initSocket();
        initView();
        addEvent();
    }

    private void initSocket() {
        Singleton.Instance().getmSocket().on(SERVER_SEND_RESULT_UP_IMAGE,onResult);
        Singleton.Instance().getmSocket().on(SEVER_SEND_RESULT_ADD_MENU,onResultAddMenu);
    }

    private void addEvent() {
        btnXong.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
        imgAnhMonAn.setOnClickListener(this);
        spLoaiMonAn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loaiMonAn=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spDonViTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                donViTinh=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void editMenu(String anhMonAnAn) {
        String tenMonAn=edtTenMonAn.getText().toString();
        String giaMonAn =edtGiaMonAn.getText().toString()+ " VND";
        String query="INSERT INTO `danhsachmonan`  VALUES ('"+tenMonAn+"','"+loaiMonAn+"','"+giaMonAn+"','"+donViTinh+"','cohang','"+anhMonAnAn+"','1')";
        Singleton.Instance().getmSocket().emit(CLIENT_REQUEST_ADD_MENU,query);
    }

    private void initView() {
        edtGiaMonAn=findViewById(R.id.edtAddGiaMonAn);
        edtTenMonAn=findViewById(R.id.edtAddTenMonAn);
        btnHuy=findViewById(R.id.btnHuyAddMenu);
        btnXong=findViewById(R.id.btnDongYAddMenu);
        imgAnhMonAn =findViewById(R.id.imgAddHinhMonAn);
        spDonViTinh=findViewById(R.id.spDonViTinh);
        spLoaiMonAn=findViewById(R.id.spLoaiMonAn);
        animationButton= AnimationUtils.loadAnimation(AddItemMenuActivity.this,R.anim.button_apha);

        arrSpLoaiMonAn =new ArrayList<>();
        arrSpLoaiMonAn.add("Món Canh");
        arrSpLoaiMonAn.add("Món Cơm");
        arrSpLoaiMonAn.add("Nước ngọt");
        arrSpLoaiMonAn.add("Rượu");
        arrSpDonViTinh =new ArrayList<>();
        arrSpDonViTinh.add("Tô");
        arrSpDonViTinh.add("Lon");
        arrSpDonViTinh.add("Đĩa");
        arrSpDonViTinh.add("Chai - 750ml");
        arrSpDonViTinh.add("Chai - 1500ml");
        ArrayAdapter<String> donViTinhAdater = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrSpDonViTinh);
        donViTinhAdater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDonViTinh.setAdapter(donViTinhAdater);

        ArrayAdapter<String> loaiMonAnAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrSpLoaiMonAn);
        loaiMonAnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLoaiMonAn.setAdapter(loaiMonAnAdapter);

    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHuyAddMenu:
                btnHuy.startAnimation(animationButton);
                finish();
                break;
            case R.id.btnDongYAddMenu:
                btnXong.startAnimation(animationButton);
                upLoadHinh();
                break;
            case R.id.imgAddHinhMonAn:
                imgAnhMonAn.startAnimation(animationButton);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                break;
        }
    }

    private void upLoadHinh(){
        String tenMonAn=edtTenMonAn.getText().toString();
        String giaMonAn =edtGiaMonAn.getText().toString();
        if (tenMonAn.isEmpty()||giaMonAn.isEmpty()||aByte.length==0){
            if (aByte.length==0){
                Snackbar snackbar = Snackbar
                        .make(btnXong, "Bạn chưa chọn ảnh", Snackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Color.WHITE);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.DKGRAY);
                snackbar.show();
            }else {
                Snackbar snackbar = Snackbar
                        .make(btnXong, "Bạn chưa nhập đủ thông tin!", Snackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Color.WHITE);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.DKGRAY);
                snackbar.show();
            }
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
