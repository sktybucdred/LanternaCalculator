import org.example.Service.HardCalculatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HardCalculatorServiceImplTest {
    private HardCalculatorServiceImpl calculatorService;
    @BeforeEach
    void setUp() {
        calculatorService = new HardCalculatorServiceImpl();
    }
    // 1. Basic Arithmetic Operations

    @Test
    @DisplayName("Test addition: 2 + 3 = 5")
    void testAddition() {
        String expression = "2 + 3";
        double expected = 5.0;
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "2 + 3 should equal 5");
    }

    @Test
    @DisplayName("Test subtraction: 5 - 2 = 3")
    void testSubtraction() {
        String expression = "5 - 2";
        double expected = 3.0;
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "5 - 2 should equal 3");
    }

    @Test
    @DisplayName("Test multiplication: 4 * 3 = 12")
    void testMultiplication() {
        String expression = "4 * 3";
        double expected = 12.0;
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "4 * 3 should equal 12");
    }

    @Test
    @DisplayName("Test division: 10 / 2 = 5")
    void testDivision() {
        String expression = "10 / 2";
        double expected = 5.0;
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "10 / 2 should equal 5");
    }

    @Test
    @DisplayName("Test exponentiation: 2 ^ 3 = 8")
    void testExponentiation() {
        String expression = "2 ^ 3";
        double expected = 8.0;
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "2 ^ 3 should equal 8");
    }

    // 2. Unary Operations

    @Test
    @DisplayName("Test unary minus at the start: -5 + 3 = -2")
    void testUnaryMinusAtStart() {
        String expression = "-5 + 3";
        double expected = -2.0;
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "-5 + 3 should equal -2");
    }

    @Test
    @DisplayName("Test unary minus after operator: 5 + -3 = 2")
    void testUnaryMinusAfterOperator() {
        String expression = "5 + (-3)";
        double expected = 2.0;
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "5 + -3 should equal 2");
    }

    // 3. Operator Precedence

    @Test
    @DisplayName("Test operator precedence: 2 + 3 * 4 = 14")
    void testOperatorPrecedenceMultiplicationBeforeAddition() {
        String expression = "2 + 3 * 4";
        double expected = 14.0; // 3 * 4 = 12; 2 + 12 = 14
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "2 + 3 * 4 should equal 14");
    }

    @Test
    @DisplayName("Test operator precedence: 2 ^ 3 * 4 = 32")
    void testOperatorPrecedenceExponentiationBeforeMultiplication() {
        String expression = "2 ^ 3 * 4";
        double expected = 32.0; // 2 ^ 3 = 8; 8 * 4 = 32
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "2 ^ 3 * 4 should equal 32");
    }

    // 4. Parentheses Handling

    @Test
    @DisplayName("Test parentheses: (2 + 3) * 4 = 20")
    void testExpressionWithParentheses() {
        String expression = "(2 + 3) * 4";
        double expected = 20.0; // (2 + 3) = 5; 5 * 4 = 20
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "(2 + 3) * 4 should equal 20");
    }

    @Test
    @DisplayName("Test nested parentheses: ((2 + 3) * (4 - 1)) ^ 2 = 225")
    void testNestedParentheses() {
        String expression = "((2 + 3) * (4 - 1)) ^ 2";
        double expected = 225.0; // (2+3)=5; (4-1)=3; 5*3=15; 15^2=225
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "((2 + 3) * (4 - 1)) ^ 2 should equal 225");
    }

    // 5. Error Handling

    @Test
    @DisplayName("Test invalid characters: 2 + a")
    void testInvalidCharacters() {
        String expression = "2 + a";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.calculate(expression);
        });
        assertTrue(exception.getMessage().contains("niepoprawny znak"));
    }

    @Test
    @DisplayName("Test mismatched parentheses: (2 + 3")
    void testMismatchedParentheses() {
        String expression = "2 + 3)";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.calculate(expression);
        });
        assertTrue(exception.getMessage().contains("Brakujący nawias otwierający"));
    }

    @Test
    @DisplayName("Test division by zero: 10 / 0 = Infinity")
    void testDivisionByZero() {
        String expression = "10 / 0";
        double result = calculatorService.calculate(expression);
        assertTrue(Double.isInfinite(result), "10 / 0 should result in Infinity");
    }

    @Test
    @DisplayName("Test empty expression")
    void testEmptyExpression() {
        String expression = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.calculate(expression);
        });
        assertTrue(exception.getMessage().contains("Puste wyrażenie"));
    }

    // 6. Additional Tests

    @Test
    @DisplayName("Test expression with multiple spaces:  2    +    3 * 4  = 14")
    void testExpressionWithMultipleSpaces() {
        String expression = "  2    +    3 * 4  ";
        double expected = 14.0; // 3 * 4 = 12; 2 + 12 = 14
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "Expression with multiple spaces should be parsed correctly");
    }

    @Test
    @DisplayName("Test expression with decimal numbers: 3.5 * 2 = 7.0")
    void testExpressionWithDecimals() {
        String expression = "3.5 * 2";
        double expected = 7.0;
        double result = calculatorService.calculate(expression);
        assertEquals(expected, result, "3.5 * 2 should equal 7.0");
    }
}
