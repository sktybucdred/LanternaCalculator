package org.example.Service;
import java.util.ArrayList;
import java.util.List;

public class FunctionServiceImpl implements FunctionService {
    public List<Double> calculateFunction(String function) {
        if(function == null || function.isEmpty()) {
            throw new IllegalArgumentException("Funkcja nie może być pusta");
        }
        validateFunction(function);
        List<Double> results = new ArrayList<>();
        for(double x = -10; x <= 10; x+=0.5) {
            String currentExpression = preprocessFunction(function);
            currentExpression = currentExpression.replace("x", Double.toString(x));
            try{
                double y = new HardCalculatorServiceImpl().calculate(currentExpression);
                if(Double.isInfinite(y) || Double.isNaN(y)) {
                    results.add(Double.NaN);
                    continue;
                }
                double roundedValue = Math.round(y * 100.0) / 100.0;
                results.add(roundedValue);
            }catch(Exception e){
                results.add(Double.NaN);
            }
        }
        return results;
    }
    private String preprocessFunction(String function) {
        // Dodaj '*' między liczbą a 'x' (np. 4x -> 4*x)
        function = function.replaceAll("(\\d)(x)", "$1*$2");
        // Dodaj '*' między 'x' a liczbą (np. x2 -> x*2)
        function = function.replaceAll("(x)(\\d)", "$1*$2");
        // Dodaj '*' między ')' a liczbą lub 'x' (np. )(x -> )*x)
        function = function.replaceAll("\\)(\\d|x)", ")*$1");
        // Dodaj '*' między liczbą lub 'x' a '(' (np. x( -> x*( )
        function = function.replaceAll("(\\d|x)\\(", "$1*(");
        // Dodaj '*' między ')' a '(' (np. )( -> )*()
        function = function.replaceAll("\\)\\(", ")*(");
        return function;
    }

    public void validateFunction(String function) {
        for (char token : function.toCharArray()) {
            if (!Character.isDigit(token) && token != 'x' && token != '+' && token != '-' && token != '*' &&
                    token != '/' && token != '(' && token != ')' && token != '.' && token != '^' && !Character.isWhitespace(token)) {
                throw new IllegalArgumentException("Funkcja zawiera niepoprawny znak: " + token);
            }
        }
    }

}
