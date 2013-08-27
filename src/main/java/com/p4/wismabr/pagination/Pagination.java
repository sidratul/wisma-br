
package com.p4.wismabr.pagination;


public class Pagination {    
    private Integer awalPageTampil;
    private Integer akhirPageTampil;
    private Integer awalList;
    private Integer akhirList;
    private Integer totalHalaman;

    public Integer getAkhirList() {
        return akhirList;
    }

    public Integer getAkhirPageTampil() {
        return akhirPageTampil;
    }

    public Integer getAwalList() {
        return awalList;
    }

    public Integer getAwalPageTampil() {
        return awalPageTampil;
    }

    public Integer getTotalHalaman() {
        return totalHalaman;
    }
    
    public Pagination(Integer jumPerHalaman,Integer jumlahPageTampil,Integer halaman,Integer jumlahList){
        awalList=(halaman-1)*jumPerHalaman;
        akhirList=halaman*jumPerHalaman;
        
        if(jumlahList<akhirList){
            akhirList=jumlahList;
        }
        
        totalHalaman =(int) Math.ceil(jumlahList/(double)jumPerHalaman);
        
//        untuk bagian kiri dan kanan
        Integer  setengahJumPageTampil=jumlahPageTampil/2;
        
        Integer jumPageKeAwal = halaman - 1;
        Integer jumPageKeAkhir = totalHalaman-halaman;
        
        if(jumPageKeAwal<=jumPageKeAkhir){
            if(jumPageKeAwal<=setengahJumPageTampil){
                jumlahPageTampil = jumlahPageTampil - jumPageKeAwal;                
            }else{
                jumPageKeAwal = setengahJumPageTampil;
                jumlahPageTampil = jumlahPageTampil-jumPageKeAwal;
            }
            
            awalPageTampil = halaman -jumPageKeAwal;
            akhirPageTampil = halaman +jumlahPageTampil;
            if(akhirPageTampil > totalHalaman){
                akhirPageTampil = totalHalaman;
            }
        }else if(jumPageKeAwal>jumPageKeAkhir){
            if(jumPageKeAkhir<=setengahJumPageTampil){
                jumlahPageTampil = jumlahPageTampil - jumPageKeAkhir;
            }else{
                jumPageKeAkhir = setengahJumPageTampil;
                jumlahPageTampil = jumlahPageTampil - jumPageKeAkhir;
            }
            
            akhirPageTampil = halaman +jumPageKeAkhir;
            awalPageTampil = halaman - jumlahPageTampil;
            if(awalPageTampil < 1 ){
                awalPageTampil = 1;
            }
        }
    }
}
