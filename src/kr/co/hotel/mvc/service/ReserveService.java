package kr.co.hotel.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.hotel.mvc.dao.ReserveDaoInter;


@Service
public class ReserveService {
	
	@Autowired
	private ReserveDaoInter reserveDaoInter;
	
	@Transactional
	public void rvstateChangeService(String today) {
		reserveDaoInter.rvstatechange2(today);
		reserveDaoInter.rvstatechange(today);
	}
}
