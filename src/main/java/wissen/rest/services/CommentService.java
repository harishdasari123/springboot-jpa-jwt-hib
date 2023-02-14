package wissen.rest.services;

import org.springframework.stereotype.Service;
import wissen.rest.helper.PagedListInfo;
import wissen.rest.model.Comment;

import java.util.List;

@Service
public interface CommentService {

    List<Comment> getComments();
    List<Comment> findAll(PagedListInfo pagedListInfo);

    Comment getComment(Long empId);

    boolean saveComment(Comment e);
    boolean updateComment(Comment e);
    boolean deleteComment(Long empId);


}
