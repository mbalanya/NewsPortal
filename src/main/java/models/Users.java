package models;

import java.util.Objects;

public class Users {
    private String name;
    private String department;
    private String userDepartmentNumber;
    private String position;
    private String role;
    private int id;

    public Users(String name, String department, String userDepartmentNumber, String position, String role) {
        this.name = name;
        this.department = department;
        this.userDepartmentNumber = userDepartmentNumber;
        this.position = position;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserDepartmentNumber() {
        return userDepartmentNumber;
    }

    public void setUserDepartmentNumber(String userDepartmentNumber) {
        this.userDepartmentNumber = userDepartmentNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return id == users.id &&
                name.equals(users.name) &&
                department.equals(users.department) &&
                userDepartmentNumber.equals(users.userDepartmentNumber) &&
                position.equals(users.position) &&
                role.equals(users.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, department, userDepartmentNumber, position, role, id);
    }
}
