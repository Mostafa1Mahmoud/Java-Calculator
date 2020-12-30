package com.GUI_Project;
import com.fathzer.soft.javaluator.*;

public class Operate {
    public Operate(){}
    DoubleEvaluator Evaluator =new DoubleEvaluator();
    private String Expression=new String();
    private Double result = 0.0 ;
    public double Result(String Expression) throws IllegalArgumentException, OverflowException {
        this.Expression=Expression;
        Double result=Evaluator.evaluate(Expression);
        this.result=result;
        return result;
    }
    public String toString(){
        return Expression;
    }
    public Double Result(){
        return result;
    }
}