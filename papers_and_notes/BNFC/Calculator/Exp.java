package Calc.Absyn; // Java Package generated by the BNF Converter.

public abstract class Exp implements java.io.Serializable {
  public abstract <R,A> R accept(Exp.Visitor<R,A> v, A arg);
  public interface Visitor <R,A> {
    public R visit(Calc.Absyn.EAdd p, A arg);
    public R visit(Calc.Absyn.ESub p, A arg);
    public R visit(Calc.Absyn.EMul p, A arg);
    public R visit(Calc.Absyn.EDiv p, A arg);
    public R visit(Calc.Absyn.EInt p, A arg);

  }
  public abstract Integer eval() ;
}