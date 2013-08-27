
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daftar Checkin</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${not empty param.hasilCheckin}">
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Berhasil Checkin!</strong>
                </div>
            </c:when>
            <c:when test="${param.hasilHapus==1}">
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Berhasil Menghapus Checkin !</strong>
                </div>
            </c:when>
            <c:when test="${param.hasilHapus==0}">
                <div class="alert alert-block">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Hapus Gagal ! Checkin sudah terproses</strong>
                </div>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${empty daftarCheckin}">
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
                            <th>Inap ID</th>
                            <th>Nama Tamu</th>
                            <th>Kota</th>                            
                            <th>Waktu Checkin</th>
                            <th colspan="3"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${daftarCheckin}" var="di" varStatus="i">
                            <tr>
                                <td>${i.count}</td>
                                <td>${di.inapId}</td>
                                <td>${di.oTamu.nama}</td>
                                <td>${di.oTamu.kota}</td>
                                <td>
                                    <fmt:formatDate value="${di.waktuCheckin}" pattern="EEE, dd MMM yyyy" ></fmt:formatDate>
                                </td>
                                <td><a class="btn-small btn-block btn btn-primary" href="../status-inap/daftar-status?idInap=${di.id}">Status</a></td>
                                <td><a class="btn-small btn-block btn btn-primary" href="hapus?id=${di.id}">Hapus</a></td>
                                <td><a class="btn-small btn-block btn btn-primary" href="checkout?id=${di.id}">Checkout</a></td>
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
