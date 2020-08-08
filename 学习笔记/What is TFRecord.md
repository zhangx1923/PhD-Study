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


### 读取

- [1] [https://juejin.im/entry/6844903624930230279](https://juejin.im/entry/6844903624930230279)
