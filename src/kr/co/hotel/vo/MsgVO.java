package kr.co.hotel.vo;

public class MsgVO {
	private AdminVO admin;
	private int msgnum;
	private String sender;
	private String receiver;
	private String sub;
	private String cont;
	private String sdate;

	public AdminVO getAdmin() {
		return admin;
	}
	public void setAdmin(AdminVO admin) {
		this.admin = admin;
	}
	public int getMsgnum() {
		return msgnum;
	}
	public void setMsgnum(int msgnum) {
		this.msgnum = msgnum;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	
}
