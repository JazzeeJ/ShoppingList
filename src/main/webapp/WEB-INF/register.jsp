<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        
        <!--Sends to ShoppingList in doPost method in servlet-->
        <form action="ShoppingList" method="post">
            <label for="name">Username: </label>
            <input type="text" name="name" id="name">
            <button type="submit">Register name</button>
        </form>
        
    </body>
</html>
