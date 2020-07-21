SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS departments (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 description VARCHAR,
 numberOfEmployees INTEGER
);

CREATE TABLE IF NOT EXISTS users (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 department VARCHAR,
 userDepartmentNumber VARCHAR,
 position VARCHAR,
 role VARCHAR
);

CREATE TABLE IF NOT EXISTS news (
 id int PRIMARY KEY auto_increment,
 headline VARCHAR,
 newsArticle VARCHAR,
 departmentid INTEGER
);