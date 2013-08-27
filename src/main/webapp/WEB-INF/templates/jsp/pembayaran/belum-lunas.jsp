
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pembayaran Belum Lunas</title>
    </head>
    <body>
        <c:if test="${not empty param.hasilPembayaran}">
            <div class="alert alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Pembayaran Berhasil ! Sisa Tagihan adalah Rp <c:out value="${param.sisaPembayaran}"></c:out></strong>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${empty daftarPembayaran}">
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
                            <th>Checkin ID</th>
                            <th>No ID Tamu</th>
                            <th>Nama</th>
                            <th>Kota</th>
                            <th>Total Biaya</th>
                            <th>Bayar</th>                            
                            <th>Sisa</th>                            
                            <th>Tanggal Bayar</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${daftarPembayaran}" var="dp" varStatus="i">
                            <tr>
                                <td>${i.count}</td>
                                <td>${dp.oInap.inapId}</td>
                                <td>${dp.oInap.oTamu.noId}</td>
                                <td>${dp.oInap.oTamu.nama}</td>
                                <td>${dp.oInap.oTamu.kota}</td>
                                <td>
                                    <fmt:formatNumber value="${dp.totalBiaya}" type="currency"/>
                                </td>
                                <td>
                                    <fmt:formatNumber value="${dp.sudahBayar}" type="currency"/>
                                </td>
                                <td>
                                    <fmt:formatNumber value="${dp.totalBiaya - dp.sudahBayar}" type="currency"/>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty dp.tglBayar}">-</c:when>
                                        <c:otherwise>
                                            <fmt:formatDate pattern="EEE,dd MMM yyyy" value="${dp.tglBayar}"></fmt:formatDate>
                                        </c:otherwise>
                                    </c:choose> 
                                </td>
                                <td><a class="btn-small btn-block btn btn-primary" href="input-pembayaran?id=${dp.id}">Bayar</a></td>
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
