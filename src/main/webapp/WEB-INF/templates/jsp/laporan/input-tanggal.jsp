<%-- 
    Document   : input-laporan
    Created on : May 31, 2013, 9:18:32 AM
    Author     : Muhammad Sidratul M
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Input Tanggal Laporan</title>
    </head>
    <body>
        <form class="form-horizontal text-center" action="tampil-laporan" method="POST">
            <input type="date" name="tglAwal" required="required"/> 
            sampai 
            <input type="date" name="tglAkhir" required="required"/>
            <input type="submit" class="btn" value="laporan"/>
        </form>
    </body>
</html>
