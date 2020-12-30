package com.GUI_Project;

import java.nio.BufferOverflowException;

public class Operations {
    public Operations(){

    }
    public static Double Factorial (double value) throws OverflowException {
        if(value>31){
            throw new OverflowException(value+" Is Too Big To be Handled");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Value must be positive");
        }

        int result=1;

        for (int factor = 2; factor <= value; factor++) {
            result *= factor;
        }
        return Double.valueOf(result);
    }
    public static Double Combination (double n,double r) throws OverflowException {

        if(n < r){
            throw  new IllegalArgumentException("n mustn't be smaller than r");
        }
        Operations Com = new Operations();

        double result=Com.Factorial(n)/(Com.Factorial(r)*Com.Factorial(n-r));

        return result;
    }
    public static Double Permutation (double n,double r)throws OverflowException{

        if(n < r ){
            throw  new IllegalArgumentException("n mustn't be smaller than r");
        }
        Operations perm =new Operations();

        double result =perm.Factorial(n)/perm.Factorial(r);

        return result;
    }
}
