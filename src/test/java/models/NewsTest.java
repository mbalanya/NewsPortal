package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public News setupNews() {
        return new News("Apple buys apples", "For sure they had to eat healthy", 1);
    }

    @Test
    public void getHeadlineReturnsCorrectHeadline() throws Exception {
        News testNews = setupNews();
        assertEquals("Apple buys apples", testNews.getHeadline());
    }

    @Test
    public void getNewsArticleReturnsCorrectNewsArticle() throws Exception {
        News testNews = setupNews();
        assertEquals("For sure they had to eat healthy", testNews.getNewsArticle());
    }

    @Test
    public void getDepartmentIdReturnsCorrectDepartmentId() throws Exception {
        News testNews = setupNews();
        assertEquals(1, testNews.getDepartmentId());
    }

    @Test
    public void setHeadlineSetsCorrectHeadline() throws Exception {
        News testNews = setupNews();
        testNews.setHeadline("Its Samsung");
        assertNotEquals("Apple buys apples", testNews.getHeadline());
    }

    @Test
    public void setNewsArticleSetsCorrectNewsArticle() throws Exception {
        News testNews = setupNews();
        testNews.setNewsArticle("They dont eat fruits");
        assertNotEquals("For sure they had to eat healthy", testNews.getNewsArticle());
    }

    @Test
    public void setDepartmentIdSetsCorrectDepartmentId() throws Exception {
        News testNews = setupNews();
        testNews.setDepartmentId(5);
        assertNotEquals(1, testNews.getDepartmentId());
    }
}
