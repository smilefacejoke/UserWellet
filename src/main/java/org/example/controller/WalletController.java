package org.example.controller;
import org.example.entity.TradeRecord;
import org.example.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    private WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService=walletService;
    }

    /**
     * 查询用户钱包剩余余额
     * @param userId 用户id主键
     */
    @RequestMapping("/getRestMoney")
    public String getRestMoney(@RequestParam String userId){

        try{
            //非法校验
            if(userId.isEmpty()){
                return "用户主键为空";
            }
            return String.valueOf(walletService.getRestMoney(userId));
        }catch (Exception e){
            return (e+"系统异常");
        }
    }

    /**
     * 用户消费
     * @param userId
     * @param walletId
     * @param costMoney
     */
    @RequestMapping("/costMoney")
    public String costMoney(@RequestParam String userId,@RequestParam String walletId,@RequestParam int costMoney){
        String state=new String();
        try{
            //非法校验
            if(userId.isEmpty()||costMoney<0){
                 logger.error("输入数据有误");
            }
            state = walletService.costMoney(userId,walletId,costMoney);
        }catch (Exception e){
            logger.error(e+"系统异常");
        }
        return state;
    }

    /**
     * 用户退款
     * @param userId
     * @param walletId
     * @param repayMoney
     */
    @RequestMapping("/repayMoney")
    public String repayMoney(@RequestParam String userId,@RequestParam String walletId,@RequestParam int repayMoney){
        String state=new String();
        try{
            //非法校验
            if(userId.isEmpty()||repayMoney<0){
                logger.error("输入数据有误");
            }
            state = walletService.repayMoney(userId,walletId,repayMoney);
        }catch (Exception e){
            logger.error(e+"系统异常");
        }
        return state;
    }

    /**
     * 查询用户钱包交易记录
     *@param userId
     *@param walletId
     */
    @RequestMapping("/getTradeRecord")
    public List<TradeRecord> getTradeRecord(@RequestParam String userId,@RequestParam String walletId){
        try{
            //非法校验
            if(userId.isEmpty()||walletId.isEmpty()){
                logger.error("输入数据有误");
            }
        }catch (Exception e){
            logger.error(e+"系统异常");
        }
        return walletService.getTradeRecord(userId,walletId);
    }



}
