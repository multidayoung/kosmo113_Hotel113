package kr.co.hotel.mvc.dao;

import java.util.List;

import kr.co.hotel.vo.ClickVO;

public interface ClickDaoInter {

	public void addClick(ClickVO cvo);
	public int getAge(String mid);
	public List<ClickVO> popularRoom();
}
