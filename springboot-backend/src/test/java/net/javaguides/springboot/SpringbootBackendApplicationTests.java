package net.javaguides.springboot;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mindtree.employeemanagerapp.repository.EmployeeRepository;
import com.mindtree.employeemanagerapp.service.EmployeeService;
import com.mindtree.employeemanagerapp.model.Employee;


@SpringBootTest
class SpringbootBackendApplicationTests {
	
	@Autowired
	private EmployeeService service;
	
	@MockBean
	private  EmployeeRepository repository;
	
	@Test
	public void getEmployeeTest()
	{
		when(repository.findAll()).thenReturn((List<Employee>) Stream
				.of(new Employee("Rhutik","Sahu","rhutiksahu@gmail.com"),new Employee("Rhutik","Sahu","rhutiksahu@gmail.com")).collect(Collectors.toList()));
				
				assertEquals(2,service.getAllEmployees().size());
	}
	@Test
	public void getEmployeebyIdTest() {
		long id = 1;
		when(repository.findById(id)).
				thenReturn((List<Employee>) Stream.of(new Employee("Rhutik","Sahu","rhutiksahu@gmail.com")).collect(Collectors.toList()));
		assertEquals(1, service.getEmployeeById(id));
	}

	@Test
	public void saveEmployeeTest() {
		Employee employee = new Employee("Rhutik","Sahu","rhutiksahu@gmail.com");
		when(repository.save(employee)).thenReturn(employee);
		assertEquals(employee, service.createEmployee(employee));
	}

	@Test
	public void deleteEmployeeTest() {
		Employee employee = new Employee("Rhutik","Sahu","rhutiksahu@gmail.com");
		service.deleteEmployee(employee);
		verify(repository, times(1)).delete(employee);
	}

	

}
