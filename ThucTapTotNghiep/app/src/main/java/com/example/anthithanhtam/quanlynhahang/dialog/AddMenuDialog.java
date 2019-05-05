package com.example.anthithanhtam.quanlynhahang.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.constant.Constant;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.database.ApiUtils;
import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.interfacee.DialogAddMenuInterface;
import com.example.anthithanhtam.quanlynhahang.model.Message;
import com.example.anthithanhtam.quanlynhahang.model.Type;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class AddMenuDialog extends BaseDialog {
    @BindView(R.id.edtNameMenu)
    TextInputEditText edtNameMenu;
    @BindView(R.id.spDanhMuc)
    Spinner spDanhMuc;
    @BindView(R.id.edtGia)
    TextInputEditText edtGia;
    @BindView(R.id.edtDescrible)
    TextInputEditText edtDescrible;
    @BindView(R.id.imgAnhDaiDien)
    ImageView imgAnhDaiDien;
    @BindView(R.id.btnSelectImage)
    Button btnSelectImage;
    @BindView(R.id.btnXacNhan)
    Button btnXacNhan;
    private Context context;
    private List<Type> mListType = new ArrayList<>();
    private ArrayList<String> arrDanhMuc = new ArrayList<>();
    private ArrayAdapter<String> danhMucAdapter;
    private SOService soService;
    private final int REQUEST_CHOOSE_PICTURE = 321;
    private String imageCode;
    private DialogAddMenuInterface dialogAddMenuInterface;

    public static AddMenuDialog newInstace(Context context, DialogAddMenuInterface dialogAddMenuInterface) {
        AddMenuDialog addMenuDialog = new AddMenuDialog(context, dialogAddMenuInterface);
        addMenuDialog.setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_Light_NoTitleBar);
        return addMenuDialog;
    }

    public AddMenuDialog(Context context, DialogAddMenuInterface dialogAddMenuInterface) {
        this.context = context;
        this.dialogAddMenuInterface=dialogAddMenuInterface;
    }

    @Override
    public int layoutId() {
        return R.layout.dialog_add_menu;
    }

    @Override
    public void loadData() {
        soService = ApiUtils.getSOService();
        soService.getDataType().enqueue(new Callback<List<Type>>() {
            @Override
            public void onResponse(Call<List<Type>> call, Response<List<Type>> response) {
                if (response.body() != null) {
                    mListType.clear();
                    mListType.addAll(response.body());
                    danhMuc=mListType.get(0).getTypeId();
                    arrDanhMuc.clear();
                    for (Type type : mListType) {
                        arrDanhMuc.add(type.getTypeName());
                    }
                    danhMucAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arrDanhMuc);
                    danhMucAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spDanhMuc.setAdapter(danhMucAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Type>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
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
                xacNhanThemMenu();
                break;
        }
    }
    String danhMuc;

    private void xacNhanThemMenu()
    {
        String nameMenu=edtNameMenu.getText().toString().trim();
        spDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                danhMuc=mListType.get(position).getTypeId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String price=edtGia.getText().toString().trim();
        String describe=edtDescrible.getText().toString().trim();
        if (nameMenu.isEmpty()||danhMuc==null||price.isEmpty()||describe.isEmpty()||imageCode==null)
        {
            Toast.makeText(context, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        }else {
            String nameImage=Utils.getNameImage();
            soService.uploadImage(imageCode,nameImage).enqueue(new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    if (response.body()!=null)
                    {
                        soService.insertMenu(nameMenu,nameImage,describe,danhMuc,price).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body()!=null)
                                {
                                    Utils.toastMessage((Activity) context, getString(R.string.success));
                                    dialogAddMenuInterface.onResult();
                                    AddMenuDialog.this.dismiss();
                                }else {
                                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                call.clone().enqueue(this);
                            }
                        });
                    }else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHOOSE_PICTURE && resultCode == getActivity().RESULT_OK) {
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
        InputStream input = context.getContentResolver().openInputStream(uri);

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
        input = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0) return 1;
        else return k;
    }
}
