package org.example.Model;

import org.example.Service.HardCalculatorServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class CalculationHistory {
    private List<CalculationResult> history;
    public CalculationHistory() {
        this.history = new ArrayList<>();
    }
    public List<CalculationResult> getHistory() {
        return history;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CalculationResult result : history) {
            sb.append(result.toString()).append("\n");
        }
        return sb.toString();
    }
}
