package com.fesiacindy99.listmusic;

public class Music {
    private String judul;
    private String penyanyi;
    private String rilis;
    private String pencipta;

    public Music(String judul, String penyanyi, String rilis, String pencipta) {
        this.judul = judul;
        this.penyanyi = penyanyi;
        this.rilis = rilis;
        this.pencipta = pencipta;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenyanyi() {
        return penyanyi;
    }

    public void setPenyanyi(String penyanyi) {
        this.penyanyi = penyanyi;
    }

    public String getRilis() {
        return rilis;
    }

    public void setRilis(String rilis) {
        this.rilis = rilis;
    }

    public String getPencipta() {
        return pencipta;
    }

    public void setPencipta(String pencipta) {
        this.pencipta = pencipta;
    }
}
