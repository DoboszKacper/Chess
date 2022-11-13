package com.dobosz.chess.mvc.controller;

import com.dobosz.chess.entieties.Board;
import com.dobosz.chess.mvc.dto.BoardDTO;
import com.dobosz.chess.mvc.dto.BoardRowDTO;
import com.dobosz.chess.mvc.service.BoardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("chessBoard")
public class BoardController {
    private final BoardService service;
    private final ModelMapper modelMapper;

    public BoardController(BoardService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/loadBoard")
    public BoardRowDTO loadBoard() {
        Board board = service.loadBoard();
        return convertToDto(board).getBoardRows().get(0);
    }

//    @PostMapping("/loadBoard/move")
//    public String moveFigureToPoint(B) {
//        service.moveFigure();
//    }


    private BoardDTO convertToDto(Board board) {
        return modelMapper.map(board, BoardDTO.class);
    }
}
