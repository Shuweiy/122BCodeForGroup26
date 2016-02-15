<!DOCTYPE html>

<head>
<style>
#header {
    background-color:black;
    color:white;
    text-align:center;
    padding:5px;
}



</style>
</head>

<div id="header">
<h1> Enter your information </h1>
</div>
 

<center>
<form action="/TomcatForm/servlet/customer" method = "POST">
  First Name: <input type="text" name="firstname"><br>
  Last Name: <input type="text" name="lasrname"><br>
  Card Number: <input type="number" name="cardnumber"><br>
  Expiration Date: <input type="date" name="expirationdate"><br>
  <button type="submit" value="Submit">Submit</button>
</form>
</center>
