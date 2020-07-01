package Expression;

import Calc.Calc;

public class Sum implements Expression {
    @Override
    public void execute(){
        if(Calc.getStack().size() >= 2){
            Integer oper1 = Calc.getStack().pop();
            Integer oper2 = Calc.getStack().pop();
            Calc.getStack().push(oper1 + oper2);
        }
        else{
            System.err.println("There isn't two operand");
        }
    }
}
