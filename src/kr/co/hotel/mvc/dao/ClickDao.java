package kr.co.hotel.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.ClickVO;

@Repository
public class ClickDao implements ClickDaoInter{

	@Autowired
	private SqlSessionTemplate ss;
	
	//Ŭ�� AOP
	@Override
	public void addClick(ClickVO cvo) {
		ss.insert("click.click", cvo);
	}

	//���� ���ϱ�
	@Override
	public int getAge(String mid) {
		return ss.selectOne("click.age", mid);
	}
	
	//������� ���� ���� ����
	@Override
	public List<ClickVO> popularRoom(){
		return ss.selectList("click.list");	
	}
}
