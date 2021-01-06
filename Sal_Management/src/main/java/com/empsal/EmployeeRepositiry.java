package com.empsal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositiry extends CrudRepository<Employee, Long>{
}
