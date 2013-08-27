package com.p4.wismabr.kamar;

import java.math.BigDecimal;

public class Kamar {
    private Integer id;
    private String noKamar;
    private String acTv;
    private BigDecimal maxOrang;
    private String StatIn;
    private String StatReservasi;
    private String kelasKamar;

    public String getKelasKamar() {
        return kelasKamar;
    }

    public void setKelasKamar(String kelasKamar) {
        this.kelasKamar = kelasKamar;
    }

    public String getStatIn() {
        return StatIn;
    }

    public void setStatIn(String StatIn) {
        this.StatIn = StatIn;
    }

    public String getStatReservasi() {
        return StatReservasi;
    }

    public void setStatReservasi(String StatReservasi) {
        this.StatReservasi = StatReservasi;
    }

    public String getAcTv() {
        return acTv;
    }

    public void setAcTv(String acTv) {
        this.acTv = acTv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMaxOrang() {
        return maxOrang;
    }

    public void setMaxOrang(BigDecimal maxOrang) {
        this.maxOrang = maxOrang;
    }

    public String getNoKamar() {
        return noKamar;
    }

    public void setNoKamar(String noKamar) {
        this.noKamar = noKamar;
    }
    
    
    
}
