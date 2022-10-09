package kr.co.hotel.vo;

import java.util.List;

public class ReserveVO {

	private int rvnum, rnum, rvstate, rvprice;
	private String mid,rvstart, rvend;
	private RoomVO roomvo;	
	private List<RoomServiceVO> roomservice;
	
	public List<RoomServiceVO> getRoomservice() {
		return roomservice;
	}
	public void setRoomservice(List<RoomServiceVO> roomservice) {
		this.roomservice = roomservice;
	}
	
	public RoomVO getRoomvo() {
		return roomvo;
	}
	public void setRoomvo(RoomVO roomvo) {
		this.roomvo = roomvo;
	}
	public int getRvprice() {
		return rvprice;
	}
	public void setRvprice(int rvprice) {
		this.rvprice = rvprice;
	}
	public int getRvnum() {
		return rvnum;
	}
	public void setRvnum(int rvnum) {
		this.rvnum = rvnum;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getRvstate() {
		return rvstate;
	}
	public void setRvstate(int rvstate) {
		this.rvstate = rvstate;
	}
	public String getRvstart() {
		return rvstart;
	}
	public void setRvstart(String rvstart) {
		this.rvstart = rvstart;
	}
	public String getRvend() {
		return rvend;
	}
	public void setRvend(String rvend) {
		this.rvend = rvend;
	}
	
}
