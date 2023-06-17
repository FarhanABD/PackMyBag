package com.example.packmybag.Data;

import android.app.Application;
import android.content.Context;

import com.example.packmybag.Constants.MyConstants;
import com.example.packmybag.Database.RoomDB;
import com.example.packmybag.Models.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppData extends Application {

    RoomDB database;
    String category;
    Context context;

    public static final String LAST_VERSION = "LAST_VERSION";
    public static final int NEW_VERSION = 3;

    public AppData(RoomDB database) {
        this.database = database;
    }

    public AppData(RoomDB database, Context context) {
        this.database = database;
        this.context = context;
    }

    //------- LIST DATA BAWAAN CATEGORY BASIC NEEDS ------------//
    public List<Items> getBasicData(){
        category = "Basic Needs";
        List<Items> basicItem = new ArrayList<>();
        basicItem.add(new Items("Visa",category,false));
        basicItem.add(new Items("Passport",category,false));
        return basicItem;
    }

    public List<Items> getPersonalCareData(){
        String []data = {"Tooth-brush", "Tooth-paste", "Floss", "Mouthwash"};
        return prepareItemsList(MyConstants.PERSONAL_CAMEL_CASE, data);
    }
    public List<Items> prepareItemsList(String category, String []data){
        List<String> list = Arrays.asList(data);
        List<Items> dataList = new ArrayList<>();
        dataList.clear();

        for(int i=0; i<list.size(); i++){
            dataList.add(new Items(list.get(i), category, false));
        }
        return dataList;
    }
}
