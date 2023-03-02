package org.example.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Wallet {

    private String walletId;

    private String userId;

    /**
     * 余额
     */
    private int money;

    /**
     * 银行卡号
     */
    private String bankCardId;

}
