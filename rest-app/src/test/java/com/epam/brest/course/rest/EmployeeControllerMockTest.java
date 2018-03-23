package com.epam.brest.course.rest;

import com.epam.brest.course.dao.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class EmployeeControllerMockTest {

    @Autowired
    EmployeeRestController employeeRestController;

    @Autowired
    EmployeeService mockEmployeeService;

    MockMvc mockMvc;

    private static final int EMPLOYEEID = 1;
    private static final String EMPLOYEENAME = "Kenny";
    private static final String EMPLOYEEEMAIL = "Kenny@gmail.com";
    private static final int SALARY = 1000;
    private static final int DEPARTMENTID = 2;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        EasyMock.reset(mockEmployeeService);
    }

    @Test
    public void getAllEmployeesTest() throws Exception {
        Collection<Employee> employees = new ArrayList<>();
        employees.add(new Employee(EMPLOYEENAME, EMPLOYEEEMAIL, SALARY, DEPARTMENTID));

        EasyMock.expect(mockEmployeeService.getAllEmployee())
                .andReturn(employees).times(1);
        EasyMock.replay(mockEmployeeService);

        mockMvc.perform(
                get("/employees")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"employeeId\":0," +
                        "\"employeeName\":\"Kenny\"," +
                        "\"employeeEmail\":\"Kenny@gmail.com\"," +
                        "\"salary\":1000," +
                        "\"departmentId\":2}]"));

        EasyMock.verify(mockEmployeeService);
    }

    @Test
    public void getEmployeeByIdTest() throws Exception {
        Employee employee = new Employee(EMPLOYEENAME, EMPLOYEEEMAIL, SALARY, DEPARTMENTID);
        employee.setEmployeeId(EMPLOYEEID);

        EasyMock.expect(mockEmployeeService.getEmployeeById(EMPLOYEEID))
                .andReturn(employee);
        EasyMock.replay(mockEmployeeService);

        mockMvc.perform(
                get("/employees/{id}", EMPLOYEEID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isFound())
                .andExpect(content().string("{\"employeeId\":1," +
                        "\"employeeName\":\"Kenny\"," +
                        "\"employeeEmail\":\"Kenny@gmail.com\"," +
                        "\"salary\":1000," +
                        "\"departmentId\":2}"));

        EasyMock.verify(mockEmployeeService);
    }

    @Test
    public void addEmployeeTest() throws Exception {
        Employee employee = new Employee(EMPLOYEENAME, EMPLOYEEEMAIL, SALARY, DEPARTMENTID);
        employee.setEmployeeId(EMPLOYEEID);

        EasyMock.expect(mockEmployeeService.addEmployee(new Employee(
                EMPLOYEENAME, EMPLOYEEEMAIL, SALARY, DEPARTMENTID)))
                .andReturn(employee);
        EasyMock.replay(mockEmployeeService);

        mockMvc.perform(
                post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employeeName\":\"Kenny\"," +
                                "\"employeeEmail\":\"Kenny@gmail.com\"," +
                                "\"salary\":1000," +
                                "\"departmentId\":2}")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string("{\"employeeId\":1," +
                        "\"employeeName\":\"Kenny\"," +
                        "\"employeeEmail\":\"Kenny@gmail.com\"," +
                        "\"salary\":1000," +
                        "\"departmentId\":2}"));

        EasyMock.verify(mockEmployeeService);
    }

    @Test
    public void deleteEmployeeByIdTest() throws Exception{
        mockEmployeeService.deleteEmployeeById(EMPLOYEEID);
        EasyMock.expectLastCall();

        EasyMock.replay(mockEmployeeService);

        mockMvc.perform(
                delete("/employees/{id}", EMPLOYEEID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isFound());

        EasyMock.verify(mockEmployeeService);
    }

    @Test
    public void updateEmployeeTest() throws Exception {
        Employee employee = new Employee(EMPLOYEENAME, EMPLOYEEEMAIL, SALARY, DEPARTMENTID);
        employee.setEmployeeId(EMPLOYEEID);

        mockEmployeeService.updateEmployee(employee);
        EasyMock.expectLastCall();

        EasyMock.replay(mockEmployeeService);

        mockMvc.perform(
                put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employeeId\":1," +
                                "\"employeeName\":\"Kenny\"," +
                                "\"employeeEmail\":\"Kenny@gmail.com\"," +
                                "\"salary\":1000," +
                                "\"departmentId\":2}")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());

        EasyMock.verify(mockEmployeeService);
    }

}
