package kr.co.hotel.manager.dao;

import java.util.List;

import kr.co.hotel.vo.WorkList;

public interface WorkListDaoInter {
	public void addWorkList(List<WorkList> list);
	public void delWorklist(List<WorkList> list);
	public void workConfirm(WorkList vo);
	public void workDeny(WorkList vo);
	public void workCancel(WorkList vo);
	public void workComplete(WorkList vo);
}
