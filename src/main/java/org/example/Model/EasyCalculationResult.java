package org.example.Model;

public class EasyCalculationResult implements CalculationResult{
    private double operand1;
    private double operand2;
    private String operation;
    private double result;

    public EasyCalculationResult(double operand1, double operand2, String operation, double result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.result = result;
    }

    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public String getOperation() {
        return operation;
    }
    public String getExpression(){
        return operand1 + " " + operation + " " + operand2;
    }

    public double getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Wyrażenie: " + operand1 + operation + operand2 + " = " + result;
    }
}
