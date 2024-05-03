package com.statista.code.challenge.application.port;

import com.statista.code.challenge.domain.model.department.Department;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface DepartmentRepositoryPort {

  void insert(@Valid Department department);

  Optional<Department> findByName(String name);

  List<Department> findAll();
}
