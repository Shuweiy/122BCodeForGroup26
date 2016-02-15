<%@page import="java.util.ArrayList,
    java.util.List"%>

<%@page import="java.sql.*,
 javax.sql.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*"
%>



<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Landing Page - Start Bootstrap Theme</title>
    <!-- Bootstrap Core CSS -->

    <!-- Bootstrap Core CSS -->
    <link href="/fabflix/css2/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/fabflix/css2/1-col-portfolio.css" rel="stylesheet">
    <!-- Custom CSS -->

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
        <div class="container topnav">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">asdf navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand topnav" href="#">Start Bootstrap</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
 
                    <li>
                        <a href="/fabflix/servlet/Shopping?action=check">Shopping Cart</a>
                    </li>

                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>


    <!-- Header -->
    <div class = "container" style = "margin-top:4%">
         <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Page Heading
                    <small>Secondary Text</small>
                </h1>
            </div>
        </div>


    <% ArrayList<Integer> Movielist = (ArrayList<Integer>) session.getAttribute("Movielist");
        Integer PageNo = (Integer) session.getAttribute("PageNo");
        Integer display = (Integer) session.getAttribute("display");
        Class.forName("com.mysql.jdbc.Driver").newInstance();           
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "");

        ArrayList<Integer>  movie_id_list = new ArrayList<Integer>();   

            if((Movielist.size()-display*PageNo)>= display){

               movie_id_list = new ArrayList<Integer>(Movielist.subList(PageNo*display, (PageNo+1)*display));

              }
              else{

              movie_id_list = new ArrayList<Integer>(Movielist.subList(PageNo*display,Movielist.size()));
              }
            
            for(int movie_id : movie_id_list) 
                {

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

/* !!! NOTICE BEFORE DELETE: trailer_url IS NOT USED IN Movielist.jsp BUT IN movie.jsp.
                // Get trailer_url
                String sqlTrailerURL = "SELECT trailer_url FROM movies WHERE id="+movie_id+";";
                ResultSet resultTrailerURL = select.executeQuery(sqlTrailerURL);

                String trailer_url = null;
                if(resultTrailerURL.next())
                {
                    trailer_url = resultTrailerURL.getString(1);
                }

                resultTrailerURL.close();   
*/

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
                
                
                out.write("<div class ='row'>");
                out.write("<div class'col-md-7'");
                out.write("<a href='/fabflix/movie.jsp?id="+movie_id+"'>");
                //out.write("<img class='img-responsive' src='http://placehold.it/700x300'>");
                out.write("<img class='img-responsive' src='"+banner_url+"'>");
                out.write("</a>");
                out.write("</div>");
                out.write("<div class='col-md-5'>");

                // Show contents on the webpage
                out.write("<h3>Movie Title: "+title+"</h3>");
                out.write("<h4> Director: "+director +"</h4>");
                out.write("<p>Year: "+year+"</p>");

                out.write("<p>Genre: ");
                for(int i=0; i<genreStrings.size()-1; i++)
                {
                    out.write(genreStrings.get(i)+", ");
                }
                out.write(genreStrings.get(genreStrings.size()-1)+".</p>");

                out.write("<p>Stars: ");
                for(int j=0; j<star_ids.size()-1; j++)
                {
                    int star_id = star_ids.get(j);
                    String starString = starNames.get(j);
                    out.write("<a href = '/fabflix/Star.jsp?id="+star_id+"'>"+starString+"</a>, ");
                }
                out.write("<a href = '/fabflix/Star.jsp?id="+star_ids.get(star_ids.size()-1)+"'>"+starNames.get(starNames.size()-1)+"</a>.</p>");


                out.write("<a class='btn btn-primary' href='/fabflix/movie.jsp?id="+movie_id+"'>View Project <span class='glyphicon glyphicon-chevron-right'></span></a>");
                
                out.write("</div>");
                out.write("</div>");

                out.write("<hr>");


    }; %>
    </div>
    <div class = "col-md-12" style="
    text-align: center"> 
        <h3 style="display: inline;"><a href='/fabflix/servlet/Page?display=10'>10</a></h3> 
        <h3 style="display: inline;margin-left:2%"><a href='/fabflix/servlet/Page?display=50'>50</a></h3> 
        <h3 style="display: inline;margin-left:2%"><a href='/fabflix/servlet/Page?display=100'>100</a></h3>
    </div>


 <div class="row text-center">
            <div class="col-lg-12">
                <ul class="pagination">
                    <%
                    if(PageNo > 0)
                    {
                    out.write("<li><a href='/fabflix/servlet/Page?input=back'>&laquo;</a></li>");
                    }
                    %>

                    <%      
                    if(((PageNo+1)*display)<Movielist.size()) 
                    {                    
                    out.write("<li><a href='/fabflix/servlet/Page?input=next'>&raquo;</a></li>");
                    } 
                    %>   
                </ul>
            </div>
        </div>
        <!-- /.row -->




    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
