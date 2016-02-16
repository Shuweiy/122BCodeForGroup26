<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CSS 122B </title>

    <!-- Bootstrap Core CSS -->
    <link href="/fabflix/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/fabflix/css/landing-page.css" rel="stylesheet">

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
                    <span class="sr-only">Toggle navigation</span>
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
                        <a href="#about">About</a>
                    </li>
                    <li>
                        <a href="#services">Services</a>
                    </li>
                    <li>
                        <a href="#contact">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>



<a name="about"></a>
    <div class="intro-header" style = "background:white;color:#ccc">
        <div class="container">

            <div class="row">
                <div class="col-lg-12" style ="text-align:center">	<H1>Search Movie</H1>



				</div>

			<div class="col-lg-6 col-lg-offset-4" style = "margin-top:5%;">
				<form action="/fabflix/servlet/Search" method="POST">

    			    <div class="input-group">
    				     
    				      <label>title</label><input name = "title" type="text" class="form-control" >
                          <br>
                          <label>year</label><input name = "year" type="text" class="form-control" >
                                                    <br>

                          <label>director</label><input name = "director" type="text" class="form-control">
                                                    <br>

                          <label>star first name</label><input name = "starFN" type="text" class="form-control">
                                                    <br>

                          <label>star last name</label><input name = "starLN" type="text" class="form-control">
                                                    <br>


                            <button type="submit" class="btn btn-default" type="button">Search Movie</button>
    			    </div><!-- /input-group -->

			    </form>  

			  </div><!-- /.col-lg-6 -->

			<div class="col-lg-12" style = "text-align:center">
				<h3>Browser Movie</h3>

				<div class="col-lg-6" style = "text-align:center">
					<h3>Genre</h3>

<a href="/fabflix/servlet/Broswer?input=action"/>action</a> 
<a href="/fabflix/servlet/Broswer?input=adventure"/>adventure</a> 
<a href="/fabflix/servlet/Broswer?input=comedy"/>comedy</a> 
<a href="/fabflix/servlet/Broswer?input=crime"/>crime</a> 
<a href="/fabflix/servlet/Broswer?input=drama"/>drama</a> 
<a href="/fabflix/servlet/Broswer?input=horror"/>horror</a> 
<a href="/fabflix/servlet/Broswer?input=drama"/>drama</a>

				</div>

				<P>

				<div class="col-lg-6" style = "text-align:center">
					<h3>Title</h3>
					<p>
<a href="/fabflix/servlet/Broswer?input=Q"/>Q</a>
<a href="/fabflix/servlet/Broswer?input=W"/>W</a>
<a href="/fabflix/servlet/Broswer?input=E"/>E</a> 
<a href="/fabflix/servlet/Broswer?input=R"/>R</a> 
<a href="/fabflix/servlet/Broswer?input=T"/>T</a> 
<a href="/fabflix/servlet/Broswer?input=Y"/>Y</a> 
<a href="/fabflix/servlet/Broswer?input=U"/>U</a> 
<a href="/fabflix/servlet/Broswer?input=I"/>I</a> 
<a href="/fabflix/servlet/Broswer?input=O"/>O</a>
<a href="/fabflix/servlet/Broswer?input=P"/>P</a> 
<a href="/fabflix/servlet/Broswer?input=A"/>A</a> 
<a href="/fabflix/servlet/Broswer?input=S"/>S</a> 
<a href="/fabflix/servlet/Broswer?input=D"/>D</a> 
<a href="/fabflix/servlet/Broswer?input=F"/>F</a> 
<a href="/fabflix/servlet/Broswer?input=G"/>G</a> 
<a href="/fabflix/servlet/Broswer?input=H"/>H</a> 
<a href="/fabflix/servlet/Broswer?input=J"/>J</a> 
<a href="/fabflix/servlet/Broswer?input=K"/>K</a> 
<a href="/fabflix/servlet/Broswer?input=L"/>L</a> 
<a href="/fabflix/servlet/Broswer?input=Z"/>Z</a> 
<a href="/fabflix/servlet/Broswer?input=X"/>X</a> 
<a href="/fabflix/servlet/Broswer?input=C"/>C</a> 
<a href="/fabflix/servlet/Broswer?input=V"/>V</a> 
<a href="/fabflix/servlet/Broswer?input=B"/>B</a>
<a href="/fabflix/servlet/Broswer?input=N"/>N</a> 

					</p>
				</div>
			
			</div>

			</div>	
		</div>	
	</div>	



</BODY>
</HTML>
