package wissen.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wissen.rest.model.Department;
import wissen.rest.services.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveDepartment(@RequestBody Department department){
        departmentService.saveOrUpdate(department);
        return new ResponseEntity(new GenericResponse("saved successfully"), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateDepartment(@RequestBody Department department){
        departmentService.saveOrUpdate(department);
        return new ResponseEntity(new GenericResponse("Updated successfully"),HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") Long depId){
        departmentService.deleteDepartment(depId);
        return new ResponseEntity(new GenericResponse("deleted successfully"),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity getDepartment(@PathVariable("id") Long depId){
        Department department = departmentService.getDepartment(depId);
        if(department ==null){
            return new ResponseEntity<>(new GenericResponse("Department not found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

}
