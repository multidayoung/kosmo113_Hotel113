package kr.co.hotel.vo;

import org.springframework.web.multipart.MultipartFile;

public class RoomVO {
	private int rnum; // 방번호
	private String rname; // 방이름
	private int rmax; // 인원
	private int rprice; // 가격
	private String rimg; // 사진
	private String rdesc; // 설명

	
	// spring web에서 제공해주는 multpart.MultipartFile
	private MultipartFile mfile;
	

	public MultipartFile getMfile() {
		return mfile;
	}
	public void setMfile(MultipartFile mfile) {
		this.mfile = mfile;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public int getRmax() {
		return rmax;
	}
	public void setRmax(int rmax) {
		this.rmax = rmax;
	}
	public int getRprice() {
		return rprice;
	}
	public void setRprice(int rprice) {
		this.rprice = rprice;
	}
	public String getRimg() {
		return rimg;
	}
	public void setRimg(String rimg) {
		this.rimg = rimg;
	}
	public String getRdesc() {
		return rdesc;
	}
	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}

}
