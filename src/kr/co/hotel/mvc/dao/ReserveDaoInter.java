package kr.co.hotel.mvc.dao;

import java.util.List;

import kr.co.hotel.vo.CannotReserveParaVO;
import kr.co.hotel.vo.CannotReserveVO;
import kr.co.hotel.vo.ReserveDetailParaVO;
import kr.co.hotel.vo.ReserveListParaVO;
import kr.co.hotel.vo.ReserveVO;

public interface ReserveDaoInter{

	public int reservecnt(ReserveListParaVO vo);	
	public void addreserve(ReserveVO vo);	
	public List<ReserveVO> reservelist(ReserveListParaVO vo);
	public List<CannotReserveVO> cannotreserve(CannotReserveParaVO vo);
	public void rvstatechange(String today);
	public void rvstatechange2(String today);
	public ReserveVO reservedetail(ReserveDetailParaVO vo);
	public void reservecancel(ReserveVO vo);
}
