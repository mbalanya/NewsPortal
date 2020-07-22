package dao;

import models.Departments;
import models.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {

    private Connection conn;
    private Sql2oNewsDao newsDao;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        newsDao = new Sql2oNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    //helpers

    public News setupNews() {
        News news = new News("Apple buys apples", "They had to eat healthy", 1);
        newsDao.add(news);
        return news;
    }

    public News setupNewsForDepartments(Departments departments) {
        News news = new News("Apple buys apples", "They had to eat healthy", departments.getId());
        newsDao.add(news);
        return news;
    }

    public Departments setupDepartments() {
        Departments departments = new Departments("Finance", "Take care of money", 5);
        departmentDao.add(departments);
        return departments;
    }

    @Test
    public void addingNewsSetsId() throws Exception {
        News testNews = setupNews();
        assertEquals(1, testNews.getId());
    }

    @Test
    public void getAll() throws Exception {
        News news1 = setupNews();
        News news2 = setupNews();
        assertEquals(2, newsDao.getAll().size());
    }

    @Test
    public void getAllNewsByDepartment() throws Exception {
        Departments testDepartment = setupDepartments();
        Departments otherDepartment = setupDepartments(); //add in some extra data to see if it interferes
        News news1 = setupNewsForDepartments(testDepartment);
        News news2 = setupNewsForDepartments(testDepartment);
        News newsForOtherDepartment = setupNewsForDepartments(otherDepartment);
        assertEquals(2, newsDao.getAllNewsByDepartment(testDepartment.getId()).size());
    }

    @Test
    public void deleteById() throws Exception {
        News testNews = setupNews();
        News otherReview = setupNews();
        assertEquals(2, newsDao.getAll().size());
        newsDao.deleteById(testNews.getId());
        assertEquals(1, newsDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        News testNews = setupNews();
        News otherNews = setupNews();
        newsDao.clearAll();
        assertEquals(0, newsDao.getAll().size());
    }
}