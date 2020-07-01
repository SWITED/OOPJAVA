package Expression;

import java.util.ArrayList;

public class Define implements Expression {
    private ArrayList<Expression> operations = new ArrayList<>();
    @Override
    public void execute() {
        operations.forEach(e -> e.execute());
    }

    public void setOperations(ArrayList<Expression> operations) {
        this.operations = operations;
    }
}
