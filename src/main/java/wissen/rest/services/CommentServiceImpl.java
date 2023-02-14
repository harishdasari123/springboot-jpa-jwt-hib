package wissen.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wissen.rest.helper.PagedListInfo;
import wissen.rest.model.Comment;
import wissen.rest.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Override
    public List<Comment> getComments() {
        List<Comment> employees = new ArrayList<>();
        commentRepository.findAll().forEach(e -> employees.add(e));
        return employees;
    }

    @Override
    public List<Comment> findAll(PagedListInfo pagedListInfo) {
        Sort sort = Sort.by(pagedListInfo.getSortBy());
        if(!pagedListInfo.isAscending()){
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(pagedListInfo.getPage(), pagedListInfo.getSize(), sort);
        Page<Comment> pagedResult = commentRepository.findAll(pageable);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Comment>();
        }
    }

    @Override
    public Comment getComment(Long empId) {
        return commentRepository.findById(empId).orElse(null);
    }

    @Override
    public boolean saveComment(Comment e) {
        commentRepository.save(e);
        return true;
    }

    @Override
    public boolean updateComment(Comment e) {
        commentRepository.save(e);
        return true;
    }

    @Override
    public boolean deleteComment(Long empId) {
        commentRepository.deleteById(empId);
        return true;
    }
}
