package tqsdemo.employeemngr.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqsdemo.employeemngr.boundary.EmployeeRestController;
import tqsdemo.employeemngr.data.Employee;
import tqsdemo.employeemngr.service.EmployeeService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * WebMvcTest loads a simplified web environment for the tests. Note that the normal
 * auto-discovery of beans (and dependency injection) is limited
 * This strategy deploys the required components to a test-friendly web framework, that can be accessed
 * by injecting a MockMvc reference
 */
@WebMvcTest(EmployeeRestController.class)
class C_EmployeeController_WithMockServiceTest {

    @Autowired
    private MockMvc mvc;    //entry point to the web framework

    // inject required beans as "mockeable" objects
    // note that @AutoWire would result in NoSuchBeanDefinitionException
    @MockBean
    private EmployeeService service;


    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostEmployee_thenCreateEmployee( ) throws Exception {
        Employee alex = new Employee("alex", "alex@deti.com");

        when( service.save(Mockito.any()) ).thenReturn( alex);

        mvc.perform(
                post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(alex)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("alex")));

        verify(service, times(1)).save(Mockito.any());

    }

    @Test
    void givenManyEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
        Employee alex = new Employee("alex", "alex@deti.com");
        Employee john = new Employee("john", "john@deti.com");
        Employee bob = new Employee("bob", "bob@deti.com");

        List<Employee> allEmployees = Arrays.asList(alex, john, bob);

        when( service.getAllEmployees()).thenReturn(allEmployees);

        mvc.perform(
                get("/api/employees").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(alex.getName())))
                .andExpect(jsonPath("$[1].name", is(john.getName())))
                .andExpect(jsonPath("$[2].name", is(bob.getName())));

        verify(service, times(1)).getAllEmployees();
    }
}