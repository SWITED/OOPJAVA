package Calc;


import Expression.Expression;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;

public class Calc {
    private static Stack<Integer> string = new Stack<>();
    private static TreeMap<String, Expression> mapOfDefine = new TreeMap<>();

    public static void main(String[] args) {

        ArrayList<Expression> expressionString = Parse.parseInput();
        for(Expression expression : expressionString){
            expression.execute();
        }
    }

    public static Stack<Integer> getStack(){
        return string;
    }

    public static TreeMap<String, Expression> getMapOfDefine() {
        return mapOfDefine;
    }
}
