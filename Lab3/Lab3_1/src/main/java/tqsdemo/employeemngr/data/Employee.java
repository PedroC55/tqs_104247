package tqsdemo.employeemngr.data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tqs_employee")
public class Employee {

    static final int MAX_NAME_SIZE = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = MAX_NAME_SIZE)
    private String name;

    @NotNull
    @Email
    private String email;

    public Employee() {
    }

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Employee(String name, String email, Long id) {
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