drop table if exists clients.account,clients.user;
create table clients.account (id bigint not null auto_increment, begin_date date, contract ENUM ('PREMIUM','BASIC'), country ENUM('BRASIL','EUA','CANADA','CHINA','UNITED_KINGDOM'), email varchar(255), password varchar(255), username varchar(255),primary key (id));
create table clients.user (name varchar(255) not null, age integer, account_id bigint not null, primary key (name), foreign key(account_id) references clients.account(id));