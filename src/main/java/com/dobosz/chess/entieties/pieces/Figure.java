package com.dobosz.chess.entieties.pieces;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.FigureColor;
import com.dobosz.chess.logic.FigureMove;
import com.dobosz.chess.logic.Position;

import java.util.List;

public interface Figure {

    default String getName() {
        return this.getClass().getSimpleName();
    }

    FigureColor getColor();

    Position getPosition();

    List<FigureMove> getAllMovements(Board board);

}
