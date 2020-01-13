# xin_phd_study
Weekly summaries of Xin Zhang. Latest summaries come first.

## Date: 2020/1/13 – The beginning of NEW Semester

1. **Progress:**
	* 
2. **Plan:**
  * Read two papers about CV in top tier
  * Leetcode (0/15)

## Date: 2019/12/14 – Weekly Summary

1. **Progress:**
	* In the past month, I finish the project "how to train DNN model with limited precision". Just run the code https://github.com/BertMoons/QuantizedNeuralNetworks-Keras-Tensorflow by modifying the parameters. Learnt QNN,定量神经网络, 训练模型使用fixed-point进行.
	* Dr. Wang's project: get the length of nail in pictures and recogize the word in picture. 
	* CSCE 750 final exam.
2. **Plan:**
  * how to use trained model
  * how to recoginze a lot of word in one picture
  * OCR, tesseract-ocr(https://github.com/UB-Mannheim/tesseract/wiki   https://blog.csdn.net/qq_31446377/article/details/81708006), text detection, color thresholding

## Date: 2019/11/18 – Weekly Summary

1. **Progress:**
	* run code https://github.com/BertMoons/QuantizedNeuralNetworks-Keras-Tensorflow on the server and get a rough result
	* CNN结构：若干Convolution Layer (ReLU) + pooling layer (no activation function) + 几层fully connected layers(FC for short) + softmax..... (https://www.cnblogs.com/pinard/p/6483207.html)
	### 1.1 Convolution Layer 抽取局部特征
	#### $s(i,j) = (X*W)(i,j) = \sum_m \sum_n X(i+m,j+n)W(m,n)$
	where X stands for the matrix of input, W is a convolution kernel. If X is 2-dimension, W is also 2; If X is multi-tensor, so does W. 对输入的图像的不同局部的矩阵和卷积核矩阵各个位置的元素相乘，然后相加得到。
	#### Take the following pic as an example. 
	图中的输入是一个二维的3x4的矩阵，而卷积核是一个2x2的矩阵。这里我们假设卷积是一次移动一个像素来卷积的，那么首先我们对输入的左上角2x2局部和卷积核卷积，即各个位置的元素相乘再相加，得到的输出矩阵S的$S_{00}$的元素，值为$aw+bx+ey+fz$。接着我们将输入的局部向右平移一个像素，现在是(b,c,f,g)四个元素构成的矩阵和卷积核来卷积，这样我们得到了输出矩阵S的$S_{01}$的元素，同样的方法，我们可以得到输出矩阵S的$S_{02}$，$S_{10}$，$S_{11}$，$S_{12}$的元素。
	![image](https://github.com/quz105/xin_phd_study/blob/master/images/ck.png)
	#### 这是输入为2维的情况，如果输入为三维（3个$7\times 7$的矩阵），要使用多个卷积核，看下面这个动图：
	使用了两个卷积核，先关注卷积核W0。由于输入是3个7x7的矩阵，或者说是7x7x3的张量，则我们对应的卷积核W0也必须最后一维是3的张量，这里卷积核W0的单个子矩阵维度为3x3。那么卷积核W0实际上是一个3x3x3的张量。这里的步幅为2，也就是每次卷积后会移动2个像素的位置。最终的卷积过程和上面的2维矩阵类似，上面是矩阵的卷积，即两个矩阵对应位置的元素相乘后相加。这里是张量的卷积，即两个张量的3个子矩阵卷积后，再把卷积的结果相加后再加上偏倚b。7x7x3的张量和3x3x3的卷积核张量W0卷积的结果是一个3x3的矩阵。由于我们有两个卷积核W0和W1，因此最后卷积的结果是两个3x3的矩阵。或者说卷积的结果是一个3x3x2的张量。
	http://cs231n.github.io/assets/conv-demo/index.html
	#### 卷积之后的矩阵元素还要通过ReLU进行变换
	### 1.2 Pooling layer 抽象和容错
	#### 对输入张量的各个子矩阵进行压缩。
	假如是2x2的池化，那么就将子矩阵的每2x2个元素变成一个元素，如果是3x3的池化，那么就将子矩阵的每3x3个元素变成一个元素，这样输入矩阵的维度就变小了。(average pooling and max pooling)
	### 1.3 FC 相当于DNN的隐含层，解释卷积层提取出来的信息
	#### 卷积层、池化层和激活函数层等操作是将原始数据映射到隐层特征空间的话，全连接层则起到将学到的*“分布式特征表示”映射到样本标记空间的作用*。
	在实际使用中，全连接层可由卷积操作实现：对前层是全连接的全连接层可以转化为卷积核为1x1的卷积；而前层是卷积层的全连接层可以转化为卷积核为$h\times w$的全局卷积，h和w分别为前层卷积结果的高和宽
	### 1.4 几个基本概念
	1.4.1 *feature map* 
	
	在cnn的每个卷积层，数据都是以三维形式存在的。你可以把它看成许多个二维图片叠在一起（像豆腐皮一样），其中每一个称为一个feature map。
	
	*如何生成：*输入层：在输入层，如果是灰度图片，那就只有一个feature map；如果是彩色图片，一般就是3个feature map（红绿蓝）。其它层：层与层之间会有若干个卷积核（kernel）（也称为过滤器），上一层每个feature map跟每个卷积核做卷积，都会产生下一层的一个feature map，有N个卷积核，下层就会产生N个feather map。(不同卷积得到的是对图片从不同角度的描述，下层核实简单的边缘检测器，上层是对简单核的叠加)
	
	1.4.2 *filter*
	
 	filter就是卷积核
	
	1.4.3 *channels*
	
	a.最初输入的图片样本的channels，取决于图片类型，比如RBG就是3
	
	b.卷积操作完成后输出的out_channels，取决于卷积核的数量
	
	c.卷积核中的in_channels，就是上一次卷积的out_channels
	

2. **Plan:**
  * 统计QNN代码的执行结果，并绘制图形(use matplotlib)
  2.1 其他所有参数相同，bit数不同，横坐标epoch，总坐标精确度+时间
  2.2  bit数相同，网络层数和宽度不同
  * 学习chap2

## Date: 2019/11/18 – Weekly Summary

1. **Progress:**
	* Contect to server via Putty
	* learn a part of chap2 《深度学习框架PyTorch：入门与实践》
	
2. **Plan:**
  * run code https://github.com/BertMoons/QuantizedNeuralNetworks-Keras-Tensorflow on the server and get a rough result
  * learn chap2 and chap3, the more the better

## Date: 2019/11/13 – Weekly Summary

1. **Progress:**
	* learn the basic knowledge about DNN 
	* read paper about how to use limited-precision to train model.

2. **Plan:**
  * learn https://github.com/chenyuntc/pytorch-book/blob/master/chapter2-%E5%BF%AB%E9%80%9F%E5%85%A5%E9%97%A8/chapter2:%20PyTorch%E5%BF%AB%E9%80%9F%E5%85%A5%E9%97%A8.ipynb
  * try to run some code about limited-precision

## Date: 2019/10/07 – Weekly Summary

1. **Progress:**
	* learn how to use OpenCV-python: OpenCV Python tutorial (https://www.youtube.com/watch?v=kdLM6AOd2vc&list=PLS1QulWo1RIa7D1O6skqDQ-JZ1GGHKK-K) and pysource (https://pysource.com/2018/01/31/object-detection-using-hsv-color-space-opencv-3-4-with-python-3-tutorial-9/)
	* OpenCV mannual (https://docs.opencv.org/4.1.1/)
	* See papers_and_notes/OpenCV for more information.

2. **Plan:**
  * Watch all the classes in youtube
  * Focus on Object detection

## Date: 2019/09/09 – Weekly Summary

1. **Progress:**
	* Read Paper DSN19' ML-based Fault Injection for Autonomous Vehicles (random FI + ML-based fault selection engine)
	![image](https://github.com/quz105/xin_phd_study/blob/master/images/ADSArchitecture.png)
	* An Overview of Publicly Available Driving Datasets and Virtual Testing Environments (37 dataset and 22 simulations)
	* Autoware.AI
	![image](https://github.com/quz105/xin_phd_study/blob/master/images/autowave.ai1.png)
	![image](https://github.com/quz105/xin_phd_study/blob/master/images/autowave.ai2.png)
	
2. **Challenges:**
	* The architecture of ADS is not standardized (exists difference).
	* Open-loop testing and closed-loop testing (in the overview paper). The former means using dataset to train and improve the ML-based algorithm, the latter means test in simulation environment or reality vehicles.
	


3. **Plan:**
  * Find more resource about the Plan&Control layer
  * Learn Apollo of Baidu and do a presentaion


## Date: 2019/09/02 – Weekly Summary

1. **Progress:**
	* Read Paper AirSim: High-Fidelity Visual and Physical simulation for autonomous vehicles
	* Learn how to use APIS to control vehicle in AirSim, front, rear, left, right and brake.
	* Use APIS to collect photoes generated by camera.
	* University of Passua (Alessio Gambi) focus on Software Testing, Testing self-driving cars and cloud computing. Focus on ADS in 2018 or 2019.
	![image](https://github.com/quz105/xin_phd_study/blob/master/images/UP%20work.png)
	
	
2. **Challenges:**
	* Difficult to generate the test environement. The model is pre-builded, no way to change it by code.
	* Consider Using Drone to control AV? 


3. **Plan:**
  * Read DSN19' ML-based Fault injection.
  * Find more papers about the testing of ADS.

## Date: 2019/08/26 – First presentation

1. **Progress:**
	* Read Paper Software Engineering for Automated Vehicles: Addressing the Needs of Cars That Run on Software and Data
	* ASFAULT: Testing Self-Driving Car Software Using Search-based Procedural Content Generation
	![image](https://github.com/quz105/xin_phd_study/blob/master/images/AsFault.jpg)
	![image](https://github.com/quz105/xin_phd_study/blob/master/images/genroad.jpg)
	* Generating Effective Test Cases for Self-Driving Cars from Police Reports
	![image](https://github.com/quz105/xin_phd_study/blob/master/images/AC3R.jpg)
	* AirSim by Microsoft
	
	
2. **Challenges:**
	* A bug in AsFault, the file direction is not right and a lot of function can find corresponding resourse.
	* Build the environment of AirSim. Pay attentation to the version of used software(VS2017, Python 3.4 and so on. Find more in https://github.com/Microsoft/AirSim/#how-to-use-it).


3. **Plan:**
  * Find more papers about how to test Auto-car software efficiently and effectively;
  * Try to learn AirSim (FSR 17’), find ways to control ego-cars and gather data;
  * Follow the work of University of Passau.
