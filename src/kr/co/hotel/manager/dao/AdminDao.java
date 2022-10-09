package kr.co.hotel.manager.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.AdminVO;
import kr.co.hotel.vo.ReserveVO;
import kr.co.hotel.vo.SuperVO;
import kr.co.hotel.vo.WorkList;

@Repository("adminList")
public class AdminDao implements AdminDaoInter{
	
	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public List<AdminVO> getList(Map<String, Integer> map) {
		return ss.selectList("admin.list", map);
	}

	@Override
	public int getCnt() {
		int a = ss.selectOne("admin.cnt");
		return a;
	}

	@Override
	public AdminVO getDetail(String id) {
		return ss.selectOne("admin.detail", id);
	}

	@Override
	public void roomserviceConfirm(String id) {
		System.out.println("confirm : "+ id);
		ss.update("admin.confirm", id);
	}

	@Override
	public void roomserviceCancel(String id) {
		ss.update("admin.cancel", id);
	}

	@Override
	public void roomserviceComplete(String id) {
		ss.update("admin.complete", id);
	}

	@Override
	public void roomserviceDeny(String id) {
		ss.update("admin.deny", id);
	}

	@Override
	public void addWorkList(List<WorkList> list) {
		ss.update("admin.addWorkList", list);
	}

	@Override
	public void delWorklist(List<WorkList> list) {
		ss.update("admin.delWorklist", list);
	}

	//관리자 로그인
	@Override
	public AdminVO loginAdmin(AdminVO vo) {
		return ss.selectOne("admin.login", vo);
	}

	@Override
	public List<AdminVO> getalist() {
		return ss.selectList("admin.alist");
	}

}
