package Expression;

import Calc.Calc;

public class Rot implements Expression {
    @Override
    public void execute() {
        if(Calc.getStack().size() >= 3){
            Integer oper1 = Calc.getStack().pop();
            Integer oper2 = Calc.getStack().pop();
            Integer oper3 = Calc.getStack().pop();
            Calc.getStack().push(oper2);
            Calc.getStack().push(oper1);
            Calc.getStack().push(oper3);
        }
        else{
            System.err.println("There isn't three operand");
            System.exit(0);
        }
    }
}
