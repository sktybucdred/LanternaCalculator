package org.example.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.Model.CalculationResult;
import org.example.Model.EasyCalculationResult;

import java.io.IOException;
import java.util.List;

public class CalculationHistoryView{
    private LanternaView lanternaView;
    public CalculationHistoryView(LanternaView lanternaView) {
        this.lanternaView = lanternaView;
    }

    public void displayCalculationHistory(List<CalculationResult> history) {
        try {
            lanternaView.clearScreen();
            TextGraphics textGraphics = lanternaView.getTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
            textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);

            // Wyświetl nagłówek
            textGraphics.putString(5, 5, "Historia obliczeń:", SGR.BOLD);

            int line = 7; // Startowa pozycja wiersza dla historii
            for (CalculationResult result : history) {
                textGraphics.putString(5, line++, result.toString());
            }

            // Wyświetl komunikat informujący, aby nacisnąć klawisz
            textGraphics.putString(5, line + 2, "Naciśnij dowolny klawisz, aby kontynuować...");
            lanternaView.flush();

            // Poczekaj na wciśnięcie klawisza przez użytkownika
            lanternaView.readUserInput();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
