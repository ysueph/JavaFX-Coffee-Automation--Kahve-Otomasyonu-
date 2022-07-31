package application;

public class kullanici {
	public kullanici(){
	}
	
	public kullanici(int id, String ad, String soyad,String telefon, String sifre,int bakiye){
		this.id=id;
		this.ad=ad;
		this.soyad=soyad;
		this.telefon=telefon;
		this.sifre=sifre;
		this.bakiye=bakiye;
	}
	

	private int id;
	private String ad;
	private String soyad;
	private String telefon;
	private String sifre;
	private int bakiye;
	

	public int getBakiye() {
		return bakiye;
	}

	public void setBakiye(int bakiye) {
		this.bakiye = bakiye;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	
}
