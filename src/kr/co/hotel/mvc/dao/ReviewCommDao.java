package kr.co.hotel.mvc.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.ReviewVO;

@Repository
public class ReviewCommDao implements ReviewCommDaoInter{

	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public ReviewVO rcdetail(int renum) {
		return ss.selectOne("rc.rcdetail", renum);
	}

	
}
