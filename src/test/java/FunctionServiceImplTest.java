import org.example.Service.FunctionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionServiceImplTest {
    private FunctionServiceImpl functionService;
    @BeforeEach
    public void setUp() {
        functionService = new FunctionServiceImpl();
    }
    @Test
    @DisplayName("Test linear function: f(x) = 2x + 3")
    void testLinearFunction() {
        String function = "2x + 3";
        List<Double> results = functionService.calculateFunction(function);
        double x = -10.0;
        for (Double y : results) {
            double expected = 2 * x + 3;
            expected = Math.round(expected * 100.0) / 100.0; // Round to 2 decimal places
            assertEquals(expected, y, "For x = " + x + ", expected y = " + expected + " but got " + y);
            x += 0.5;
        }
    }

    @Test
    @DisplayName("Test quadratic function: f(x) = x^2")
    void testQuadraticFunction() {
        String function = "x^2";
        List<Double> results = functionService.calculateFunction(function);

        double x = -10.0;
        for (Double y : results) {
            double expected = Math.pow(x, 2);
            expected = Math.round(expected * 100.0) / 100.0;
            assertEquals(expected, y, "For x = " + x + ", expected y = " + expected + " but got " + y);
            x += 0.5;
        }
    }

    @Test
    @DisplayName("Test cubic function: f(x) = x^3 - 4x + 1")
    void testCubicFunction() {
        String function = "x^3 -4x +1";
        List<Double> results = functionService.calculateFunction(function);

        double x = -10.0;
        for (Double y : results) {
            double expected = Math.pow(x, 3) - 4 * x + 1;
            expected = Math.round(expected * 100.0) / 100.0;
            assertEquals(expected, y, "For x = " + x + ", expected y = " + expected + " but got " + y);
            x += 0.5;
        }
    }

    @Test
    @DisplayName("Test polynomial function: f(x) = 2x^2 + 3x + 5")
    void testPolynomialFunction() {
        String function = "2x^2 + 3x + 5";
        List<Double> results = functionService.calculateFunction(function);

        double x = -10.0;
        for (Double y : results) {
            double expected = 2 * Math.pow(x, 2) + 3 * x + 5;
            expected = Math.round(expected * 100.0) / 100.0;
            assertEquals(expected, y, "For x = " + x + ", expected y = " + expected + " but got " + y);
            x += 0.5;
        }
    }

    @Test
    @DisplayName("Test function with multiple operations: f(x) = (x + 2)(x - 3)")
    void testMultipleOperationsFunction() {
        String function = "(x + 2)(x - 3)";
        List<Double> results = functionService.calculateFunction(function);

        double x = -10.0;
        for (Double y : results) {
            double expected = (x + 2) * (x - 3);
            expected = Math.round(expected * 100.0) / 100.0;
            assertEquals(expected, y, "For x = " + x + ", expected y = " + expected + " but got " + y);
            x += 0.5;
        }
    }

    @Test
    @DisplayName("Test function with unary minus: f(x) = -(x^2) + 4x")
    void testUnaryMinusFunction() {
        String function = "-(x^2) + 4x";
        List<Double> results = functionService.calculateFunction(function);

        double x = -10.0;
        for (Double y : results) {
            double expected = -Math.pow(x, 2) + 4 * x;
            expected = Math.round(expected * 100.0) / 100.0;
            assertEquals(expected, y, "For x = " + x + ", expected y = " + expected + " but got " + y);
            x += 0.5;
        }
    }

    @Test
    @DisplayName("Test function with division: f(x) = (x + 1) / (x - 1)")
    void testDivisionFunction() {
        String function = "(x + 1)/(x - 1)";
        List<Double> results = functionService.calculateFunction(function);

        double x = -10.0;
        for (Double y : results) {
            if (x == 1.0) {
                assertTrue(Double.isNaN(y), "For x = " + x + ", expected y = Nan due to division by zero.");
            } else {
                double expected = (x + 1) / (x - 1);
                expected = Math.round(expected * 100.0) / 100.0;
                assertEquals(expected, y, "For x = " + x + ", expected y = " + expected + " but got " + y);
            }
            x += 0.5;
        }
    }

    @Test
    @DisplayName("Test invalid function with unsupported characters")
    void testInvalidFunction() {
        String function = "2x + a"; // 'a' is invalid

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            functionService.calculateFunction(function);
        });

        String expectedMessage = "funkcja zawiera niepoprawny znak";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.toLowerCase().contains(expectedMessage.toLowerCase()),
                "Expected error message to contain '" + expectedMessage + "', but got '" + actualMessage + "'");
    }

    @Test
    @DisplayName("Test empty function")
    void testEmptyFunction() {
        String function = "";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            functionService.calculateFunction(function);
        });

        String expectedMessage = "Funkcja nie może być pusta";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.toLowerCase().contains(expectedMessage.toLowerCase()),
                "Expected error message to contain '" + expectedMessage + "', but got '" + actualMessage + "'");
    }
}
