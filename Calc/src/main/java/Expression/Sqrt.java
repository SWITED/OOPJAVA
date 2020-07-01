package Expression;

import Calc.Calc;

public class Sqrt implements Expression {
    @Override
    public void execute(){
        if(Calc.getStack().size() >= 1){
            Calc.getStack().push((int) Math.sqrt(Calc.getStack().pop()));
        }
        else{
            System.err.println("There isn't one operand");
            System.exit(0);
        }
    }

}
