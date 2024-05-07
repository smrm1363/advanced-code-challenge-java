package com.statista.code.challenge.infrastructure.output.data;

import com.statista.code.challenge.application.port.DepartmentRepositoryPort;
import com.statista.code.challenge.domain.model.department.Department;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Repository
@Validated
public class DepartmentMemoryRepositoryAdaptor implements DepartmentRepositoryPort {

  private final Map<UUID, Department> departmentMap = new ConcurrentHashMap<>();

  @Override
  public void insert(@Validated Department department) {
    validateDuplication(department.name());
    departmentMap.put(department.id(), department);
  }

  @Override
  public Optional<Department> findByName(String name) {
    return departmentStreamByName(name).findFirst();
  }

  @Override
  public List<Department> findAll() {
    return new ArrayList<>(departmentMap.values());
  }

  private void validateDuplication(String name) {
    if (departmentStreamByName(name).findAny().isPresent())
      throw new ValidationException(String.format(
          "A department with the name is already exist, the department name should be uniq. Department name : %s",
          name));
  }

  private Stream<Department> departmentStreamByName(String name) {
    return departmentMap.values().stream().filter(department -> department.name().equals(name));
  }
}
