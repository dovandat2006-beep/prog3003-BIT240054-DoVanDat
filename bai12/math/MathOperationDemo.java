package com.example.bai12.math;

// Bai 1: Functional Interface MathOperation va Lambda Expression
public final class MathOperationDemo {

    private MathOperationDemo() {
    }

    public static void main(String[] args) {
        System.out.println("===== BAI 1: MATH OPERATION VOI LAMBDA EXPRESSION =====");

        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> {
            if (b == 0) {
                throw new IllegalArgumentException("Khong the chia cho 0");
            }
            return a / b;
        };

        printExample("Cong", 12, 8, addition);
        printExample("Tru", 12, 8, subtraction);
        printExample("Nhan", 12, 8, multiplication);
        printExample("Chia", 20, 5, division);
    }

    private static void printExample(String label, int a, int b, MathOperation operation) {
        int result = operation.compute(a, b);
        System.out.printf("%s: %d va %d => %d%n", label, a, b, result);
    }
}
