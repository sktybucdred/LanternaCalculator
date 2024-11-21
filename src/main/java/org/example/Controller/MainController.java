package org.example.Controller;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.View.MainView;

import java.io.IOException;

public class MainController {
    private final MainView mainView;
    private final EasyCalculatorController calculatorController;
    private final CalculationHistoryController calculationHistoryController;
    private final HardCalculatorController hardCalculatorController;
    private final FunctionController functionController;

    public MainController(MainView mainView, EasyCalculatorController calculatorController,
                          CalculationHistoryController calculationHistoryController,
                          HardCalculatorController hardCalculatorController,
                          FunctionController functionController) {
        this.mainView = mainView;
        this.calculatorController = calculatorController;
        this.calculationHistoryController = calculationHistoryController;
        this.hardCalculatorController = hardCalculatorController;
        this.functionController = functionController;
    }

    /**
     * Obsługuje główne menu aplikacji z nawigacją za pomocą strzałek.
     */
    public void handleMainMenu() {
        try {
            boolean running = true;
            int selectedOption = 0;
            String[] menuOptions = mainView.getMenuOptions();

            // Początkowe wyświetlenie menu
            mainView.displayMainMenu(selectedOption);

            while (running) {
                KeyStroke keyStroke = mainView.readUserInput();

                switch (keyStroke.getKeyType()) {
                    case ArrowUp:
                        if (selectedOption > 0) {
                            selectedOption--;
                            mainView.displayMainMenu(selectedOption);
                        }
                        break;
                    case ArrowDown:
                        if (selectedOption < menuOptions.length - 1) {
                            selectedOption++;
                            mainView.displayMainMenu(selectedOption);
                        }
                        break;
                    case Enter:
                        handleSelection(selectedOption);
                        // Po obsłużeniu opcji, ponownie wyświetlamy menu
                        mainView.displayMainMenu(selectedOption);
                        break;
                    case Escape:
                        running = false;
                        break;
                    default:
                        // Ignorowanie innych klawiszy
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Zamknięcie terminala na końcu aplikacji
            mainView.closeTerminal();
        }
    }

    /**
     * Wykonuje akcję na podstawie wybranej opcji menu.
     *
     * @param selectedOption indeks wybranej opcji (0-based)
     */
    private void handleSelection(int selectedOption) {
        try {
            mainView.clearScreen();
            TextGraphics tg = mainView.getTextGraphics();
            tg.setForegroundColor(TextColor.ANSI.YELLOW);
            mainView.flush();

            // Dodaj logikę dla każdej opcji
            switch (selectedOption) {
                case 0:
                    // Przejście do prostego kalkulatora
                    calculatorController.handleUserInput();
                    break;
                case 1:
                    // Przejście do zaawansowanego kalkulatora
                    hardCalculatorController.handleUserInput();
                    break;
                case 2:
                    // Wyświetlenie historii obliczeń
                    calculationHistoryController.handleHistory();
                    break;
                case 3:
                    // Obsługa funkcji
                    functionController.handleUserInput();
                    break;
                case 4:
                    mainView.closeTerminal();
                default:
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
