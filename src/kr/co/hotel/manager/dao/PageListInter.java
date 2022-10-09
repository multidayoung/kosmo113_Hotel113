package kr.co.hotel.manager.dao;

import java.util.List;
import java.util.Map;

import kr.co.hotel.vo.SuperVO;

public interface PageListInter {
	public List<? extends SuperVO> getList(Map<String, Integer> map);
	public int getCnt();
}
