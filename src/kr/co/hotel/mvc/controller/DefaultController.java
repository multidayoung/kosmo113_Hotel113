package kr.co.hotel.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.hotel.mvc.dao.ClickDaoInter;
import kr.co.hotel.vo.ClickVO;

@Controller

public class DefaultController {

	@Autowired
	private ClickDaoInter clickDaoInter;
	
	@RequestMapping(value = {"/","/main"})
	public String defaultIndex(Model m) {
		List<ClickVO> list = clickDaoInter.popularRoom();
		m.addAttribute("list", list);
		return "member/main/index";
	}
}
