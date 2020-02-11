# Distance Measurement 

Focus on how to use binocular stereo camera to measure the distance between object and the camera

Further, we can 

1. reconstructe the 3D picture from this technology (if we can get the distance between camera and every pixel)

2. get the world coordinates of the target object

# Key step
1. 相机内参、外参标定 (camera Calibration) -> 

2. 立体匹配 (area-based, energy-based and so on，极线约束：根据极线，匹配的点只可能位于极线上，减少搜索的空间实际上要保证两个相机完全共面且参数一致是非常困难的，而且计算过程中也会产生误差累积，因此对于左图的一个点，其在右图的对应点不一定恰好在极线上。但是应该是在极线附近，所以搜索范围需要适当放宽。) -> 

3. 光学三角形求解(Parallax视差+triangulation methods )

视差怎么计算：

需要先完成点对点的匹配，之后分析左相机的该像素点(xl, yl)和右相机中对应点(xr, yr)的具体坐标，那么parallax=xr-xl

# some useful links and paper
立体匹配算法很好的综述（Stereo vision algorithm）:

Review of stereo vision algorithms: from software to hardware

综述了SGBM，GC，BM，带数据:

A Comparative Evaluation of Leading Dense Stereo Vision Algorithms using OpenCV

上述三个算法的对比+代码:

https://blog.csdn.net/cxgincsu/article/details/74451940

怎么计算parallax

https://blog.csdn.net/Tommy_wxie/article/details/75085158

双目测距原理

https://blog.csdn.net/bit_cs2010/article/details/52829190

http://www.sohu.com/a/203027140_100007727
