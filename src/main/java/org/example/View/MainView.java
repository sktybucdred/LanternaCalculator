package org.example.View;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class MainView {
    private LanternaView lanternaView;
    private String[] menuOptions = {
            "1. Prosty Kalkulator",
            "2. Zaawansowany Kalkulator",
            "3. Historia Obliczeń",
            "4. Funkcje",
            "5. Wyjście"
    };

    public MainView(LanternaView lanternaView) {
        this.lanternaView = lanternaView;
    }

    public void displayMainMenu(int selectedOption) {
        try {
            lanternaView.clearScreen();
            // Rysowanie ramki wokół menu
            lanternaView.drawBorder(3, 3, 50, 20);

            // Nagłówek
            lanternaView.drawHeader("Strona Główna", 5, 5);

            // Opcje menu
            for (int i = 0; i < menuOptions.length; i++) {
                if (i == selectedOption) {
                    lanternaView.drawSelectedMenuOption(menuOptions[i], 5, 7 + i);
                } else {
                    lanternaView.drawMenuOption(menuOptions[i], 5, 7 + i);
                }
            }

            lanternaView.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getMenuOptions() {
        return menuOptions;
    }

    public KeyStroke readUserInput() throws IOException {
        return lanternaView.readUserInput();
    }

    public void closeTerminal() {
        lanternaView.closeTerminal();
    }

    public void clearScreen() {
        lanternaView.clearScreen();
    }

    public TextGraphics getTextGraphics() throws IOException {
        return lanternaView.getTextGraphics();
    }

    public void flush() {
        lanternaView.flush();
    }
}
