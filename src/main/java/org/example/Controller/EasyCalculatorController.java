package org.example.Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Model.CalculationHistory;
import org.example.Model.EasyCalculationResult;
import org.example.Service.EasyCalculatorService;
import org.example.View.EasyCalculatorView;
import org.example.View.LanternaView;

import java.io.IOException;

public class EasyCalculatorController {
    private final EasyCalculatorService calculatorService;
    private final EasyCalculatorView view;
    private final CalculationHistoryController calculationHistoryController;

    public EasyCalculatorController(EasyCalculatorService calculatorService, EasyCalculatorView view, CalculationHistoryController calculationHistoryController) {
        this.calculatorService = calculatorService;
        this.view = view;
        this.calculationHistoryController = calculationHistoryController;
    }

    public void handleUserInput() {
        try {
            boolean inCalculatorSection = true;
            while (inCalculatorSection) {
                view.clearScreen();
                // Wyświetlenie menu kalkulatora
                view.displayCalculatorMenu();  // Możesz zmienić nazwę na `displayCalculatorMenu()`, aby odróżnić od głównego menu

                // Pobranie wyboru użytkownika
                KeyStroke keyStroke = view.readUserInput();
                if (keyStroke.getKeyType() == KeyType.Character) {
                    char c = keyStroke.getCharacter();
                    switch (c) {
                        case '1':
                            // Wykonanie dodawania
                            performAddition();
                            break;
                        case '2':
                            // Wykonanie odejmowania
                            performSubtraction();
                            break;
                        case '3':
                            // Wykonanie mnożenia
                            performMultiplication();
                            break;
                        case '4':
                            // Wykonanie dzielenia
                            performDivision();
                            break;
                        case '5':
                            // Cofnięcie do strony głównej
                            inCalculatorSection = false;
                            break;
                    }
                } else if (keyStroke.getKeyType() == KeyType.Escape) {
                    // Cofnięcie do strony głównej
                    inCalculatorSection = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void performAddition() {
        try {
            // Pobierz liczby od użytkownika za pomocą widoku
            double firstNumber = view.getNumberFromUser("Podaj pierwszą liczbę: ", 13);
            double secondNumber = view.getNumberFromUser("Podaj drugą liczbę: ", 15);

            // Oblicz wynik za pomocą serwisu
            double result = calculatorService.add(firstNumber, secondNumber);

            // Stwórz wynik obliczenia jako obiekt `EasyCalculationResult`
            EasyCalculationResult calculationResult = new EasyCalculationResult(
                    firstNumber,
                    secondNumber,
                    "+",
                    result
            );

            // Dodaj wynik do historii obliczeń
            calculationHistoryController.addResult(calculationResult);

            // Wyświetl wynik użytkownikowi za pomocą widoku
            view.displayResult(result);

        } catch (NumberFormatException e) {
            view.displayErrorMessage("Błędne dane wejściowe! Spróbuj ponownie.");
        }
    }
    private void performSubtraction() {
        try {
            // Pobierz liczby od użytkownika za pomocą widoku
            double firstNumber = view.getNumberFromUser("Podaj pierwszą liczbę: ", 13);
            double secondNumber = view.getNumberFromUser("Podaj drugą liczbę: ", 15);

            // Oblicz wynik za pomocą serwisu
            double result = calculatorService.subtract(firstNumber, secondNumber);

            // Stwórz wynik obliczenia jako obiekt `EasyCalculationResult`
            EasyCalculationResult calculationResult = new EasyCalculationResult(
                    firstNumber,
                    secondNumber,
                    "-",
                    result
            );

            // Dodaj wynik do historii obliczeń
            calculationHistoryController.addResult(calculationResult);

            // Wyświetl wynik użytkownikowi za pomocą widoku
            view.displayResult(result);

        } catch (NumberFormatException e) {
            view.displayErrorMessage("Błędne dane wejściowe! Spróbuj ponownie.");
        }
    }

    private void performMultiplication() {
        try {
            // Pobierz liczby od użytkownika za pomocą widoku
            double firstNumber = view.getNumberFromUser("Podaj pierwszą liczbę: ", 13);
            double secondNumber = view.getNumberFromUser("Podaj drugą liczbę: ", 15);

            // Oblicz wynik za pomocą serwisu
            double result = calculatorService.multiply(firstNumber, secondNumber);

            // Stwórz wynik obliczenia jako obiekt `EasyCalculationResult`
            EasyCalculationResult calculationResult = new EasyCalculationResult(
                    firstNumber,
                    secondNumber,
                    "*",
                    result
            );

            // Dodaj wynik do historii obliczeń
            calculationHistoryController.addResult(calculationResult);

            // Wyświetl wynik użytkownikowi za pomocą widoku
            view.displayResult(result);

        } catch (NumberFormatException e) {
            view.displayErrorMessage("Błędne dane wejściowe! Spróbuj ponownie.");
        }
    }
    private void performDivision() {
        try {
            // Pobierz liczby od użytkownika za pomocą widoku
            double firstNumber = view.getNumberFromUser("Podaj pierwszą liczbę: ", 13);
            double secondNumber = view.getNumberFromUser("Podaj drugą liczbę: ", 15);
            while(secondNumber == 0)
            {
                view.displayErrorMessage("Nie można dzielić przez zero! Spróbuj ponownie.");
                secondNumber = view.getNumberFromUser("Podaj drugą liczbę: ", 15);
            }
            // Oblicz wynik za pomocą serwisu
            double result = calculatorService.divide(firstNumber, secondNumber);

            // Stwórz wynik obliczenia jako obiekt `EasyCalculationResult`
            EasyCalculationResult calculationResult = new EasyCalculationResult(
                    firstNumber,
                    secondNumber,
                    "/",
                    result
            );

            // Dodaj wynik do historii obliczeń
            calculationHistoryController.addResult(calculationResult);

            // Wyświetl wynik użytkownikowi za pomocą widoku
            view.displayResult(result);

        } catch (NumberFormatException e) {
            view.displayErrorMessage("Błędne dane wejściowe! Spróbuj ponownie.");
        }
    }
}
