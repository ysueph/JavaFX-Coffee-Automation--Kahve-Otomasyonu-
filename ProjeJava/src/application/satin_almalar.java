package application;

import java.sql.Date;

public class satin_almalar {

	public satin_almalar(){
	}
	
	public satin_almalar(int Sid, int miktar,String Skul_tel,int Skul_id, Date Starih){
		this.Sid=Sid;
		this.miktar=miktar;
		this.Skul_tel=Skul_tel;
		this.Skul_id=Skul_id;
		this.Starih=Starih;
	}
	

	public String getSkul_tel() {
		return Skul_tel;
	}

	public void setSkul_tel(String skul_tel) {
		Skul_tel = skul_tel;
	}

	public int getSkul_id() {
		return Skul_id;
	}

	public void setSkul_id(int skul_id) {
		Skul_id = skul_id;
	}
	
	public int getSid() {
		return Sid;
	}

	public void setSid(int sid) {
		Sid = sid;
	}

	public int getMiktar() {
		return miktar;
	}

	public void setMiktar(int miktar) {
		this.miktar = miktar;
	}

	public Date getStarih() {
		return Starih;
	}

	public void setStarih(Date starih) {
		Starih = starih;
	}
	private int Sid;
	private String Skul_tel;
	private int Skul_id;
	private int miktar;
	private Date Starih;

}
