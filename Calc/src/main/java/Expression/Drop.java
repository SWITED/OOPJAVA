package Expression;

import Calc.Calc;

public class Drop implements Expression {
    public void execute() {
        if(Calc.getStack().size() >= 1){
            Calc.getStack().pop();
        }
        else{
            System.err.println("There isn't one operand");
            System.exit(0);
        }
    }

    Drop(){};
}
