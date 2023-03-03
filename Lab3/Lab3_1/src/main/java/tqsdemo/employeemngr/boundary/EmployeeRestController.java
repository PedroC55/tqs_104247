package tqsdemo.employeemngr.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqsdemo.employeemngr.data.Employee;
import tqsdemo.employeemngr.data.EmployeeDTO;
import tqsdemo.employeemngr.service.EmployeeService;

import java.util.List;

/**
 * API endpoints. Try with Postman or curl
 * $curl -v http://localhost:8080/api/employees
 */
@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    /**
     * Using constructor Injection instead of @autowired
     * when using a constructor to set injected properties, you do not have to provide the autowire annotation
     * @param employeeService
     */
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees" )
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employee) {
        HttpStatus status = HttpStatus.CREATED;
        Employee saved = employeeService.save( employee.toEmployeeEntity() );
        return new ResponseEntity<>(saved, status);
    }


    @GetMapping(path="/employees" )
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

}
