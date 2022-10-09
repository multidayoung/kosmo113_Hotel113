package kr.co.hotel.manager.dao;

import java.util.List;

import kr.co.hotel.vo.AdminVO;
import kr.co.hotel.vo.WorkList;

public interface AdminDaoInter extends PageListInter{

	public void addWorkList(List<WorkList> list);
	public void delWorklist(List<WorkList> list);
	
	public AdminVO getDetail(String id);
	public void roomserviceConfirm(String id);
	public void roomserviceCancel(String id);
	public void roomserviceComplete(String id);
	public void roomserviceDeny(String id);
	
	public AdminVO loginAdmin(AdminVO vo);
	public List<AdminVO> getalist();
}
