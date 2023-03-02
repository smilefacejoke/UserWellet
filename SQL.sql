CREATE TABLE 'Wallet'(
  'walletId' varchar2(12) not NULL,
  'userId' varchar2(12) NOT NULL,
  'money' int,
  'bankCardId' varchar2(24),
  PRIMARY KEY 'walletId'
);


CREATE TABLE 'TradeRecored'(
  'TradeId' varchar2(12) NOT NULL
  'walletId' varchar2(12) not NULL,
  'userId' varchar2(12) not NULL,
  'money' int,
  'tradeType' int,
  PRIMARY KEY 'TradeId'
);
  