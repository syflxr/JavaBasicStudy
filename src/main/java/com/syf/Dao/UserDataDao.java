package com.syf.Dao;

import com.syf.Entity.UserDataEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
public class UserDataDao extends AbstractDao<UserDataEntity> {
    public UserDataDao(String s){}
    private static volatile UserDataDao instance;
    public void testSpring(){
        System.out.println("spring test");
    }

    public static UserDataDao getInstance(){
        if(instance==null){
            synchronized (UserDataDao.class){
                if(instance==null){
                    return new UserDataDao("1");
                }
            }
        }
        return instance;
    }


    public ResultSet sqlQuery(String sql){
        ResultSet resultSet=null;
        try {
            Statement statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public void insert(UserDataEntity entity){
        try {
            PreparedStatement ps=connection.prepareStatement("INSERT INTO "+getTableName()+" (time,count,lasttime,name) VALUE (?,?,?,?)");
            ps.setObject(1,new Date());
            ps.setObject(2,entity.getCount());
            ps.setObject(3,entity.getLastTime());
            ps.setObject(4,entity.getName());

            ps.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update2(){
        try {
            PreparedStatement ps=connection.prepareStatement("UPDATE "+getTableName()+" SET lasttime=? where lasttime<?");
            ps.setObject(1,new Date());
            ps.setObject(2,new Date());

            ps.executeUpdate();
        }catch (Exception e) {

        }

    }
    public void update(UserDataEntity entity){
        try {
            PreparedStatement ps=connection.prepareStatement("UPDATE userdata SET count=?,lasttime=? WHERE id=?");
            ps.setObject(1,entity.getCount());
            ps.setObject(2,new Date());
            ps.setObject(3,entity.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public int getCount(){
        int result=0;
        try{
            String sql="select COUNT(id) from userdata";
            Statement s=connection.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            while(resultSet.next()){
                result = resultSet.getInt(1);
            }
        }catch (Exception e){

        }
        return result;

    }

    public void getSpecialCount(){
        try{
            PreparedStatement ps=connection.prepareStatement("select * from userdata where lasttime<?");
            ps.setObject(1,new Date());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                UserDataEntity temp=new UserDataEntity();
                Integer id =resultSet.getInt(1);
                String time=resultSet.getString(2);
                Integer count=resultSet.getInt(3);
                String lastTime=resultSet.getString(4);
                System.out.println(id+" "+time+" "+count+" "+lastTime);
            }

        }catch(Exception e){

        }


    }
    public void delete(Integer id){
        try {
            PreparedStatement ps=connection.prepareStatement("DELETE FROM"+getTableName()+" WHERE id=?");
            ps.setObject(1,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Map<Integer,UserDataEntity> getMap() throws Exception{
        Map<Integer,UserDataEntity> result=new HashMap<Integer, UserDataEntity>();
        List<UserDataEntity> list = getList(null);
        list.forEach(ele->result.put(ele.getId(),ele));
        return result;
    }
    public List<UserDataEntity> getList(String condition) throws Exception{
        if(StringUtils.isEmpty(condition)){
            condition="1=1";
        }
        List<UserDataEntity> result=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        sb.append("select * from ").append(getTableName()).append(" where ").append(condition);
        Statement s=connection.createStatement();
        ResultSet rs=s.executeQuery(sb.toString());
        while (rs.next()){
            UserDataEntity entity=new UserDataEntity();
            entity.setId(rs.getInt(1));
            entity.setTime(rs.getDate(2));
            entity.setCount(rs.getInt(3));
            entity.setLastTime(rs.getDate(4));
            entity.setName(rs.getString(5));
            result.add(entity);
        }
        return result;
    }
    public static void main(String[] args) {

    }
}
