package kr.co.hotel.manager.dao;

import java.util.List;
import java.util.Map;

import kr.co.hotel.vo.RoomVO;

public interface RoomDaoInter {
	public void addRoom(RoomVO vo);
	public int getCnt();
	public List<RoomVO> listRoom(Map<String, Integer> map);
	public RoomVO detailRoom(int rnum);
	public void updateRoom(RoomVO vo);
	public void deleteRoom(int rnum);
	public int checkRoom(int rnum); // 방번호 체크 추가
}
