<%-- 
    Document   : status-tamu
    Created on : May 4, 2013, 8:18:07 PM
    Author     : Muhammad Sidratul M
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Status Tamu</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${param.hasilHapus==1}">
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    Berhasil Menghapus Tamu
                </div>
            </c:when>
            <c:when test="${param.hasilHapus==0}">
                <div class="alert alert-block">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    Gagal hapus ! Tamu Termasuk Pelanggan , Tamu Tidak Boleh Dihapus
                </div>
            </c:when>
            <c:when test="${param.hasilSimpan=='update'}">
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    Data Tamu Telah di Ubah
                </div>
            </c:when>
            <c:when test="${param.hasilSimpan=='tambah'}">
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    Berhasil Menambahkan Tamu
                </div>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${empty daftarTamu}">
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
                            <th>jenis id</th>
                            <th>no id</th>
                            <th>nama</th>
                            <th>jenis kelamin</th>
                            <th>alamat</th>
                            <th>kota</th>
                            <th>telpon</th>
                            <th colspan="2"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${daftarTamu}" var="t" varStatus="i">
                            <tr>
                                <td>${i.count}</td>
                                <td>${t.jenisId}</td>
                                <td>${t.noId}</td>
                                <td>${t.nama}</td>
                                <td>${t.jenisKelamin}</td>
                                <td>${t.alamat}</td>
                                <td>${t.kota}</td>
                                <td>${t.telpon}</td>
                                <td><a class="btn-small btn-block btn btn-primary" href="input?id=${t.id}"> edit </a></td>
                                <td><a class="btn-small btn-block btn btn-primary" href="hapus?id=${t.id}"> hapus </a></td>
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
