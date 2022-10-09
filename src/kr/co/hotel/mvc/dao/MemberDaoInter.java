package kr.co.hotel.mvc.dao;

import kr.co.hotel.vo.MemberVO;
import kr.co.hotel.vo.MyLoginLoggerDTO;


public interface MemberDaoInter extends PageListInter{
	
	//회원가입
	public void addMember(MemberVO vo);
	
	//아이디 중복체크
	public int idCheck(String mid);

	//로그인
	public MemberVO loginMember(MemberVO vo);
	
	//로그 기록
	public void addLoginLogging(MyLoginLoggerDTO vo);

	public MemberVO getDetail(int num);

	public MemberVO getDetail2(String mid);
	
	public void upMember(MemberVO vo);
	
	public void delMember(int num);
}
