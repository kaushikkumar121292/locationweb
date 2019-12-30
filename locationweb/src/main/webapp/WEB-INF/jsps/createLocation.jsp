<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Loaction</title>
</head>
<body>

<form action="saveLoc" method="post">
<pre>
Id: <input type="text" name="id"/>
Code: <input type="text" name="code"/>
Name: <input type="text" name="name"/>
Type Urban: <input type="radio" name="type" value="URBAN"/>
     Rural: <input type="radio" name="type" value="RURAL"/>
<input type="submit" value="save"/>
</pre>
</form>
${msg} 

<a href="displayLocations">view all</a>

</body>
</html>