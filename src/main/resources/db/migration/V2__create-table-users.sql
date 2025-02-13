CREATE TABLE IF NOT EXISTS USERS (
  ID INT NOT NULL UNIQUE,
  NAME VARCHAR(255) NOT NULL,
  CPF VARCHAR(11) NOT NULL,
  EMAIL VARCHAR(50) NOT NULL,
  PASSWORD VARCHAR(50) NOT NULL,
  TYPE TEXT,
  WALLET_ID INT NOT NULL,

  FOREIGN KEY (WALLET_ID) REFERENCES WALLETS(ID),

  PRIMARY KEY (ID)
)