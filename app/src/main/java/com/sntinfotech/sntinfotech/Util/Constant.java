package com.sntinfotech.sntinfotech.Util;

import android.app.Dialog;
import android.graphics.Color;
import android.view.Window;
import android.widget.CheckBox;

import com.sntinfotech.sntinfotech.R;
import com.victor.loading.newton.NewtonCradleLoading;
import com.victor.loading.rotate.RotateLoading;

/**
 * Created by Andriod Avnish on 9/16/2016.
 */
public class Constant {

    public static Boolean val=true;


    public static  String BaseUrl="https://www.bizzduniya.com/commonJson/jsondata.php?task=getCompanyDetail";
    public static  String BaseUrl2="https://www.bizzduniya.com/commonJson/jsondata.php?task=clientInformation";
    public static  String BaseUrl3="https://www.bizzduniya.com/commonJson/jsondata.php?task=checkEmailId";
    public static  String BaseUrl4="https://www.bizzduniya.com/commonJson/jsondata.php?task=sendQuery";
    public static  String BaseUrl5="https://www.bizzduniya.com/commonJson/jsondata.php?task=viewDetailByCategory";
    public static  String BaseUrl6="https://www.bizzduniya.com/commonJson/jsondata.php?task=viewDetail";
    public static  String BaseUrlUpdate="https://www.bizzduniya.com/commonJson/jsondata.php?task=upcoming_items";


    public static  String client_id="14179";
    public  static String client_token="{V_7ZOicnuMmuoo93CW3699T8um5tjay";
    String id,name,photo,code,desc;
    String cb,value;
    String id_client,title_client,photo_client;


    public Constant (String id_client,String title_client, String photo_client){
        this.id_client=id_client;
        this.title_client=title_client;
        this.photo_client=photo_client;

    }

    public Constant (String id,String name, String photo, String code, String desc){
        this.id=id;
        this.name=name;
        this.photo=photo;
        this.code=code;
        this.desc=desc;
    }
    public Constant(String cb,String value){
        this.cb=cb;
        this.value=value;
    }
    public  String getcb()
    {
        return  cb;
    }
    public  void  setcb(String cb){
        this.cb=cb;
    }
    public  String getvalue()
    {
        return  value;
    }
    public  void  setvalue(String value){
        this.value=value;
    }

    public  String getId()
    {
        return  id;
    }
    public  void  setId(String id){
        this.id=id;

    }
    public  String getName1()
    {
        return  name;
    }
    public  void  setName1(String name){
        this.name=name;

    }
    public  String getPhoto()
    {
        return  photo;
    }
    public  void  setPhoto(String photo){
        this.photo=photo;

    }
    public  String getCode()
    {
        return  code;
    }
    public  void  setCode(String code){
        this.code=code;

    }
    public  String getDesc()
    {
        return  desc;
    }
    public  void  setDesc(String desc){
        this.desc=desc;

    }

    public String getId_client(){
        return id_client;
    }
    public void setId_client(String id_client){
        this.id_client=id_client;
    }
    public String getPhoto_client(){
        return photo_client;
    }
    public void setPhoto_client(String photo_client){
        this.photo_client=photo_client;
    }
    public String getTitle_client(){
        return title_client;
    }
    public void setTitle_client(String title_client){
        this.title_client=title_client;
    }


    public  static void showDialog(Dialog dialog){
        dialog.setContentView(R.layout.custom_loading);
        dialog.setCanceledOnTouchOutside(false);

        RotateLoading newtonCradleLoading;
        newtonCradleLoading = (RotateLoading)dialog.findViewById(R.id.rotateloading);
        newtonCradleLoading.start();
        newtonCradleLoading.setLoadingColor(Color.parseColor("#d4020b"));


        dialog.show();
    }

}
