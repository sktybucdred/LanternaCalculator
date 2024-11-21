package org.example.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class EasyCalculatorView{
    private LanternaView lanternaView;
    public EasyCalculatorView(LanternaView lanternaView) {
        this.lanternaView = lanternaView;
    }

    public void displayCalculatorMenu() {
        try {
            TextGraphics textGraphics = lanternaView.getTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
            textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
            textGraphics.putString(5, 5, "Wybierz operację matematyczną:");
            textGraphics.putString(5, 7, "1. Dodawanie");
            textGraphics.putString(5, 8, "2. Odejmowanie");
            textGraphics.putString(5, 9, "3. Mnożenie");
            textGraphics.putString(5, 10, "4. Dzielenie");
            textGraphics.putString(5, 11, "5. Wyjście");

            lanternaView.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public double getNumberFromUser(String prompt, int row) {
        try {
            TextGraphics textGraphics = lanternaView.getTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
            textGraphics.putString(5, row, prompt, SGR.BOLD);
            lanternaView.flush();
            String userInput = lanternaView.readLine();
            return Double.parseDouble(userInput);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;  // Możemy też rzucić wyjątek lub obsłużyć błędnie wprowadzone dane inaczej.
        } catch (NumberFormatException e) {
            displayErrorMessage("Błędne dane wejściowe. Użyj liczb.");
            return getNumberFromUser(prompt, row); // Rekurencyjne ponowne zapytanie użytkownika
        }
    }

    public void displayResult(double result) {
        try {
            TextGraphics textGraphics = lanternaView.getTextGraphics();
            textGraphics.putString(5, 17, "Wynik: " + result);
            lanternaView.flush();

            textGraphics.putString(5, 19, "Naciśnij dowolny klawisz, aby kontynuować...");
            lanternaView.flush();
            lanternaView.readUserInput(); // Poczekaj na wciśnięcie klawisza
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayErrorMessage(String message) {
        try {
            TextGraphics textGraphics = lanternaView.getTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.RED);
            textGraphics.putString(5, 21, message);
            lanternaView.flush();
            textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
            lanternaView.readUserInput(); // Poczekaj na wciśnięcie klawisza przez użytkownika
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KeyStroke readUserInput() throws IOException {
        return lanternaView.readUserInput();
    }

    public void clearScreen() {
        lanternaView.clearScreen();
    }
}
