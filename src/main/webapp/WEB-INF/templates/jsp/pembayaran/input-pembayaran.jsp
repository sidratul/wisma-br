
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="id">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Pembayaran</title>
    </head>
    <body>
        <sf:form class="form-horizontal" method="POST" modelAttribute="pembayaran">
<!--            untuk inap-->
            <div class="control-group">                
                <label class="control-label">Checkin ID</label>
                <div class="controls">
                    <span class="input-large uneditable-input">${pembayaran.oInap.inapId}</span>
                </div>
            </div>
<!--            untuk tamu-->
            <div class="control-group">                
                <label class="control-label">No ID Tamu</label>
                <div class="controls">
                    <span class="input-large uneditable-input">${pembayaran.oInap.oTamu.noId}</span>
                </div>
            </div>
            <div class="control-group">                
                <label class="control-label">Nama Tamu</label>
                <div class="controls">
                    <span class="input-large uneditable-input">${pembayaran.oInap.oTamu.nama}</span>
                </div>
            </div>
            <div class="control-group">                
                <label class="control-label">Kota</label>
                <div class="controls">
                    <span class="input-large uneditable-input">${pembayaran.oInap.oTamu.kota}</span>
                </div>
            </div>
            
<!--            main edit-->            
            <div class="control-group">                
                <label class="control-label">Sudah Terbayar</label>
                <div class="controls">
                    <sf:hidden path="sudahBayar"></sf:hidden>
                    <sf:hidden path="totalBiaya"></sf:hidden>
                    <span class="input-large uneditable-input" id="totalBayar">${pembayaran.sudahBayar}</span>
                    <span class="help-inline">dari total</span>
                    <span class="input-large uneditable-input">${pembayaran.totalBiaya}</span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Jumlah Bayar</label>
                <div class="controls">
                    <input type="number" min="0" max="${pembayaran.totalBiaya-pembayaran.sudahBayar}" name="bayarSisa" placeholder="Jumlah Bayar" required="required"/>
                </div>
            </div>            
            <div class="control-group">
                <div class="controls">          
                  <button type="submit" class="btn">Simpan</button>
                </div>
            </div>
        </sf:form>
        <script language="javascript"> 
            $("[name='bayarSisa']").keyup(function(){
                hitungTerbayar(this);
            });
            
            $("[name='bayarSisa']").click(function(){
                hitungTerbayar(this);
            });
            
            function hitungTerbayar(obIn){
                var total = parseInt($(obIn).val())+ ${pembayaran.sudahBayar};
                $("#sudahBayar").val(total);                
            }
        </script>
    </body>
</html>