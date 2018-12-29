package com.proj.service.operator;

public enum Operator {
    ADD {
        @Override
        public int apply(int a, int b) {
            return a+b;
        }
    };
    //MULTIPLY, SUBTRACT, DIVIDE


    public abstract int apply(int a, int b);
}