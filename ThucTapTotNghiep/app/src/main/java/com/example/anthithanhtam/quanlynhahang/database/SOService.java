package com.example.anthithanhtam.quanlynhahang.database;

import com.example.anthithanhtam.quanlynhahang.model.Bill;
import com.example.anthithanhtam.quanlynhahang.model.BillDetail;
import com.example.anthithanhtam.quanlynhahang.model.Employee;
import com.example.anthithanhtam.quanlynhahang.model.Finance;
import com.example.anthithanhtam.quanlynhahang.model.Menu;
import com.example.anthithanhtam.quanlynhahang.model.Message;
import com.example.anthithanhtam.quanlynhahang.model.MyItem;
import com.example.anthithanhtam.quanlynhahang.model.MyOrder;
import com.example.anthithanhtam.quanlynhahang.model.MyTable;
import com.example.anthithanhtam.quanlynhahang.model.MyUser;
import com.example.anthithanhtam.quanlynhahang.model.OrderDetail;
import com.example.anthithanhtam.quanlynhahang.model.Position;
import com.example.anthithanhtam.quanlynhahang.model.Type;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface SOService {

    //quan ly nhan vien
    @GET("getdataemployee.php")
     Call<List<Employee>> getEmployee();

    @FormUrlEncoded
    @POST("getemployee.php")
    Call<List<Employee>> checkLogin(@Field("employee_username") String userName, @Field("employee_pass") String pass);


    @FormUrlEncoded
    @POST("deleteemployee.php")
    Call<String> deleteEmployee(@Field("employee_id") String id);

    @FormUrlEncoded
    @POST("editemployee.php")
    Call<String> editEmployee(@Field("employee_id") String id,
                              @Field("employee_pass") String employee_pass,@Field("employee_name") String employee_name,@Field("employee_image") String employee_image,
                              @Field("employee_phone") String employee_phone,@Field("employee_address") String employee_address,@Field("employee_email") String employee_email,
                              @Field("employee_dateofbirth") String employee_dateofbirth,@Field("employee_position_id") String employee_position_id,@Field("employee_salary") String employee_salary,
                              @Field("employee_timestart") String employee_timestart,@Field("employee_timefinish") String employee_timefinish);

    @FormUrlEncoded
    @POST("insertemployee.php")
    Call<String> insertEmployee(@Field("employee_username")String employee_username,
                                @Field("employee_pass") String employee_pass,@Field("employee_name") String employee_name,@Field("employee_image") String employee_image,
                                @Field("employee_phone") String employee_phone,@Field("employee_address") String employee_address,@Field("employee_email") String employee_email,
                                @Field("employee_dateofbirth") String employee_dateofbirth,@Field("employee_position_id") String employee_position_id,@Field("employee_salary") String employee_salary,
                                @Field("employee_timestart") String employee_timestart,@Field("employee_timefinish") String employee_timefinish);

    //

    //upload hinh anh
    @FormUrlEncoded
    @POST("uploadimage.php")
    Call<Message> uploadImage(@Field("imageCode")String imgCode,
                              @Field("imageName")String imgName);

    @FormUrlEncoded
    @POST("deletefile.php")
    Call<String> deleteImage(@Field("name_image")String imgName);
    //


    // get data Table
    @GET("gettable.php")
    Call<List<MyTable>> getMyTable();

    @FormUrlEncoded
    @POST("edittable.php")
    Call<String> editTable(@Field("number")String number, @Field("numpeople")String numpeople, @Field("check_tb")String check_tb, @Field("note")String note, @Field("time_check")String timeCheck);
    ///

    /// get data My Item
    @FormUrlEncoded
    @POST("getmyitem.php")
    Call<List<MyItem>> getMyItem(@Field("number_table")String numberTable);

    @FormUrlEncoded
    @POST("editmyitem.php")
    Call<String> editMyItem(@Field("id")String id, @Field("count")String count, @Field("status")String status);

    @FormUrlEncoded
    @POST("insertmyitem.php")
    Call<String> insertMyItem(@Field("menu_id")String menuId, @Field("numberTable")String numberTable, @Field("count")String count, @Field("create_at")String createAt);

    @FormUrlEncoded
    @POST("deletemyitem.php")
    Call<String> deleteMyItem(@Field("id")String id);

    @FormUrlEncoded
    @POST("deleteallmyitem.php")
    Call<String> deleteAllMyItem(@Field("id")String id);
    ///

    ////get data Menu

    @GET("getdatatype.php")
    Call<List<Type>> getDataType();

    @FormUrlEncoded
    @POST("getdatamenu.php")
    Call<List<Menu>> getMenu(@Field("id_type_menu")String type);
    ///




    //bill
    @GET("getdatabill.php")
    Call<List<Bill>>getDataBill();

    @FormUrlEncoded
    @POST("insertbill.php")
    Call<String>insertBill(@Field("bill_number_tb")String bill_number_tb, @Field("bill_number_people") String bill_number_people, @Field("employee_id")String employee_id
    , @Field("bill_create_date") String bill_create_date);
    //


    //Bill Detail
    @GET("getdatabilldetail.php")
    Call<List<BillDetail>>getDataBillDetail();


    @FormUrlEncoded
    @POST("insertbilldetail.php")
    Call<String>insertBillDetail(@Field("bill_id")String bill_id, @Field("menu_id")String menu_id, @Field("count")String count, @Field("bill_detail_create_date")String bill_detail_create_date);

    //



    @GET("getdatafinance.php")
    Call<List<Finance>> getFinance();



    @GET("getdatamyorder.php")
    Call<List<MyOrder>> getMyOrder();

    @GET("getdataorderdetail.php")
    Call<List<OrderDetail>> getOrderDetail();

    @GET("getdataposition.php")
    Call<List<Position>> getPosition();

    @GET("getdatatype.php")
    Call<List<Type>> getType();

    @GET("getdatauser.php")
    Call<List<MyUser>> getMyUser();









}
