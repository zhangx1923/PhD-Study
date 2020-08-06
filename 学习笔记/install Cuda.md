# windows
 ## 需要TensorFlow 和 CUDA相匹配，还需要查看下自己GPU的驱动版本，如果不匹配会出现很多问题。打开nvidia控制面板，点击帮助里面的系统信息，再切换到组件，即可看到当前支持的cuda版本，对应的去下载就可以
 
 ## https://www.tensorflow.org/install/source#tested_build_configurations
![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/Cuda%20install1.png)
![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/cuda%20install2.png)

##下载cuda

https://developer.nvidia.com/cuda-toolkit-archive


##安装选择自定义（取消driver component），否则会重新安装显卡驱动
提示CUDA Visual Studio Intergration，要求安装VS，不装，返回上一步，自定义安装，不选装Visual Studio Intergration模块，安装成功


##安装CuDnn（cudnn是CUDA的扩展加速库，说白了还是补丁https://developer.nvidia.com/rdp/cudnn-download）解压缩我们下载的CuDnn文件，得到3个文件夹：bin, include, lib。如下图所示，将这个三个文件夹复制到cuda的安装目录

##打开系统环境变量设置，确认CUDA_PATH和CUDA_PATH_V8.0已经存在
手动添加 “C:\ProgramData\NVIDIA GPU Computing Toolkit\版本号\bin”到Path里面。


##命令行nvcc -V查看是否安装成功


##（验证安装：通过NVIDIA提供的 deviceQuery.exe 和 bandwidthTest.exe 来查看GPU的状态，两者均在安装目录的 extras\demo_suite文件夹中，也可以按照https://zhuanlan.zhihu.com/p/29841665的介绍自己编译这两个文件，必要性不大。看到 result = pass代表安装测试成功）
