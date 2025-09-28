package br.github.math;

public class SimpleMath {

    private static final Integer QTD_ELEMENTOS_MEDIA = 2;

    public Double sum(Double number1, Double number2) {
        return number1 + number2;
    }

    public Double subtraction(Double number1, Double number2) {
        return number1 - number2;
    }

    public Double multiplication(Double number1, Double number2) {
        return number1 * number2;
    }

    public Double division(Double number1, Double number2) {
        return number1 / number2;
    }

    public Double mean(Double number1, Double number2) {
        return sum(number1, number2) / QTD_ELEMENTOS_MEDIA;
    }

    public Double squareRoot(Double number) {
        return Math.sqrt(number);
    }

}
