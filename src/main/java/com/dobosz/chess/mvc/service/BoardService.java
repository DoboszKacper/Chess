package com.dobosz.chess.mvc.service;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.pieces.Figure;
import com.dobosz.chess.logic.FigureMove;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final Board board;

    public BoardService(Board board) {
        this.board = board;
        this.board.init();
    }

    public Board loadBoard() {
        return board;
    }

    public void moveFigure(int x, int y, int moveIndex) throws Exception {
        Figure figure = board.getFigure(x, y);
        List<FigureMove> allMovementsForFigure = figure.getAllMovements(board);
        FigureMove figureMove = allMovementsForFigure.get(moveIndex);
        board.moveFigure(figureMove, figure);
    }
}
