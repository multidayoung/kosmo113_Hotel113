package kr.co.hotel.manager.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.hotel.vo.AdminVO;
import kr.co.hotel.vo.MsgVO;
@Repository
public class MsgDao implements MsgDaoInter{

	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public void addAlarm(MsgVO vo) {
		// TODO Auto-generated method stub
		ss.insert("msg.add", vo);
	}

	@Override
	public List<MsgVO> getMsgBox(String id) {
		// TODO Auto-generated method stub
		return ss.selectList("msg.frtmsg", id);
	}

	@Override
	public MsgVO getMsgDetail(int msgnum) {
		// TODO Auto-generated method stub
		return ss.selectOne("msg.msgdetail", msgnum);
	}
	

}
