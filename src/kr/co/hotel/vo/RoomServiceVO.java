package kr.co.hotel.vo;

import java.util.List;

public class RoomServiceVO extends SuperVO {

	private ReserveVO reserve;
	private List<WorkList> worklist;
	private int servicenum;
	private int rvnum;
	private String service;
	private String servicedate;
	private int servicestate;

	
	public ReserveVO getReserve() {
		return reserve;
	}
	public void setReserve(ReserveVO reserve) {
		this.reserve = reserve;
	}
	public List<WorkList> getWorklist() {
		return worklist;
	}
	public void setWorklist(List<WorkList> worklist) {
		this.worklist = worklist;
	}
	public int getServicestate() {
		return servicestate;
	}
	public void setServicestate(int servicestate) {
		this.servicestate = servicestate;
	}
	public int getServicenum() {
		return servicenum;
	}
	public void setServicenum(int servicenum) {
		this.servicenum = servicenum;
	}
	public int getRvnum() {
		return rvnum;
	}
	public void setRvnum(int rvnum) {
		this.rvnum = rvnum;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}

	public String getServicedate() {
		return servicedate;
	}
	public void setServicedate(String servicedate) {
		this.servicedate = servicedate;
	}

}
