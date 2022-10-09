package kr.co.hotel.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.AdminVO;
@Repository
public class AdminDao implements AdminDaoInter{

	@Autowired
	private SqlSessionTemplate ss;
	@Override
	public List<AdminVO> getAllList() {
		// TODO Auto-generated method stub
		return ss.selectList("admin.alllist");
	}

}
