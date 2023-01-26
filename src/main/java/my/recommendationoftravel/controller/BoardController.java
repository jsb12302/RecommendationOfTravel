package my.recommendationoftravel.controller;

import my.recommendationoftravel.domain.board.BoardDTO;
import my.recommendationoftravel.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/board")
    public String postBoard(BoardDTO boardDTO, HttpSession httpSession){
        boardService.saveBoard(boardDTO, httpSession);
        System.out.println(boardDTO.toString());
        return "redirect:/board";
    }

}
