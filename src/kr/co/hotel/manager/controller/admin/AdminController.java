package kr.co.hotel.manager.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	// ȸ�� ����Ʈ ��� (AOP�� ����¡ ó��)
	@RequestMapping(value = "/adminlist")
	public String adminList(Model model,String cPage) {	
		return "manager/admin/adminList";
	}
}
