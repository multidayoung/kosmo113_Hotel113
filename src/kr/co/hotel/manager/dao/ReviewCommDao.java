package kr.co.hotel.manager.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.ReviewCommVO;
import kr.co.hotel.vo.ReviewVO;


@Repository
public class ReviewCommDao implements ReviewCommDaoInter{
	
	@Autowired
	private SqlSessionTemplate ss;

	@Override
	public void addRC(ReviewCommVO rcvo) {
		ss.insert("rc.add", rcvo);
	}

	@Override
	public int doubleChk(int renum) {
		return ss.selectOne("rc.chk", renum);
	}

	@Override
	public void delRC(int renum) {
		ss.delete("rc.del", renum);
	}

	@Override
	public void updateRC(ReviewCommVO rcvo) {
		ss.update("rc.update", rcvo);
	}

	@Override
	public ReviewCommVO detailRC(int renum) {
		return ss.selectOne("rc.detail", renum);
	}
	
	@Override
	public List<ReviewCommVO> rcList() {
		return ss.selectList("rc.list");
	}

	//합친 후 리스트와 디테일 새로!
	@Override
	public List<ReviewVO> rrcList() {
		return ss.selectList("rc.rcalist");
	}

	@Override
	public ReviewVO rcdetail(int renum) {
		return ss.selectOne("rc.rcdetail", renum);
	}
	
	
	
}
