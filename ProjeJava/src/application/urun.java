package application;

public class urun {
	public urun(){
	}
	
	public urun(int Uid, String urun_adi, int fiyat, String urun_tur){
		this.Uid=Uid;
		this.urun_adi=urun_adi;
		this.fiyat=fiyat;
		this.urun_tur=urun_tur;
		
		
	}
	

	public int getUid() {
		return Uid;
	}

	public void setUid(int uid) {
		Uid = uid;
	}

	public String getUrun_adi() {
		return urun_adi;
	}

	public void setUrun_adi(String urun_adi) {
		this.urun_adi = urun_adi;
	}

	public int getFiyat() {
		return fiyat;
	}

	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}

	public String getUrun_tur() {
		return urun_tur;
	}

	public void setUrun_tur(String urun_tur) {
		this.urun_tur = urun_tur;
	}

	/*private int[] tikla;
	

	public int[] getTikla() {
		return tikla;
	}

	public void setTikla(int[] tikla) {
		this.tikla = tikla;
	}*/

	private int Uid;
	private String urun_adi;
	private int fiyat;
	private String urun_tur;
}
