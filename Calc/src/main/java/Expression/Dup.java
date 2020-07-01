package Expression;

import Calc.Calc;

public class Dup implements Expression {
    @Override
    public void execute() {
        if(Calc.getStack().size() >= 1){
            Integer oper = Calc.getStack().peek();
            Calc.getStack().push(oper);
        }
        else{
            System.err.println("There isn't one operand");
            System.exit(0);
        }
    }

}