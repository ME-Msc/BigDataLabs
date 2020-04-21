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
hadoop jar [$absolute path of InvertedIndex.jar in linux] [$input file in hdfs] [$output file in hdfs]
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


## TfIdf
### Files
* code
    * TfIdf/src
* jar 
    * TfIdf/jar/TfIdf.jar
    * other jar packets are libraries
* test
    * simple
        * InvertedIndex/test/InvertedIndexTest-out
        * TfIdf/test/TfIdfTest-out
        * InvertedIndex/test/InvertedIndexTest-in
    * whole
        * InvertedIndex/test/InvertedIndex-out
        * TfIdf/test/TfIdf-out
        * InvertedIndex/test/InvertedIndex-in

### Run
**Attention** : TfIdf.jar need 3 parameters.

```
hadoop jar [$absolute path of TfIdf.jar in linux] [$InvertedIndex-out file in hdfs] [$output file in hdfs] [$InvertedIndex-in file in hdfs] 
```

