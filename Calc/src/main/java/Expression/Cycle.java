package Expression;

import Calc.Calc;

import java.util.ArrayList;

public class Cycle implements Expression {
    private ArrayList<Expression> bodeCycle = new ArrayList<Expression>();
    @Override
    public void execute() {
        while(Calc.getStack().pop() != 0) {
            bodeCycle.forEach(e -> e.execute());
        }
    }

    public void setBodeCycle(ArrayList<Expression> bodeCycle) {
        this.bodeCycle = bodeCycle;
    }
}
