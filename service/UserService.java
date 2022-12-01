package edu.ncucst.fruitweb.service;

import edu.ncucst.fruitweb.bean.User;
import edu.ncucst.fruitweb.dao.UserDao;

import java.util.ArrayList;

public class UserService {
    private UserDao userDao=new UserDao();
    public ArrayList<User> queryFruitItem() {
        //调用Dao层的获取所有数据方法获取所有数据
        ArrayList<User> data = userDao.queryAllData();
        //ArrayList<Fruitltem> data = adminDao.queryDataForCon();
//返回数据
        return data;
    }
    public boolean updataFruitItem( int id,String username,
                                    String password) {
        //调用Dao层的获取所有数据方法获取所有数据
        ArrayList<User> data = queryFruitItem();
        //使用输入的编号与所有数据对比
        for (int i = 0; i < data.size(); i++) {
            User user = data.get(i);
            //如果存在相同编号数据，则可以更新
            if(id==user.getId()) {
                //调用Dao层的删除指定编号数据方法
                //adminDao.delFr
                // uitItem(number);
                //如果没有重复编号，将数据封装为FruitItem对象
                User thisUser = new User(id,username,password);
                //调用Dao层的添加数据方法
                userDao.updataFruitItem(thisUser);
                // adminDao.addFruitItem(thisFruitItem);
                //在修改数据后，返回添加成功
                return true;
            }
        }
        return false;
    }
}

