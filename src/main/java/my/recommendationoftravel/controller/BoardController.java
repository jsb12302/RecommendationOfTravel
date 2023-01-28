package my.recommendationoftravel.controller;

import lombok.extern.slf4j.Slf4j;
import my.recommendationoftravel.domain.board.Board;
import my.recommendationoftravel.domain.board.BoardDTO;
import my.recommendationoftravel.domain.user.User;
import my.recommendationoftravel.service.BoardService;
import my.recommendationoftravel.util.SessionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Slf4j
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

    @GetMapping("/board/{id}")
    public String boardDetail(@SessionResolver User user, @PathVariable Long id, Model model){
        Board board = boardService.getBoard(user, id);
        model.addAttribute("board", board);
        return "board/userBoardDetail";
    }
}
