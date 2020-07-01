package Expression;

import Calc.Calc;

public class Number implements Expression {
    int value;
    @Override
    public void execute(){
        Calc.getStack().push(value);
    };
    void setValue(int value){
        this.value = value;
    }
}
