package com.epam.brest.course.rest;

import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;
import com.epam.brest.course.service.DepartmentService;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class DepartmentControllerMockTest {

    @Autowired
    private DepartmentRestController departmentRestController;

    @Autowired
    private DepartmentService mockDepartmentService;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void getDepartmentsTest() throws Exception{
        Collection<DepartmentDtoWithAvgSalary> departments = new ArrayList<>();
        DepartmentDtoWithAvgSalary dep = new DepartmentDtoWithAvgSalary();
        dep.setDepartmentId(1);
        dep.setDepartmentName("Java");
        dep.setAvgSalary(10);
        departments.add(dep);
        EasyMock.expect(mockDepartmentService.getAllDepartmentWithAvgSalary())
                .andReturn(departments).times(1);
        EasyMock.replay(mockDepartmentService);

        mockMvc.perform(
                get("/departments")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("[{\"departmentId\":1,\"departmentName\":\"Java\",\"avgSalary\":10}]"));

        EasyMock.verify(mockDepartmentService);


    }

}

