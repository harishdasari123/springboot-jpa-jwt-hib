package wissen.rest.services;


import wissen.rest.model.Department;

public interface DepartmentService {
    void saveOrUpdate(Department department);

    void deleteDepartment(Long depId);

    Department getDepartment(Long depId);

    Department getDepartmentByName(String name);
}
