package com.dobosz.chess.mvc.controller;

import com.dobosz.chess.entieties.Board;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    private final Board board;

    public BoardController(Board board) {
        this.board = board;
        board.init();
    }

    @GetMapping("/loadBoard")
    public String loadBoard() {
        return "index";
    }

    @PostMapping("/loadBoard")
    public String moveFigureToPoint(String s) {
        return "index";
    }

//    @PostMapping("/adduser")
//    public String addUser(@Valid User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-user";
//        }
//
//        userRepository.save(user);
//        return "redirect:/index";
//    }
}
