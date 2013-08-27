package com.p4.wismabr.tamu;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
public class Tamu {
    private Integer id;
    
    @NotNull
    @NotEmpty(message="Nomor ID harus di isi")
    @Size(min=10,max=30,message="Nomor ID harus lebih besar dari pada 9 dan lebih kecil dari 31 karakter")    
    private String noId;
    
    @NotNull
    @NotEmpty(message="Jenis ID belum di pilih")
    private String jenisId;
    
    @NotNull
    @NotEmpty(message="Nama harus di isi")
    @Size(min=3,max=255, message="Nama harus lebih besar 2 dan maksimal 256 karakter")
    private String nama;
    
    @NotNull
    @NotEmpty(message="Jenis Kelamin belum di pilih")
    private String jenisKelamin;
    
    @NotNull
    @NotEmpty(message="Kota harus diisi")
    private String kota;
    
    @NotEmpty(message="Alamat harus di isi")
    private String alamat;
        
    @NotEmpty(message="Telpon harus di isi")
    private String telpon;

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJenisId() {
        return jenisId;
    }

    public void setJenisId(String jenisId) {
        this.jenisId = jenisId;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoId() {
        return noId;
    }

    public void setNoId(String noId) {
        this.noId = noId;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }
    
}
