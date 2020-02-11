# This is a note for CSCE531 Project 1a

Install BNFC on your computer. 

Follow the steps described in sections 2.1 and 2.2 [R] and verify that you obtain the files described there, for both Haskell and Java. 

Submit a single pdf document with a script or screenshot showing the files in your directory of folder. (One makefile will be renamed by BNCF; that is fine.)

# What we need
BNF Convert for windows

cygwin, so that we can use linux tools, such as make and so on

Haskell platform

JDK

Jlex+ CUP:a lexical analyzer generator + Parser Generator

# Steps:
1. https://bnfc.digitalgrammars.com/download/

下载windows版本的exe文件，改名为bnfc.exe，将该文件的路径添加到环境变量里面的系统变量path中。打开cmd，输入bnfc，测试是否成功

2. http://www.cygwin.com/

下载cygwin，可以在windows里面使用linux的命令和工具。下载，安装（install from internet->use system proxy settings,安装的时候需要选择如下：

https://blog.csdn.net/Baron_wu/article/details/83149641

https://blog.csdn.net/lvsehaiyang1993/article/details/81027399

https://www.cnblogs.com/zhenggege/p/10724122.html

），运行cygwin,测试g++ --version, gcc --version, make等指令。之后将cygwin的bin文件夹的路径放到环境变量的系统变量path里面。在任意打开的cmd窗口就可以使用linux的命令了，比如ls，make等

3. 新建文件夹，名字是自己的名字，将Calc.cf文件放进去

4. 执行

bnfc -m Calc.cf

得到haskell版本的输出。执行在该文件夹中执行make指令。
https://www.haskell.org/platform/windows.html
下载window版本的platform进行安装，通过在cmd窗口输入ghci看是否安装成功。
成功之后可以在Calc.cf的文件夹中执行make命令了

下载最新版会报错，需要下载2014年的版本：
https://www.haskell.org/platform/prior.html

测试：写一个test.txt文件：5+6*6然后
TestCalc test.txt即可或者直接echo 5+6*6 | TestCalc

5. bnfc -m -java Calc.cf
得到java版本的输出 
首先安装java jdk

https://www.oracle.com/technetwork/java/javase/archive-139210.html

下载jdk1.5的版本，然后配置环境变量：

JAVA_HOME：D:\Java\jdk1.5

CLASSPATH：.;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;%JAVA_HOME%\lib\J\;%JAVA_HOME%\lib\C\;

Path添加：%JAVA_HOME%\jre\bin和%JAVA_HOME%\bin

再安装JLex
https://www.cs.princeton.edu/~appel/modern/java/JLex/
下载200k的源码，放在java安装路径的J/JLex里面，执行
（文件夹J/JLex需要自己在D:\Java\jdk1.5\lib中新建）
Javac Main.java
测试java JLex.Main

再安装CUP
https://www.cs.princeton.edu/~appel/modern/java/CUP/
Source code (CUP 0.10k) (compressed tar)
下载源代码，将压缩包里面的java_cup文件夹放在文件夹C中（文件夹C需要自己在D:\Java\jdk1.5\lib中新建）
测试 java java_cup.Main

切换到bnfc包含Calc.cf文件的路径中：
执行bnfc -m -java Calc.cf
Make
即可
测试方法同Haskell