package wissen.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wissen.rest.model.Department;
import wissen.rest.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public void saveOrUpdate(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long depId) {
        departmentRepository.deleteById(depId);
    }

    @Override
    public Department getDepartment(Long depId) {
        return departmentRepository.findById(depId).orElse(null);
    }

    @Override
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }
}
