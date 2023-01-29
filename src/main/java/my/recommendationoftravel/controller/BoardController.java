package my.recommendationoftravel.controller;

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

    @PostMapping("/boards")
    public String postBoard(BoardDTO boardDTO, @SessionResolver User user){
        System.out.println(user.getUserId());
        boardService.saveBoard(boardDTO, user);
        return "redirect:/board";
    }

    @GetMapping("/boards")
    public String getBoardList(@SessionResolver User user, Model model){
        List<Board> boardList = boardService.getBoardList(user);
        model.addAttribute("boardList",boardList);
        return "board/userBoard";
    }

    @DeleteMapping("/boards")
    public String deleteBoard(@RequestParam Long id){
        boardService.removeBoard(id);
        return "redirect:/board";
    }

    @GetMapping("/boards/{id}")
    public String boardDetail(@SessionResolver User user, @PathVariable Long id, Model model){
        Board board = boardService.getBoard(user, id);
        model.addAttribute("board", board);
        return "board/userBoardDetail";
    }

    @DeleteMapping("/admin/boards")
    public String adminDeleteBoard(@RequestParam Long id){
        boardService.removeBoard(id);
        return "redirect:/admin/boards";
    }

    @GetMapping("admin/boards")
    public String adminBoards(@SessionResolver User user, Model model){
        model.addAttribute("boards",boardService.getAllBoards());
        return "admin/adminBoard";
    }

    @GetMapping("/admin/boards/{id}")
    public String adminBoardDetail(@PathVariable Long id, Model model){
        Board board = boardService.adminGetBoard(id);
        model.addAttribute("board", board);
        model.addAttribute("answer",board.getAnswer());
        return "admin/adminBoardDetail";
    }

    @PostMapping("admin/boards")
    public String postAnswer(String answer, Long boardId){
        boardService.postAnswer(answer,boardId);
        return "redirect:/admin/boards";
    }

}
