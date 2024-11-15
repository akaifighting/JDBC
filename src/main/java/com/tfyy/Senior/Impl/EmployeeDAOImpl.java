package com.tfyy.Senior.Impl;

import com.tfyy.Dao.Employee;
import com.tfyy.Senior.DAO.EmployeeDAO;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<Employee> selectALL() {
        return List.of();
    }

    @Override
    public Employee selectByID(Integer empId) {
        return null;
    }

    @Override
    public int insert(Employee employee) {
        return 0;
    }

    @Override
    public int update(Employee employee) {
        return 0;
    }

    @Override
    public int delete(Integer empId) {
        return 0;
    }
}
