
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="id">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Input Checkin</title>
    </head>
    <body>        
        <sf:form class="form-horizontal" method="POST" action="input" modelAttribute="statusInap" >
            <div class="control-group">
                <label class="control-label">Checkin ID</label>
                <div class="controls">
                    <span class="input-large uneditable-input">${statusInap.oInap.inapId}</span>
                    <sf:hidden path="oInap.inapId"/>
                    <input type="hidden" name="idReservasi" value="${idReservasi}"/>
                </div>
            </div>
            <div class="control-group">                
                <label class="control-label">Nomor ID Tamu</label>
                <div class="controls">
                    <sf:input path="oInap.oTamu.noId" list="list-id-tamu" placeholder="Nomor ID Tamu" required="required"/>
                    <sf:hidden path="oInap.oTamu.id" />
                    <datalist id="list-id-tamu">
                        <c:forEach items="${daftarTamu}" var="t" varStatus="i">
                            <option label="${t.nama} dari ${t.kota}" value="${t.noId}"></option>                            
                        </c:forEach>
                    </datalist>
                    <span class="help-inline"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Kota</label>
                <div class="controls">
                    <sf:select path="oInap.oTamu.kota" >
                        <sf:option value="">Kota</sf:option>
                        <c:forEach items="${daftarKota}" var="dk" varStatus="i">                            
                            <sf:option value="${dk}">${dk}</sf:option>
                        </c:forEach>
                    </sf:select>                    
                </div>
            </div>
            <div class="control-group">                
                <label class="control-label">Nama Tamu</label>
                <div class="controls">
                    <sf:select path="oInap.oTamu.nama">
                        <sf:option value="">Nama Tamu</sf:option>
                        <c:forEach items="${daftarTamu}" var="t" varStatus="i">
                            <sf:option value="${t.nama}">${t.nama}</sf:option>
                        </c:forEach>
                    </sf:select>
                </div>
            </div>
            <div class="control-group">                
                <label class="control-label" >Kamar</label>
                <div class="controls">
                    <sf:select path="oKamar.id" required="required">
                        <sf:option value="">Kamar</sf:option>
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
            $("[name='oInap.oTamu.kota']").change(function(){
                $("[name='oInap.oTamu.nama'] option:gt(0)").remove();
                <c:forEach items="${daftarTamu}" var="t">
                    if($(this).val()=="${t.kota}"){
                        $("[name='oInap.oTamu.nama']").append("<option value='${t.nama}'>${t.nama}</option>");
                    }
                </c:forEach>
            });
            
            $("[name='oInap.oTamu.nama']").change(function(){
                <c:forEach items="${daftarTamu}" var="t" varStatus="i">                    
                    if($(this).val()=="${t.nama}"){
                        $("[name='oInap.oTamu.noId']").val("${t.noId}");
                        $("[name='oInap.oTamu.id']").val("${t.id}");
                    }
                </c:forEach>
            });
            
            $("[name='oInap.oTamu.noId']").keyup(function(){               
                idTamuByInput($(this).val());
            });
            
            $("[name='oInap.oTamu.noId']").focusout(function(){
                idTamuByInput($(this).val());
            });
            
            function idTamuByInput(noId){
                switch(noId){
                    <c:forEach items="${daftarTamu}" var="t">
                        case "${t.noId}":
                            $("[name='oInap.oTamu.id']").val(${t.id});
                        break;
                    </c:forEach>
                        default:
                            $("[name='oInap.oTamu.id']").val("");
                }
            }
            //akhir dari pengaturan id tamu
            
            //pengaturan biaya perkamar
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
