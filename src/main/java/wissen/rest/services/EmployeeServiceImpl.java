package wissen.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wissen.rest.helper.PagedListInfo;
import wissen.rest.model.Employee;
import wissen.rest.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

   /* @Autowired
    PasswordEncoder passwordEncoder;*/
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(e -> employees.add(e));
        return employees;
    }

    @Override
    public List<Employee> findAll(PagedListInfo pagedListInfo) {
        Sort sort = Sort.by(pagedListInfo.getSortBy());
        if(!pagedListInfo.isAscending()){
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(pagedListInfo.getPage(), pagedListInfo.getSize(), sort);
        Page<Employee> pagedResult = employeeRepository.findAll(pageable);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Employee>();
        }
    }

    @Override
    public Employee getEmployee(Long empId) {
        return employeeRepository.findById(empId).orElse(null);
    }

    @Override
    public boolean saveEmployee(Employee e) {
        //PasswordEncoder passwordEncoder =
        //e.setPassword(passwordEncoder.encode(e.getPassword()));
        employeeRepository.save(e);
        return true;
    }

    @Override
    public boolean updateEmployee(Employee e) {
        employeeRepository.save(e);
        return true;
    }

    @Override
    public boolean deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
        return true;
    }

    @Override
    public Employee findByEmail(String username) {
        return employeeRepository.findByEmail(username);
    }
}
