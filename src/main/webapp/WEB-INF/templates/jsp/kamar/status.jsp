<%-- 
    Document   : status
    Created on : May 11, 2013, 8:14:45 PM
    Author     : Muhammad Sidratul M
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Status Kamar</title>
    </head>
    <body>
        <table class="table table-hover table-bordered">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Nomor Kamar </th>
                    <th>Ac dan Tv</th>
                    <th>Batas Tamu</th>
                    <th>Terisi</th>
                    <th>Terpesan</th>                    
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${daftarKamar}" var="k" varStatus="i">
                    <tr>
                        <td>${i.count}</td>
                        <td>${k.noKamar}</td>
                        <td>
                            <c:choose>
                                <c:when test="${k.acTv==0 }"><b class="icon-remove" title="tidak sterfasilitasi"></b></c:when>
                                <c:otherwise><b class="icon-ok" title="terfasilitasi"></b></c:otherwise>
                            </c:choose>
                        </td>
                        <td>${k.maxOrang} orang</td>
                        <td>
                            <c:choose>
                                <c:when test="${k.statIn==0 }"><b class="icon-remove" title="tidak tersisi"></b></c:when>
                                <c:otherwise><b class="icon-ok" title="terisi"></b></c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${k.statReservasi==0 }" ><b class="icon-remove" title="tidak terpesan"></b></c:when>
                                <c:otherwise><b class="icon-ok" title="terpesan"></b></c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
