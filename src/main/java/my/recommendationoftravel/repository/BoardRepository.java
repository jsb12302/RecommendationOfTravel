package my.recommendationoftravel.repository;

import my.recommendationoftravel.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
