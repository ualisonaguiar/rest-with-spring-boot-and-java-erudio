package br.github.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.github.math.SimpleMath;
import br.github.request.converters.NumberConvert;

@RestController
@RequestMapping("/math")
public class MathControllerer {

    private SimpleMath math = new SimpleMath();

    @GetMapping("sum/{number1}/{number2}")
    public Double sum(@PathVariable("number1") String number1, @PathVariable("number2") String number2) {

        NumberConvert.isNumeric(number1);
        NumberConvert.isNumeric(number2);

        return math.sum(NumberConvert.convertToDouble(number1), NumberConvert.convertToDouble(number2));
    }

    @GetMapping("subtraction/{number1}/{number2}")
    public Double subtraction(@PathVariable("number1") String number1, @PathVariable("number2") String number2) {
        NumberConvert.isNumeric(number1);
        NumberConvert.isNumeric(number2);

        return math.subtraction(NumberConvert.convertToDouble(number1), NumberConvert.convertToDouble(number2));
    }

    @GetMapping("multiplication/{number1}/{number2}")
    public Double multiplication(@PathVariable("number1") String number1, @PathVariable("number2") String number2) {
        NumberConvert.isNumeric(number1);
        NumberConvert.isNumeric(number2);

        return math.multiplication(NumberConvert.convertToDouble(number1), NumberConvert.convertToDouble(number2));
    }

    @GetMapping("division/{number1}/{number2}")
    public Double division(@PathVariable("number1") String number1, @PathVariable("number2") String number2) {
        NumberConvert.isNumeric(number1);
        NumberConvert.isNumeric(number2);

        return math.division(NumberConvert.convertToDouble(number1), NumberConvert.convertToDouble(number2));
    }

    @GetMapping("mean/{number1}/{number2}")
    public Double mean(@PathVariable("number1") String number1, @PathVariable("number2") String number2) {
        NumberConvert.isNumeric(number1);
        NumberConvert.isNumeric(number2);

        return math.mean(NumberConvert.convertToDouble(number1), NumberConvert.convertToDouble(number2));
    }

    @GetMapping("square/{number}")
    public Double squareRoot(@PathVariable("number") String number) {
        NumberConvert.isNumeric(number);

        return math.squareRoot(NumberConvert.convertToDouble(number));
    }

}
