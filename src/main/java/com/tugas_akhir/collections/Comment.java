package com.tugas_akhir.collections;


public class Comment {
    public int idPertanyaan;
    public String namaKomentator,isiKomentar;

    public Comment(int idPertanyaan , String namaKomentator, String isiKomentar){
        this.idPertanyaan = idPertanyaan;
        this.namaKomentator = namaKomentator;
        this.isiKomentar = isiKomentar;
    }
}
