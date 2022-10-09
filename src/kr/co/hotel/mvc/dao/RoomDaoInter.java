package kr.co.hotel.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.hotel.vo.RoomVO;

public interface RoomDaoInter {

	public int getCnt();
	public List<RoomVO> listRoom(Map<String, Integer> map);
	public RoomVO detailRoom(int rnum);

}
