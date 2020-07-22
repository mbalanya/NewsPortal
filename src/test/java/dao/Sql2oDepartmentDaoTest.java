package dao;

import models.Users;
import models.Departments;
import dao.Sql2oUsersDao;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

public class Sql2oDepartmentDaoTest {
    private Connection conn;
    private Sql2oDepartmentDao departmentDao;
    private Sql2oUsersDao usersDao;
    private Sql2oNewsDao newsDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    //helpers

    public Departments setupDepartments (){
        Departments departments = new Departments("Finance", "Tracks money", 5);
        departmentDao.add(departments);
        return departments;
    }

    @Test
    public void addingUsersSetsId() throws Exception {
        Departments testDepartments = setupDepartments();
        assertNotEquals(0, testDepartments.getId());
    }

    @Test
    public void addedDepartmentsAreReturnedFromGetAll() throws Exception {
        Departments testDepartment = setupDepartments();
        assertEquals(1, departmentDao.getAll().size());
    }

    @Test
    public void noDepartmentsReturnsEmptyList() throws Exception {
        assertEquals(0, departmentDao.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectDepartment() throws Exception {
        Departments testDepartment = setupDepartments();
        Departments otherDepartment = setupDepartments();
        assertEquals(testDepartment, departmentDao.findById(testDepartment.getId()));
    }

    @Test
    public void updateCorrectlyUpdatesAllFields() throws Exception {
        Departments testDepartment = setupDepartments();
        departmentDao.update(testDepartment.getId(), "Networking", "Bring internet", 12);
        Departments foundDepartment = departmentDao.findById(testDepartment.getId());
        assertEquals("Networking", foundDepartment.getName());
        assertEquals("Bring internet", foundDepartment.getDescription());
        assertEquals(12, foundDepartment.getNumberOfEmployees());
    }

    @Test
    public void deleteByIdDeletesCorrectDepartment() throws Exception {
        Departments testDepartment = setupDepartments();
        Departments otherDepartment = setupDepartments();
        departmentDao.deleteById(testDepartment.getId());
        assertEquals(1, departmentDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Departments testDepartment = setupDepartments();
        Departments otherDepartment = setupDepartments();
        departmentDao.clearAll();
        assertEquals(0, departmentDao.getAll().size());
    }

}