package com.statista.code.challenge.domain.model.department;

public class EducationDepartment implements DepartmentStrategy {

  @Override
  public String doBusiness(Department department) {
    return String.format("The magic logic of EducationDepartment is done. Department name : %s",
        department.name());
  }
}
