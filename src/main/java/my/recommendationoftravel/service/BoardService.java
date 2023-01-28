package my.recommendationoftravel.service;

import my.recommendationoftravel.domain.board.Board;
import my.recommendationoftravel.domain.board.BoardDTO;
import my.recommendationoftravel.domain.user.User;
import my.recommendationoftravel.repository.BoardRepository;
import my.recommendationoftravel.util.AlertException;
import my.recommendationoftravel.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void saveBoard(BoardDTO boardDTO, User user) {
        Board board = new Board(user, boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);
    }

    public List<Board> getBoardList(User user) {
        List<Board> allByUserId = boardRepository.findAllByUserId(user.getId());
        return allByUserId;
    }

    public void removeBoard(Long id) {
        boardRepository.deleteById(id);
    }

    public Board getBoard(User user, Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        if (board.isPresent() && board.get().getUser().equals(user)) {
            return board.get();
        } else {
            throw new AlertException(ErrorMessage.NOT_FIND_BOARD);
        }
    }
}
