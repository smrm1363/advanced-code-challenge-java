package com.statista.code.challenge.application.service;

import com.statista.code.challenge.application.port.DepartmentRepositoryPort;
import com.statista.code.challenge.application.usecase.DepartmentUseCase;
import com.statista.code.challenge.domain.model.department.Department;
import com.statista.code.challenge.domain.model.department.EducationDepartment;
import com.statista.code.challenge.domain.model.department.MedicalDepartment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentUseService implements DepartmentUseCase {

  private final DepartmentRepositoryPort departmentRepositoryPort;

  @Override
  @PostConstruct
  public void createDepartments() {
    departmentRepositoryPort.insert(
        new Department(UUID.randomUUID(), "Entertainment department", new EducationDepartment()));
    departmentRepositoryPort.insert(
        new Department(UUID.randomUUID(), "Medical department", new MedicalDepartment()));
    departmentRepositoryPort.insert(
        new Department(UUID.randomUUID(), "Education department", new EducationDepartment()));
  }

  @Override
  public Optional<Department> findByDepartment(String name) {
    return departmentRepositoryPort.findByName(name);
  }

  @Override
  public List<Department> findAll() {
    return departmentRepositoryPort.findAll();
  }
}
