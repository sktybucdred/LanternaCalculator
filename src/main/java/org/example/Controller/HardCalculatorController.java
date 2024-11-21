package org.example.Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Model.HardCalculationResult;
import org.example.Service.HardCalculatorService;
import org.example.View.HardCalculatorView;

import java.io.IOException;

public class HardCalculatorController {
    private final HardCalculatorService calculatorService;
    private final HardCalculatorView view;
    private final CalculationHistoryController calculationHistoryController;
    public HardCalculatorController(HardCalculatorService calculatorService, HardCalculatorView view, CalculationHistoryController calculationHistoryController) {
        this.calculatorService = calculatorService;
        this.view = view;
        this.calculationHistoryController = calculationHistoryController;
    }

    public void handleUserInput() {
        boolean inCalculatorSection = true;
        while (inCalculatorSection) {
        try {
            view.clearScreen();
            // Wyświetlenie menu kalkulatora
            view.displayCalculatorMenu();
            KeyStroke keyStroke = view.readUserInput();

            // Sprawdzenie, czy wciśnięto klawisz Escape
            if (keyStroke.getKeyType() == KeyType.Escape) {
                inCalculatorSection = false;
                continue;
            }
            String expression = view.getExpressionFromUser("Wpisz swoje działanie:", 10);
            if (expression.isEmpty()) {
                inCalculatorSection = false;
                continue;
            }
            double result = calculatorService.calculate(expression);
            HardCalculationResult hardCalculationResult = new HardCalculationResult(expression, result);
            calculationHistoryController.addResult(hardCalculationResult);
            view.displayResult(result);

        } catch (Exception e) {
            // Jeśli coś poszło nie tak (np. błąd parsowania), wyświetl komunikat o błędzie
            view.displayErrorMessage("Wystąpił błąd podczas obliczania. Spróbuj ponownie.");
        }
        }
    }
}
