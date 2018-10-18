create table TRANSACTION (
  id long not null auto_increment,
  day varchar(15) not null,
  time varchar(15) not null,
  description varchar(100) not null,
  amount decimal(10,2) not null,
  type varchar(1) not null,
  primary key(id)
  );