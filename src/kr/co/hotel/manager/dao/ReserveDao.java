package kr.co.hotel.manager.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.ReserveVO;
import kr.co.hotel.vo.RoomVO;

@Repository
public class ReserveDao implements ReserveDaoInter{
	
	@Autowired
	private SqlSessionTemplate ss;


	@Override
	public List<ReserveVO> adminreservelist(Map<String, Integer> map) {
		List<ReserveVO> list = ss.selectList("reserve.adminreservelist", map);
		return list;
	}

	@Override
	public int adminreservecnt() {
		int cnt = ss.selectOne("reserve.adminreservecnt");
		return cnt;
	}

	
}
