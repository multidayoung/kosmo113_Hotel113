package kr.co.hotel.manager.dao;

import java.util.Collection;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.ReserveVO;
import kr.co.hotel.vo.RoomServiceVO;
import kr.co.hotel.vo.SuperVO;
import kr.co.hotel.vo.WorkList;

@Repository("roomServiceList")
public class RoomServiceDao implements RoomServiceDaoInter {
	@Autowired
	private SqlSessionTemplate ss;

	@Override
	public List<? extends SuperVO> getList(Map<String, Integer> map) {
		// TODO Auto-generated method stub

		return ss.selectList("roomservice.list", map);
	}


	@Override
	public int getCnt() {
		// TODO Auto-generated method stub
		return ss.selectOne("roomservice.cnt");
	}
	@Override
	public RoomServiceVO getDetail(int num) {
		
		return ss.selectOne("roomservice.detail", num);
	}

	@Override
	public void statemodify(int num) {
		// TODO Auto-generated method stub
		ss.update("roomservice.statemodify",num);
	}

	//다시 상태 복구
	@Override
	public void statemodify2(int num) {
		// TODO Auto-generated method stub
		ss.update("roomservice.statemodify2",num);
	}
	
	@Override
	public int chkDetail(int num) {
		// TODO Auto-generated method stub
		return ss.selectOne("roomservice.chkdetail", num);
	}


	@Override
	public void workComplete(int num) {
		// TODO Auto-generated method stub
		ss.update("roomservice.complete",num);
	}








	
}
