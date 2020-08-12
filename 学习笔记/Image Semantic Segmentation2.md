## 图片语义分割 几种具体方法
![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/multiSegmentMethod.jpg)
### FCN
Fully Convolutional Networks for Semantic Segmentation 2014

现在的深度学习语义分割模型基本上都是基于FCN发展而来的，它是开山鼻祖。

1. 全卷积化(Fully Convolutional)：用于解决逐像素(pixel-wise)的预测问题。通过将基础网络(例如VGG)最后面几个全连接层换成卷积层，可实现任意大小的图像输入，并且输出图像大小与输入相对应；
2. 反卷积(deconvolution) ：上采样操作，用于恢复图片尺寸，方便后续进行逐像素预测;
3. 跳跃结构(skip architecture)：用于融合高低层特征信息。通过跨层连接的结构，结合了网络浅层的细(fine-grain)粒度信息信息以及深层的粗糙(coarse)信息，以实现精准的分割任务。


### SegNet
SegNet: A Deep Convolutional Encoder-Decoder Architecture for Image Segmentation 2015

### Dilated convolution


### DeepLab(v1,v2)


### RefineNet


### PSPNet

### deeplab v3


- [1] [https://zhuanlan.zhihu.com/p/37801090](https://zhuanlan.zhihu.com/p/37801090)
- [2] [https://arxiv.org/pdf/1704.06857.pdf](https://arxiv.org/pdf/1704.06857.pdf)
- [3] [https://blog.qure.ai/notes/semantic-segmentation-deep-learning-review](https://blog.qure.ai/notes/semantic-segmentation-deep-learning-review)
