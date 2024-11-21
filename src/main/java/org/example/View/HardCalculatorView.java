package org.example.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class HardCalculatorView {
    private LanternaView lanternaView;

    public HardCalculatorView(LanternaView lanternaView) {
        this.lanternaView = lanternaView;
    }
    public void displayCalculatorMenu() {
        try {
            TextGraphics textGraphics = lanternaView.getTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
            textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
            textGraphics.putString(5, 5, "Witaj w zaawansowanym kalkulatorze!:");
            textGraphics.putString(5, 7, "Wciśnij dowolny przycisk, aby kontynuować:");

            lanternaView.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getExpressionFromUser(String prompt, int row) {
        try {
            TextGraphics textGraphics = lanternaView.getTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
            textGraphics.putString(5, row, prompt, SGR.BOLD);
            lanternaView.flush();
            return lanternaView.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (NumberFormatException e) {
            displayErrorMessage("Błędne dane wejściowe.");
            return getExpressionFromUser(prompt, row); // Rekurencyjne ponowne zapytanie użytkownika
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

    public void clearScreen() {
        lanternaView.clearScreen();
    }

    public KeyStroke readUserInput() throws IOException {
        return lanternaView.readUserInput();
    }
}
