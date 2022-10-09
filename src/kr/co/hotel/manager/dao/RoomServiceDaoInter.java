package kr.co.hotel.manager.dao;

import java.util.List;

import kr.co.hotel.vo.ReserveVO;
import kr.co.hotel.vo.RoomServiceVO;
import kr.co.hotel.vo.WorkList;

public interface RoomServiceDaoInter extends PageListInter {

	public int chkDetail(int num);
	public RoomServiceVO getDetail(int num);

	public void statemodify(int num);
	public void statemodify2(int num);
	public void workComplete(int num);

}
