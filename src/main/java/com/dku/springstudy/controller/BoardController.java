package com.dku.springstudy.controller;

import com.dku.springstudy.model.BoardType;
import com.dku.springstudy.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.management.InstanceNotFoundException;

@Controller
public class BoardController {

    @Autowired
    BoardService service;

    @RequestMapping(path = "/board", method = RequestMethod.GET)
    public String getBoard(Model model, String title) {

        try {
            BoardType board = service.getBoardType(title);

            model.addAttribute("title", board.getTitle());
            model.addAttribute("content", board.getContent());

            return "board";

        } catch (InstanceNotFoundException __) {
            return "board_not_found";
        }
    }

    @RequestMapping(path = "/board", method = RequestMethod.POST)
    public String updateBoard(Model model,
                              BoardType updatedBoardData) {

        try {
            BoardType board = service.updateBoardType(updatedBoardData);

            model.addAttribute("title", board.getTitle());
            model.addAttribute("content", board.getContent());

            return "board";
        } catch (InstanceNotFoundException e) {

            return "board_not_found";
        }
    }

}
