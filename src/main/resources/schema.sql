drop table if exists EXCHANGE_RATE;
create table EXCHANGE_RATE(
  EX_ID bigint NOT NULL,
  EX_FROM varchar(10) NOT NULL,
  EX_TO varchar(10) NOT NULL,
  EX_RATE int NOT NULL,
  primary key (EX_ID),
);

create table EXCHANGE_NUM(
  EX_NUM bigint auto_increment,
  EX_TRANS varchar(20) NOT NULL
);