Course Demo
===

```shell
# initial
mysqld --defaults-file=my.cnf --initialize-insecure

# start
mysqld --defaults-file=my.cnf

# connect
mysql -h127.0.0.1 -P 3316 -uroot

# create account on master
mysql> CREATE USER 'repl'@'%' IDENTIFIED BY '123456';

# grant privileges
mysql> GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
mysql> flush privileges;

# check binlog position
mysql> show master status
+------------------+----------+--------------+------------------+-------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+------------------+----------+--------------+------------------+-------------------+
| mysql-bin.000002 |      856 |              |                  |                   |
+------------------+----------+--------------+------------------+-------------------+

# common commands
mysql> show schemas;
mysql> create schema test;
mysql> use test;
mysql> show tables;
mysql> create table t1(id int);
mysql> insert into t1(id) values(1),(2);

mysql> show warnings;
mysql> select host,user,plugin from mysql.user;


# set slave(replica)
mysql> CHANGE MASTER TO
	  MASTER_HOST='localhost',
	  MASTER_PORT=3316,
	  MASTER_USER='repl',
	  MASTER_PASSWORD='123456',
	  MASTER_LOG_FILE='mysql-bin.000002',
	  MASTER_LOG_POS=1684;

mysql> START SLAVE;	

mysql> show slave status;

# equals, above will remove in future.

mysql> CHANGE REPLICATION SOURCE TO
	  SOURCE_HOST='localhost',
	  SOURCE_PORT=3316,
	  SOURCE_USER='repl',
	  SOURCE_PASSWORD='123456',
	  SOURCE_LOG_FILE='mysql-bin.000002',
	  SOURCE_LOG_POS=1684;

mysql> START REPLICA;

mysql> show replica status;
```

## -
## my.cnf

```
# Default Homebrew MySQL server config
[mysqld]
# Only allow connections from localhost
bind-address = 127.0.0.1
mysqlx-bind-address = 127.0.0.1
port = 3316
server-id = 1
datadir = /Users/kai/dojo/java/GeekUniversityAdvancedJava/mysql/mysql1/data
socket = /tmp/mysql3316.sock
default_authentication_plugin=mysql_native_password

sql_mode = NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES
log_bin = mysql-bin
binlog-format = Row
```