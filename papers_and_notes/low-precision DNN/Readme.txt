PhD first year Dr.Hu jianjun's independent study

Low precision trainning for DNN


TRANNING：
32-->16 deep learning with limited numerical precision 
Main thought:
stochastic round scheme, instand of round to nearest

IEEE的FP32的格式。(sign, exponent, mantissa)=1,8,23

16-->8 https://www.ibm.com/blogs/research/2018/12/8-bit-precision-training/
       Training Deep Neural Networks with 8-bit Floating Point Numbers

Main thought：
SR + Chunk

Unlike inference, training with numbers represented with less than 16 bits has been very challenging 
due to the need to maintain fidelity of the gradient computations and weight updates during back-propagation.

why < 16 is difficult
1. when all the operands and convolution computations are reduced to 8 bits, most DNNs suffer noticeable accuracy degradation.
2. impacts the convergence of DNN training.
3. reducing the bit precision of weight updates to 16 impacts accuracy

许多16bit train的算法，还是需要用到32bit，为什么？SWAMPING问题
Even with 16-bit data representations, these techniques require the partial products to be accumulated
in 32-bits and subsequently rounded down to 16 bits for the following computation. In addition, in
all cases, a 32-bit copy of the weights is maintained to preserve the fidelity of the weight update
process.

NEW FP8 and FP16
(1,5,2) (1,6,9)         实验得到的结论






INFERENCE：
8-->4 inference 4 to 2 (2 paper)


4-->2 https://www.ibm.com/blogs/research/2019/04/2-bit-precision/
      accurate and efficient 2 bit quantized neural network




plan:
train MNIST use pytorch
then change the code to use 16 bit
then change to 8-16 bit
