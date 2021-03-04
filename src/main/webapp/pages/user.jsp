<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form action="/addUser">
    <input type="text" name="name" placeholder="name"><br>
    <input type="text" name="surname" placeholder="surname"><br>
    <input value="addUser" type="submit">
</form><br>
<p>Find by ID</p>
<form action="/showUserById">
    <input type="text" name="id" placeholder="id"><br>
    <input value="findUserById" type="submit">
</form><br>
<p>Find greater than ID</p>
<form action="/showUserGreaterThanId">
    <input type="text" name="id" placeholder="id"><br>
    <input value="findUserGreaterThanId" type="submit">
</form><br>
<p>Find by name</p>
<form action="/showUserByName">
    <input type="text" name="name" placeholder="name"><br>
    <input value="findByName" type="submit">
</form><br>
<p>Find by name sorted</p>
<form action="/showUserByNameSorted">
    <input type="text" name="name" placeholder="name"><br>
    <input value="findByNameSortedBySurname" type="submit">
</form>
</body>
</html>
