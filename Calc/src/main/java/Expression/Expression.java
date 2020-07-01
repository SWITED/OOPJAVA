package Expression;

import Calc.Calc;
import org.graalvm.compiler.lir.LIRInstruction;

import java.util.ArrayList;
import java.util.List;

public interface Expression {
    void execute();
    static Expression of (List<String> inputString){
        String type = inputString.remove(0);


        switch (type){
            case "+": return new Sum();
            case "-": return new Sub();
            case "*": return new Mul();
            case "/": return new Div();
            case "sqrt": return new Sqrt();
            case "print": return new Print();
            case "swap": return new Swap();
            case "drop": return new Drop();
            case "rot": return new Rot();
            case "dup": return new Dup();
            case "define":
                inputString.remove(0);
                Define t = new Define();
                ArrayList<Expression> defineList = new ArrayList<Expression>();
                while(!inputString.get(0).equals(";")){
                    defineList.add(Expression.of(inputString));
                }
                t.setOperations(defineList);
                return t;
            case "[":
                Cycle c = new Cycle();
                ArrayList<Expression> bodyList = new ArrayList<Expression>();
                while(!inputString.get(0).equals("]")){
                    bodyList.add(Expression.of(inputString));
                }
                inputString.remove(0);
                c.setBodeCycle(bodyList);
                return c;

            default:
                try{
                    int y = Integer.parseInt(type);
                    Number number = new Number();
                    number.setValue(y);
                    return  number;
                }catch (NumberFormatException e){
                    System.err.print("There isn't such operation");
                    System.exit(0);
                }
                return null;
        }
    }

}
