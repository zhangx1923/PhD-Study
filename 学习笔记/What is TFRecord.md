## TFRecord
TFRecord 是谷歌推荐的一种二进制文件格式，理论上它可以保存任何格式的信息。Tensorflow 提供了丰富的 API 可以帮助我们轻松读写 TFRecord 文件。TFRecord 的核心内容在于内部有一系列的 Example ，Example 是 protocolbuf 协议下的消息体。protocolbuf本质上和 xml 及 json 没有多大的区别。

**相当于是protocolbuf定义了example的书写规范(？)**

一个 Example 消息体包含了一系列的 feature 属性。每一个 feature 是一个 map，也就是 key-value 的键值对。

key 取值是 String 类型。

而 value 是 Feature 类型的消息体，它的取值有 3 种：BytesList， FloatList， Int64List。需要注意的是，他们都是列表的形式。

protocolbuf 是通用的协议格式，对主流的编程语言都适用。所以这些 List 对应到 python 语言当中是 列表，而对于 Java 或者 C/C++ 来说他们就是数组。

### 为什么使用TFRecord

TFRecord 也不是非用不可，但它确实是谷歌官方推荐的文件格式。
1. 它特别适应于 Tensorflow ，或者说它就是为 Tensorflow 量身打造的。 
2. 因为 Tensorflow开发者众多，统一训练时数据的文件格式是一件很有意义的事情。也有助于降低学习成本和迁移成本。

### 创建
使用**TFWrite**

但制作之前，我们要先明确自己的目的，要把什么信息存储到 TFRecord 文件当中。

下面，举例说明。因为深度学习很多都是与图片集打交道，那么，我们可以尝试下把一张张的图片转换成 TFRecord 文件。

首先定义 Example 消息体。
![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/tfrecord1.jpg)

上面的 Example 表示，要将一张 cat 图片信息写进 TFRecord 当中，而图片信息包含了图片的名字，图片的维度信息还有图片的数据，分别对应了 name、shape、data 3 个 feature。

代码实现：

![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/tfrecord2.jpg)

运行上面的代码，就可以在当前目录生成 cat.tfrecord 文件。上面代码注释都比较详细，我挑重点来讲。

1. **将图片解码，然后转化成 string 数据，然后填充进去。**
2. **Feature 的 value 是列表，所以要记得加 []**
3. **example 需要调用 SerializetoString() 进行序列化后才行。**


### 读取
![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/tfrecord3.jpg)

代码比较简单，我也有给详细的注释，我挑重要的几点讲解一下。

1. 用 dataset 去读取 tfrecord 文件
2. 在解析 example 的时候，用现成的 API 就好了 tf.parse_single_example
3. 用 np.fromstring() 方法就可以获取解析后的 string 数据，记得数据格式还原成 np.uint8
4. 用 tf.image.encode_jpeg() 方法可以将图片数据编码成 jpeg 格式。
5. 用 tf.gfile.GFile 对象可以将图片数据保存到本地。
6. 因为将图片 shape 写进了 example 中，解析的时候必须制定维度，在这里是 [3] ,不然程序报错。
- [1] [https://juejin.im/entry/6844903624930230279](https://juejin.im/entry/6844903624930230279)
