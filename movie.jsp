<%@page import="java.util.ArrayList,
    java.util.List"%>

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
    <%
        String movie_id = request.getParameter("id");
    %>

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
                  <%out.write("<a class='navbar-brand'  href='/fabflix/servlet/Shopping?item="+movie_id+"'>Purchease</a>");%>            </div>
<!--                 <a class="navbar-brand" href="#">Start Bootstrap</a>
 -->            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
        
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-12">
            <%



                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "");


                Statement select = connection.createStatement();
                
                // Get attributes of Movie Info

                // Get banner_url
                String sqlBannerURL = "SELECT banner_url FROM movies WHERE id="+movie_id+";";
                ResultSet resultBannerURL = select.executeQuery(sqlBannerURL);

                String banner_url = null;
                if(resultBannerURL.next())
                {
                    banner_url = resultBannerURL.getString(1);
                }

                resultBannerURL.close(); 

                // Get trailer_url
                String sqlTrailerURL = "SELECT trailer_url FROM movies WHERE id="+movie_id+";";
                ResultSet resultTrailerURL = select.executeQuery(sqlTrailerURL);

                String trailer_url = null;
                if(resultTrailerURL.next())
                {
                    trailer_url = resultTrailerURL.getString(1);
                }

                resultTrailerURL.close();   

                // Get title
                String sqlTitle = "SELECT title FROM movies WHERE id="+movie_id+";";
                ResultSet resultTitle = select.executeQuery(sqlTitle);

                String title = null;
                if(resultTitle.next())
                {
                    title = resultTitle.getString(1);
                }

                resultTitle.close();

                // Get year
                String sqlYear = "SELECT year FROM movies WHERE id="+movie_id+";";
                ResultSet resultYear = select.executeQuery(sqlYear);
                
                Integer year = 0;
                if(resultYear.next())
                {
                    year=resultYear.getInt(1);
                }

                resultYear.close();

                // Get director
                String sqlDirector = "SELECT director FROM movies WHERE id="+movie_id+";";
                ResultSet resultDirector = select.executeQuery(sqlDirector);

                String director = null;
                if(resultDirector.next())
                {
                    director = resultDirector.getString(1);
                }

                resultDirector.close();

                // Get genre_ids
                String sqlGenres = "SELECT genre_id FROM genres_in_movies WHERE movie_id="+movie_id+";";                
                ResultSet resultGenre = select.executeQuery(sqlGenres);

                ArrayList<Integer> genre_ids = new ArrayList<Integer>();
                while(resultGenre.next())
                {
                    genre_ids.add(resultGenre.getInt(1));
                }

                resultGenre.close();

                // Get genreStrings
                ArrayList<String> genreStrings = new ArrayList<String>();
                for(int genre_id: genre_ids)
                {
                    String sqlGenreString = "SELECT name from genres where id="+genre_id;
                    ResultSet resultGenreString = select.executeQuery(sqlGenreString);
                    
                    if(resultGenreString.next())
                    {
                        String genreString = resultGenreString.getString(1);
                        genreStrings.add(genreString);
                    }

                    resultGenreString.close();
                }

                // Get star_ids
                String sqlStars = "SELECT star_id FROM stars_in_movies WHERE movie_id="+movie_id+";";
                ResultSet resultStar = select.executeQuery(sqlStars);

                ArrayList<Integer> star_ids = new ArrayList<Integer>();                
                while(resultStar.next())
                {
                    star_ids.add(resultStar.getInt(1));
                }

                resultStar.close();

                // Get starNames
                ArrayList<String> starNames = new ArrayList<String>();
                for(int star_id : star_ids)
                {
                    String sqlStarNames = "SELECT * from stars where id="+star_id;
                    ResultSet resultStarNames = select.executeQuery(sqlStarNames);

                    if(resultStarNames.next())
                    {
                        String starName = resultStarNames.getString(2) + " " + resultStarNames.getString(3);
                        starNames.add(starName);
                    }

                    resultStarNames.close();
                }  

                // Show on the webpage
                //// basics
                out.write("<h1>Movie: "+title+"</h1>");
                out.write("<h4> Director: "+director +"</h4>");
                out.write("<p>Year: "+year+"</p>");

                //// genres
                out.write("<p>Genre: ");
                for(int i=0; i<genreStrings.size()-1; i++)
                {
                    out.write(genreStrings.get(i)+", ");
                }
                out.write(genreStrings.get(genreStrings.size()-1)+".</p>");

                //// stars
                out.write("<p>Stars: ");
                for(int j=0; j<star_ids.size()-1; j++)
                {
                    int star_id = star_ids.get(j);
                    String starString = starNames.get(j);
                    out.write("<a href = '/fabflix/Star.jsp?id="+star_id+"'>"+starString+"</a>, ");
                }
                out.write("<a href = '/fabflix/Star.jsp?id="+star_ids.get(star_ids.size()-1)+"'>"+starNames.get(starNames.size()-1)+"</a>.</p>");

                //// trailer_url
                out.write("Watch trailer: <a href = '"+trailer_url+"'>"+trailer_url+"</a>.");
                out.write("<p><a href = '"+trailer_url+"'><i>[Download]</i>.</a></p>");

                //// banner_url
                out.write("<img class='img-responsive' src='"+banner_url+"'>");
                out.write("<br>");
            %>            
                <p>TO ADJUST: 1. change the size and location of Banner Image ("banner_url").
                    2. change the location of trailer url ("trailer_url").
                    3. display the trailer_url's link into embedded movie</p>
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
