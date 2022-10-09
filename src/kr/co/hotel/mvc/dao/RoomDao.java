package kr.co.hotel.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.RoomVO;

@Repository
public class RoomDao implements RoomDaoInter {
	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public int getCnt() {
		int cnt = ss.selectOne("room.totalCount");
		return cnt;
	}

	@Override
	public List<RoomVO> listRoom(Map<String, Integer> map) {
		List<RoomVO> list = ss.selectList("room.list", map);
		return list;
	}

	@Override
	public RoomVO detailRoom(int rnum) {
		RoomVO vo = ss.selectOne("room.detail", rnum);
		return vo;
	}

}
