package kr.co.hotel.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.MemberVO;
import kr.co.hotel.vo.MyLoginLoggerDTO;

@Repository("memberList")
public class MemberDao implements MemberDaoInter{

	@Autowired
	private SqlSessionTemplate ss;
	
	//ȸ������
	@Override
	public void addMember(MemberVO vo) {
		ss.insert("member.add", vo);
	}

	//���̵��ߺ�üũ
	@Override
	public int idCheck(String mid) {
		return ss.selectOne("member.idchk", mid);
	}
	
	//�α���
	@Override
	public MemberVO loginMember(MemberVO vo) {
		return ss.selectOne("member.login", vo);
	}
	
	//�α� ���
	@Override
	public void addLoginLogging(MyLoginLoggerDTO vo) {
		ss.insert("member.logger_in",vo);
	}
	
	// getCnt
	@Override
	public int getCnt() {
		int cnt = ss.selectOne("member.totalCount");
		return cnt;
	}
	
	// ����¡ ó���� �Ϸ�� ����Ʈ
	@Override
	public List<MemberVO> getList(Map<String, Integer> map){
		List<MemberVO> list = ss.selectList("member.memberlist", map);
		return list;
	}

	@Override
	public MemberVO getDetail2(String mid) {
		MemberVO vo = ss.selectOne("member.memberdetail2",mid);
		return vo;
	}

	@Override
	public void upMember(MemberVO vo) {
		ss.update("member.memberupdate",vo);
	}

	@Override
	public void delMember(int num) {
		ss.delete("member.memberdelete",num);	
	}

	@Override
	public MemberVO getDetail(int num) {
		MemberVO vo = ss.selectOne("member.memberdetail",num);
		return vo;
	}

	

	

	
	
	
}
