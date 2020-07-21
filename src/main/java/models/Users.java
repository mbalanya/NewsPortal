package models;

public class Users {
    private String name;
    private String department;
    private String userDepartmentNumber;
    private String position;
    private String role;

    public Users(String name, String department, String userDepartmentNumber, String position, String role) {
        this.name = name;
        this.department = department;
        this.userDepartmentNumber = userDepartmentNumber;
        this.position = position;
        this.role = role;
    }
}
