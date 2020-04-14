# README
lab2 -- Inverted_Index of BigData.
## Files
* code in lab2/src
* jar packet is lab2/src/lab2.jar
* other jar packets are libraries

## Run
* modify the code
* Build -- BuildArtifacts -- Rebuild
* run in Terminal

```

cd /hadoop/sbin  
./start_all.sh  
./mr-jobhistory-daemon.sh start historyserver  
hadoop fs -put [$input file in ubuntu] [$input file in hdfs]  
hadoop fs -ls [$input file in hdfs]
hadoop jar [$absolute path of la2.jar in ubuntu] [$input file in hdfs] [$output file in hdfs]
hadoop fs -cat [$input file in hdfs]/part-r-00000
./mr-jobhistory-daemon.sh stop historyserver
./stop_all.sh

```