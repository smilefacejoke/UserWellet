package org.example.service;


import org.example.entity.TradeRecord;

import java.util.List;

public interface WalletService {

    /**
     * 查询用户钱包余额
     * @param userId
     */
    int getRestMoney(String userId);

    /**
     * 用户消费钱包金额
     * @param userId
     * @param costMoney
     */
    String costMoney(String userId,String walletId, int costMoney);

    /**
     * 用户退款
     * @param userId
     * @param walletId
     * @param repayMoney
     */
    String repayMoney(String userId, String walletId, int repayMoney);

    /**
     * 查询用户钱包交易记录
     * @param userId
     * @param walletId
     * @return
     */
    List<TradeRecord> getTradeRecord(String userId, String walletId);
}
