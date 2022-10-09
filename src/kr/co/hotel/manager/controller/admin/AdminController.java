package kr.co.hotel.manager.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	// 회원 리스트 출력 (AOP로 페이징 처리)
	@RequestMapping(value = "/adminlist")
	public String adminList(Model model,String cPage) {	
		return "manager/admin/adminList";
	}
}
