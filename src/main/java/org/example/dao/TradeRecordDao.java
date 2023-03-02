package org.example.dao;

import lombok.Data;
import org.example.entity.TradeRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Component
public class TradeRecordDao {

    TradeRecord tradeRecord;

    Connection con;

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    public void connection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.con = DriverManager.getConnection("url", "usr", "password");
    }


    public String recordMessage(String userId, String walletId, int costMoney, int tradeType) {
        tradeRecord=new TradeRecord();
        Date day=new Date();
        try{
            try {
                connection();
                String sql="insert into TradeRecord (userId,walletId,tradeMoney,tradeType,tradeTime) value (?,?,?,?,?)";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1,userId);
                ps.setString(2,walletId);
                ps.setInt(3,costMoney);
                ps.setInt(4,tradeType);
                ps.setDate(5, (java.sql.Date) day);
                if (1==ps.executeUpdate(sql)){
                    return "操作成功";
                }else
                    return "操作失败";
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return "操作失败";
            }
        }catch (SQLException e){
            e.printStackTrace();
            return "操作失败";
        }
    }

    public List<TradeRecord> getTradeRecord(String userId, String walletId) {
        List<TradeRecord> records= new ArrayList<TradeRecord>();
        try{
            try {
                connection();
                String sql="select TradeRecord from Wallet where userId =? and walletId=?";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1,userId);
                ps.setString(2,walletId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    tradeRecord=new TradeRecord();
                    tradeRecord.setUseId(rs.getString("userId"));
                    tradeRecord.setWalletId(rs.getString("walletId"));
                    tradeRecord.setTradeMoney(rs.getInt("tradeMoney"));
                    tradeRecord.setTradeType(rs.getInt("tradeType"));
                    tradeRecord.setTradeTime(rs.getDate("tradeTime"));
                    records.add(tradeRecord);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                logger.error("数据库连接失败");
            }
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("数据库异常");
        }
        return records;
    }
}
