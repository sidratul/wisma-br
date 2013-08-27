package com.p4.wismabr.inap;

import com.p4.wismabr.tamu.Tamu;
import java.util.Date;

public class Inap {
    private Integer id;
    private String inapId;
    private Date waktuCheckin;
    private Date waktuCheckout;
    private Tamu oTamu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInapId() {
        return inapId;
    }

    public void setInapId(String inapId) {
        this.inapId = inapId;
    }

    public Tamu getoTamu() {
        return oTamu;
    }

    public void setoTamu(Tamu oTamu) {
        this.oTamu = oTamu;
    }

    public Date getWaktuCheckin() {
        return waktuCheckin;
    }

    public void setWaktuCheckin(Date waktuCheckin) {
        this.waktuCheckin = waktuCheckin;
    }

    public Date getWaktuCheckout() {
        return waktuCheckout;
    }

    public void setWaktuCheckout(Date waktuCheckout) {
        this.waktuCheckout = waktuCheckout;
    }
    
}
