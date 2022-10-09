package kr.co.hotel.manager.dao;

import java.util.List;

import kr.co.hotel.vo.AdminVO;
import kr.co.hotel.vo.MsgVO;

public interface MsgDaoInter{

	public void addAlarm(MsgVO vo);
	public List<MsgVO> getMsgBox(String id);
	public MsgVO getMsgDetail(int msgnum);
}
