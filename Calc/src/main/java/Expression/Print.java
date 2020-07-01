package Expression;

import Calc.Calc;

public class Print implements Expression {
    @Override
    public void execute() {
        if(Calc.getStack().size() >= 1){
            Integer oper1 = Calc.getStack().peek();
            System.out.print(oper1 + " ");
        }
        else{
            System.err.println("There isn't one operand");
            System.exit(0);
        }
    }
}
