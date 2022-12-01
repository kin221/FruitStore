package edu.ncucst.fruitweb.service;




import edu.ncucst.fruitweb.bean.FruitItem;
import edu.ncucst.fruitweb.dao.AdminDao;

import java.util.ArrayList;

public class AdminService {

    AdminDao dao=new AdminDao();
    public ArrayList<FruitItem> queryAllFruitItem(){
        ArrayList<FruitItem> data=dao.queryAllData();
        return data;
    }

    public boolean addFruitItem(String number,String name,String price,String unit){
        ArrayList<FruitItem> fruitList=queryAllFruitItem();
        for(int i=0;i<fruitList.size();i++){
            if(number.equals(fruitList.get(i).getNumber())){
                return false;
            }
        }
        FruitItem newFruit=new FruitItem(number,name,Double.parseDouble(price),unit);
        dao.addFruitItem(newFruit);
        return true;
    }

    public boolean updataFruitItem(String number,String name,String price,String unit){
        ArrayList<FruitItem> fruitList=queryAllFruitItem();
        for(int i=0;i<fruitList.size();i++){
            if(number.equals(fruitList.get(i).getNumber())){
                FruitItem newFruit=new FruitItem(number,name,Double.parseDouble(price),unit);
                dao.updataFruitItem(newFruit);
                return true;
            }
        }
        return false;
    }

    public boolean delFruitItem(String number){
        ArrayList<FruitItem> fruitList=queryAllFruitItem();
        for(int i=0;i<fruitList.size();i++){
            if(number.equals(fruitList.get(i).getNumber())){
                dao.delFruitItem(number);
                return true;
            }
        }
        return false;
    }

    public ArrayList<FruitItem> queryFruitItemByCon(String number,String name,String price,String unit){
        double dprice;
        try{
            dprice=Double.parseDouble(price);
        }catch (Exception e){
            dprice=0.0;
        }
        FruitItem queryFruit=new FruitItem(number,name,dprice,unit);
        ArrayList<FruitItem> data=dao.queryDataByCondition(queryFruit);
        return data;
    }

    public  ArrayList<FruitItem> showFruitlistOrderby(String sort){

        System.out.println(sort);
//        if ("1".equals(sort)){
//            sort="number";
//        }
//        if ("2".equals(sort)){
//            sort="fruitname";
//        }
//        if ("3".equals(sort)){
//            sort="price";
//        }
//        if ("4".equals(sort)){
//            sort="unit";
//        }
        ArrayList<FruitItem> fruitList = dao.queryAllDataOrderby(sort);
        return fruitList;
    }


}
