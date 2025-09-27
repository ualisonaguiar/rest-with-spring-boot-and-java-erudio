package br.github.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.github.exception.UnsupportedMathOperationException;

@RestController
@RequestMapping("/math")
public class MathControllerer {

    @GetMapping("sum/{number1}/{number2}")
    public Double sum(
            @PathVariable("number1") String number1,
            @PathVariable("number2") String number2) {

        return operacao("sum", number1, number2);
    }

    @GetMapping("subtraction/{number1}/{number2}")
    public Double subtraction(@PathVariable("number1") String number1,
            @PathVariable("number2") String number2) {

        return operacao("subtraction", number1, number2);
    }

    @GetMapping("multiplication/{number1}/{number2}")
    public Double multiplication(@PathVariable("number1") String number1,
            @PathVariable("number2") String number2) {

        return operacao("multiplication", number1, number2);
    }

    @GetMapping("division/{number1}/{number2}")
    public Double division(@PathVariable("number1") String number1,
            @PathVariable("number2") String number2) {

        return operacao("division", number1, number2);
    }

    @GetMapping("media/{number1}/{number2}")
    public Double media(@PathVariable("number1") String number1,
            @PathVariable("number2") String number2) {

        return sum(number1, number2) / 2;
    }

    @GetMapping("square/{number}")
    public Double squareRoot(@PathVariable("number") String number) {

        validateNumber(number);

        return Math.sqrt(convertToDouble(number));
    }

    public Double operacao(String operation, String number1, String number2) {
        validateNumber(number1);
        validateNumber(number2);

        Double num1 = convertToDouble(number1);
        Double num2 = convertToDouble(number2);

        return switch (operation.toLowerCase()) {
            case "sum" -> num1 + num2;
            case "subtraction" -> num1 - num2;
            case "multiplication" -> num1 * num2;
            case "division" -> num1 / num2;
            default -> throw new IllegalArgumentException("Operação inválida: " + operation);
        };
    }

    private void validateNumber(String number) {
        if (!isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
    }

    private Double convertToDouble(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) {
            return false;
        }

        String number = strNumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
