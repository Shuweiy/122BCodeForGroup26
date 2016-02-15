<%@page import="java.util.ArrayList,
    java.util.List, java.util.Date, java.text.SimpleDateFormat"%>

<%@page import="java.sql.*,
 javax.sql.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*"
%>



<!DOCTYPE html>
<html class="full" lang="en">
<!-- Make sure the <html> tag is set to the .full CSS class. Change the background image in the full.css file. -->

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>The Big Picture - Start Bootstrap Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/the-big-picture.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-bottom" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Start Bootstrap</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#">Services</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-12">
           <%

                String star_id = request.getParameter("id");


                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "");


                Statement select = connection.createStatement();
                
                // Get attributes of Star Info
                // Get name, dob, photo_url
                String sqlStar = "SELECT * FROM stars WHERE id="+star_id;
                String name = null;
                String dob = null;
                String photo_url = null;

                ResultSet resultStar = select.executeQuery(sqlStar);
                if(resultStar.next())
                {
                    String fn = resultStar.getString(2);
                    String ln = resultStar.getString(3);
                    name = fn + " " + ln;

                    java.sql.Date date = resultStar.getDate(4);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    dob=df.format(date);

                    photo_url = resultStar.getString(5);
                }
                resultStar.close();

                // Get movies
                // Get movie ids.
                ArrayList<Integer> movieIDs = new ArrayList<Integer>();
                String sqlMovieID = "SELECT movie_id FROM stars_in_movies where star_id=" + star_id;
                ResultSet resultMovieID = select.executeQuery(sqlMovieID);

                while(resultMovieID.next())
                {
                    movieIDs.add(resultMovieID.getInt(1));
                }
                resultMovieID.close();

                // Get movie titles
                String movieTitleString = "";
                ArrayList<String> movieTitles = new ArrayList<String>();
                for(int movieID : movieIDs)
                {
                    String sqlMovieTitle = "SELECT title FROM movies WHERE id="+movieID;
                    Statement select2 = connection.createStatement();
                    ResultSet resultMovieTitle = select2.executeQuery(sqlMovieTitle);
                    while(resultMovieTitle.next())
                    {
                        String movieTitle = resultMovieTitle.getString(1);
                        //movieTitleString += movieTitle +". ";
                        movieTitles.add(movieTitle);
                    }
                    resultMovieTitle.close();
                }

                // Show contents on the webpage
                out.write("<h1>Star: "+name+"</h1>");

                //// basics
                out.write("<p>Date of Birth: "+dob+"</p>");
                out.write("<p>Star ID: "+star_id+"</p>");

                //// movies
                out.write("<p>Movies: </p>");
                for(int i=0; i<movieIDs.size(); i++)
                {
                    int movie_id = movieIDs.get(i);
                    String movieTitle = movieTitles.get(i);
                    out.write("<a href= '/fabflix/movie.jsp?id="+movie_id+"'>"+movieTitle+"</a>");
                    out.write("<br>");
                }
                out.write("<br>");

                //// photo_url
                out.write("<img class='img-responsive' src='"+photo_url+"'>");
                out.write("<br>");
            %> 

                <p>TO ADJUST:1. change the size and location of star's profile image("photo_url").
                </p>            
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
