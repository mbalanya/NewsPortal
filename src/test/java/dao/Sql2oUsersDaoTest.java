package dao;

import models.Users;
import dao.Sql2oUsersDao;
import dao.Sql2oDepartmentDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Sql2oUsersDaoTest {
    private Sql2oUsersDao usersDao;
    private Sql2oDepartmentDao departmentDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    // helpers

    public Users setupNewUsers(){
        return new Users("Tom", "Finance", "5432", "Finance Officer", "Take care of money");
    }

    @Test
    public void addingUserSetsId() throws Exception {
        Users testUsers = setupNewUsers();
        int originalUsersId = testUsers.getId();
        usersDao.add(testUsers);
        assertNotEquals(originalUsersId, testUsers.getId());
    }

    @Test
    public void addedUsersAreReturnedFromGetAll() throws Exception {
        Users testUsers = setupNewUsers();
        usersDao.add(testUsers);
        assertEquals(1, usersDao.getAll().size());
    }

    @Test
    public void noUsersReturnsEmptyList() throws Exception {
        assertEquals(0, usersDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectUsers() throws Exception {
        Users testusers = setupNewUsers();
        usersDao.add(testusers);
        usersDao.deleteById(testusers.getId());
        assertEquals(0, usersDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Users testUsers = setupNewUsers();
        Users otherUsers = setupNewUsers();
        usersDao.clearAll();
        assertEquals(0, usersDao.getAll().size());
    }
}