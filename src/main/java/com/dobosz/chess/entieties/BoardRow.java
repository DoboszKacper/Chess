package com.dobosz.chess.entieties;



import com.dobosz.chess.entieties.pieces.EmptySpace;
import com.dobosz.chess.entieties.pieces.Figure;

import java.util.*;

import static com.dobosz.chess.utils.Constants.BOARD_SIZE;

public class BoardRow {
    private List<Figure> figures = new ArrayList<>();

    public BoardRow(int y) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            figures.add(new EmptySpace(i, y));
        }
    }

    public BoardRow(Figure figure) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            figures.add(figure);
        }
    }

    public BoardRow(List<Figure> newRow) {
        figures.addAll(newRow);
    }

    public List<Figure> getFigures() {
        return figures;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("|");
        for(Figure figure : figures) {
            s.append(figure);
            s.append("|");
        }
        s.append("\n");
        return s.toString();
    }
}
