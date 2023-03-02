package org.example.service.impl;

import org.example.constant.TradeType;
import org.example.dao.TradeRecordDao;
import org.example.dao.WalletDao;
import org.example.entity.TradeRecord;
import org.example.service.WalletService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private WalletDao walletDao;

    private TradeRecordDao tradeRecordDao;

    public WalletServiceImpl(WalletDao walletDao,TradeRecordDao tradeRecordDao){
        this.walletDao=walletDao;
        this.tradeRecordDao=tradeRecordDao;
    }

    /**
     * 查询用户钱包余额
     *
     * @param userId
     */
    @Override
    public int getRestMoney(String userId) {
        return walletDao.getRestMoney(userId);
    }

    /**
     * 用户消费钱包金额
     *
     * @param userId
     * @param costMoney
     */
    @Override
    public String costMoney(String userId,String walletId, int costMoney) {
        //进行钱包余额的操作
        walletDao.costMoney(userId,costMoney);
        //对钱包的操作进行记录
        String state=tradeRecordDao.recordMessage(userId,walletId,costMoney,TradeType.cost);
        return state;
    }

    /**
     * 用户退款
     *
     * @param userId
     * @param walletId
     * @param repayMoney
     */
    @Override
    public String repayMoney(String userId, String walletId, int repayMoney) {
        //钱包余额的操作
        walletDao.repayMoney(userId,repayMoney);
        //对钱包的操作进行记录
        String state=tradeRecordDao.recordMessage(userId,walletId,repayMoney,TradeType.tradeBack);
        return state;
    }

    /**
     * 查询用户钱包交易记录
     *  @param userId
     * @param walletId
     * @return
     */
    @Override
    public List<TradeRecord> getTradeRecord(String userId, String walletId) {
        return tradeRecordDao.getTradeRecord(userId,walletId);
    }
}
