package ro.teamnet.zth.appl.service.impl;

import ro.teamnet.zth.api.annotations.MyService;
import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.service.EmployeeService;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
@MyService(name = "EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao employee = new EmployeeDao();

    @Override
    public List<Employee> findAllEmployees() {

        return employee.getAllEmployees();
    }

    @Override
    public Employee findOneEmployee(Long id) {
        return employee.getEmployeeById(id);
    }

    @Override
    public void deleteOneEmployee(Long id) {
        employee.deleteEmployee(employee.getEmployeeById(id));
    }
}
