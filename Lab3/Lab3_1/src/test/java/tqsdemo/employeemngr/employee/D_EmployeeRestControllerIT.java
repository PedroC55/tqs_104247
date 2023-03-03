package tqsdemo.employeemngr.employee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqsdemo.employeemngr.EmployeeMngrApplication;
import tqsdemo.employeemngr.data.Employee;
import tqsdemo.employeemngr.data.EmployeeRepository;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Run as a SpringBoot test. The parameters to SpringBootTest could be omitted, but, in this case,
 * we are trying to limit the web context to a simplified web framework, and load the designated application
 */
//@SpringBootTest

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = EmployeeMngrApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
// adapt AutoConfigureTestDatabase with TestPropertySource to use a real database
// @TestPropertySource(locations = "application-integrationtest.properties")
class D_EmployeeRestControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
     void whenValidInput_thenCreateEmployee() throws IOException, Exception {
        Employee bob = new Employee("bob", "bob@deti.com");
        mvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(bob)));

        List<Employee> found = repository.findAll();
        assertThat(found).extracting(Employee::getName).containsOnly("bob");
    }

    @Test
     void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
        createTestEmployee("bob", "bob@deti.com");
        createTestEmployee("alex", "alex@deti.com");

        mvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is("bob")))
                .andExpect(jsonPath("$[1].name", is("alex")));
    }

    private void createTestEmployee(String name, String email) {
        Employee emp = new Employee(name, email);
        repository.saveAndFlush(emp);
    }


}
