package com.p4.wismabr.pembayaran;

import com.p4.wismabr.inap.Inap;
import java.math.BigDecimal;
import java.util.Date;

public class Pembayaran {
    private Integer id;
    private BigDecimal totalBiaya;
    private BigDecimal sudahBayar;
    private Date tglBayar;
    private String StatusBayar;
    private Inap oInap;

    public String getStatusBayar() {
        return StatusBayar;
    }

    public void setStatusBayar(String StatusBayar) {
        this.StatusBayar = StatusBayar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Inap getoInap() {
        return oInap;
    }

    public void setoInap(Inap oInap) {
        this.oInap = oInap;
    }

    public BigDecimal getSudahBayar() {
        return sudahBayar;
    }

    public void setSudahBayar(BigDecimal sudahBayar) {
        this.sudahBayar = sudahBayar;
    }

    public Date getTglBayar() {
        return tglBayar;
    }

    public void setTglBayar(Date tglBayar) {
        this.tglBayar = tglBayar;
    }

    public BigDecimal getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(BigDecimal totalBiaya) {
        this.totalBiaya = totalBiaya;
    }
}