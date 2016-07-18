package ro.teamnet.zth.appl.service.impl;

import ro.teamnet.zth.api.annotations.MyService;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.service.DepartmentService;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
@MyService(name = "DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentDao department = new DepartmentDao();

    @Override
    public List<Department> findAllDepartments() {
        return department.findAllDepartments();
    }

    @Override
    public Department findOneDepartment(Long id) {
        return department.findDepartmentById(id);
    }
}
