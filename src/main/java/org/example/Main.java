package org.example;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.Controller.*;
import org.example.Model.CalculationHistory;
import org.example.Service.*;
import org.example.View.*;

public class Main {
    public static void main(String[] args) {
        try{
            // Inicjalizacja terminala
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            // Inicjalizacja modelu
            CalculationHistory calculationHistory = new CalculationHistory();

            // Inicjalizacja serwisu
            EasyCalculatorServiceImpl calculatorService = new EasyCalculatorServiceImpl();
            HardCalculatorServiceImpl hardCalculatorService = new HardCalculatorServiceImpl();
            FunctionServiceImpl functionService = new FunctionServiceImpl();
            // Inicjalizacja widoków
            LanternaView lanternaView = new LanternaView(terminal, Theme.DEFAULT);
            MainView mainView = new MainView(lanternaView);
            EasyCalculatorView calculatorView = new EasyCalculatorView(lanternaView);
            CalculationHistoryView historyView = new CalculationHistoryView(lanternaView);
            HardCalculatorView hardCalculatorView = new HardCalculatorView(lanternaView);
            FunctionView functionView = new FunctionView(lanternaView);
            // Inicjalizacja kontrolerów
            CalculationHistoryController calculationHistoryController = new CalculationHistoryController(historyView, calculationHistory);
            EasyCalculatorController calculatorController = new EasyCalculatorController(calculatorService, calculatorView, calculationHistoryController);
            HardCalculatorController hardCalculatorController = new HardCalculatorController(hardCalculatorService, hardCalculatorView,calculationHistoryController);
            FunctionController functionController = new FunctionController(functionService, functionView);
            MainController mainController = new MainController(mainView, calculatorController, calculationHistoryController, hardCalculatorController,functionController);

            // Rozpoczęcie obsługi logiki użytkownika przez kontroler głównego menu
            mainController.handleMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//////FUNCKJONALNOŚCI:
///parser(z odwrocona notacja polska, rozwiazywanie obliczen,
// i rysowanie wielomianow
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
////testy obliczania wielomianów - dostaje liste punktow i dla kazdego obliczam y recznie w tescie i sprawdzam czy sie rowna temu co dosatlem w funkcji
////testy obliczania dzialan
///lanternaView na klase pomocniczą z widokami zamiast dziedziczenia