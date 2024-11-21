package org.example.Controller;

import org.example.Model.CalculationHistory;
import org.example.Model.CalculationResult;
import org.example.Model.EasyCalculationResult;
import org.example.View.CalculationHistoryView;
import org.example.View.LanternaView;

import java.io.IOException;
import java.util.List;

public class CalculationHistoryController {
    private final CalculationHistory calculationHistory;
    private final CalculationHistoryView view;
    public CalculationHistoryController(CalculationHistoryView view, CalculationHistory calculationHistory) {
        this.calculationHistory = calculationHistory;
        this.view = view;
    }
    public void addResult(CalculationResult result) {
        calculationHistory.getHistory().add(result);
    }

    public void handleHistory() {
        view.displayCalculationHistory(calculationHistory.getHistory());
    }

}
