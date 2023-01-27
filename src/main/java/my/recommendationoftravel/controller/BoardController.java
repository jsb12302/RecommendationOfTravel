package my.recommendationoftravel.controller;

import my.recommendationoftravel.domain.board.Board;
import my.recommendationoftravel.domain.board.BoardDTO;
import my.recommendationoftravel.domain.user.User;
import my.recommendationoftravel.service.BoardService;
import my.recommendationoftravel.util.SessionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/board")
    public String postBoard(BoardDTO boardDTO, @SessionResolver User user){
        System.out.println(user.getUserId());
        boardService.saveBoard(boardDTO, user);
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String getBoardList(@SessionResolver User user, Model model){
        List<Board> boardList = boardService.getBoardList(user);
        model.addAttribute("boardList",boardList);
        return "board/userBoard";
    }

    @DeleteMapping("/board")
    public String deleteBoard(@RequestParam Long id){
        boardService.removeBoard(id);
        return "redirect:/board";
    }
}
