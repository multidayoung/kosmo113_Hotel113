package kr.co.hotel.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.hotel.vo.ReviewVO;

public interface ReviewInter {

	
	
public void write(ReviewVO rvo); // �Խñ� �ۼ� SQL, 

public void update(ReviewVO rvo); // �Խñ� ���� SQL, 

public void delete(int renum); // �Խñ� ���� SQL,

public List<ReviewVO> list(Map<String, Integer> map); // ��ü ��� ��ȸ SQL, 

public ReviewVO detailView(int renum); // �� �ı��� ���� ����

public int getCnt();




}
