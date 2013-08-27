<%-- 
    Document   : index
    Created on : May 2, 2013, 2:48:19 PM
    Author     : Muhammad Sidratul M
--%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wisma Bintang Rejeki</title>
        <script src="<%= request.getContextPath() %>/js/jquery-1.9.1.js"></script>
        <link href="<%= request.getContextPath() %>/css/bootstrap.css" rel="stylesheet" media="screen">
        <link href="<%= request.getContextPath() %>/css/bootstrap-responsive.css" rel="stylesheet">
        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }
            
            .form-signin {
                max-width: 300px;
                padding: 19px 29px 29px;
                margin: 0 auto 20px;
                background-color: #fff;
                border: 1px solid #e5e5e5;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                    border-radius: 5px;
                -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                    box-shadow: 0 1px 2px rgba(0,0,0,.05);
            }            
            
            .form-signin input[type="text"],
            .form-signin input[type="password"] {                
                height: auto;
                margin-bottom: 15px;
                padding: 7px 9px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <form class="form-signin" action="<c:url value='/j_spring_security_check'/>" method='POST'>
                <h3 class="form-signin-heading">Login</h3>
                <c:if test="${not empty error}">
                    <div class="alert">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>Login Gagal!</strong><br/> username dan password tidak cocok.
                    </div>
                </c:if>
                <input type="text" name="j_username" class="input-block-level" placeholder="Username">
                <input type="password" name="j_password" class="input-block-level" placeholder="Password">                
                <button class="btn btn-large btn btn-primary" type="submit">Login</button>
            </form>
        </div> <!-- /container -->
        <script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>
    </body>
</html>
