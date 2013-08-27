package com.p4.wismabr.reservasi;

import com.p4.wismabr.kamar.Kamar;
import com.p4.wismabr.tamu.Tamu;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class Reservasi {
    private Integer id;
    
    @NotNull
    @NotEmpty
    private String reservasiId;
    private Date tglReservasi;
    private Date reservasiTgl;
    private String statusReservasi;
    private Tamu oTamu;
    private Kamar oKamar;

    public Date getReservasiTgl() {
        return reservasiTgl;
    }

    public void setReservasiTgl(Date reservasiTgl) {
        this.reservasiTgl = reservasiTgl;
    }

    public Date getTglReservasi() {
        return tglReservasi;
    }

    public void setTglReservasi(Date tglReservasi) {
        this.tglReservasi = tglReservasi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReservasiId() {
        return reservasiId;
    }

    public void setReservasiId(String reservasiId) {
        this.reservasiId = reservasiId;
    }

    public Kamar getoKamar() {
        return oKamar;
    }

    public void setoKamar(Kamar oKamar) {
        this.oKamar = oKamar;
    }

    public Tamu getoTamu() {
        return oTamu;
    }

    public void setoTamu(Tamu oTamu) {
        this.oTamu = oTamu;
    }

    public String getStatusReservasi() {
        return statusReservasi;
    }

    public void setStatusReservasi(String statusReservasi) {
        this.statusReservasi = statusReservasi;
    }
}
