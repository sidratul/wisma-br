package com.p4.wismabr.statusinap;

import com.p4.wismabr.inap.Inap;
import com.p4.wismabr.kamar.Kamar;
import java.math.BigDecimal;
import java.util.Date;

public class StatusInap {
    private Integer id;
    private Inap oInap;
    private Kamar oKamar;
    private Integer jumOrang;
    private BigDecimal biaya;
    private Date waktuStatus;

    public BigDecimal getBiaya() {
        return biaya;
    }

    public void setBiaya(BigDecimal biaya) {
        this.biaya = biaya;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJumOrang() {
        return jumOrang;
    }

    public void setJumOrang(Integer jumOrang) {
        this.jumOrang = jumOrang;
    }

    public Inap getoInap() {
        return oInap;
    }

    public void setoInap(Inap oInap) {
        this.oInap = oInap;
    }

    public Kamar getoKamar() {
        return oKamar;
    }

    public void setoKamar(Kamar oKamar) {
        this.oKamar = oKamar;
    }

    public Date getWaktuStatus() {
        return waktuStatus;
    }

    public void setWaktuStatus(Date waktuStatus) {
        this.waktuStatus = waktuStatus;
    }
    
}
