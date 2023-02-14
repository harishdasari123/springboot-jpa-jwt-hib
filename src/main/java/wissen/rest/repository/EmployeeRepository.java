package wissen.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wissen.rest.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmail(String username);

    @Query("SELECT E FROM Employee E WHERE E.id = ?1")
    List<Employee> getAllEmployeeByID(int id);


    @Query (value="SELECT * from EMPLOYEE WHere Name=?1", nativeQuery=true)
    List<Employee> getAllByName(String name);
}
