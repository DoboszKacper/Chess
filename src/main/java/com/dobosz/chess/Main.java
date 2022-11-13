package com.dobosz.chess;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.entieties.pieces.Figure;
import com.dobosz.chess.logic.FigureMove;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean running = true;
        Board board = new Board();
        board.init();
        System.out.println(board);
        System.out.println();
        Scanner myScanner = new Scanner(System.in);
        while (running) {
            System.out.println("Select figure type x and y:");
            int x = myScanner.nextInt();
            int y = myScanner.nextInt();
            Figure figure;
            try {
                figure = board.getFigure(x, y);
                System.out.println("You have selected " + figure);
                System.out.println("Where do you want to move it? Here are possible x and y.\n");
                List<FigureMove> allMovementsForFigure = figure.getAllMovements(board);
                if (!allMovementsForFigure.isEmpty()) {
                    System.out.println(allMovementsForFigure);
                    int possibleMoveIndex = myScanner.nextInt();
                    FigureMove figureMove = allMovementsForFigure.get(possibleMoveIndex);
                    board.moveFigure(figureMove, figure);
                    System.out.println(board);
                    System.out.println();
                } else {
                    System.out.println("Select another figure! This can not move!\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
