drop table if exists clients.user,clients.account;
create table clients.account (id bigint not null auto_increment, begin_date date, contract varchar(255), country varchar(255), email varchar(255), password varchar(255), username varchar(255),primary key (id));
create table clients.user (name varchar(255) not null, age integer, account_id bigint not null, primary key (name), foreign key(account_id) references clients.account(id));