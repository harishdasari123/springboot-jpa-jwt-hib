package wissen.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wissen.rest.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
