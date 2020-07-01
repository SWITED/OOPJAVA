package Calc;

import Expression.Expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parse {
    public static ArrayList<Expression> parseInput(){
        Scanner console = new Scanner(System.in);

        while(true){
            String line = console.nextLine();
            System.out.println(line);
            if(line.startsWith("define")){
                parseDefine(console, line);
            }

            if(!line.startsWith("define") && !line.startsWith("#")){
                return makeExpressionLine(new ArrayList<String>(Arrays.asList(line.split(" "))));
            }
        }
    }

    private static void parseDefine(Scanner console, String line){
        ArrayList<String> exp = new ArrayList<>(Arrays.asList(line.split(" ")));
        String name = exp.get(1);

        while(true){
            if(exp.get(exp.size() - 1).equals(";")){
                break;
            }
            System.out.println(exp.get(exp.size() - 1));
            exp.addAll(Arrays.asList(console.nextLine().split(" ")));
        }
        Calc.getMapOfDefine().put(name, Expression.of(exp));
    }

    private static ArrayList<Expression> makeExpressionLine(List<String> arrayList){
        ArrayList<Expression> finish = new ArrayList<Expression>();
        while (!arrayList.isEmpty()){
            Expression def = Calc.getMapOfDefine().get(arrayList.get(0));
            if(def != null){
                finish.add(def);
                arrayList.remove(0);
            }else{
                finish.add(Expression.of(arrayList));
            }
        }

        return finish;
    }
}
