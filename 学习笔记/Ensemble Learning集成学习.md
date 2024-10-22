### 集成学习

它本身不是一个单独的机器学习算法，而是通过**构建并结合多个机器学习器来完成学习任务**。也就是我们常说的“博采众长”。集成学习可以用于分类问题集成，回归问题集成，特征选取集成，异常点检测集成等等，可以说所有的机器学习领域都可以看到集成学习的身影。

![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/ensemblelearning.png)

问题在于两点：**1.如何得到若干个体学习器；2.结合策略**。

#### 个体学习器

1. **同质**:个体学习器都是同一种类的。比如都是决策树，或者都是神经网络。**这种应用最广泛**。最多的模型是CART决策树和神经网络的组合。

2. **非同质**:不是同一种类。

其中同质的又可以分为两类：

1. **Boosting**思路：个体学习器之间存在强依赖，串行
2. **Bagging**思路：无强依赖，并行（random forest算在这里）。

#### Boosting

首先从训练集用初始权重训练出一个弱学习器1，根据弱学习的学习误差率表现来更新训练样本的权重，**使得之前弱学习器1学习误差率高的训练样本点的权重变高，使得这些误差率高的点在后面的弱学习器2中得到更多的重视**。然后基于调整权重后的训练集来训练弱学习器2.，如此重复进行，直到弱学习器数达到事先指定的数目T，最终将这T个弱学习器通过集合策略进行整合，得到最终的强学习器。

![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/el1.png)

AdaBoost, Gradient Boosting Tree.....

#### Bagging

![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/el2.png)

bagging的个体弱学习器的训练集是通过随机采样得到的。通过T次的随机采样，我们就可以得到T个采样集，对于这T个采样集，我们可以分别独立的训练出T个弱学习器，再对这T个弱学习器通过集合策略来得到最终的强学习器。其中随机采样是**自助采样法**（对于m个样本的原始训练集，我们每次先随机采集一个样本放入采样集，接着把该样本放回，也就是说下次采样时该样本仍有可能被采集到，这样采集m次，最终可以得到m个样本的采样集）。

#### 结合策略
1. 平均法（如果每个学习器都有权重，那么加上权重）；**bagging的分类问题常用**

2. 投票法，少数服从多数并且过半数（如果每个学习器都有权重，那么票数需要加上权重）；**bagging的回归问题**

3. 学习法，stacking。再加上一层学习器，也就是说，我们将训练集弱学习器的学习结果作为输入，将训练集的标记作为标记，重新训练一个学习器来得到最终结果。在这种情况下，我们将弱学习器称为初级学习器，将用于结合的学习器称为次级学习器meta-learner。对于测试集，我们首先用初级学习器预测一次，得到次级学习器的输入样本，再用次级学习器预测一次，得到最终的预测结果。**似乎常用于非同质学习器**

#### random forest
使用bagging思路。用cart决策树作为弱学习器。RF对决策树的建立做了改进：**对于普通的决策树，我们会在节点上所有的n个样本特征中选择一个最优的特征来做决策树的左右子树划分，但是RF通过随机选择节点上的一部分样本特征，这个数字小于n，假设为$n_{sub}$，然后在这些随机选择的$n_{sub}$个样本特征中，选择一个最优的特征来做决策树的左右子树划分。这样进一步增强了模型的泛化能力**。

$n_{sub}$越小，模型越健壮，对于训练集的拟合程度会变差。即$n_{sub}$越小，模型的方差会减小，偏倚会增大。实际中通过交叉验证来调整$n_{sub}$。假设属性数为d，**一般而言$n_{sub}=log_2d$是最合适的。**

算法流程：

input: $D={(x_1,y_1),(x_2,y_2),\cdots,(x_m,y_m)}$，弱分类器个数T

output: 强分类器f(x)

1. for t from 1 to T:

1.1 对训练集进行第t次随机采样，共采集m次，得到包含m个样本的采样集$D_t$

1.2 用采样集$D_t$训练第t个决策树模型$G_t(x)$，在训练决策树模型的节点的时候， 在节点上所有的样本特征中选择一部分样本特征， 在这些随机选择的部分样本特征中选择一个最优的特征来做决策树的左右子树划分

2. 分类：投票；回归：平均。

#### RF优点
1. 训练可以高度并行化，对于大数据时代的大样本训练速度有优势。

2. 由于可以随机选择决策树节点划分特征，这样在样本特征维度很高的时候，仍然能高效的训练模型。

3. 由于采用了随机采样，训练出的模型的方差小，泛化能力强

4. 对部分特征缺失不敏感。

5. 训练完之后能给出哪些feature更重要。

#### RF缺点
1. 在某些噪音比较大的样本集上，RF模型容易陷入过拟合。

2. 对于有不同取值的属性的数据，取值划分较多的属性（指某个属性的取值很多）会对随机森林产生更大的影响，所以随机森林在这种数据上产出的属性权值是不可信的。

#### RF不会overfitting？
Breiman： “*Random forests does not overfit. You can run as many trees as you want*” ？？？

其实会，只是因为两个随机（自助随机采样，属性随机）使得对于overfitting问题更有效。Breiman的意思应该是：随着tree增加，testing error不会急剧增大，会稳定在一个数值。但是我们通常讨论的overfitting是testing error比training error大很多，这个RF是无法避免的，但是会限制error的limit。


- [1] [集成学习原理](https://www.cnblogs.com/pinard/p/6131423.html)

- [2] [Does Random Forest overfit?](https://mljar.com/blog/random-forest-overfitting/)

- [3] [bagging与随机森林算法原理小结](https://www.cnblogs.com/pinard/p/6156009.html)

- [4] [机器学习 第十三章 集成学习（2） Bagging 随机森林 结合策略](https://zyzypeter.github.io/2017/08/09/machine-learning-ch13-Bagging-RF/#%E9%9A%8F%E6%9C%BA%E6%A3%AE%E6%9E%97)

