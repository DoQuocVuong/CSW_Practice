package com.example.CSW_Practice.model;

import com.example.CSW_Practice.entity.Employee;
import com.example.CSW_Practice.utils.ConnectionHelper;
import javafx.scene.media.EqualizerBand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    private Connection conn;

    public EmployeeModel() {
        conn = ConnectionHelper.getConnection();
    }

    public List<Employee> findAll() throws SQLException {
        List<Employee> products = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("select * from employee");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            int status = rs.getInt("status");
            products.add(new Employee(id, name, age, status));
        }
        return products;
    }

    public Employee findById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("select * from employee where id = ?");
        stmt.setInt(1, 1);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            int status = rs.getInt("status");
            return new Employee(id, name, age, status);
        }
        return null;
    }

    public Employee save(Employee employee) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("insert into employee (name, age, status) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, employee.getName());
        stmt.setInt(2, employee.getAge());
        stmt.setInt(3, employee.getStatus());
        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            ResultSet resultSetGeneratedKeys = stmt.getGeneratedKeys();
            if (resultSetGeneratedKeys.next()) {
                int id = resultSetGeneratedKeys.getInt(1);
                employee.setId(id);
                return employee;
            }
        }
        return null;
    }

    public Employee update(int id, Employee updateEmployee) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("update employee set name = ?, age = ?, status = ? where id = ?");
        stmt.setString(1, updateEmployee.getName());
        stmt.setInt(2, updateEmployee.getAge());
        stmt.setInt(3, updateEmployee.getStatus());
        stmt.setInt(4, id);
        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            updateEmployee.setId(id);
            return updateEmployee;
        }
        return null;
    }

    public boolean delete(int id) throws SQLException {
        PreparedStatement stmtDelete = conn.prepareStatement("delete from employee where id = ?");
        stmtDelete.setInt(1, id);
        int affectedRows = stmtDelete.executeUpdate();
        if (affectedRows > 0) {
            return true;
        }
        return false;
    }
}
