package org.example.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;

import java.util.Arrays;
import java.util.List;

public class Theme {
    public TextColor foregroundColor;
    public TextColor backgroundColor;
    public List<SGR> modifiers;

    public Theme(TextColor fg, TextColor bg, SGR... mods) {
        this.foregroundColor = fg;
        this.backgroundColor = bg;
        this.modifiers = Arrays.asList(mods);
    }

    // Definicje różnych motywów
    public static final Theme DEFAULT = new Theme(TextColor.ANSI.WHITE, TextColor.ANSI.BLUE);
    public static final Theme HEADER = new Theme(TextColor.ANSI.CYAN, TextColor.ANSI.BLUE, SGR.BOLD);
    public static final Theme MENU_OPTION = new Theme(TextColor.ANSI.GREEN, TextColor.ANSI.BLUE);
    public static final Theme SELECTED_OPTION = new Theme(TextColor.ANSI.YELLOW, TextColor.ANSI.BLUE, SGR.BOLD);
    public static final Theme BORDER = new Theme(TextColor.ANSI.WHITE, TextColor.ANSI.BLUE);
}
