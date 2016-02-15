<%@page import="java.util.ArrayList,
    java.util.List,
    java.util.HashMap,
    java.util.Set,
    java.util.Map"%>

<%@page import="java.sql.*,
 javax.sql.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*,
 java.util.HashSet"
%>

<!DOCTYPE html>
<html>
	<head>
		<title>Shopping Cart</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="/fabflix/assets/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="/fabflix/assets/css/custom.css"/>		
	</head>

	<body>
		
		<nav class="navbar">
			<div class="container">
				<a class="navbar-brand" href="#">Your online store</a>
				<div class="navbar-right">
					<div class="container minicart"></div>
				</div>
			</div>
		</nav>
		
		<div class="container-fluid breadcrumbBox text-center">
			<ol class="breadcrumb">
				<li><a href="#">Review</a></li>
				<li class="active"><a href="#">Order</a></li>
				<li><a href="#">Payment</a></li>
			</ol>
		</div>
		
		<div class="container text-center">

			<div class="col-md-5 col-sm-12">
				<div class="bigcart"></div>
				<h1>Your shopping cart</h1>
				
			</div>
			
			<div class="col-md-7 col-sm-12 text-left">
				<ul>
					<li class="row list-inline columnCaptions">
						<span>QTY</span>
						<span>ITEM</span>
						<span>Price</span>
					</li>
					


					<% ArrayList<String> ShoppingItems = (ArrayList<String>) session.getAttribute("previousItems");


					Class.forName("com.mysql.jdbc.Driver").newInstance();
            		Connection connection =
           			DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "");

           			Map<String, Integer> Itemmap = new HashMap<String, Integer>();

           			for (String i : ShoppingItems)
				        {
				            Integer retrievedValue = Itemmap.get(i);
				            if (null == retrievedValue)
				            {
				                Itemmap.put(i, 1);
				            }
				            else
				            {
				                Itemmap.put(i, retrievedValue + 1);
				            }
				        }

           			// Set<String> item_ids = new HashSet<String>(ShoppingItems);
           			for(String item_id : Itemmap.keySet()){

           			Statement select = connection.createStatement();


           			String sqlTitle = "SELECT title FROM movies WHERE id='"+item_id+"';";

           			String title = null;
	                ResultSet resultTitle = select.executeQuery(sqlTitle);
	                if(resultTitle.next())
	                {
	                    title = resultTitle.getString(1);
	                }
	                resultTitle.close();

					out.write("<li class='row'>");
					out.write("<span class='quantity'>"+Itemmap.get(item_id)+"</span>");
					out.write("<span class='itemName'>"+title+"</span>");
					out.write("<span class='popbtn'><a class='arrow' href = '/fabflix/servlet/Shopping?action=remove&item_id="+item_id+"'></a></span>");
					}
					%>
				</ul>

				<li class="row totals">
					<span class="order"><a href = "/fabflix/Checkout.jsp" class="text-center">ORDER</a></span>
				</li>			

		</div>

		<!-- The popover content -->

		<div id="popover" style="display: none">
			<a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
			<a href="#"><span class="glyphicon glyphicon-remove"></span></a>
		</div>

		
		
		<!-- JavaScript includes -->

		<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/customjs.js"></script>

	</body>
</html>