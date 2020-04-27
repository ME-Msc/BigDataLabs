# Lab3

## Hive
### Install
* http://archive.apache.org/dist/hive/hive-2.3.7/
* tar -zxvf apache-hive-2.3.7-bin
* gedit ~/.bashrc
    * export HIVE_HOME=/home/hadoop/app/hive-2.3.7
    * export PATH=$HIVE_HOME/bin:$PATH
* source ~/.bashrc
* cd /home/hadoop/app/hive-2.3.7/bin
### Initialize
* ./schematool -initSchema -dbType derby
* rename "metastore_db" to "metastore_db.tmp"
* ./schematool -initSchema -dbType derby
* https://blog.csdn.net/hl17200178/article/details/97259094

## MyJoin
https://www.jianshu.com/p/8eb068f862bf
### Files
* code
    * MyJoin/src
* jar 
    * MyJoin/jar/MyJoin.jar
    * other jar packets are libraries
* test
    * MyJoin/test/lab3-in
    * MyJoin/test/lab3-out

## Run
```
hadoop jar [$path of MyJoin.jar] [$path of lab3-in] [$path of lab3-out]
hadoop fs -rm /lab3-out/_SUCCESS
cd /home/hadoop/app/hive-2.3.7/bin
hive
hive> create table orders(order_id int,order_date string,product_id string,product_name string,product_price int,amount int) row format delimited fields terminated by '\t' location '/lab3-out';
ctrl+d to exit hive
```

## Images 
Pictures including errors & results are in folder named "images".