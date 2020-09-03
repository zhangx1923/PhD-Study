### Bias Variance tradeoff
讨论模型预测时，需要理解预测误差（bias，variance）

#### what is bias
偏差/偏倚，表示模型的平均预测和实际值之间的差异。高偏倚表示模型并没有太关注训练数据或者过度简化模型，高错误！

#### what is variance
方差。表示模型面对同规模的不同数据集时的预测结果发生变动的程度。高方差表示模型太关注训练数据，不具有泛化性。此时在训练集上表现很好，而测试集误差大。

#### trade-off
![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/bias.png)

![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/variance.png)
 

model too simple: bias high;

model too large: variance high;

模型不可能在变的更复杂的同时变得更简单：we need to find the right/good balance without overfitting and underfitting the data.
 
训练模型的最终目的：在测试数据上拟合好（Error-test小）。但实际上test data往往拿不到，那么分两步：

1. 让error-train尽可能小

2. 让error-train尽可能等于error-test

(因为A小，而A=B，那么B就小)。对于第一步而言：如何时error train尽可能小？模型复杂化，参数多点，这会导致low bias；对于第二步而言，如何使error train尽可能等于error test呢？模型简单化，减少参数，具有通用性，这就是low variance
