package tqsdemo.employeemngr.data;

/**
 * A  data transfer object for an employee (that is not automatically bound to the persistence)
 */
public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;

    public static EmployeeDTO fromEmployeeEntity(Employee employee){
        return new EmployeeDTO(employee.getName(), employee.getEmail(), employee.getId());
    }
    public Employee toEmployeeEntity(){
        return new Employee(getName(), getEmail(), getId());
    }

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String email, Long id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
