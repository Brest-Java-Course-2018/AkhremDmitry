package com.epam.brest.course.client.rest;

import com.epam.brest.course.dao.Department;
import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.dto.DepartmentDtoWithAvgSalary;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

public class DepertmentConsumerRest implements DepartmentService{

    private String url;

    private RestTemplate restTemplate;

    public DepertmentConsumerRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/" + departmentId, Department.class);
        Department result = (Department) responseEntity.getBody();
        return result;
    }

    @Override
    public Collection<DepartmentDto> getAllDepartmentDto() {
        ResponseEntity responseEntity
                = restTemplate.getForEntity(url+"dto", List.class);
        List<DepartmentDto> result = (List<DepartmentDto>) responseEntity.getBody();
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<DepartmentDtoWithAvgSalary> getAllDepartmentWithAvgSalary() {
        ResponseEntity responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<DepartmentDtoWithAvgSalary> departments
                = (List<DepartmentDtoWithAvgSalary>)responseEntity.getBody();
        return departments;
    }

    @Override
    public Department addDepartment(Department department) {
        ResponseEntity responseEntity
                = restTemplate.postForEntity(url, department, Department.class);
        Department result = (Department) responseEntity.getBody();
        return result;
    }

    @Override
    public void updateDepartment(Department department) {
        restTemplate.put(url, department);
    }

    @Override
    public void deleteDepartmentById(int departmentId) {
        restTemplate.delete(url + "/" + departmentId);
    }
}
