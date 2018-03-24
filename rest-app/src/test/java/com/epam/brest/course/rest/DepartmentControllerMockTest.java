package com.epam.brest.course.rest;

import com.epam.brest.course.dao.Department;
import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;
import com.epam.brest.course.service.DepartmentService;
import org.easymock.EasyMock;
import org.hamcrest.Matchers;
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

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class DepartmentControllerMockTest {

    @Autowired
    private DepartmentRestController departmentRestController;

    @Autowired
    private DepartmentService mockDepartmentService;

    private MockMvc mockMvc;

    private static final int DEPARTMENTID = 1;
    private static final String DEPARTMENTNAME = "Java";
    private static final int AVGSALARY = 1000;
    private static final String DESCRIPTION = "Java department";

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        EasyMock.reset(mockDepartmentService);
    }

    @Test
    public void getDepartmentsDtoTest() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto(
                DEPARTMENTID, DEPARTMENTNAME);

        EasyMock.expect(mockDepartmentService.getAllDepartmentDto())
                .andReturn(Arrays.asList(departmentDto));
        EasyMock.replay(mockDepartmentService);

        mockMvc.perform(
                get("/departmentsdto")
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].departmentId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].departmentName", Matchers.is("Java")));

        EasyMock.verify(mockDepartmentService);
    }

    @Test
    public void getDepartmentsTest() throws Exception {
        DepartmentDtoWithAvgSalary department = new DepartmentDtoWithAvgSalary(
                DEPARTMENTID, DEPARTMENTNAME, AVGSALARY);

        EasyMock.expect(mockDepartmentService.getAllDepartmentWithAvgSalary())
                .andReturn(Arrays.asList(department)).times(1);
        EasyMock.replay(mockDepartmentService);

        mockMvc.perform(
                get("/departments")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].departmentId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].departmentName", Matchers.is("Java")))
                .andExpect(jsonPath("$[0].avgSalary", Matchers.is(1000)));
//                        .string("[{\"departmentId\":1," +
//                        "\"departmentName\":\"Java\"," +
//                        "\"avgSalary\":1000}]"));

        EasyMock.verify(mockDepartmentService);
    }

    @Test
    public void departmentByIdTest() throws Exception {
        Department department = new Department(
                DEPARTMENTNAME, DESCRIPTION);
        department.setDepartmentId(DEPARTMENTID);

        EasyMock.expect(mockDepartmentService.getDepartmentById(DEPARTMENTID))
                .andReturn(department).times(1);
        EasyMock.replay(mockDepartmentService);

        mockMvc.perform(
                get("/departments/{id}", DEPARTMENTID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isFound())
                .andExpect(content().string("{\"departmentId\":1," +
                        "\"departmentName\":\"Java\"," +
                        "\"description\":\"Java department\"}"));

        EasyMock.verify(mockDepartmentService);
    }

    @Test
    public void addDepartmentTest() throws Exception {
        Department department = new Department(
                DEPARTMENTNAME, DESCRIPTION);
        department.setDepartmentId(DEPARTMENTID);

        EasyMock.expect(mockDepartmentService.addDepartment(new Department(
                DEPARTMENTNAME, DESCRIPTION))).andReturn(department);
        EasyMock.replay(mockDepartmentService);

        mockMvc.perform(
                post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departmentName\":\"Java\"," +
                                "\"description\":\"Java department\"}")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string("{\"departmentId\":1," +
                        "\"departmentName\":\"Java\"," +
                        "\"description\":\"Java department\"}"));

        EasyMock.verify(mockDepartmentService);
    }

    @Test
    public void deleteDepartmentByIdTest() throws Exception{
        mockDepartmentService.deleteDepartmentById(DEPARTMENTID);
        EasyMock.expectLastCall();

        EasyMock.replay(mockDepartmentService);

        mockMvc.perform(
                delete("/departments/{id}", DEPARTMENTID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isFound());

        EasyMock.verify(mockDepartmentService);
    }

    @Test
    public void updateDepartmentTest() throws Exception {
        Department department = new Department(
                DEPARTMENTNAME, DESCRIPTION);
        department.setDepartmentId(DEPARTMENTID);

        mockDepartmentService.updateDepartment(department);
        EasyMock.expectLastCall();

        EasyMock.replay(mockDepartmentService);

        mockMvc.perform(
                put("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departmentId\":1," +
                                "\"departmentName\":\"Java\"," +
                                "\"description\":\"Java department\"}")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());

        EasyMock.verify(mockDepartmentService);
    }

}

