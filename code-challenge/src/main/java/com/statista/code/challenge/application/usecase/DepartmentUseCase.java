package com.statista.code.challenge.application.usecase;

import com.statista.code.challenge.domain.model.department.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentUseCase {

  void createDepartments();

  Optional<Department> findByDepartment(String name);

  List<Department> findAll();
}
