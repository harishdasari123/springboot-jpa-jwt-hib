package wissen.rest.services;

import org.springframework.stereotype.Service;
import wissen.rest.helper.PagedListInfo;
import wissen.rest.model.Employee;


import java.util.List;

@Service
public interface EmployeeService {

    List<Employee> getEmployees();
    List<Employee> findAll(PagedListInfo pagedListInfo);

    Employee getEmployee(Long empId);

    boolean saveEmployee(Employee e);
    boolean updateEmployee(Employee e);
    boolean deleteEmployee(Long empId);


    Employee findByEmail(String username);
}
