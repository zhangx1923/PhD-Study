模型和别人的一样时候，可以直接加载，节约下训练模型需要的时间。
## 预知识
### pickle
pickle 是一个 python 中, 压缩/保存/提取 文件的模块. 最一般的使用方式非常简单
```
import pickle

a_dict = {'da': 111, 2: [23,1,4], '23': {1:2,'d':'sad'}}
# pickle a variable to a file
file = open('pickle_example.pickle', 'wb')
pickle.dump(a_dict, file)
file.close()
```
文件目录里多了一个 ‘pickle_example.pickle’ 文件, 这就是那个字典了.

提取的时候相对简单点, 同样我们以读的形式打开那个文件, 然后 load 进一个 python 的变量.
```
# reload a file to a variable
with open('pickle_example.pickle', 'rb') as file:
    a_dict1 =pickle.load(file)

print(a_dict1)
```

### torch.save 和 torch.load
保存和加载模型，这两者都是通过**pickle**实现的

### state_dict 状态字典
PyTorch 中，一个模型(torch.nn.Module)的可学习参数(也就是权重和偏置值)是包含在模型参数(model.parameters())中的，一个状态字典就是一个简单的 Python 的字典，**其键值对是每个网络层和其对应的参数张量**。模型的状态字典只包含带有可学习参数的网络层（比如卷积层、全连接层等）和注册的缓存（batchnorm的 running_mean）

### before LOAD a model or state_dict
我们首先需要定义一个与训练模型时相同结构的model类
**未完待续**

## load/save state_dict
当需要为预测保存一个模型的时候，只需要保存训练模型的可学习参数即可。采用 torch.save() 来保存模型的状态字典的做法可以更方便加载模型，这也是**推荐**这种做法的原因。

通常会用 .pt 或者 .pth 后缀来保存模型。

### save
```
torch.save(model.state_dict(), PATH)
```
### load
```
model = TheModelClass(*args, **kwargs)
model.load_state_dict(torch.load(PATH))
model.eval()
```
load_state_dict() 方法必须传入一个字典对象，而不是对象的保存路径，也就是说必须先反序列化字典对象，然后再调用该方法，也是例子中先采用 torch.load() ，而不是直接 model.load_state_dict(PATH)
## load/save the whole model
保存和加载模型都是采用非常直观的语法并且都只需要几行代码即可实现。这种实现保存模型的做法将是采用 Python 的 pickle 模块来保存整个模型，这种做法的缺点就是序列化后的数据是属于特定的类和指定的字典结构，原因就是 pickle 并没有保存模型类别，而是保存一个包含该类的文件路径，因此，当在其他项目或者在 refactors 后采用都可能出现错误。
### save
```
torch.save(model, PATH)
```
### load
```
# Model class must be defined somewhere
model = torch.load(PATH)
model.eval()
```


## Reference
1. [知乎：PyTorch | 保存和加载模型](https://zhuanlan.zhihu.com/p/82038049)
2. [some trained model](https://github.com/aaron-xichen/pytorch-playground)
3. [使用mnist预训练模型的demo](https://github.com/floydhub/mnist/blob/master/main.py)
