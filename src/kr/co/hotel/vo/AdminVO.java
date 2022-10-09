package kr.co.hotel.vo;

import java.util.List;

public class AdminVO extends SuperVO{
	private int anum;
	private String aid;
	private String apwd;
	private String aname;
	private String ajob;
	private int astate;
	private String adate;
	private List<WorkList> worklist;
	
	public List<WorkList> getWorklist() {
		return worklist;
	}
	public void setWorklist(List<WorkList> worklist) {
		this.worklist = worklist;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public int getAnum() {
		return anum;
	}
	public void setAnum(int anum) {
		this.anum = anum;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	public String getAjob() {
		return ajob;
	}
	public void setAjob(String ajob) {
		this.ajob = ajob;
	}
	public int getAstate() {
		return astate;
	}
	public void setAstate(int astate) {
		this.astate = astate;
	}
	public String getAdate() {
		return adate;
	}
	public void setAdate(String adate) {
		this.adate = adate;
	}
	
}
