package kr.co.hotel.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.CannotReserveParaVO;
import kr.co.hotel.vo.CannotReserveVO;
import kr.co.hotel.vo.ReserveDetailParaVO;
import kr.co.hotel.vo.ReserveListParaVO;
import kr.co.hotel.vo.ReserveVO;



@Repository
public class ReserveDao implements ReserveDaoInter{

	@Autowired
	private SqlSessionTemplate ss;
	
	//페이징 처리를 위한 게시물 개수 받아오기
	@Override
	public int reservecnt(ReserveListParaVO vo) {
		int cnt = ss.selectOne("reserve.reservecnt", vo);
		return cnt;
	}
	
	//예약 insert
	@Override
	public void addreserve(ReserveVO vo) {
		ss.insert("reserve.addreserve", vo);		
	}	
	
	//회원 아이디에 해당하는 예약 리스트 가져오기 + 페이징 처리
	@Override
	public List<ReserveVO> reservelist(ReserveListParaVO vo) {
		List<ReserveVO> list = ss.selectList("reserve.reservelist", vo);
		return list;
	}

	//예약이 불가능한 기간을 받아옴
	@Override
	public List<CannotReserveVO> cannotreserve(CannotReserveParaVO vo) {
		List<CannotReserveVO> list = ss.selectList("reserve.cannotreservedays", vo);
		return list;
	}

	
	@Override
	public void rvstatechange(String today) {
		ss.update("reserve.rvstatechange", today);		
	}

	@Override
	public void rvstatechange2(String today) {
		ss.update("reserve.rvstatechange2", today);	
		
	}

	@Override
	public ReserveVO reservedetail(ReserveDetailParaVO vo) {
		ReserveVO vo1 = ss.selectOne("reserve.reservedetail", vo);
		return vo1;
	}

	@Override
	public void reservecancel(ReserveVO vo) {
		ss.update("reserve.reservecancel", vo);		
	}

}
