package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    public Departments setupDepartments() {
        return new Departments("Finance", "provide money", 5);
    }

    @Test
    public void getNameReturnsCorrectName() throws Exception {
        Departments testDepartments = setupDepartments();
        assertEquals("Finance", testDepartments.getName());
    }

    @Test
    public void getDescriptionReturnsCorrectDescription() throws Exception {
        Departments testDepartments = setupDepartments();
        assertEquals("provide money", testDepartments.getDescription());
    }

    @Test
    public void getNumberOfEmployeesReturnsCorrectNumberOfEmployees() throws Exception {
        Departments testDepartments = setupDepartments();
        assertEquals(5, testDepartments.getNumberOfEmployees());
    }

    @Test
    public void setNameSetsCorrectName() throws Exception {
        Departments testDepartments = setupDepartments();
        testDepartments.setName("marketing");
        assertNotEquals("Finance", testDepartments.getName());
    }

    @Test
    public void setDescriptionSetsCorrectDescription() throws Exception {
        Departments testDepartments = setupDepartments();
        testDepartments.setDescription("say about us");
        assertNotEquals("provide money", testDepartments.getDescription());
    }

    @Test
    public void setNumberOfEmployeesSetsCorrectNumberOfEmployees() throws Exception {
        Departments testDepartments = setupDepartments();
        testDepartments.setNumberOfEmployees(3);
        assertNotEquals(5, testDepartments.getNumberOfEmployees());
    }
}
