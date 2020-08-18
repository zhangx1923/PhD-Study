## Deep Neural Networks 深度神经网络
这是深度学习的基础

### 感知机模型
![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/ganzhiji.PNG)

### 神经网络
神经网络就是对感知机的扩展，加上了
1. 隐藏层，对模型的表达能力进行了扩展
2. 神经元输出不止一个，模型更灵活运用于分类和回归，而非仅仅二分类
3. 扩展激活函数，不仅仅是sign函数

![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/nn1.png)

### DNN
多层隐藏层的神经网络。

输入层，隐藏层和输出层,如下图示例，一般来说第一层是输入层，最后一层是输出层，而中间的层数都是隐藏层。

![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/dnn.png)

层与层之间是全连接的。由于DNN层数多，则我们的线性关系系数w和偏倚b的数量也就是很多了

![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/dnn1.png)

![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/dnn2.png)

### Forward propagation algorithm （FP）
从输入计算得到输出，即为FP算法的过程

![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/dnn3.png)

![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/dnn4.PNG)

![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/DNN5.PNG)

注意：**w:n行m列，每一行对应于该层的一个神经元，每一行的某一列对应上一层对应行的一个神经元，b：n行1列**。这也是为什么我们将第二层的第4个神经元到第三层的第2个神经元的线性系数定义为w^3_{2 4}而不是w^3_{4 2}的原因。这样我们可以直接计算wx而不用先计算w的转置。

### Backward propagation algorithm (BP)

#### 什么是反向传播算法
监督学习中，假设我们有m个样本，样本维度为$n_{in}$


- [1] [深度神经网络（DNN）模型与前向传播算法](https://www.cnblogs.com/pinard/p/6418668.html)
- [2] [深度神经网络（DNN）反向传播算法(BP)](https://www.cnblogs.com/pinard/p/6422831.html)
- [3] [深度神经网络（DNN）损失函数和激活函数的选择](https://www.cnblogs.com/pinard/p/6437495.html)
