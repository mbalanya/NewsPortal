package dao;

import models.Users;
import java.util.List;

public interface UsersDao {

    //create
    void add(Users users);
    //void addUsersToDepartment(Users users, Department department);

    //read
    List<Users> getAll();
    // List<Users> getAllUsersForADepartment(int id);

    //update
    //omit for now

    //delete
    void deleteById(int id);
    void clearAll();
}