package org.example.dao;

import lombok.Data;
import org.example.entity.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;


@Data
@Component
public class WalletDao {

    Wallet wallet;

    Connection con;

    private final Logger logger= LoggerFactory.getLogger(this.getClass());



    public void connection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.con = DriverManager.getConnection("url", "usr", "password");
    }



    /**
     * 获取当前钱包余额
     */
    public int getRestMoney(String userId){
        try{
            try {
                connection();
                String sql="select money from Wallet where userId =?";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1,userId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    wallet.setMoney(rs.getInt("money"));
                }
                return wallet.getMoney();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return -1;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 减少钱包金额
     * @param userId
     * @param costMoney
     */
    public String costMoney(String userId,int costMoney){
        try{
            int restMoney=getRestMoney(userId);
            if (restMoney<costMoney){
                return "用户金额小于消费金额";
            }
            restMoney-=costMoney;
            String sql="update wallet set money=? where userId =?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,restMoney);
            ps.setString(2,userId);
            if (1==ps.executeUpdate()){
                return "操作成功";
            } else
                return "操作失败";
        }catch (SQLException e){
            logger.error("更新失败"+e);
            return "操作失败";
        }
    }


    /**
     * 用户退款
     * @param userId
     * @param repayMoney
     */
    public String repayMoney(String userId, int repayMoney) {
        try{
            int restMoney=getRestMoney(userId);
            restMoney+=repayMoney;
            String sql="update wallet set money=? where userId =?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,restMoney);
            ps.setString(2,userId);
            if (1==ps.executeUpdate()){
                return "操作成功";
            } else
                return "操作失败";
        }catch (SQLException e){
            logger.error("更新失败"+e);
            return "操作失败";
        }
    }
}
