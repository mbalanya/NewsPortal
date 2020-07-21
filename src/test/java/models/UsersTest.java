package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsersTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public Users setupUsers() {
        return new Users("Tom", "Finance", "5432", "Finance Officer", "Take care of money");
    }

    @Test
    public void getNameReturnsCorrectName() throws Exception {
        Users testUsers = setupUsers();
        assertEquals("Tom", testUsers.getName());
    }

    @Test
    public void getDepartmentReturnsCorrectDepartment() throws Exception {
        Users testUsers = setupUsers();
        assertEquals("Finance", testUsers.getDepartment());
    }

    @Test
    public void getUserDepartmentNumberReturnsCorrectUserDepartmentNumber() throws Exception {
        Users testUsers = setupUsers();
        assertEquals("5432", testUsers.getUserDepartmentNumber());
    }

    @Test
    public void getPositionReturnsCorrectPosition() throws Exception {
        Users testUsers = setupUsers();
        assertEquals("Finance Officer", testUsers.getPosition());
    }

    @Test
    public void getRoleReturnsCorrectRole() throws Exception {
        Users testUsers = setupUsers();
        assertEquals("Take care of money", testUsers.getRole());
    }

    @Test
    public void setNameSetsCorrectName() throws Exception {
        Users testUsers = setupUsers();
        testUsers.setName("Jerry");
        assertNotEquals("Tom", testUsers.getName());
    }

    @Test
    public void setDepartmentSetsCorrectDepartment() throws Exception {
        Users testUsers = setupUsers();
        testUsers.setDepartment("IT");
        assertNotEquals("Finance", testUsers.getDepartment());
    }

    @Test
    public void setUserDepartmentNumberSetsCorrectUserDepartmentNumber() throws Exception {
        Users testUsers = setupUsers();
        testUsers.setUserDepartmentNumber("2345");
        assertNotEquals("5432", testUsers.getUserDepartmentNumber());
    }

    @Test
    public void setPositionSetsCorrectPosition() throws Exception {
        Users testUsers = setupUsers();
        testUsers.setPosition("Coder");
        assertNotEquals("Finance Officer", testUsers.getPosition());
    }

    @Test
    public void setRoleSetsCorrectRole() throws Exception {
        Users testUsers = setupUsers();
        testUsers.setRole("Be artistic in code");
        assertNotEquals("Take care of money", testUsers.getRole());
    }
}
