package com.statista.code.challenge.infrastructure.output.data;

import com.statista.code.challenge.domain.model.department.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.statista.code.challenge.TestDataProvider.getADepartment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentMemoryRepositoryAdaptorTest {

  @InjectMocks
  private DepartmentMemoryRepositoryAdaptor repository;
  @Mock
  private Map<UUID, Department> departmentMap;
  @Captor
  private ArgumentCaptor<Department> departmentArgumentCaptor;

  @BeforeEach
  void innitEachTest() {
    ReflectionTestUtils.setField(repository, "departmentMap", departmentMap);
  }

  @Test
  void givenADepartment_insert_theDepartmentInserted() {
    Department department = getADepartment();

    repository.insert(department);

    verify(departmentMap).put(any(), departmentArgumentCaptor.capture());
    Department insertedDepartment = departmentArgumentCaptor.getValue();
    assertEquals(department.id(), insertedDepartment.id());
  }

  @Test
  void givenListOfDepartments_findByName_departmentFund() {
    Department department = getADepartment();

    when(departmentMap.values()).thenReturn(List.of(department));

    Optional<Department> foundDepartment = repository.findByName(department.name());

    assertEquals(department, foundDepartment.get());
  }

  @Test
  void givenListOfDepartment_findAll_ReturnedAllDepartments() {
    Department department = getADepartment();

    when(departmentMap.values()).thenReturn(List.of(department));

    List<Department> departments = repository.findAll();

    assertEquals(1, departments.size());
    assertEquals(department, departments.get(0));
  }
}