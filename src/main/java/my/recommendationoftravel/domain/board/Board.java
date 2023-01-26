package my.recommendationoftravel.domain.board;

import lombok.NoArgsConstructor;
import my.recommendationoftravel.domain.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private User user;

    private String title;

    @Lob
    private String content;

    @CreatedDate
    private LocalDateTime createdDate;

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public Board(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }
}
