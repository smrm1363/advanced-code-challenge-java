package com.statista.code.challenge.domain.model.department;

public class EntertainmentDepartment implements DepartmentStrategy {

  @Override
  public String doBusiness(Department department) {
    return String.format("The magic logic of EntertainmentDepartment is done. Department name : %s",
        department.name());
  }
}
