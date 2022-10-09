package kr.co.hotel.manager.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.MemberVO;
import kr.co.hotel.vo.SearchVO;

@Repository("memberList")
public class MemberDao implements MemberDaoInter{

	@Autowired
	private SqlSessionTemplate ss;	
	
	@Override
	public int getCnt() {
		int cnt = ss.selectOne("member.totalCount");
		return cnt;
	}	
	@Override
	public List<MemberVO> getList(Map<String, Integer> map){
		List<MemberVO> list = ss.selectList("member.memberlist", map);
		return list;
	}
	@Override
	public MemberVO getDetail(int num) {
		MemberVO vo = ss.selectOne("member.memberdetail",num);
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
	public int getSearchCnt(SearchVO vo) {
		return ss.selectOne("member.searchCount",vo);
	}
	@Override
	public List<MemberVO> getSearchList(SearchVO vo) {
		return ss.selectList("member.searchlist",vo);
	}

}
