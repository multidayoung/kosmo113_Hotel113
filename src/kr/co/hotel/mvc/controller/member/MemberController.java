package kr.co.hotel.mvc.controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hotel.mvc.dao.MemberDaoInter;
import kr.co.hotel.vo.MemberVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberDaoInter memberDaoInter;
	
	//회원가입
	@RequestMapping("/etcmemberForm")
	public String etcmemberForm() {
		return "member/member/etcmemberForm";
	}
		
	@RequestMapping("/memberForm")
	public String memberForm() {
		return "member/member/memberForm";
	}
	
	@PostMapping("/memberIn")
	public String memberIn(MemberVO vo) {
		memberDaoInter.addMember(vo);
		return "redirect:/member/main";
	}
		
	//아이디 중복체크
	@RequestMapping("/idChk")
	public String idChk(Model m,@RequestParam("mid") String mid) {
		int cnt = memberDaoInter.idCheck(mid);
		System.out.println(cnt);
		m.addAttribute("cnt", cnt);
		return "idCheck";
	}
	
	@RequestMapping(value = "/memberDetail")
	public String memberDetail(Model model,int num) {
		MemberVO vo = memberDaoInter.getDetail(num);
		model.addAttribute("vo",vo);
		return "member/member/memberDetail";
	}
	
	@GetMapping(value = "/memberUpForm")
    public ModelAndView memberUpForm(@RequestParam("mnum") int num) {
        ModelAndView mav = new ModelAndView();
        MemberVO vo = memberDaoInter.getDetail(num);
        mav.addObject("vo", vo);
        mav.setViewName("member/member/memberUpdate");
        return mav;
    }
	
	@PostMapping(value = "/memberUpdate")
	public String memberUpdate(MemberVO vo) {
		memberDaoInter.upMember(vo);
		return "redirect:/member/member/myPage";
	}
	
	@GetMapping(value = "/memberDelete")
	public String memberDelete(int num) {
		memberDaoInter.delMember(num);
		return "redirect:/member/main";
	}	
	
	@RequestMapping(value = "/myPage")
	public String myPage(Model model,HttpSession session) {
		MemberVO vo = memberDaoInter.getDetail2((String) session.getAttribute("sessionid"));
		model.addAttribute("vo",vo);
		return "member/member/myPage";
	}
	
	
}
