import static spark.Spark.*;

import com.google.gson.Gson;
import dao.Sql2oNewsDao;
import dao.Sql2oUsersDao;
import dao.Sql2oDepartmentDao;
import models.Departments;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import exceptions.ApiException;

import java.util.List;
import java.util.*;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oUsersDao usersDao;
        Sql2oDepartmentDao departmentDao;
        Sql2oNewsDao newsDao;
        Connection conn;
        Gson gson = new Gson();

        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();

        //CREATE
        post("/departments/new", "application/json", (req, res) -> {
            Departments departments = gson.fromJson(req.body(), Departments.class);
            departmentDao.add(departments);
            res.status(201);
            return gson.toJson(departments);
        });

        post("/departments/departmentId/news/new", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("departmentId"));
            News news = gson.fromJson(req.body(), News.class);
            news.setDepartmentId(departmentId);
            newsDao.add(news);
            res.status(201);
            return gson.toJson(news);
        });




        //FILTERS
        after((req, res) -> {
            res.type("application/json");
        });
    }
}

