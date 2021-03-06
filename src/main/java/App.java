import static spark.Spark.*;

import com.google.gson.Gson;
import dao.Sql2oNewsDao;
import dao.Sql2oUsersDao;
import dao.Sql2oDepartmentDao;
import models.Departments;
import models.News;
import models.Users;
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

        post("/departments/:departmentId/news/new", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("departmentId"));
            News news = gson.fromJson(req.body(), News.class);
            news.setDepartmentId(departmentId);
            newsDao.add(news);
            res.status(201);
            return gson.toJson(news);
        });

        post("/users/new", "application/json", (req, res) -> {
            Users users = gson.fromJson(req.body(), Users.class);
            usersDao.add(users);
            res.status(201);
            return gson.toJson(users);
        });


        //READ
        get("/departments", "application/json", (req, res) -> { //accept a request in format JSON from an app
            System.out.println(departmentDao.getAll());
            if(departmentDao.getAll().size() > 0){
                return gson.toJson(departmentDao.getAll());//send it back to be displayed
            } else {
                return "{\"message\":\"I'm sorry, no departments are currently listed.\"}";
            }

        });

        get("/departments/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int departmentId = Integer.parseInt(req.params("id"));
            Departments departmentToFind = departmentDao.findById(departmentId);

            if (departmentToFind == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(departmentToFind);
        });

        get("/departments/:id/news", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));

            Departments departmentToFind = departmentDao.findById(departmentId);
            List<News> allNews;

            if (departmentToFind == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }

            allNews = newsDao.getAllNewsByDepartment(departmentId);

            return gson.toJson(allNews);
        });

        get("/news", "application/json", (req, res) -> {
            return gson.toJson(newsDao.getAll());
        });

        get("/users", "application/json", (req, res) -> {
            return gson.toJson(usersDao.getAll());
        });

        get("/users/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int usersId = Integer.parseInt(req.params("id"));
            Users usersToFind = usersDao.findById(usersId);

            if(usersToFind == null) {
                throw new ApiException(404, String.format("No user with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(usersToFind);
        });


        //FILTERS
        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = (ApiException) exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

        after((req, res) -> {
            res.type("application/json");
        });
    }
}

