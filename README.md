# Cinema-project

---

# Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer-start)
* [Author](#author)

---

# <a name="purpose"></a>Project purpose
Creation of cinema booking system with basic operations required for it.

Without being authenticated you can register and login. There are specific urls for admins and users.
Only users can complete order and add tickets to shopping cart.
As admin you can adding new movies, movie sessions and cinema halls.

---

# <a name="structure"></a>Project Structure
* Java 11
* Maven 3.6.0
* Hibernate 5.4.5.Final
  * core
  * java8 
* Spring 5.2.6.RELEASE
  * context, orm, webmvc
* Spring Security
  * core, config, web 5.3.3.RELEASE
* Jackson databind 2.11.0
* Jackson datatype jsr310 2.11.0
* Maven checkstyle plugin 3.1.1
* Maven war plugin 3.2.3
* Javax servlet-api 3.1.0
* Javax annotation-api 1.3.2
* Taglibs 1.1.2
* Apache commons-dbcp2 2.7.0
* Lombok 1.18.12
* Mysql connector java 8.0.17
* Hibernate validator 6.1.5.Final
* Log4j 1.2.17
<hr>

---

# <a name="developer-start"></a>For developer

1. Open the project in your IDE.

2. Add Java SDK 11 or above in Project Structure.

3. Configure Tomcat:
add the artifact cinema-project:war exploded;
add as URL http://localhost:8080/

4. Install MySQL if you don't have it and start MySQLWorkbench.

5. At src.main.resources.db.properties use your username 
and password for your MySQLWorkbench to create a Connection.

6. Run this line to create a schema: CREATE SCHEMA `cinema` DEFAULT CHARACTER SET utf8.

7. Change a path to log file in src.main.resources.log4j.properties.

8. Run the project.

9. For testing this API you can download Postman or another such an analogue.

10. For authorization, you must add a new header, where Authorization is key and Basic email:password is value, where email:password must be encoded to Base64 format.

There are test data that you can use.
There’s one user already registered with ADMIN role (email = "admin@gmail.com", password = "1234
") and
one user with USER role (email = "user@gmail.com", password = "1234"). You can change these test data in InjectDataController if you want.

---

# <a name="author"></a>Author

[Skorobohatyi Dmytro](https://github.com/6ALLIKA)
