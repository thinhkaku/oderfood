package com.example.quang.orderfood.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.icu.util.Output;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.quang.orderfood.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

import adapter.ListviewStaffAdapter;
import consts.Constants;
import objects.Staff;
import singleton.Singleton;

public class AddStaffActivity extends AppCompatActivity implements View.OnClickListener {

    private final String CLIENT_SEND_IMAGE_STAFF = "CLIENT_SEND_IMAGE_STAFF";
    private final String SERVER_SEND_RESULT_UP_IMAGE = "SERVER_SEND_RESULT_UP_IMAGE";
    private final String CLIENT_SEND_REQUEST_INSERT_STAFF = "CLIENT_SEND_REQUEST_INSERT_STAFF";
    private final String SERVER_SEND_RESULT_INSERT_STAFF = "SERVER_SEND_RESULT_INSERT_STAFF";

    Emitter.Listener onResult;
    Emitter.Listener onResultInsert, onListStaff;
    private int THUMBNAIL_SIZE=300;
    private String id;
    private ArrayList<Staff>arrStaff;
    private String CLIENT_SEND_REQUEST_LIST_STAFF="CLIENT_SEND_REQUEST_LIST_STAFF";
    private String SERVER_SEND_LIST_STAFF="SERVER_SEND_LIST_STAFF";

    {
        onResult = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            getResult(args[0]);
        }
        };

        onResultInsert = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getResultInsert(args[0]);
            }
        };
         onListStaff =new Emitter.Listener() {
             @Override
             public void call(Object... args) {
                 getListStaff(args[0]);
             }
         };
    }

    private void getListStaff(final Object arg) {
        arrStaff =new ArrayList<>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONArray data = (JSONArray) arg;
                arrStaff.clear();
                for (int i=0; i<data.length(); i++)
                {
                    try {
                        JSONObject object = data.getJSONObject(i);
                        String id1 = object.getString("idNhanVien");
                        String name = object.getString("tenNhanVien");
                        String sex = object.getString("gioiTinh");
                        String dateOfBirth = object.getString("ngaySinh");
                        String address = object.getString("queQuan");
                        String phone = object.getString("soDienThoai");
                        String dateStart = object.getString("ngayVao");
                        String image = object.getString("anhDaiDien");
                        int check = object.getInt("online");
                        String salary = object.getString("luongNgay");
                        String userPass = object.getString("userPass");
                        String position = object.getString("chucVu");
                        Staff staff = new Staff(name,id1,sex,dateOfBirth,address
                                ,phone,dateStart,image,check,salary);
                        staff.setUserPass(userPass);
                        staff.setPosition(position);
                        if (check == 1)
                        {
                            arrStaff.add(0,staff);
                        }
                        else {
                            arrStaff.add(staff);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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
                    Toast.makeText(AddStaffActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void getResultInsert(final Object arg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String object = (String) arg;
                Toast.makeText(AddStaffActivity.this,object+"",Toast.LENGTH_SHORT).show();
                if (object.split("").equals("1"))
                {
                    Snackbar snackbar = Snackbar
                            .make(edtAddress, "Thất bại!", Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    snackbar.show();
                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(edtAddress, "Thành công!", Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    snackbar.show();
                    CountDownTimer countDownTimer = new CountDownTimer(2000,1000) {
                        @Override
                        public void onTick(long l) {


                        }

                        @Override
                        public void onFinish() {
                            Singleton.Instance().getmSocket().emit("CLIENT_SEND_REQUEST_LIST_STAFF","123");
                            finish();
                        }
                    }.start();

                }
            }
        });
    }

    private void getResult(Object arg) {
        String url = (String) arg;

        id = edtId.getText().toString();
        String name = edtName.getText().toString();
        String address = edtAddress.getText().toString();
        String phone = edtPhone.getText().toString();
        String salary = edtSalary.getText().toString();
        String user = edtUser.getText().toString();
        String pass = edtPassword.getText().toString();
        String birth = btnDateOfBirth.getText().toString();
        String[] a = birth.trim().split("-");
        String dateBirth = a[2]+"-"+a[1]+"-"+a[0];
        String dateStart = btnDateStart.getText().toString();
        String[] b = dateStart.trim().split("-");
        String dateStart1 = b[2]+"-"+b[1]+"-"+b[0];
        int isCheckedSex = groupSex.getCheckedRadioButtonId();
        radioSexButton = findViewById(isCheckedSex);
        String sex = radioSexButton.getText().toString();
        int isCheckedPosition = groupPosition.getCheckedRadioButtonId();
        radioPositionButton = findViewById(isCheckedPosition);
        String position = radioPositionButton.getText().toString();
        if (ktTaiLen==false){
            ktTaiLen=true;
            String query = "INSERT INTO `nhanvien` VALUES ('"+id+"','"+name+"','"+sex+"','"+dateBirth+"','"+address+"','"+phone+"','"+position+"','"+dateStart1+"','"+salary+"','"+user+"-"+pass+"','"+url+"',0)";
            Singleton.Instance().getmSocket().emit(CLIENT_SEND_REQUEST_INSERT_STAFF,query);
        }else {
            return;
        }

    }


    private final int REQUEST_CHOOSE_PICTURE = 321;

    private EditText edtId;
    private EditText edtName;
    private EditText edtAddress;
    private EditText edtPhone;
    private EditText edtSalary;
    private EditText edtUser;
    private EditText edtPassword;
    private Button btnDateOfBirth;
    private Button btnDateStart;
    private Button btnChooseAvatar;
    private Button btnDone;
    private Button btnExit;
    private boolean ktTaiLen=false;
    private RadioGroup groupSex;
    private RadioButton radioButton_male;
    private RadioButton radioButton_female;
    private RadioButton radioSexButton;
    private RadioGroup groupPosition;
    private RadioButton radioButton_BB;
    private RadioButton radioButton_QL;
    private RadioButton radioPositionButton;
    private ImageView imAvatar;

    private int year;
    private int month;
    private int day;

    static final int DATE_PICKER_ID = 1111;
    private boolean checkClick, ktSoLanTaiLen=false;

    private byte[] bytes=new byte[]{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        initSockets();
        findId();
        clickEvents();
    }

    private void initSockets() {
        Singleton.Instance().getmSocket().on(SERVER_SEND_RESULT_UP_IMAGE,onResult);
        Singleton.Instance().getmSocket().on(SERVER_SEND_RESULT_INSERT_STAFF,onResultInsert);
        Singleton.Instance().getmSocket().emit(CLIENT_SEND_REQUEST_LIST_STAFF,"123");
        Singleton.Instance().getmSocket().on(SERVER_SEND_LIST_STAFF,onListStaff);
    }

    private void clickEvents() {
        btnDateOfBirth.setOnClickListener(this);
        btnDateStart.setOnClickListener(this);
        btnChooseAvatar.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        btnExit.setOnClickListener(this);

    }

    private void findId() {
        edtId = findViewById(R.id.edtIdStaffAddStaff);
        edtName = findViewById(R.id.edtNameStaffAddStaff);
        edtAddress = findViewById(R.id.edtAddressStaffAddStaff);
        edtPhone = findViewById(R.id.edtPhoneStaffAddStaff);
        edtSalary = findViewById(R.id.edtSalaryStaffAddStaff);
        edtUser = findViewById(R.id.edtUserStaffAddStaff);
        edtPassword = findViewById(R.id.edtPassStaffAddStaff);
        btnDateOfBirth = findViewById(R.id.btnChooseDateOfBirthAddStaff);
        btnDateStart = findViewById(R.id.btnChooseDateStartAddStaff);
        btnChooseAvatar = findViewById(R.id.btnChooseAvatarAddStaff);
        groupSex = findViewById(R.id.groupSex);
        btnDone = findViewById(R.id.btnDoneAddStaff);
        btnExit = findViewById(R.id.btnExitAddStaff);
        radioButton_male = findViewById(R.id.radioButton_male_AddStaff);
        radioButton_female = findViewById(R.id.radioButton_female_AddStaff);
        groupPosition = findViewById(R.id.groupPosition);
        radioButton_BB = findViewById(R.id.radioButton_BB_AddStaff);
        radioButton_QL = findViewById(R.id.radioButton_QL_AddStaff);
        imAvatar = findViewById(R.id.imAvatarAddStaff);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            // Show selected date
            if (checkClick)
            {
                btnDateOfBirth.setText(new StringBuilder().append(day)
                        .append("-").append(month + 1).append("-").append(year)
                        .append(" "));
            }
            else {
                btnDateStart.setText(new StringBuilder().append(day)
                        .append("-").append(month + 1).append("-").append(year)
                        .append(" "));
            }

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnChooseDateOfBirthAddStaff:
                checkClick = true;
                showDialog(DATE_PICKER_ID);
                break;
            case R.id.btnChooseDateStartAddStaff:
                checkClick = false;
                showDialog(DATE_PICKER_ID);
                break;
            case R.id.btnChooseAvatarAddStaff:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CHOOSE_PICTURE);
                break;
            case R.id.btnDoneAddStaff:
                clickDone();
                break;
            case R.id.btnExitAddStaff:
                finish();
                break;
        }
    }

    private void clickDone(){
        ktSoLanTaiLen=false;
        String id = edtId.getText().toString();
        String name = edtName.getText().toString();
        String address = edtAddress.getText().toString();
        String phone = edtPhone.getText().toString();
        String salary = edtSalary.getText().toString();
        String user = edtUser.getText().toString();
        String pass = edtPassword.getText().toString();
        String birth = btnDateOfBirth.getText().toString();
        String dateStart = btnDateStart.getText().toString();
        int isCheckedSex = groupSex.getCheckedRadioButtonId();
        radioSexButton = findViewById(isCheckedSex);
        String sex = radioSexButton.getText().toString();
        int isCheckedPosition = groupPosition.getCheckedRadioButtonId();
        radioPositionButton = findViewById(isCheckedPosition);
        String position = radioPositionButton.getText().toString();
        if (id.isEmpty()|| id.length() != 5||name.isEmpty()||address.isEmpty()||phone.isEmpty()
                ||salary.isEmpty()||user.isEmpty()||pass.isEmpty() ||bytes.length==0)
        {
            if (id.length() != 5){
                Snackbar snackbar = Snackbar
                        .make(edtAddress, "Mã nhân nhiên bắt buộc phải có 5 kí tự!", Snackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Color.WHITE);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.DKGRAY);
                snackbar.show();
            }else if (bytes.length==0){
                Snackbar snackbar = Snackbar
                        .make(edtAddress, "Bạn chưa chọn ảnh!", Snackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Color.WHITE);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.DKGRAY);
                snackbar.show();
            }else {
                Snackbar snackbar = Snackbar
                        .make(edtAddress, "Bạn chưa nhập đủ thông tin!", Snackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Color.WHITE);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.DKGRAY);
                snackbar.show();
            }

            return;
        }
        if (btnDateOfBirth.getText().toString().equalsIgnoreCase("Ngày sinh")
                ||btnDateStart.getText().toString().equalsIgnoreCase("Ngày vào"))
        {
            Snackbar snackbar = Snackbar
                    .make(edtAddress, "Bạn chưa nhập đủ thông tin!", Snackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.DKGRAY);
            snackbar.show();
            return;
        }
        int dem=0;

        for (int i=0;i<arrStaff.size();i++){
            if (arrStaff.get(i).getId().equalsIgnoreCase(id)){
                dem++;
            }
        }
        if (dem==0 &&ktSoLanTaiLen==false){
            ktSoLanTaiLen=true;
            Singleton.Instance().getmSocket().emit(CLIENT_SEND_IMAGE_STAFF,bytes);
        }else {
            ktSoLanTaiLen=true;
            Toast.makeText(AddStaffActivity.this, "Mã nhân viên này đã tồn tại!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHOOSE_PICTURE && resultCode == RESULT_OK)
        {
                Uri imageURI = data.getData();
                try {
                    Bitmap bitmap = getThumbnail(imageURI);
                    bytes = getByteArrayFromBitmap(bitmap);
                    imAvatar.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }


        }
    }
    private static Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > ratioBitmap) {
                finalWidth = (int) ((float)maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float)maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            return image;
        } else {
            return image;
        }
    }
    public byte[] getByteArrayFromBitmap(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public Bitmap getThumbnail(Uri uri) throws FileNotFoundException, IOException {
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
