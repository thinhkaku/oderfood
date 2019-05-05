package com.example.anthithanhtam.quanlynhahang.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ManagerTypeActivity;
import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.model.Type;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHodler> {
    private LayoutInflater inflater;
    private Context context;
    private List<Type> listType;
    private SOService soService;
    private ManagerTypeActivity managerTypeActivity;

    public TypeAdapter(Context context, List<Type> listType, SOService soService) {
        this.context = context;
        managerTypeActivity= (ManagerTypeActivity) context;
        this.listType = listType;
        this.soService=soService;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_type, viewGroup, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler viewHodler, int i) {
        Type type = listType.get(i);
        viewHodler.txtNameType.setText(type.getTypeName());
        viewHodler.txtTypeID.setText(type.getTypeId());
        viewHodler.btnDeleteType.setOnClickListener(v -> {
            questionDeleteType(type.getTypeId());
        });

        viewHodler.btnEditType.setOnClickListener(v -> {

        });
    }

    private void questionDeleteType(String typeId)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn thực sự muốn xóa loại món ăn này không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                soService.deleteType(typeId).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body()!=null &&response.body().equals("1"))
                        {
                            managerTypeActivity.getData();
                            Toast.makeText(context, "Xóa loại món ăn thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Xóa loại món ăn thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        call.clone().enqueue(this);
                    }
                });
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Không", (dialog, which) -> dialog.dismiss());
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return listType.size();
    }


    public class ViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.txtNameType)
        TextView txtNameType;
        @BindView(R.id.txtTypeID)
        TextView txtTypeID;
        @BindView(R.id.btnEditType)
        ImageView btnEditType;
        @BindView(R.id.btnDeleteType)
        ImageView btnDeleteType;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
