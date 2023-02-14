package wissen.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wissen.rest.helper.PagedListInfo;
import wissen.rest.model.Comment;
import wissen.rest.model.Employee;
import wissen.rest.services.*;


import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CommentService commentService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmailService emailService;
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long empId){
        Employee employee = employeeService.getEmployee(empId);
        if(employee ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "size", required = false, defaultValue = "5") int size, @RequestParam(name = "sortby") String sortBy, @RequestParam(name = "order") String order){
        PagedListInfo pagedListInfo = new PagedListInfo();
        pagedListInfo.setPage(page>0?page-1:0);
        pagedListInfo.setSize(size);
        pagedListInfo.setSortBy(sortBy);
        pagedListInfo.setAscending(order.equals("asc")?true:false);
        List<Employee> employees = employeeService.findAll(pagedListInfo);
        if(employees ==null || employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveEmployee(@RequestBody Employee employee){
        employee.setDepartment(departmentService.getDepartmentByName(employee.getDepartmentName()));

        employeeService.saveEmployee(employee);
        return new ResponseEntity(new GenericResponse("saved successfully"),HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
        return new ResponseEntity(new GenericResponse("updated successfully"), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") Long empId){
        employeeService.deleteEmployee(empId);
        return new ResponseEntity(new GenericResponse("deleted successfully"),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/email")
    public ResponseEntity sendEmail(@PathVariable("id") Long empId){
        emailService.sendEmail(new EmailEntity("harish.d84@gmail.com","harindiad@gmail.com","test","test body"));
        return new ResponseEntity(new GenericResponse("email sent successfully"),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/comments")
    public ResponseEntity sendEmail(@RequestBody Comment comment, @PathVariable("id") Long empId){
        Employee employee = employeeService.getEmployee(empId);
        comment.setEmployee(employee);
        commentService.saveComment(comment);
        return new ResponseEntity(new GenericResponse("Comment added successfully"),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}/comments/{commentid}")
    public ResponseEntity deleteComment(@PathVariable("commentid") Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity(new GenericResponse("deleted successfully"),HttpStatus.OK);
    }
}
