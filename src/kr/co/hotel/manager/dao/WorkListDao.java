package kr.co.hotel.manager.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.WorkList;

@Repository
public class WorkListDao implements WorkListDaoInter {

	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public void addWorkList(List<WorkList> list) {
		// TODO Auto-generated method stub
		ss.insert("worklist.addWorkList", list);
	}


	@Override
	public void delWorklist(List<WorkList> list) {
		// TODO Auto-generated method stub
		ss.delete("worklist.delWorkList",list);
	}


	@Override
	public void workConfirm(WorkList vo) {
		// TODO Auto-generated method stub
		System.out.println("confirm id : "+ vo.getAid());
		System.out.println("confirm num : "+ vo.getServicenum());
		ss.update("worklist.confirm",vo);
	}


	@Override
	public void workDeny(WorkList vo) {
		// TODO Auto-generated method stub
		ss.delete("worklist.deny", vo);
	}


	@Override
	public void workCancel(WorkList vo) {
		// TODO Auto-generated method stub
		ss.update("worklist.cancel",vo);
	}


	@Override
	public void workComplete(WorkList vo) {
		// TODO Auto-generated method stub
		ss.update("worklist.complete",vo);
	}
}
