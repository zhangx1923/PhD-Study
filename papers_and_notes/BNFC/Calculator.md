# This is a note for CSCE531 Project 1b

Implement a calculator in Haskell as described in Section 2.6 [R].

Implement a calculator in Java as described in Section 2.7 [R]. Please use BNFC and Java under Ubuntu
for this. 

<b>[R]</b>Aarne Ranta [R]. Implementing Programming Languages: An Introduction to Compilers and Interpreters. College Publications, 2012. ISBN: 978-1-84890-064-6.

Reference:

[R]

http://www.cse.chalmers.se/edu/year/2011/course/TIN321/lectures/bnfc-tutorial.html#toc6

## Haskell
在包含calc.cf文件的文件夹下执行： (这一步就是project1a)

bnfc -m Calc.cf

make

之后测试得到的抽象语法等信息，现在我们要做的就是将这些抽象的信息转化为计算器的计算结果。

## Java
在包含calc.cf文件的文件夹下执行： (这一步就是project1a)

bnfc -m -java Calc.cf

make

之后进去生成的Calc/Absyn文件夹，修改EInt.java, EAdd.java, EMul.java, EDiv.java, ESub.java, Exp.java文件：修改方式为在每个文件里面的类中添加一个eval()方法。
### Exp.java
添加一行
'''
public abstract Integer eval() ;
'''

### EInt.java
添加
'''
public Integer eval() {return integer_ ;}
'''

### EDiv.java
添加
'''
public Integer eval() {return exp_1.eval() / exp_2.eval() ;}
'''

### EAdd.java
添加
'''
public Integer eval() {return exp_1.eval() + exp_2.eval() ;}
'''

### ESub.java
添加
'''
public Integer eval() {return exp_1.eval() - exp_2.eval() ;}
'''

### EMul.java
添加
'''
public Integer eval() {return exp_1.eval() * exp_2.eval() ;}
'''

接下来复制Calc文件夹中的Test.java文件，重命名为Calculator.java。将里面的Test类更换为：
'''
public class Calculator {
  public static void main(String args[]) throws Exception
  {
    Yylex l = new Yylex(System.in) ;
    parser p = new parser(l) ;
    Calc.Absyn.Exp parse_tree = p.pExp() ;
    System.out.println(parse_tree.eval());
  }
}
'''
执行javac Calc/Calculator.java即可
