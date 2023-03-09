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

3. Uma das principais diferenças entre as anotações @Mock e @MockBean, é que é possivel utilizar a anotação @Mock em qualquer estrutura de aplicação. Já a anotação @MockBean só pode ser utilizada numa estrutura Spring.
Com @Mock instruímos Mockito a criar um mock, pois não queremos uma instância real de uma classe. A extensão JUnit Jupiter de Mockito terá então o cuidado de instanciar e injetar o mock. Por outro lado, podemos usar o @MockBean para adicionar objectos simulados ao estilo de aplicaçoes Spring. O MockBean irá substituir qualquer bean existente do mesmo tipo no contexto de aplicação.

4. O ficheiro application-integrationtest.properties contém as informaçoes para configurar uma forma de armazenar dados, tentando nao usar uma base de dados real na execução dos testes de integração. Com a anotação @TestPropertySource é possivel indicar os locais dos ficheiros de propriedades especificos para os testes.

5. A principal diferença entre os testes D e E do teste C é o facto de o teste C não envolver base de dados e os testes D e E envolvem. O teste C usa o @WebMvcTest, que simula o comportamento de um servidor da aplicaçao e além disso usa o MockMvc que fornece uma API expressiva. Além disso no teste C também não é envolvido o componente repository.
Tanto o teste D como o E são testes de integração que envolvem vários componentes. Ambos envolvem as componentes: service implementation, o repository e a base de dados. A grande diferença entre os testes D e E é nas suas APIs implementadas onde o teste D usa o Mockvc como entry point para suporte de teste Spring MVC do lado do servidor. Já o teste E usa o TestRestTemplate, um cliente REST para criar pedidos realistas, envolvendo também respostas.