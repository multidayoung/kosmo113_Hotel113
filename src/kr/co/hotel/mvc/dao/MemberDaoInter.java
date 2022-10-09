package kr.co.hotel.mvc.dao;

import kr.co.hotel.vo.MemberVO;
import kr.co.hotel.vo.MyLoginLoggerDTO;


public interface MemberDaoInter extends PageListInter{
	
	//ȸ������
	public void addMember(MemberVO vo);
	
	//���̵� �ߺ�üũ
	public int idCheck(String mid);

	//�α���
	public MemberVO loginMember(MemberVO vo);
	
	//�α� ���
	public void addLoginLogging(MyLoginLoggerDTO vo);

	public MemberVO getDetail(int num);

	public MemberVO getDetail2(String mid);
	
	public void upMember(MemberVO vo);
	
	public void delMember(int num);
}
