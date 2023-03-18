<%-- 
    Document   : users
    Created on : Mar 10, 2023, 7:39:23 PM
    Author     : khanhhoanguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        ${message}
        <h1>Manage Users</h1>
        
        <c:if test="${users.isEmpty()}">
            <p><strong>No users found. Please add a user.</strong></p>
        </c:if>
        
        <c:if test="${users.size() > 0}">
            <table border="1">
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Role</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.email}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.role.roleName}</td>
                        <td><a href="User?action=edit&amp;key=${user.email}">Edit</a></td>
                        <td><a href="User?action=delete&amp;key=${user.email}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
            
        <c:choose>
            <c:when test="${selecteduser eq null || users.isEmpty()}">
                <h2>Add User</h2>
                <form action="" method="POST">
                    Email: <input type="email" name="email" value="${email}"><br>
                    First name: <input type="text" name="firstname" value="${firstname}"><br>
                    Last name: <input type="text" name="lastname" value="${lastname}" ><br>
                    Password: <input type="password" name="password"><br>
                    Role:  
                    <select name="role">
                        <option value="2">regular user</option>
                        <option value="1">system admin</option>
                    </select><br>


                    <input type="submit" name="submit" value="Add user">
                </form>
                ${formmessage}
            </c:when>    

            <c:otherwise>
                <h2>Edit User</h2>
                <form action="User" method="POST">
                    Email: ${selecteduser.email}<br>
                    <input type="hidden" name="email" value="${selecteduser.email}">
                    First name: 
                    <input type="text" name="firstname" value="${selecteduser.firstName}"><br>
                    Last name: <input type="text" name="lastname" value="${selecteduser.lastName}"><br>
                    Password: <input type="password" name="password"><br>
                    Role:  
                    <select name="role">
                        <option value="2">regular user</option>
                        <option value="1" ${selecteduser.role.roleId == '1' ? 'selected' : ''}>
                            system admin
                        </option>
                    </select><br>
                    <input type="hidden" name="role" value="${selecteduser.role.roleId}">
                    <input type="submit" name="submit" value="Update">
                    <input type="submit" name ="submit" value="Cancel">
                    ${formmessage}
                </form>         


            </c:otherwise>  
        </c:choose>
    </body>
</html>
