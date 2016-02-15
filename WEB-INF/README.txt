1. How to compile files in classes.

A list of Java files to compile: BrowserController.java, CustomerInfoController.java, PageController.java, SearchController.java, ShoppingController.java. 

To compile a java file in the list,  do the following command: 
$ sudo javac -classpath .:/.../servlet-api.jar /.../JDBC.java /.../JAVAFILETOCOMPILE.java

One example of compile SearchController.java
$ sudo javac -classpath .:/YOUR_DIRECTORY/fabflix/WEB-INF/lib/servlet-api.jar /YOUR_DIRECTORY/fabflix/WEB-INF/classes/ParseResult.java /YOUR_DIRECTORY/fabflix/WEB-INF/classes/JDBC.java /YOUR_DIRECTORY/fabflix/WEB-INF/classes/SearchController.java

2. After compiling all java files, generate the WAR file fabflix.war as the following instructions:
1) For Mac OSX system, open the terminal and change directory to /YOUR_DIRECTORY/webapps/fabflix
2) Run the following command: 
$ sudo jar -cvf fabflix.war *
3) Now you will find a fabflix.war file inside the fabflix folder. Move it to /YOUR_DIRECTORY/webapps folder. 
4. Deploy fabflix.war from Tomcat Application Manager page. 
