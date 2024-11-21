package org.example.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
//lepiej zrobic klase pomocniczą niż dziedziczenie po LanternaView
public class LanternaView {
    private Terminal terminal;
    private TextGraphics textGraphics;
    private Theme currentTheme;

    public LanternaView(Terminal terminal, Theme theme) {
        this.terminal = terminal;
        this.currentTheme = theme;
        try {
            terminal.setBackgroundColor(theme.backgroundColor);
            terminal.clearScreen();
            terminal.flush();
            this.textGraphics = terminal.newTextGraphics();
            applyTheme();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void applyTheme() {
        textGraphics.setForegroundColor(currentTheme.foregroundColor);
        textGraphics.setBackgroundColor(currentTheme.backgroundColor);
        for (SGR modifier : currentTheme.modifiers) {
            textGraphics.enableModifiers(modifier);
        }
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void clearScreen() {
        try {
            terminal.clearScreen();
            terminal.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KeyStroke readUserInput() throws IOException {
        return terminal.readInput();
    }

    public void closeTerminal() {
        try {
            if (terminal != null) {
                terminal.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readLine() {
        StringBuilder userInput = new StringBuilder();
        try {
            while (true) {
                KeyStroke keyStroke = terminal.readInput();
                if (keyStroke.getKeyType() == KeyType.Enter) {
                    break;
                } else if (keyStroke.getKeyType() == KeyType.Backspace) {
                    if (userInput.length() > 0) {
                        userInput.deleteCharAt(userInput.length() - 1);
                        int cursorPosition = terminal.getCursorPosition().getColumn();
                        terminal.setCursorPosition(cursorPosition - 1, terminal.getCursorPosition().getRow());
                        terminal.putCharacter(' ');
                        terminal.setCursorPosition(cursorPosition - 1, terminal.getCursorPosition().getRow());
                        terminal.flush();
                    }
                } else if (keyStroke.getKeyType() == KeyType.Character) {
                    userInput.append(keyStroke.getCharacter());
                    terminal.putCharacter(keyStroke.getCharacter());
                    terminal.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInput.toString();
    }

    public void flush() {
        try {
            terminal.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TextGraphics getTextGraphics() throws IOException {
        return terminal.newTextGraphics();
    }

    public void drawHeader(String text, int x, int y) throws IOException {
        textGraphics.setForegroundColor(Theme.HEADER.foregroundColor);
        textGraphics.setBackgroundColor(Theme.HEADER.backgroundColor);
        textGraphics.enableModifiers(Theme.HEADER.modifiers.toArray(new SGR[0]));
        textGraphics.putString(x, y, text);
        textGraphics.disableModifiers(Theme.HEADER.modifiers.toArray(new SGR[0]));
    }

    public void drawMenuOption(String text, int x, int y) throws IOException {
        textGraphics.setForegroundColor(Theme.MENU_OPTION.foregroundColor);
        textGraphics.setBackgroundColor(Theme.MENU_OPTION.backgroundColor);
        textGraphics.putString(x, y, text);
    }

    public void drawSelectedMenuOption(String text, int x, int y) throws IOException {
        textGraphics.setForegroundColor(Theme.SELECTED_OPTION.foregroundColor);
        textGraphics.setBackgroundColor(Theme.SELECTED_OPTION.backgroundColor);
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(x, y, text);
        textGraphics.disableModifiers(SGR.BOLD);
    }

    public void drawBorder(int x1, int y1, int x2, int y2) throws IOException {
        textGraphics.setForegroundColor(Theme.BORDER.foregroundColor);
        textGraphics.setBackgroundColor(Theme.BORDER.backgroundColor);
        // Górna i dolna linia
        for (int x = x1 + 1; x < x2; x++) {
            textGraphics.putString(x, y1, "-");
            textGraphics.putString(x, y2, "-");
        }
        // Boczne linie
        for (int y = y1 + 1; y < y2; y++) {
            textGraphics.putString(x1, y, "|");
            textGraphics.putString(x2, y, "|");
        }
        // Rogi
        textGraphics.putString(x1, y1, "+");
        textGraphics.putString(x2, y1, "+");
        textGraphics.putString(x1, y2, "+");
        textGraphics.putString(x2, y2, "+");
    }
}
