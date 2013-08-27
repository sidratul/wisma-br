<%-- 
    Document   : status
    Created on : May 22, 2013, 9:25:56 PM
    Author     : Muhammad Sidratul M
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Status Reservasi</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${param.hasilReservasi ==1}">
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Reservasi Berhasil !</strong>
                </div>
            </c:when>
            <c:when test="${param.hasilHapus==1}">
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Reservasi Telah Dihapus !</strong>
                </div>
            </c:when>
            <c:when test="${param.hasilBatal==1}">
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Reservasi Telah Dibatalkan !</strong>
                </div>
            </c:when>
        </c:choose>
        
        <c:choose>
            <c:when test="${empty daftarReservasi}">
                <h3>Tidak Ada Data</h3>
            </c:when>
            <c:otherwise>
<!--                pagination -->
                <c:if test="${totalHalaman > 1}">
                    <div class="pagination pull-right">
                        <ul>                            
                            <li class="page1"><a href="?page=1">awal</a></li>
                            <li class="prev"><a href="?page=${halamanAktif - 1}">&laquo;</a></li>
                            <c:forEach begin="${mulaiPage}" end="${akhirPage}" var="i">
                                <li class="page${i}"><a href="?page=${i}">${i}</a></li>
                            </c:forEach>
                                <li class="next"><a href="?page=${halamanAktif + 1}">&raquo;</a></li>
                            <li class="page${totalHalaman}"><a href="?page=${totalHalaman}">akhir</a></li>
                        </ul>
                    </div>
                </c:if>
<!--                akhir paginating-->
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Reservasi ID</th>
                            <th>Nama Tamu</th>
                            <th>Kota</th>
                            <th>Kamar</th>                            
                            <th>Waktu Reservasi</th>
                            <th>Reservasi Tanggal</th>
                            <th colspan="2"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${daftarReservasi}" var="dr" varStatus="i">
                            <tr>
                                <td>${i.count}</td>
                                <td>${dr.reservasiId}</td>
                                <td>${dr.oTamu.nama}</td>
                                <td>${dr.oTamu.kota}</td>
                                <td>${dr.oKamar.noKamar}</td>
                                <td><fmt:formatDate value="${dr.tglReservasi}" pattern="EEE, dd MMM yyyy"></fmt:formatDate></td>
                                <td><fmt:formatDate value="${dr.reservasiTgl}" pattern="EEE, dd MMM yyyy"></fmt:formatDate></td>
                                <td><a class="btn-small btn-block btn btn-primary" href="batal?id=${dr.id}">Batal</a></td>
                                <td><a class="btn-small btn-block btn btn-primary" href="hapus?id=${dr.id}">Hapus</a></td>
                                <td><a class="btn-small btn-block btn btn-primary" href="../inap/input?idReservasi=${dr.id}">Checkin</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
<!--                pagination -->
                <c:if test="${totalHalaman > 1}">
                    <div class="pagination pull-right">
                        <ul>                            
                            <li class="page1"><a href="?page=1">awal</a></li>
                            <li class="prev"><a href="?page=${halamanAktif - 1}">&laquo;</a></li>
                            <c:forEach begin="${mulaiPage}" end="${akhirPage}" var="i">
                                <li class="page${i}"><a href="?page=${i}">${i}</a></li>
                            </c:forEach>
                                <li class="next"><a href="?page=${halamanAktif + 1}">&raquo;</a></li>
                            <li class="page${totalHalaman}"><a href="?page=${totalHalaman}">akhir</a></li>
                        </ul>
                    </div>
                </c:if>
<!--                akhir paginating-->
            </c:otherwise>
        </c:choose>
        <script>
            $(document).ready(function(){                
                $(".page${halamanAktif}").addClass("active");
                if(${halamanAktif == 1}){
                    $(".prev").addClass("disabled");
                    $(".prev a").attr("href","");
                }else if(".page${halamanAktif == totalHalaman}"){
                    $(".next").addClass("disabled");
                    $(".next a").attr("href","");
                }
            });
        </script>
    </body>
</html>
