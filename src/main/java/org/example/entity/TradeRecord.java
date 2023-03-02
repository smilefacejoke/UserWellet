package org.example.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class TradeRecord {

    private String useId;

    private String walletId;

    private int tradeMoney;

    //交易类型
    private int tradeType;

    //交易时间
    private Date tradeTime;


}
