# 课程设计——金庸的江湖

[TOC]

## Assets

- 标签传播算法的初始标签

  asset/Label.txt



## Program Files

* code
    * src
* jar 
    * jar/Final_NoMainClass.jar



## Run

**Attention** : Final_NoMainClass.jar未指定主类。

主类可选：

1. Driver：总驱动，连续执行5个任务。

```
hadoop jar [path of Final_NoMainClass.jar] Driver [path of wuxia_novels in HDFS] [path of People_List_unique.txt in HDFS] [output path in HDFS] [times of PageRank iteration] [path of Labels.txt in HDFS] [times of LabePropagation iteration]
```

[path of wuxia_novels in HDFS]：218本小说在HDFS中的路径

[path of People_List_unique.txt in HDFS]：人名列表People_List_unique.txt在HDFS中的路径

[output path in HDFS]：总输出路径

[times of PageRank iteration]：PageRank算法迭代次数，推荐次数为30

[path of Labels.txt in HDFS]：LabePropagation算法的初始标签文件Labels.txt在HDFS中的路径

[times of LabePropagation iteration]：LabePropagation算法迭代次数，推荐次数为15

命令实例：

```
hadoop jar /home/2020st39/Final/Final_NoMainClass.jar Driver /MP_Data/task2/wuxia_novels /MP_Data/task2/People_List_unique.txt /user/2020st39/Final_out 30 /user/2020st39/Labels.txt 15
```



2. Job1_Driver：任务一驱动

```
hadoop jar [path of Final_NoMainClass.jar] Job1_Driver [path of wuxia_novels in HDFS] [path of People_List_unique.txt in HDFS] [output path of Job1 in HDFS]
```

[path of wuxia_novels in HDFS]：218本小说在HDFS中的路径

[path of People_List_unique.txt in HDFS]：人名列表People_List_unique.txt在HDFS中的路径

[output path of Job1 in HDFS]：任务一输出路径

命令实例：

```
hadoop jar /home/2020st39/Final/Final_NoMainClass.jar Job1_Driver /MP_Data/task2/wuxia_novels /MP_Data/task2/People_List_unique.txt /user/2020st39/Final_out/Job1_out
```



3. Job2_Driver：任务二驱动

```
hadoop jar [path of Final_NoMainClass.jar] Job2_Driver [output path of Job1 in HDFS] [output path of Job2 in HDFS]
```

[output path of Job1 in HDFS]：任务一输出路径

[output path of Job2 in HDFS]：任务二输出路径

命令实例：

```
hadoop jar /home/2020st39/Final/Final_NoMainClass.jar Job2_Driver /user/2020st39/Final_out/Job1_out /user/2020st39/Final_out/Job2_out
```



4. Job3_Driver：任务三驱动

```
hadoop jar [path of Final_NoMainClass.jar] Job3_Driver [output path of Job2 in HDFS] [output path of Job3 in HDFS]
```

[output path of Job2 in HDFS]：任务二输出路径

[output path of Job3 in HDFS]：任务三输出路径

命令实例：

```
hadoop jar /home/2020st39/Final/Final_NoMainClass.jar Job3_Driver /user/2020st39/Final_out/Job2_out /user/2020st39/Final_out/Job3_out
```



5. Job4_Driver：任务四驱动

```
hadoop jar [path of Final_NoMainClass.jar] Job4_Driver [output path of Job3 in HDFS] [output path of Job4 in HDFS] [times of PageRank iteration]
```

[output path of Job3 in HDFS]：任务三输出路径

[output path of Job4 in HDFS]：任务四输出路径

[times of PageRank iteration]：PageRank算法迭代次数，推荐次数为30

命令实例：

```
hadoop jar /home/2020st39/Final/Final_NoMainClass.jar Job4_Driver /user/2020st39/Final_out/Job3_out /user/2020st39/Final_out/Job4_out 30
```



6. Job5_Driver：任务五驱动

```
hadoop jar [path of Final_NoMainClass.jar] Job5_Driver [output path of Job3 in HDFS] [output path of Job5 in HDFS] [path of Labels.txt in HDFS] [times of LabePropagation  iteration]
```

[output path of Job3 in HDFS]：任务三输出路径

[output path of Job5 in HDFS]：任务五输出路径

[path of Labels.txt in HDFS]：LabePropagation算法的初始标签文件Labels.txt在HDFS中的路径

[times of LabePropagation iteration]：LabePropagation算法迭代次数，推荐次数为15

命令实例：

```
hadoop jar /home/2020st39/Final/Final_NoMainClass.jar Job5_Driver /user/2020st39/Final_out/Job3_out /user/2020st39/Final_out/Job5_out /user/2020st39/Labels.txt 15
```



## Data Visualization

- Gephi文件

  数据可视化/Label_degree.gephi

  数据可视化/Label_pagerank.gephi

- 可视化结果

  数据可视化/标签传播结果可视化（标签大小由度决定）.pdf

  数据可视化/标签传播结果可视化（标签大小由PageRank决定）.pdf