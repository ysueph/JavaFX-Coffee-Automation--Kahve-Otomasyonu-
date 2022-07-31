package application;

import java.sql.Date;

public class yuklemeler {

	public yuklemeler(){
	}
	
	public yuklemeler(int Yid, int Ymiktar,String kul_tel,int kul_id, Date Ytarih){
		this.Yid=Yid;
		this.Ymiktar=Ymiktar;
		this.kul_tel=kul_tel;
		this.kul_id=kul_id;
		this.Ytarih=Ytarih;
	}
	
	public int getYid() {
		return Yid;
	}

	public void setYid(int yid) {
		Yid = yid;
	}

	public String getKul_tel() {
		return kul_tel;
	}

	public void setKul_tel(String kul_tel) {
		this.kul_tel = kul_tel;
	}

	public int getKul_id() {
		return kul_id;
	}

	public void setKul_id(int kul_id) {
		this.kul_id = kul_id;
	}

	public int getYmiktar() {
		return Ymiktar;
	}

	public void setYmiktar(int ymiktar) {
		Ymiktar = ymiktar;
	}

	public Date getYtarih() {
		return Ytarih;
	}

	public void setYtarih(Date ytarih) {
		Ytarih = ytarih;
	}
	private int Yid;
	private String kul_tel;
	private int kul_id;
	private int Ymiktar;
	private Date Ytarih;
}
