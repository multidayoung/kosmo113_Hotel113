package kr.co.hotel.mvc.dao;

import java.util.List;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.ReserveVO;
import kr.co.hotel.vo.RoomServiceVO;
@Repository("serviceReserve")
public class RoomServiceDao implements RoomServiceDaoInter {

	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public void reserveService(RoomServiceVO vo) {
		// TODO Auto-generated method stub
		System.out.println("test");
		ss.insert("roomservice.add",vo);
		System.out.println("test");
	}

	@Override
	public int sChk(RoomServiceVO vo) {
		// TODO Auto-generated method stub
		int a = ss.selectOne("roomservice.schk",vo);
		System.out.println("a :" +a);
		return a;
	}

	
	@Override
	public ReserveVO getReserve(int rvnum) {
		// TODO Auto-generated method stub
		return ss.selectOne("roomservice.reserve", rvnum);
	}



}
