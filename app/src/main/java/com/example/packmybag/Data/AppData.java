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
        List<Items> basicItem = new ArrayList<Items>();
        basicItem.clear();
        basicItem.add(new Items("Visa",category,false));
        basicItem.add(new Items("Passport",category,false));
        basicItem.add(new Items("Neck Pillow",category,false));
        basicItem.add(new Items("Book",category,false));
        basicItem.add(new Items("House Key",category,false));
        basicItem.add(new Items("Note Book",category,false));
        basicItem.add(new Items("Wallet",category,false));
        return basicItem;
    }

    public List<Items> getPersonalCareData(){
        String []data = {"Tooth-brush", "Tooth-paste", "Floss", "Mouthwash","Skincare","Bodycare"};
        return prepareItemsList(MyConstants.PERSONAL_CAMEL_CASE, data);
    }

    public List<Items> getClothingData(){
        String []data = {"Kemeja", "Kaos", "Celana Pendek", "Celana Panjang", "Jaket"};
        return prepareItemsList(MyConstants.CLOTHING_CAMEL_CASE, data);
    }

    public List<Items> getBabyNeedsData(){
        String []data = {"Pampers", "Bedak Bayi", "Baju", "Celana", "Minyak Telon", "Dot"};
        return  prepareItemsList(MyConstants.BABY_NEEDS_CAMEL_CASE,data);
    }

    public List<Items> getHealthData(){
        String []data = {"Obat Maag","Antimo","Tolakangin"};
        return prepareItemsList(MyConstants.HEALTH_CAMEL_CASE,data);
    }

    public List<Items> getTechnologyData(){
        String []data = {"Powerbank", "Charger", "Laptop"};
        return prepareItemsList(MyConstants.TECHNOLOGY_CAMEL_CASE,data);
    }

    public List<Items> getFoodData(){
        String []data = {"Mie Goreng", "Snack", "Aqua"};
        return prepareItemsList(MyConstants.FOOD_CAMEL_CASE,data);
    }

    public List<Items> getBeachSupplies(){
        String []data = {"Pelampung", "Baju renang", "Kacamata Renang"};
        return prepareItemsList(MyConstants.BEACH_SUPPLIES_CAMEL_CASE,data);
    }

    public List<Items> getCarSuppliesData(){
        String []data = {"Bantal Leher", "Stella Jeruk", "Tisu"};
        return prepareItemsList(MyConstants.CAR_SUPPLIES_CAMEL_CASE,data);
    }

    public List<Items> getNeedsData(){
        String []data = {"Backpack","Daily Bags","Book"};
        return prepareItemsList(MyConstants.NEEDS_CAMEL_CASE,data);
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

    public List<List<Items>> getAllData(){
        List<List<Items>> listAllOfItems = new ArrayList<>();
        listAllOfItems.clear();
        listAllOfItems.add(getBasicData());
        listAllOfItems.add(getClothingData());
        listAllOfItems.add(getBeachSupplies());
        listAllOfItems.add(getFoodData());
        listAllOfItems.add(getBabyNeedsData());
        listAllOfItems.add(getNeedsData());
        listAllOfItems.add(getTechnologyData());
        listAllOfItems.add(getPersonalCareData());
        listAllOfItems.add(getHealthData());
        listAllOfItems.add(getCarSuppliesData());
        return listAllOfItems;
    }

    public void persistAllData(){
        List<List<Items>> listofAllItems = getAllData();
        for (List<Items> list:listofAllItems){
            for (Items items:list){
                database.mainDao().saveItem(items);
            }
        }

        System.out.println("Data Added");
    }
}
