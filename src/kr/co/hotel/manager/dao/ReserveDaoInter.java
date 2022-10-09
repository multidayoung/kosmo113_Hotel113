package kr.co.hotel.manager.dao;

import java.util.List;
import java.util.Map;

import kr.co.hotel.vo.ReserveVO;

public interface ReserveDaoInter {
	public List<ReserveVO> adminreservelist(Map<String, Integer> map);
	public int adminreservecnt();
}
