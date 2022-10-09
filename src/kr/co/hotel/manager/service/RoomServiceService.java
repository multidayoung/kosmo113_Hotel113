package kr.co.hotel.manager.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.hotel.manager.dao.AdminDaoInter;
import kr.co.hotel.manager.dao.RoomServiceDaoInter;
import kr.co.hotel.manager.dao.WorkListDaoInter;
import kr.co.hotel.vo.AdminVO;
import kr.co.hotel.vo.ReserveVO;
import kr.co.hotel.vo.RoomServiceVO;
import kr.co.hotel.vo.WorkList;

@Service
public class RoomServiceService {

	@Autowired
	private AdminDaoInter adminDaointer;
	@Autowired
	private RoomServiceDaoInter roomservicedaointer;
	@Autowired
	private WorkListDaoInter workListDaoInter;
	
	
	@Transactional
	public void addWorklist(List<WorkList> list) {
		workListDaoInter.addWorkList(list);
		adminDaointer.addWorkList(list);
	}
		
	@Transactional
	public void delWorklist(List<WorkList> list) {
		workListDaoInter.delWorklist(list);
		adminDaointer.delWorklist(list);
	}
	
	public RoomServiceVO getDetail(int num) {
		

		return roomservicedaointer.getDetail(num);
	}

	public AdminVO getAdminDetail(String id) {
		return adminDaointer.getDetail(id);
	}
	@Transactional
	public void workConfirm(WorkList vo) {

		workListDaoInter.workConfirm(vo);
		adminDaointer.roomserviceConfirm(vo.getAid());
	}
	@Transactional
	public void workDeny(WorkList vo) {

		workListDaoInter.workDeny(vo);
		adminDaointer.roomserviceDeny(vo.getAid());
	}

	@Transactional
	public void workCancel(WorkList vo) {

		workListDaoInter.workCancel(vo);
		adminDaointer.roomserviceCancel(vo.getAid());
	}
	
	@Transactional
	public void workComplete(WorkList vo) {

		workListDaoInter.workComplete(vo);
		roomservicedaointer.workComplete(vo.getServicenum());
		adminDaointer.roomserviceComplete(vo.getAid());
	}
}
