package kr.co.hotel.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.hotel.vo.ReviewVO;
@Repository("review")
public class ReviewDao implements ReviewInter {

	@Autowired
	SqlSessionTemplate ss;
	ReviewInter reviewInter; // юс╫ц

	@Override
	public void write(ReviewVO rvo) {
		ss.insert("review.add",rvo);
	}

	@Override
	public void update(ReviewVO rvo) {
		System.out.println(rvo.getRetitle());
		System.out.println(rvo.getRecontent());

		ss.update("review.upde",rvo);
	}

	@Override
	public void delete(int renum) {

		ss.delete("review.del",renum);

	}


	@Override
	public List<ReviewVO> list(Map<String, Integer> map) {
		List<ReviewVO> list = ss.selectList("review.list", map);


		return list;  

	}


	@Override
	public ReviewVO detailView(int renum) {
		ReviewVO rvo = ss.selectOne("review.deatai",renum);
		return rvo;
	}

	@Override
	public int getCnt() {
		int cnt = ss.selectOne("review.totalCount");
		return cnt;
	}

}
