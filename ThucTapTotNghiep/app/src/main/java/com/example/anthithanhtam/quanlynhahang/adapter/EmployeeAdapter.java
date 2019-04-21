package com.example.anthithanhtam.quanlynhahang.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ManagerActivity;
import com.example.anthithanhtam.quanlynhahang.constant.Constant;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.model.Employee;
import com.example.anthithanhtam.quanlynhahang.presenter.PresenterQuanLyNhanVien;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHodlerEmploy> {
    private List<Employee>arrEmploy;
    private Context context;
    private LayoutInflater inflater;
    private PresenterQuanLyNhanVien presenterQuanLyNhanVien;
    private ManagerActivity mManagerActivity;

    public EmployeeAdapter(List<Employee> arrEmploy, Context context, PresenterQuanLyNhanVien presenterQuanLyNhanVien, ManagerActivity mManagerActivity) {
        this.arrEmploy = arrEmploy;
        this.context = context;
        this.mManagerActivity=mManagerActivity;
        inflater=LayoutInflater.from(context);
        this.presenterQuanLyNhanVien = presenterQuanLyNhanVien;
    }

    @NonNull
    @Override
    public ViewHodlerEmploy onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.item_list_nhan_vien,viewGroup,false);
        return new ViewHodlerEmploy(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodlerEmploy viewHodlerEmploy, int i) {
        final Employee employee=arrEmploy.get(i);
        viewHodlerEmploy.txtTen.setText(employee.getTenNhanVien());
        viewHodlerEmploy.txtTuoi.setText(Utils.fomatDate(employee.getNgaySinh()));
        viewHodlerEmploy.txtSDT.setText(employee.getSoDienThoai());
        Glide.with(context).load(Constant.PORT_IMAGE+employee.getAnh()).into(viewHodlerEmploy.imgProfile);
        viewHodlerEmploy.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDeleteEmployee(employee.getMaNhanVien(), employee.getTenNhanVien(), employee.getAnh());
            }
        });
        viewHodlerEmploy.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEditEmployee(employee);
                //Toast.makeText(context, context.getString(R.string.edit), Toast.LENGTH_SHORT).show();
            }
        });
        viewHodlerEmploy.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.intiDilogDetailEmploy(employee,context);
            }
        });
    }

    private void dialogDeleteEmployee(final String id, String name, final String nameImage)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.notification));
        builder.setMessage("Bạn thực sự muốn xóa nhân viên "+name+" không?");
        builder.setCancelable(false);
        builder.setNegativeButton(context.getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mManagerActivity.getLnProgressBar().setVisibility(View.VISIBLE);
                presenterQuanLyNhanVien.deleteEmployee(id, nameImage);
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(context.getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    private void dialogEditEmployee(final Employee employee)
    {
        final Dialog dialog=new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_edit_employee);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        CircleImageView imgProfileDL=dialog.findViewById(R.id.imgProfileDL);
        final EditText edtTenDL=dialog.findViewById(R.id.edtTenDL);
        final TextView txtTenTaiKhoanDL=dialog.findViewById(R.id.txtTenTaiKhoanDL);
        final EditText edtpassDL=dialog.findViewById(R.id.edtpassDL);
        final EditText edtAgainPassDL=dialog.findViewById(R.id.edtAgainPassDL);
        final EditText edtEmailDL=dialog.findViewById(R.id.edtEmailDL);
        final EditText edtPhoneDL=dialog.findViewById(R.id.edtPhoneDL);
        final EditText edtAddressDL=dialog.findViewById(R.id.edtAddressDL);
        final EditText edtBirthDayDL=dialog.findViewById(R.id.edtDayBirthDL);
        final EditText edtBirthMonthDL=dialog.findViewById(R.id.edtMonthBirthDL);
        final EditText edtBirthYearDL=dialog.findViewById(R.id.edtYearBirthDL);
        final EditText edtPositionIdDL=dialog.findViewById(R.id.edtPositionIdDL);
        final EditText edtSalaryDL=dialog.findViewById(R.id.edtSalaryDL);
        final EditText edtDayTimeEndDL=dialog.findViewById(R.id.edtDayTimeEndDL);
        final EditText edtMonthTimeEndDL=dialog.findViewById(R.id.edtMonthTimeEndDL);
        final EditText edtYearTimeEndDL=dialog.findViewById(R.id.edtYearTimeEndDL);

        Glide.with(context).load(Constant.PORT_IMAGE+employee.getAnh()).into(imgProfileDL);
        txtTenTaiKhoanDL.setText(employee.getTaiKhoan());
        edtpassDL.setText(employee.getPass());
        edtAgainPassDL.setText(employee.getPass());
        edtTenDL.setText(employee.getTenNhanVien());
        edtEmailDL.setText(employee.getEmail());
        edtPhoneDL.setText(employee.getSoDienThoai());
        edtAddressDL.setText(employee.getDiaChi());
        edtBirthDayDL.setText(Utils.formatDay(employee.getNgaySinh()));
        edtBirthMonthDL.setText(Utils.formatMonth(employee.getNgaySinh()));
        edtBirthYearDL.setText(Utils.formatYear(employee.getNgaySinh()));
        edtPositionIdDL.setText(employee.getChucVu());
        edtSalaryDL.setText(employee.getMucLuong());
        edtDayTimeEndDL.setText(Utils.formatDay(employee.getThoiGianKetThuc()));
        edtMonthTimeEndDL.setText(Utils.formatMonth(employee.getThoiGianKetThuc()));
        edtYearTimeEndDL.setText(Utils.formatYear(employee.getThoiGianKetThuc()));

        Button btnXHuyDL=dialog.findViewById(R.id.btnXHuyDL);
        Button btnXacNhanDL=dialog.findViewById(R.id.btnXacNhanDL);
        btnXacNhanDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtTenDL.getText().toString().trim();
                String userPass=edtpassDL.getText().toString().trim();
                String userPassAgain=edtAgainPassDL.getText().toString().trim();
                String email=edtEmailDL.getText().toString().trim();
                String phone=edtPhoneDL.getText().toString();
                String address=edtAddressDL.getText().toString().trim();
                String dayBirth=edtBirthDayDL.getText().toString().trim();
                String monthBirth=edtBirthMonthDL.getText().toString().trim();
                String yearBirth=edtBirthYearDL.getText().toString().trim();
                String position=edtPositionIdDL.getText().toString().trim();
                String salary=edtSalaryDL.getText().toString().trim();
                String timeDayEnd=edtDayTimeEndDL.getText().toString().trim();
                String timeMonthEnd=edtMonthTimeEndDL.getText().toString().trim();
                String timeYearEnd=edtYearTimeEndDL.getText().toString().trim();
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty()||address.isEmpty()||dayBirth.isEmpty()||monthBirth.isEmpty()||yearBirth.isEmpty()||position.isEmpty()||
                salary.isEmpty()||timeDayEnd.isEmpty()||timeMonthEnd.isEmpty()||timeYearEnd.isEmpty() ||userPass.isEmpty()||userPassAgain.isEmpty())
                {
                    Toast.makeText(context, context.getString(R.string.no_enter_infomation), Toast.LENGTH_SHORT).show();
                }else if(!Utils.isValidEmail(email.trim())){
                    Toast.makeText(context, context.getString(R.string.special_email), Toast.LENGTH_SHORT).show();
                }else if (!Utils.checkRepairPass(userPass,userPassAgain)){
                    Toast.makeText(context, context.getString(R.string.diference_pass), Toast.LENGTH_SHORT).show();
                }else {
                    String birth=Utils.revertDate(dayBirth, monthBirth, yearBirth);
                    String timeEnd=Utils.revertDate(timeDayEnd, timeMonthEnd, timeYearEnd);
                    dialog.dismiss();
                    mManagerActivity.getLnProgressBar().setVisibility(View.VISIBLE);
                    presenterQuanLyNhanVien.editEmployee(new Employee(employee.getMaNhanVien(),employee.getTaiKhoan(),userPass,name,employee.getAnh(),phone, address, email, birth, position, salary, employee.getThoiGianBatDau(),timeEnd));
                }
            }
        });
        btnXHuyDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }




    @Override
    public int getItemCount() {
        return arrEmploy.size();
    }

    public class ViewHodlerEmploy extends RecyclerView.ViewHolder{
        private TextView txtTen;
        private TextView txtTuoi;
        private CircleImageView imgProfile;
        private ImageButton btnXoa, btnSua;
        private CardView cardItem;
        private TextView txtSDT;
        public ViewHodlerEmploy(@NonNull View itemView) {
            super(itemView);
            txtTen=itemView.findViewById(R.id.txtTen);
            txtTuoi=itemView.findViewById(R.id.txtTuoi);
            btnSua=itemView.findViewById(R.id.btnSua);
            btnXoa=itemView.findViewById(R.id.btnXoa);
            imgProfile=itemView.findViewById(R.id.imgProfile);
            cardItem=itemView.findViewById(R.id.cardItem);
            txtSDT=itemView.findViewById(R.id.txtSDT);
        }
    }
}
