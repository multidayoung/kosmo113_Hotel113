package kr.co.hotel.manager.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hotel.manager.dao.AdminDaoInter;
import kr.co.hotel.vo.AdminVO;

@Controller
public class LoginController {
	
	@Autowired
	private AdminDaoInter adminDaoInter;
	
	@RequestMapping("/loginform")
	public String login() {
		return "manager/login/login1";
	}
	
	@PostMapping("/loginProcess")
	public ModelAndView loginProcess(HttpSession session, HttpServletRequest request, AdminVO vo, @RequestHeader("User-Agent") String userAgent) {
		System.out.println("User-Agent : "+userAgent); //��ġ ����
		System.out.println("Reip : "+request.getRemoteAddr()); //ip �ּ�
		ModelAndView mav = new ModelAndView("redirect:/manager/");
		AdminVO dto = adminDaoInter.loginAdmin(vo);
		if (dto == null) {
			mav.setViewName("error/paramException");
			mav.addObject("emsg", "�α��� �����Դϴ�.");
		}else {
			//login���� �ߴٸ� �������� sessionName ������ ����� �̸�, sessionID ������ ����� id���� session�� ����
			System.out.println("aid : "+dto.getAid());
			System.out.println("aname : "+dto.getAname());
			session.setAttribute("sessionAnum", dto.getAnum());
			System.out.println("sessionAnum ========> "+session.getAttribute("sessionAnum"));
			session.setAttribute("sessionname", dto.getAname());
			session.setAttribute("sessionid", dto.getAid());
		}
		return mav;
	}
	
	
	@GetMapping("/logout")
	public ModelAndView loginoutProcess(HttpSession session , HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		session.removeAttribute("sessionAnum");
		session.removeAttribute("sessionname");
		session.removeAttribute("sessionid");
		session.invalidate();
		mav.setViewName("redirect:/member/");
		return mav;
	}
}
