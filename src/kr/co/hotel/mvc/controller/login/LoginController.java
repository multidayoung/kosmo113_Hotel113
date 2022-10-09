package kr.co.hotel.mvc.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hotel.mvc.dao.MemberDaoInter;
import kr.co.hotel.vo.MemberVO;

@Controller
public class LoginController {
	
	@Autowired
	private MemberDaoInter memberDaoInter;
	
	@RequestMapping("/etcloginform")
	public String etcLogin() {
		return "member/login/etclogin";
	}
	
	@RequestMapping("/loginform")
	public String login() {
		return "member/login/login";
	}
	
	@PostMapping("/loginProcess")
	public ModelAndView loginfProcess(HttpSession session, HttpServletRequest request, MemberVO vo, @RequestHeader("User-Agent") String userAgent) {
		System.out.println("User-Agent : "+userAgent); //��ġ ����
		System.out.println("Reip : "+request.getRemoteAddr()); //ip �ּ�
		ModelAndView mav = new ModelAndView("redirect:/member/");
		MemberVO dto = memberDaoInter.loginMember(vo);
		if (dto == null) {
			mav.setViewName("member/error/paramException");
			mav.addObject("emsg", "�α��� �����Դϴ�.");
		}else {
			//login���� �ߴٸ� �������� sessionName ������ ����� �̸�, sessionID ������ ����� id���� session�� ����
			System.out.println("mid : "+dto.getMid());
			System.out.println("mname : "+dto.getMname());
			session.setAttribute("sessionname", dto.getMname());
			session.setAttribute("sessionid", dto.getMid());
		}
		return mav;
	}
	
	
	@GetMapping("/logout")
	public ModelAndView loginfoutProcess(HttpSession session , HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		session.removeAttribute("sessionname");
		session.removeAttribute("sessionid");
		session.invalidate();
		mav.setViewName("redirect:/member/");
		return mav;
	}
}
