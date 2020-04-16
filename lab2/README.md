# Lab2 

## Inverted Index
### Files
* code
    * InvertedIndex/src
* jar 
    * InvertedIndex/jar/InvertedIndex.jar
    * other jar packets are libraries
* test
    * simple
        * InvertedIndex/test/InvertedIndexTest-in
        * InvertedIndex/test/InvertedIndexTest-out
    * whole
        * InvertedIndex/test/InvertedIndex-in
        * InvertedIndex/test/InvertedIndex-out

### Run
* modify the code
* Build -- BuildArtifacts -- Rebuild
* run in Terminal

```
cd /hadoop/sbin  
./start_all.sh  
./mr-jobhistory-daemon.sh start historyserver  
cd ~
hadoop fs -put [$input file in linux] [$input file in hdfs]  
hadoop fs -ls [$input file in hdfs]
hadoop jar [$absolute path of la2.jar in linux] [$input file in hdfs] [$output file in hdfs]
hadoop fs -cat [$output file in hdfs]/part-r-00000
cd /hadoop/sbin
./mr-jobhistory-daemon.sh stop historyserver
./stop_all.sh
```


## Global Sort
### Files
* code
    * GlobalSort/src
* jar 
    * GlobalSort/jar/GlobalSort.jar
    * other jar packets are libraries
* test
    * simple
        * GlobalSort/test/GlobalSortTest-in
        * GlobalSort/test/GlobalSortTest-out
    * whole
        * GlobalSort/test/GlobalSort-in
        * GlobalSort/test/GlobalSort-out

### Run
Same as InvertedIndex


## TD_IDF
### Files
* code
    * TD_IDF/src
* jar 
    * TD_IDF/jar/TD_IDF.jar
    * other jar packets are libraries
* test
    * simple
        * TD_IDF/test/TD_IDFTest-in
        * TD_IDF/test/TD_IDFTest-out
    * whole
        * TD_IDF/test/TD_IDF-in
        * TD_IDF/test/TD_IDF-out

### Run
Same as InvertedIndex
