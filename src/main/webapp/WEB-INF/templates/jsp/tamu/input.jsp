
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html>
<html lang="id">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Input Tamu</title>
    </head>
    <body>
        <sf:form class="form-horizontal" method="POST" action="input" modelAttribute="tamu">
            <sf:hidden path="id"/>
            <div class="control-group">                
                <label class="control-label">Jenis Id</label>
                <div class="controls">
                    <sf:select path="jenisId" >
                        <sf:option value="">Jenis ID</sf:option>
                        <sf:option value="ktp">KTP</sf:option>
                        <sf:option value="sim">SIM</sf:option>
                    </sf:select>
                    <span class="help-inline"><sf:errors path="jenisId"/></span>    
                </div>
            </div>
            <div class="control-group">                
                <label class="control-label">Nomor ID</label>
                <div class="controls">
                    <sf:input path="noId" placeholder="Nomor ID"/>
                    <span class="help-inline"><sf:errors path="noId"/></span>
                </div>
            </div>
            <div class="control-group">                
                <label class="control-label">Nama</label>
                <div class="controls">
                    <sf:input path="nama" placeholder="Nama"/>
                    <span class="help-inline"><sf:errors path="nama"/></span>
                </div>
            </div>
            <div class="control-group">                
                <label class="control-label">Jenis Kelamin</label>
                <div class="controls">
                    <sf:select path="jenisKelamin" >
                        <sf:option value="">Jenis Kelamin</sf:option>
                        <sf:option value="laki-laki">Laki-laki</sf:option>
                        <sf:option value="perempuan">Perempuan</sf:option>
                    </sf:select>
                    <span class="help-inline"><sf:errors path="jenisKelamin"/></span>
                </div>
            </div>
            <div class="control-group">                
                <label class="control-label">Alamat</label>
                <div class="controls">
                    <sf:textarea rows="3" path="alamat" placeholder="Alamat"></sf:textarea>
                    <span class="help-inline"><sf:errors path="alamat"/></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Kota</label>
                <div class="controls">
                    <sf:input path="kota" placeholder="Kota"/>
                    <span class="help-inline"><sf:errors path="kota"/></span>
                </div>
            </div>
            <div class="control-group">                
                <label class="control-label">Telpon</label>
                <div class="controls">
                    <sf:input path="telpon" placeholder="Telpon"/>
                    <span class="help-inline"><sf:errors path="telpon"/></span>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">          
                  <button type="submit" class="btn">Simpan</button>
                </div>
            </div>
        </sf:form>
    </body>
</html>