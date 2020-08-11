## Image Semantic Segmentation 图片语义分割  综述

语义分割是在**像素级别**上的分类，属于同一类的像素都要被归为一类，因此语义分割是从像素级别来理解图像的。注意语义分割不同于实例分割，举例来说，如果一张照片中有多个人，对于语义分割来说，只要将所由人的像素都归为一类，但是实例分割还要将不同人的像素归为不同的类。也就是说**实例分割比语义分割更进一步**。

### 传统方法
1. TextonForest
2. 基于随机森林分类器

### 深度学习方法

1. **Patch classification**

图像是切成块喂给深度模型的，然后对像素进行分类。使用图像块的主要原因是因为全连接层需要固定大小的图像。

2. **FCN full convolutional neural**

FCN将网络全连接层用卷积取代，因此使任意图像大小的输入都变成可能，而且速度比Patch classification方法快很多。

尽管移除了全连接层，但是CNN模型用于语义分割还存在一个问题，就是**下采样操作**（比如，pooling）。pooling操作可以扩大感受野因而能够很好地整合上下文信息（context中文称为语境或者上下文，通俗的理解就是综合了更多的信息来进行决策），对high-level的任务（比如分类），这是很有效的。但同时，由于pooling下采样操作，使得分辨率降低，因此削弱了位置信息，而语义分割中需要score map和原图对齐，因此需要丰富的位置信息。

缩小图像（或称为下采样（subsampled）或降采样（downsampled））的主要目的有两个：1、使得图像符合显示区域的大小；2、生成对应图像的缩略图。放大图像（或称为上采样（upsampling）或图像插值（interpolating））的主要目的是放大原图像,从而可以显示在更高分辨率的显示设备上。

3. **encoder-decoder架构**

encoder-decoder是基于FCN的架构。encoder由于pooling逐渐减少空间维度，而decoder逐渐恢复空间维度和细节信息。通常从encoder到decoder还有shortcut connetction（捷径连接，也就是跨层连接）。其中U-net就是这种架构很流行的一种
![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/Unet.jpg)

4. **dilated/atrous （空洞卷积）架构**
这种结构代替了pooling，一方面它可以保持空间分辨率，另外一方面它由于可以扩大感受野因而可以很好地整合上下文信息
![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/atrous.jpg)


5. **Conditional Random Fields (CRFs) 条件随机场**
对分割结果进行后处理，deeplab系列文章都在使用
