package kr.co.hotel.vo;

public class WorkList {

	private int servicenum;
	private String aid;
	private int wstate;
	private String wdate;
	private RoomServiceVO roomservice;
	private AdminVO admin;
	
	public RoomServiceVO getRoomservice() {
		return roomservice;
	}
	public void setRoomservice(RoomServiceVO roomservice) {
		this.roomservice = roomservice;
	}
	public AdminVO getAdmin() {
		return admin;
	}
	public void setAdmin(AdminVO admin) {
		this.admin = admin;
	}
	public int getServicenum() {
		return servicenum;
	}
	public void setServicenum(int servicenum) {
		this.servicenum = servicenum;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public int getWstate() {
		return wstate;
	}
	public void setWstate(int wstate) {
		this.wstate = wstate;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	
}
