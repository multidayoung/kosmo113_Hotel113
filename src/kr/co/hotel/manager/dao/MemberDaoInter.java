package kr.co.hotel.manager.dao;

import java.util.List;

import kr.co.hotel.vo.MemberVO;
import kr.co.hotel.vo.SearchVO;

public interface MemberDaoInter extends PageListInter{
	public int getSearchCnt(SearchVO vo);
	public List<MemberVO> getSearchList(SearchVO vo);
	public MemberVO getDetail(int num);
	public void upMember(MemberVO vo);
	public void delMember(int num);
}
