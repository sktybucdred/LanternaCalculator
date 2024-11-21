package org.example.Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Service.FunctionService;
import org.example.View.FunctionView;

import java.util.List;

public class FunctionController {
    private final FunctionService functionService;
    private final FunctionView functionView;

    public FunctionController(FunctionService functionService, FunctionView functionView) {
        this.functionService = functionService;
        this.functionView = functionView;
    }

    public void handleUserInput() {
        boolean inFunctionSection = true;
        while (inFunctionSection) {
            try {
                functionView.clearScreen();
                functionView.displayFunctionMenu();
                KeyStroke keyStroke = functionView.readUserInput();
                if (keyStroke.getKeyType() == KeyType.Escape) {
                    inFunctionSection = false;
                    continue;
                }
                String function = functionView.getFunctionFromUser("Wpisz swoją funkcję:", 5);
                if (function.isEmpty()) {
                    inFunctionSection = false;
                    continue;
                }
                functionService.validateFunction(function);
                List<Double> points = functionService.calculateFunction(function);
                functionView.clearScreen();
                functionView.drawChart(points);
                functionView.displayFunctionPrompt(function);
                functionView.flush();
                functionView.readUserInput(); // Czekaj na wciśnięcie klawisza przed ponownym wyświetleniem menu
            } catch (Exception e) {
                functionView.displayErrorMessage("Wystąpił błąd podczas obliczania. Spróbuj ponownie.");
            }
        }
    }
}
