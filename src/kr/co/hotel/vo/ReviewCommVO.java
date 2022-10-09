package kr.co.hotel.vo;

public class ReviewCommVO {
	private int renum, anum;
	private String rccontent, rcdate;
	
	private AdminVO admin;
	
	public AdminVO getAdmin() {
		return admin;
	}
	public void setAdmin(AdminVO admin) {
		this.admin = admin;
	}
	public int getRenum() {
		return renum;
	}
	public void setRenum(int renum) {
		this.renum = renum;
	}
	public int getAnum() {
		return anum;
	}
	public void setAnum(int anum) {
		this.anum = anum;
	}
	public String getRccontent() {
		return rccontent;
	}
	public void setRccontent(String rccontent) {
		this.rccontent = rccontent;
	}
	public String getRcdate() {
		return rcdate;
	}
	public void setRcdate(String rcdate) {
		this.rcdate = rcdate;
	}
	
}
