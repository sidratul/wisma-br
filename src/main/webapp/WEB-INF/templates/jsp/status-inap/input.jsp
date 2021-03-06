
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="id">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Input Status Inap</title>
    </head>
    <body>
        <sf:form class="form-horizontal" action="proses-input" method="POST" modelAttribute="statusInap">            
            <sf:hidden path="id"></sf:hidden>
            <sf:hidden path="oInap.id"></sf:hidden>            
<!--            untuk inap-->
            <div class="control-group">                
                <label class="control-label">Checkin ID</label>
                <div class="controls">
                    <span class="input-large uneditable-input">${statusInap.oInap.inapId}</span>
                </div>
            </div>
<!--            untuk tamu-->
            <div class="control-group">                
                <label class="control-label">Tamu ID</label>
                <div class="controls">
                    <span class="input-large uneditable-input">${statusInap.oInap.oTamu.noId}</span>
                </div>
            </div>
            <div class="control-group">                
                <label class="control-label">Nama Tamu</label>
                <div class="controls">
                    <span class="input-large uneditable-input">${statusInap.oInap.oTamu.nama}</span>
                </div>
            </div>
            <div class="control-group">                
                <label class="control-label">Kota</label>
                <div class="controls">
                    <span class="input-large uneditable-input">${statusInap.oInap.oTamu.kota}</span>
                </div>
            </div>
            
<!--            main edit-->
            <div class="control-group">                
                <label class="control-label">Kamar</label>
                <div class="controls">
                    <input type="hidden" name="idKamarLama" value="${statusInap.oKamar.id}">
                    <sf:select path="oKamar.id" required="required">
                        <sf:option value="">Kamar</sf:option>
                        <sf:option value="${statusInap.oKamar.id}">${statusInap.oKamar.noKamar}</sf:option>
                        <c:forEach items="${daftarKamar}" var="k" varStatus="i">                            
                            <sf:option value="${k.id}">${k.noKamar}</sf:option>
                        </c:forEach>
                    </sf:select>
                        <span class="help-inline"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Jumlah Orang</label>
                <div class="controls">
                    <sf:input path="jumOrang" placeholder="Jumlah Orang" required="required"/>
                    <span class="help-inline"></span>
                </div>
            </div>            
            <div class="control-group">                
                <label class="control-label">Biaya Kamar/Malam</label>
                <div class="controls">
                    <span class="input-large uneditable-input" id="biayaKamar">Biaya Kamar</span>
                    <sf:hidden path="biaya" value="" />
                </div>
            </div>
            <div class="control-group">
                <div class="controls">          
                  <button type="submit" class="btn">Simpan</button>
                </div>
            </div>
        </sf:form>
        <script language="javascript">
            $("[name='oKamar.id']").change(function(){            
                prosesBiaya();
            });

            $("[name='jumOrang']").keyup(function(){
                prosesBiaya();
            });

            function prosesBiaya(){
                var kelasKamar;
                var jumlahOrang;

                <c:forEach items="${daftarKamar}" var="k">
                    if($("[name='oKamar.id']").val()=="${k.id}"){
                        kelasKamar = "${k.kelasKamar}";
                    }
                    
                    if($("[name='oKamar.id']").val()=="${statusInap.oKamar.id}"){
                        kelasKamar = "${statusInap.oKamar.kelasKamar}";
                    }
                </c:forEach>

                jumlahOrang = parseInt($("[name='jumOrang']").val());
                if(isNaN(jumlahOrang)){
                    jumlahOrang=0;
                }

                if(kelasKamar=="a"){                    
                    biayaKamar=300000;
                }else if(kelasKamar=="b"){
                    biayaKamar=200000;
                }else if(kelasKamar=="c"){
                    if(jumlahOrang==1){
                        biayaKamar=90000;
                    }else if(jumlahOrang==2){
                        biayaKamar=120000;
                    }else if(jumlahOrang==3){
                        biayaKamar=150000;
                    }else{
                        biayaKamar="Tidak Bisa";

                    }
                }else if(kelasKamar=="d"){
                    if(jumlahOrang==1){
                        biayaKamar=60000;
                    }else if(jumlahOrang==2){
                        biayaKamar=90000;
                    }else if(jumlahOrang==3){
                        biayaKamar=105000;
                    }else{
                        biayaKamar="Tidak Bisa";
                    }
                }else{
                    biayaKamar="Tidak Bisa";
                }

                $("#biayaKamar").html(biayaKamar);
                $("[name='biaya']").val(biayaKamar);
            }
        </script>
    </body>
</html>