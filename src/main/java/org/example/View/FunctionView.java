package org.example.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FunctionView{
    private LanternaView lanternaView;
    public FunctionView(LanternaView lanternaView) {
        this.lanternaView = lanternaView;
    }

    public void displayFunctionMenu() {
        try {
            TextGraphics textGraphics = lanternaView.getTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
            textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
            textGraphics.putString(2, 2, "Witaj w kalkulatorze funkcji!");
            lanternaView.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFunctionFromUser(String prompt, int row) {
        try {
            TextGraphics textGraphics = lanternaView.getTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
            textGraphics.putString(2, row, prompt, SGR.BOLD);
            lanternaView.flush();
            return lanternaView.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void drawChart(List<Double> points) {
        try {
            TextGraphics textGraphics = lanternaView.getTextGraphics();
            int terminalWidth = lanternaView.getTerminal().getTerminalSize().getColumns();
            int terminalHeight = lanternaView.getTerminal().getTerminalSize().getRows();

            // Definiowanie zakresu X
            double xStart = -10;
            double xEnd = 10;
            int numPoints = points.size();
            double xStep = (xEnd - xStart) / (numPoints - 1);

            // Obliczanie minimalnej i maksymalnej wartości Y
            double minYValue = points.stream()
                    .filter(y -> !y.isNaN())
                    .mapToDouble(Double::doubleValue)
                    .min()
                    .orElse(-1);

            double maxYValue = points.stream()
                    .filter(y -> !y.isNaN())
                    .mapToDouble(Double::doubleValue)
                    .max()
                    .orElse(1);

            // Dodanie marginesów
            int marginX = 2;
            int marginY = 2;

            double xRange = xEnd - xStart;
            double yRange = maxYValue - minYValue;

            // Skalowanie
            double xScale = (terminalWidth - 2 * marginX) / xRange;
            double yScale = (terminalHeight - 2 * marginY) / yRange;

            // Obliczenie pozycji osi
            int xZeroCoord = (int) ((0 - xStart) * xScale) + marginX;
            int yZeroCoord = (int) ((maxYValue - 0) * yScale) + marginY;

            // Rysowanie osi Y
            if (xZeroCoord >= 0 && xZeroCoord < terminalWidth) {
                for (int y = 0; y < terminalHeight; y++) {
                    textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
                    textGraphics.putString(xZeroCoord, y, "|");
                }
            }

            // Rysowanie osi X
            if (yZeroCoord >= 0 && yZeroCoord < terminalHeight) {
                for (int x = 0; x < terminalWidth; x++) {
                    textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
                    textGraphics.putString(x, yZeroCoord, "-");
                }
            }

            // Etykietowanie osi
            if (xZeroCoord + 1 < terminalWidth && yZeroCoord > 0) {
                textGraphics.putString(xZeroCoord + 1, marginY - 1, "Y", SGR.BOLD);
                textGraphics.putString(terminalWidth - marginX, yZeroCoord - 1, "X", SGR.BOLD);
            }

            // Rysowanie punktów funkcji
            for (int i = 0; i < points.size(); i++) {
                double xValue = xStart + i * xStep;
                double yValue = points.get(i);

                int xCoord = (int) ((xValue - xStart) * xScale) + marginX;
                int yCoord = (int) ((maxYValue - yValue) * yScale) + marginY;

                if (xCoord >= 0 && xCoord < terminalWidth && yCoord >= 0 && yCoord < terminalHeight) {
                    textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
                    textGraphics.putString(xCoord, yCoord, "*");
                }
            }

            lanternaView.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void displayFunctionPrompt(String function) {
        try {
            TextGraphics textGraphics = lanternaView.getTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
            textGraphics.putString(2, 2, "Funkcja: " + function, SGR.BOLD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayErrorMessage(String message) {
        try {
            TextGraphics textGraphics = lanternaView.getTextGraphics();
            textGraphics.setForegroundColor(TextColor.ANSI.RED);
            textGraphics.putString(2, lanternaView.getTerminal().getTerminalSize().getRows() - 2, message);
            lanternaView.flush();
            textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
            lanternaView.readUserInput(); // Czekaj na wciśnięcie klawisza
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

    public void flush() {
        lanternaView.flush();
    }
}
