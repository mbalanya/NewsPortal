package dao;

import models.Departments;
import java.util.List;

public interface DepartmentDao {

    //create
    void add (Departments departments);
    // void addDepartmentToUser(Departments departments, Users users)

    //read
    List<Departments> getAll();
    Departments findById(int id);
    // List<Users> getAllUsersForADepartment(int departmentId);

    //update
    void update(int id, String name, String description, int numberOfEmployees);

    //delete
    void deleteById(int id);
    void clearAll();
}