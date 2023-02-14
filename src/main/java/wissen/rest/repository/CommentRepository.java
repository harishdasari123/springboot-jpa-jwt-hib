package wissen.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wissen.rest.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
