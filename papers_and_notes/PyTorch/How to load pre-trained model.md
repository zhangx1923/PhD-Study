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

## How to load


## How to save




## Reference
1. [知乎：PyTorch | 保存和加载模型](https://zhuanlan.zhihu.com/p/82038049)
2. 
