package com.statista.code.challenge.domain.model.department;

public class MedicalDepartment implements DepartmentStrategy {

  @Override
  public String doBusiness(Department department) {
    return String.format("The magic logic of MedicalDepartment is done. Department name : %s",
        department.name());
  }
}
