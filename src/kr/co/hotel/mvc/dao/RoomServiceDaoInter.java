package kr.co.hotel.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.hotel.vo.ReserveVO;
import kr.co.hotel.vo.RoomServiceVO;

public interface RoomServiceDaoInter {

	public void reserveService(RoomServiceVO vo);
	public int sChk(RoomServiceVO vo);
	public ReserveVO getReserve(int rvnum);

}
