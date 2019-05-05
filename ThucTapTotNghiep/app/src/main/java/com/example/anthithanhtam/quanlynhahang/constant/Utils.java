package com.example.anthithanhtam.quanlynhahang.constant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.model.Employee;
import com.example.anthithanhtam.quanlynhahang.sharepreferences.ShareConstand;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class Utils {
    public static void intiDilogDetailEmploy(Employee employee, Context context){
        final Dialog dialog=new Dialog(context,R.style.Theme_AppCompat_DayNight_NoActionBar);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dilog_employee_detail);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        TextView txtTen=dialog.findViewById(R.id.txtTenDL);
        TextView txtNgaySinh=dialog.findViewById(R.id.txtBirthDL);
        TextView txtPhone=dialog.findViewById(R.id.txtPhoneDL);
        TextView txtAddress=dialog.findViewById(R.id.txtAddressDL);
        TextView txtPositionID=dialog.findViewById(R.id.txtPositionIdDL);
        TextView txtEmail=dialog.findViewById(R.id.txtEmailDL);
        TextView txtSalary=dialog.findViewById(R.id.txtSalaryDL);
        TextView txtTimeStart=dialog.findViewById(R.id.txtTimeStartDL);
        TextView txtTimeEnd=dialog.findViewById(R.id.txtTimeFinishDL);
        CircleImageView imgProfile=dialog.findViewById(R.id.imgProfileDL);
        Button btnXacNhan=dialog.findViewById(R.id.btnXacNhanDL);
        txtTimeEnd.setText(fomatDate(employee.getThoiGianKetThuc()));
        txtTimeStart.setText(fomatDate(employee.getThoiGianBatDau()));
        txtSalary.setText(fomatSalary(employee.getMucLuong()));
        txtAddress.setText(employee.getDiaChi());
        txtEmail.setText(employee.getEmail());
        txtPositionID.setText(employee.getChucVu());
        txtPhone.setText(employee.getSoDienThoai());
        txtNgaySinh.setText(fomatDate(employee.getNgaySinh()));
        Glide.with(context).load(Constant.PORT_IMAGE+employee.getAnh()).centerCrop().into(imgProfile);
        txtTen.setText(employee.getTenNhanVien());
        btnXacNhan.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    public  static String getNameImage()
    {
        Date date = new Date();
        String[] chuoi = {"t", "e", "a", "g", "k", "l", "c", "m"};
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            builder.append(chuoi[random.nextInt(7)]);
        }
        return String.valueOf(date.getTime()) + builder + "imagesges.jpg";
    }

    public static void toastMessage(Activity activity, String message)
    {
        LayoutInflater inflater=LayoutInflater.from(activity);
        View view=inflater.inflate(R.layout.layout_toast_success,(ViewGroup)activity.findViewById(R.id.customToast) );
        TextView txtMessage=view.findViewById(R.id.txtMessage);
        txtMessage.setText(message);
        Toast toast=Toast.makeText(activity,"",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setView(view);
        toast.show();
    }

    public static void showDialogMessage(Context context, String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.notification));
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public static String getCurentDateTime(){
        return new SimpleDateFormat(Constant.FORMAT_DATE_YYYY_MM_DD_HH_SS).format(Calendar.getInstance().getTime());
    }

    public static String fomatSalary(String salary)
    {
        DecimalFormat dc=new DecimalFormat(Constant.FORMAT_SALARY);
        return dc.format(Integer.parseInt(salary))+" vnđ";
    }

    public static String fomatDate(String date)
    {
        String result="";
        SimpleDateFormat sd=new SimpleDateFormat(Constant.FORMAT_DATE_YYYY_MM_DD, Locale.ENGLISH);
        try {
            Date date1=sd.parse(date);
            DateFormat dateFormat=new SimpleDateFormat(Constant.FORMAT_DATE_DD_MM_YYYY,Locale.ENGLISH);
            result=dateFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String fomatDateTimeDD_MM_YYYY(String date)
    {
        String result="";
        SimpleDateFormat sd=new SimpleDateFormat(Constant.FORMAT_DATE_YYYY_MM_DD_HH_SS, Locale.ENGLISH);
        try {
            Date date1=sd.parse(date);
            DateFormat dateFormat=new SimpleDateFormat(Constant.FORMAT_DATE_DD_MM_YYYY,Locale.ENGLISH);
            result=dateFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String fomatDateTimeDD_MM_YYYY_HH_MM(String date)
    {
        String result="";
        SimpleDateFormat sd=new SimpleDateFormat(Constant.FORMAT_DATE_YYYY_MM_DD_HH_SS, Locale.ENGLISH);
        try {
            Date date1=sd.parse(date);
            DateFormat dateFormat=new SimpleDateFormat(Constant.FORMAT_DATE_HH_SS_DD_MM_YYYY,Locale.ENGLISH);
            result=dateFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }





    public static String formatDay(String date)
    {
        String result="";
        SimpleDateFormat sd=new SimpleDateFormat(Constant.FORMAT_DATE_YYYY_MM_DD, Locale.ENGLISH);
        try {
            Date date1=sd.parse(date);
            DateFormat dateFormat=new SimpleDateFormat("dd",Locale.ENGLISH);
            result=dateFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String formatMonth(String date)
    {
        String result="";
        SimpleDateFormat sd=new SimpleDateFormat(Constant.FORMAT_DATE_YYYY_MM_DD, Locale.ENGLISH);
        try {
            Date date1=sd.parse(date);
            DateFormat dateFormat=new SimpleDateFormat("MM",Locale.ENGLISH);
            result=dateFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String formatYear(String date)
    {
        String result="";
        SimpleDateFormat sd=new SimpleDateFormat(Constant.FORMAT_DATE_YYYY_MM_DD, Locale.ENGLISH);
        try {
            Date date1=sd.parse(date);
            DateFormat dateFormat=new SimpleDateFormat("yyyy",Locale.ENGLISH);
            result=dateFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String revertDate(String day, String month,String year)
    {
        return year+"-"+month+"-"+day;
    }
    public static void dilogQuitApp(final Activity activity){
        final AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle(activity.getString(R.string.notification));
        builder.setMessage(activity.getString(R.string.question_exits_app));
        builder.setPositiveButton(activity.getString(R.string.exits), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPreferences1 = activity.getSharedPreferences("oderfood", activity.MODE_PRIVATE);
                boolean checkRadio = sharedPreferences1.getBoolean("checked", false);
                if (!checkRadio)
                {
                    ShareConstand.setEmployee(activity,null);
                }
                dialog.dismiss();
                activity.finishAffinity();
            }
        });
        builder.setNegativeButton(activity.getString(R.string.cancle), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


    public static String getBitMap(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean checkRepairPass(String pass, String passRepair)
    {
        boolean check;
        if (pass.equals(passRepair))
        {
            check=true;
        }else {
            check=false;
        }
        return check;
    }


}
