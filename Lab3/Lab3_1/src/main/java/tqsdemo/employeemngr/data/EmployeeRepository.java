package tqsdemo.employeemngr.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * in a production application, you would likely more data access methods
 * specially methods that my have a complex query expressions attached
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Employee findByName(String name);
    public List<Employee> findAll();

}
