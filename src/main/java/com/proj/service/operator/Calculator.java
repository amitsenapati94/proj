package com.proj.service.operator;

public class Calculator {

    public static void main(String args[])
    {
        Calculator calculator = new Calculator();

        //int result = calculator.calculateUsingFactory(4,5, "add");
        int result = calculator.calculate(4,5, Operator.valueOf("ADD"));

        System.out.println("result: "+result);
    }

    public int calculateUsingFactory(int a, int b, String operator) {
        Operation targetOperation = OperatorFactory
                .getOperation(operator)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
        return targetOperation.apply(a, b);
    }

    public int calculate(int a, int b, Operator operator) {
        return operator.apply(a, b);
    }
}
