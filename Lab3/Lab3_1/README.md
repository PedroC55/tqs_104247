## Respostas:
1. Teste A_EmployeeRepositoryTest:
```
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail());
```
2. O melhor exemplo de testes que não se envolve com a base de dados é o B_EmployeeService_UnitTest. Nesta classe usa-se essencialmente o Mockito de forma a controlar os testes. Faz essencialmente mock do EmployeeRepository como se pode ver neste exemplo:
```
        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
```

3. 