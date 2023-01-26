package my.recommendationoftravel.service;

import my.recommendationoftravel.domain.board.Board;
import my.recommendationoftravel.domain.board.BoardDTO;
import my.recommendationoftravel.domain.user.User;
import my.recommendationoftravel.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void saveBoard(BoardDTO boardDTO, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        Board board = new Board(user,boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);

    }



}
