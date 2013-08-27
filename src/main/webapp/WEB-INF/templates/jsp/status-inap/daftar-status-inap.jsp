
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daftar Status Inap</title>
    </head>
    <body>        
        <div class="row">            
            <div class="span5">
                <div class="row-fluid">
                    <div class="span3"><strong>Checkin ID</strong></div>
                    <div class="span1">:</div>
                    <div class="span8">${daftarStatusInap[0].oInap.inapId}</div>
                </div>                
                <div class="row-fluid">
                    <div class="span3"><strong>No ID Tamu</strong></div>
                    <div class="span1">:</div>
                    <div class="span8">${daftarStatusInap[0].oInap.oTamu.noId}</div>
                </div>
                <div class="row-fluid">
                    <div class="span3"><strong>Nama</strong></div>
                    <div class="span1">:</div>
                    <div class="span8">${daftarStatusInap[0].oInap.oTamu.nama}</div>
                </div>
                <div class="row-fluid">
                    <div class="span3"><strong>Kota</strong></div>
                    <div class="span1">:</div>
                    <div class="span8">${daftarStatusInap[0].oInap.oTamu.kota}</div>
                </div>
                <div class="row-fluid">
                    <div class="span3"><strong>Status</strong></div>
                    <div class="span1">:</div>
                    <div class="span8">
                        <c:choose>
                            <c:when test="${pembayaran==null}">
                                Belum Checkout ( <a href="../inap/checkout?id=${daftarStatusInap[0].oInap.id}">Checkout</a> )
                            </c:when>
                            <c:when test="${pembayaran.statusBayar==0}">
                                Pembayaran Belum Lunas ( <a href="../pembayaran/input-pembayaran?id=${pembayaran.id}">Bayar</a> )
                            </c:when>
                            <c:otherwise>
                                Pembayaran Sudah Lunas
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        <c:set value="<%=new java.util.Date()%>" var="skrg"></c:set>
        <table class="table table-hover table-bordered">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Kamar</th>
                    <th>Jumlah Orang</th>
                    <th>Tanggal</th>                            
                    <th>Biaya</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${daftarStatusInap}" var="dsi" varStatus="i">
                    <tr>
                        <td>${i.count}</td>
                        <td>${dsi.oKamar.noKamar}</td>
                        <td>${dsi.jumOrang}</td>
                        <td>
                            <fmt:formatDate var="waktuStatus" value="${dsi.waktuStatus}" pattern="EEE, dd MMM yyyy" ></fmt:formatDate>
                            <c:out value="${waktuStatus}"></c:out>
                        </td>
                        <td>
                            <fmt:formatNumber value="${dsi.biaya}" type="currency"/>    
                            <c:set value="${totalBiayaInap + dsi.biaya}" var="totalBiayaInap" ></c:set>
                        </td>
                    </tr>
                </c:forEach>
                <tr class="breadcrumb">
                    <td colspan="4"><strong class="pull-right">Jumlah</strong></td>
                    <td colspan="1">
                        <fmt:formatNumber value="${totalBiayaInap}" type="currency"/>
                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
