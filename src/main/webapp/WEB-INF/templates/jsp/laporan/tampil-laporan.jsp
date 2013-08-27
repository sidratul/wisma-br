
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Laporan Inap</title>
    </head>
    <body>
        <div class="row">            
            <div class="span4">
                Laporan dari Tanggal
                <strong>
                    <fmt:parseDate value="${tglAwal}" pattern="yyyy-mm-dd" var="tglAwalParse" ></fmt:parseDate>
                    <fmt:formatDate value="${tglAwalParse}" pattern="dd MMM yyyy " ></fmt:formatDate>
                </strong>
                Sampai
                <strong>
                    <fmt:parseDate value="${tglAkhir}" pattern="yyyy-mm-dd" var="tglAkhirParse" ></fmt:parseDate>
                    <fmt:formatDate value="${tglAkhirParse}" pattern="dd MMM yyyy " ></fmt:formatDate>
                </strong>
            </div>
            <div class="pull-right">
                Cetak :
                <a href="../file-laporan/pdf?tglAwal=${tglAwal}&tglAkhir=${tglAkhir}">PDF</a>
                -
                <a href="../file-laporan/xls?tglAwal=${tglAwal}&tglAkhir=${tglAkhir}">XLS</a>
            </div>
        </div>      
        <c:choose>
            <c:when test="${empty laporanPembayaran}">
                <h3>Tidak Ada Data</h3>
            </c:when>
            <c:otherwise>
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Inap ID</th>
                            <th>No ID Tamu</th>
                            <th>Nama</th>
                            <th>Kota</th>
                            <th>Tgl Checkin</th>
                            <th>Tgl Checkout</th>                            
                            <th>Biaya</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${laporanPembayaran}" var="lp" varStatus="i">
                            <tr>
                                <td>${i.count}</td>
                                <td>${lp.oInap.inapId}</td>
                                <td>${lp.oInap.oTamu.noId}</td>
                                <td>${lp.oInap.oTamu.nama}</td>
                                <td>${lp.oInap.oTamu.kota}</td>
                                <td>
                                    <fmt:formatDate value="${lp.oInap.waktuCheckin}" pattern="dd MMM yyyy " ></fmt:formatDate>
                                </td>
                                <td>
                                    <fmt:formatDate value="${lp.oInap.waktuCheckout}" pattern="dd MMM yyyy " ></fmt:formatDate>
                                </td>
                                <td>
                                    <fmt:formatNumber value="${lp.totalBiaya}" type="currency"/>                                    
                                    <c:set value="${totalBiayaLaporanPerPage + lp.totalBiaya}" var="totalBiayaLaporanPerPage" ></c:set>
                                </td>   
                                <td>
                                    <c:choose>
                                        <c:when test="${lp.statusBayar==0}">
                                            Belum Lunas
                                        </c:when>
                                        <c:otherwise>
                                            Lunas
                                        </c:otherwise>
                                    </c:choose>
                                </td>                                
                            </tr>
                        </c:forEach>
                            <tr class="breadcrumb">
                                <td colspan="7"><strong class="pull-right">Total</strong></td>
                                <td colspan="2">
                                    <fmt:formatNumber value="${totalBiayaLaporanPerPage}" type="currency"/>
                                </td>
                            </tr>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>
