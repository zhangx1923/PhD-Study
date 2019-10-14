# Notes of OpenCV -- basic functions
## How to install
pip install opencv-python
download opencv from github

```
import cv2
```

## Read and write picture
```
img = cv2.imread(图片地址，flag)
```
Flag表示读取的模式：
1   彩色读取RGB (three channels)
0  灰色读取
-1 包含alpha channel
阿尔法通道（α Channel或Alpha Channel）是指一张图片的透明和半透明度。例如：一个使用每个像素16比特存储的位图，对于图形中的每一个像素而言，可能以5个比特表示红色，5个比特表示绿色，5个比特表示蓝色，最后一个比特是阿尔法。在这种情况下，它要么表示透明要么不是，因为阿尔法比特只有0或1两种不同表示的可能性。又如一个使用32个比特存储的位图，以每8个比特分别表示红绿蓝及阿尔法通道。在这种情况下，就不光可以表示透明还是不透明，阿尔法通道还可以表示256级的半透明度，因为阿尔法通道有8个比特可以有256种不同的数据表示可能性
```
cv2.imwrite(保存的路径，图片实例img)
```

## open a window to show the picture
open a window 
```
cv2.imshow(‘弹出框的名字’,图片实例img)
```
close a window
```
cv2.destoryAllWindows()
```
keep a window open
```
while(true):
  cv2.waitkey(delay=0)
```
返回值为按键的ascii码
waitKey(0)将无限显示窗口，直到按下任意按键退出延迟事件（适用于显示图像）。如果delay大于0，例如，waitKey(25)将每隔至少25ms显示视频的一帧图像（适用于显示视频帧），如果要按键退出，则需要将waitKey(25)与一个按键值（ASCII码）比较

## operations about video
read video
```
cap = cv2.VideoCapture(x)
```
x可以是打开的视频文件路径，也可以是数字，当数字时：0（或者-1）表示默认摄像头，如果有多个摄像头，可以从1开始尝试

show the video
```
while(cap.isOpened()):
  ret, frame = cap.read() #前一个变量存放是否读取成功（布尔），后一个存放读取的内容
  cv2.imshow(‘frame’, frame)
```

get the attribution of video
```
cap.get(属性名)  #for example, 属性名=cv2.CAP_PROP_FRAME_WIDTH, which stands for 宽
```

set the attribution of video
```
cap.set(cv2.CAP_PROP_FRAME_WIDTH, 1280)
cap.set(cv2.CAP_PROP_FRAME_HEIGHT, 720)
```
每个属性都有对应的整数值，比如宽对应3，高对应4
可以设置任意的大小，但如果给的值不符合条件，则设置失效，不提示错误

write video
```
out = cv2.VideoWriter(‘output.avi’, fourcc, 20.0, (640,480))
while(True):
  ret, frame = cap.read()
  out.write(frame)
```
VideoWrite第一个参数是保存的路径，第二个参数是fourcc，第三个参数是每秒多少帧，第四个是视频的大小
fourcc是Four-Character Codes，确定当前视频的编码格式。Some of the more well known FOURCCs include "DIVX", "XVID", "H264", "DX50". But these are just a few of the hundreds in use.
```
fourcc = cv2.VideoWriter_fourcc(*’XVID’)
```
or
```
fourcc = cv2.VideoWriter_fourcc(‘X’, ’V’, ‘I’, ‘D’)
```


after finishing the operation:
```
cap.release()
```
release the resource

## Draw graphics
```
img = cv2.imread('xx.jpg',1)
#绘制线
img = cv2.line(img, 起点坐标，终点坐标，颜色，线宽从1递增)
#绘制箭头线
img = cv2.arrowedLine(img, 起点，终点，颜色，线宽)
#绘制矩形
img = cv2.rectangle(img, 左上，右下，颜色，线宽)
#绘制圆形
img = cv2.circle(img, 圆心，半径，颜色，线宽)线宽取-1，填充内部
#写字
img = cv2.putText(img, ‘要加的字’，起点坐标，font-face，字体大小，颜色，线宽，line type)
#font-face = cv2.FONT_HERSHEY_SIMPLEX
#line type = cv2.LINE_AA
```

ROI(region of interest)

How to copy a region to any position? (know the target region's position)
```
img = cv2.imread(‘dd.jpg’)
ball = img[100:100, 200:200]
img[300:300,400:400] = ball
```
将100,100到200,200区域的内容复制到300,300到400,400的区间


## Merge two pictures into one
```
img = cv2.imread(‘dd.jpg’)
img2 = cv2.imread(‘dd1.jpg’)
```
想要融合，需要保证两个图片的size是一样的，因此需要先resize
AT:这里的resize是通过插值的方法实现的（将低像素的图片变得稍微清晰，就是通过插值算法完成）
```
img = cv2.resize(img, (512,512))
img2 = cv2.resize(img2, (512,512))
```
Merge:
```
dst = cv2.add(img,img2)
#或者加权融合
dst = cv2.addWeighted(img,0.9,img2,0.1)#前一个图片占0.9（颜色深），后一个图片占0.1（颜色淡）
```


## Split a picture to R G B
```
#将图片划分为b,g,r三个部分
b,g,r = cv2.split(img)
#还原
img = cv2.merge(b,g,r)
```

## Construct the trackbar
before construction
需要给cv弹出的window命名，这样才能知道将trackbar放到哪个window上
```
#如果这个名字和imshow的不一样，那么会弹出一个空白的窗口；如果一样，则为同一个窗口
cv.nameWindow(‘image’)
```
constructe a trackbar
```
cv.createTrackbar(‘B’, ‘image’,0,255,nothing)
```
第一个参数是该trackbar的名字，第二个是window的名字，第三第四参数分别表示最小值和最大值，最后一个是callback函数的名字，每次数值变动都会调用这个函数
```
while(True):
   cv.imshow(‘image’,img)
   k = cv.waitKey(1)
   If k ==27:
      Break
   #获取当前trackbar的值，第一个参数是bar的名字，第二个参数是window的名字
   b = cv.getTrackbarPos(‘B’,’image’)
```

## Object detection and object tracking using HSV color space
HSV vs RGB https://handmap.github.io/hsv-vs-rgb/












