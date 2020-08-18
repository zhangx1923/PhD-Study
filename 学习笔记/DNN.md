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
监督学习中，假设我们有m个样本，输入向量x，样本维度为$n_{in}$；输出向量y，样本维度为$n_{out}$。我们需要用这m个样本训练一个模型，使得一个新的样本$x_{test}$来的时候，我们能够预测$y_{test}$的值。

如果我们采用DNN的模型，即我们使输入层有$n_{in}$个神经元，而输出层有$n_{out}$个神经元。再加上一些含有若干神经元的隐藏层。此时我们需要找到合适的所有隐藏层和输出层对应的线性系数矩阵W,偏倚向量b,让所有的训练样本输入计算出的输出尽可能的等于或很接近样本输出。怎么找到合适的参数呢？

以用一个合适的损失函数来度量训练样本的输出损失，接着对这个损失函数进行优化求最小化的极值，对应的一系列线性系数矩阵W,偏倚向量b即为我们的最终结果。在DNN中，损失函数优化极值求解的过程最常见的一般是通过**梯度下降法**来一步步迭代完成的，当然也可以是其他的迭代方法比如牛顿法与拟牛顿法。

**对DNN的损失函数用梯度下降法进行迭代优化求极小值的过程即为我们的反向传播算法。**

#### 基本思路
在进行DNN反向传播算法前，我们需要选择一个损失函数，来度量训练样本计算出的输出和真实的训练样本输出之间的损失。训练样本计算出的输出是是随机选择一系列W,b,用前向传播算法计算出来的。这里我们使用最常见的均方差来度量损失。总结下DNN反向传播算法的过程。由于梯度下降法有**批量（Batch），小批量(mini-Batch)，随机**三个变种，为了简化描述，这里我们以最基本的批量梯度下降法为例来描述反向传播算法。实际上在业界使用最多的是mini-Batch的梯度下降法。不过区别仅仅在于迭代时训练样本的选择而已。

　**输入**: 总层数L，以及各隐藏层与输出层的神经元个数，激活函数，损失函数，迭代步长α,最大迭代次数MAX与停止迭代阈值ϵ，输入的m个训练样本{(x1,y1),(x2,y2),...,(xm,ym)}
　**输出**：各隐藏层与输出层的线性关系系数矩阵W和偏倚向量b
 
 **step1.** 初始化各隐藏层与输出层的线性关系系数矩阵W和偏倚向量b的值为一个随机值。
 
 **step2.** for iter from 1 to MAX:
 
  **2.1     ** for i from 1 to m:
            
  **2.1.1          ** 设置DNN的输入为$x_i$
                   
  **2.1.2          ** for l from 2 to L: 前向传播算法计算$a^{i,l}=\sigma(z^{i,l})=\sigma(W^l a^{i,l-1}+b^l)$

  **2.1.3          ** 通过损失函数计算输出层的$\delta ^{i,L}$
  
  **2.1.4          ** for l from L-1 to 2: 反向传播计算$\delta^{i,l}=(W^{l+1})^T\delta^{i,l+1}\odot \delta^{'}(z^{i,l})$

  **2.2     ** for l = 2 to L, 更新第l层的$w^l=w^l-\alpha \sum_{i=1}^m \delta^{i,l} (a^{i,l-1})^T$和$b^l = b^l-\alpha \sum_{i=1}^m \delta ^{i,l}$
 
  **2.3     ** 如果所有W和b的变化值都小于迭代阈值，跳出循环到step3
 
 **step3.** 输出W和b
 
- [1] [深度神经网络（DNN）模型与前向传播算法](https://www.cnblogs.com/pinard/p/6418668.html)
- [2] [深度神经网络（DNN）反向传播算法(BP)](https://www.cnblogs.com/pinard/p/6422831.html)
- [3] [深度神经网络（DNN）损失函数和激活函数的选择](https://www.cnblogs.com/pinard/p/6437495.html)
