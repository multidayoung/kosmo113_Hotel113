package kr.co.hotel.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.hotel.vo.ReviewVO;

public interface ReviewInter {

	
	
public void write(ReviewVO rvo); // 게시글 작성 SQL, 

public void update(ReviewVO rvo); // 게시글 수정 SQL, 

public void delete(int renum); // 게시글 삭제 SQL,

public List<ReviewVO> list(Map<String, Integer> map); // 전체 목록 조회 SQL, 

public ReviewVO detailView(int renum); // 상세 후기의 정보 보기

public int getCnt();




}
