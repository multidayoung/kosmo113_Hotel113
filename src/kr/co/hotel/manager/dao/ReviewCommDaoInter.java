package kr.co.hotel.manager.dao;

import java.util.List;

import kr.co.hotel.vo.ReviewCommVO;
import kr.co.hotel.vo.ReviewVO;

public interface ReviewCommDaoInter {
	
	public void addRC(ReviewCommVO rcvo);
	public int doubleChk(int renum);
	public void delRC(int renum);
	public void updateRC(ReviewCommVO rcvo);
	public ReviewCommVO detailRC(int renum); 
	public List<ReviewCommVO> rcList();
	
	public List<ReviewVO> rrcList();
	public ReviewVO rcdetail(int renum);
}
