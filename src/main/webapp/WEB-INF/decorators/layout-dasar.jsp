<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="id_ID"/>
<!DOCTYPE html>
<html lang="id">
    <head>
        <meta charset="utf-8">
        <title><decorator:title></decorator:title></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        
        <script src="<%= request.getContextPath() %>/js/jquery-1.9.1.js"></script>
        <link href="<%= request.getContextPath() %>/css/bootstrap.css" rel="stylesheet" media="screen">
        <link href="<%= request.getContextPath() %>/css/bootstrap-responsive.css" rel="stylesheet">
        <style type="text/css">		
            body {
                padding-top: 20px;
                padding-bottom: 60px;
            }

            /* Custom container */
            .container {
                margin: 0 auto;
                max-width: 1000px;
            }
            .container > hr {
                margin: 60px 0;
            }


            /* Customize the navbar links to be fill the entire space of the .navbar */

            .navbar .navbar-inner {
                padding: 0;
            }

            .navbar .nav {
                margin: 0;
                display: table;
                width: 100%;
            }

            .navbar .nav > li{
                display: table-cell;
                width: 1%;
                float: none;
            }

            .navbar .nav > li > a {
                font-weight: bold;
                text-align: center;
                border-left: 1px solid rgba(255,255,255,.75);
                border-right: 1px solid rgba(0,0,0,.1);
            }

            .navbar .nav li:first-child a {
                border-left: 0;
                border-radius: 3px 0 0 3px;
            }
            .navbar .nav li:last-child a {
                border-right: 0;
                border-radius: 0 3px 3px 0;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="masthead">
                <h3 class="muted">Wisma Bintang Rejeki</h3>
                    <div class="navbar">
                        <div class="navbar-inner">
                            <div class="container">
                                <ul class="nav">
                                    <li class="dropdown">
                                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><b class="caret text-left" ></b> Status</a>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">						
                                            <li><a href="<%= request.getContextPath() %>/aplikasi/kamar/status">Status kamar</a></li>
                                            <li><a href="<%= request.getContextPath() %>/aplikasi/tamu/status">Status Tamu</a></li>
                                            <li><a href="<%= request.getContextPath() %>/aplikasi/inap/daftar-inap">Daftar Inap</a></li>
                                            <li><a href="<%= request.getContextPath() %>/aplikasi/inap/daftar-checkin">Daftar Checkin</a></li>
                                            <li><a href="<%= request.getContextPath() %>/aplikasi/reservasi/status">Status Reservasi</a></li>
					</ul>
                                    </li>
                                    <li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#"><b class="caret text-left" ></b> Input</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                            <li><a href="<%= request.getContextPath() %>/aplikasi/tamu/input">Input Tamu</a></li>
                                            <li><a href="<%= request.getContextPath() %>/aplikasi/inap/input">Input Checkin</a></li>
                                            <li><a href="<%= request.getContextPath() %>/aplikasi/reservasi/input">Input Reservasi</a></li>
					</ul>
                                    </li>
                                    <li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#"><b class="caret text-left" ></b> Pembayaran</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                            <li><a href="<%= request.getContextPath() %>/aplikasi/pembayaran/belum-lunas">Belum Lunas</a></li>
                                            <li><a href="<%= request.getContextPath() %>/aplikasi/pembayaran/lunas">Lunas</a></li>
					</ul>
                                    </li>
                                    <li><a href="<%= request.getContextPath() %>/aplikasi/laporan/input-tanggal">Laporan</a></li>
<!--                                    <li><a href="<%= request.getContextPath() %>/aplikasi/user/profil">User Control</a></li>                -->
                                    <li><a href="<c:url value="/j_spring_security_logout" />">Keluar</a></li>                
                                </ul>
                            </div>
                        </div>
                    </div><!-- /.navbar -->
                </div>
            <div>
                <h4 class="text-center well well-small muted"><decorator:title></decorator:title></h4>                
                <decorator:body></decorator:body>
            </div>
            <hr>
            <div class="footer">
                <p>&copy; Wisma Bintang Rezeki 2013</p>
            </div>
        </div> 
        <!-- script -->
        <script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>        
    </body>
</html>