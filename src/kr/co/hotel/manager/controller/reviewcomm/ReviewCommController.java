package kr.co.hotel.manager.controller.reviewcomm;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hotel.manager.dao.ReviewCommDaoInter;
import kr.co.hotel.vo.ReviewCommVO;
import kr.co.hotel.vo.ReviewVO;


@Controller
@RequestMapping("/comm")
public class ReviewCommController {
	
	@Autowired
	private ReviewCommDaoInter reviewCommDaoInter;
	
	//�̰Ŵ�� �� �Ʒ��� /rcommList��
	@RequestMapping(value = "/commList")
	public ModelAndView commList() {
		ModelAndView mav = new ModelAndView();
		List<ReviewCommVO> commList = reviewCommDaoInter.rcList();
		mav.addObject("commList", commList);
		mav.setViewName("manager/comm/commList");
		return mav;
	}
	
	@GetMapping("/commForm")
	public ModelAndView commForm(@RequestParam("renum")int renum) { //�̹� ����ۼ��Ѱ� �ִٸ� ��� �ۼ������� �� �Ѿ�� �ϴ� �޼���
		ModelAndView mav = new ModelAndView();
		int chk = reviewCommDaoInter.doubleChk(renum);
		if (chk == 1) {
			mav.setViewName("error/paramException");
			mav.addObject("emsg", "�̹� ����� �ۼ��ߴ� �Խñ��Դϴ�.");
		}else {
		mav.addObject("renum", renum);
		mav.setViewName("manager/comm/commForm");
		}
		return mav;
	}
	
	@PostMapping("/commIn")
	public String commIn(ReviewCommVO rcvo) {
		reviewCommDaoInter.addRC(rcvo);
		return "redirect:/manager/comm/rcommList";
	}
	
	
	@GetMapping("/commDetail")
	public ModelAndView commDetail(@RequestParam("renum")int renum) {
		ModelAndView mav = new ModelAndView("manager/comm/commDetail");
		ReviewCommVO vo = reviewCommDaoInter.detailRC(renum);
		mav.addObject("vo", vo);
		return mav;
	}
	
	@GetMapping(value = "/commUpForm")
	public ModelAndView commUpForm(HttpSession session, @RequestParam("renum")int renum) {
		ModelAndView mav = new ModelAndView();
		ReviewCommVO vo = reviewCommDaoInter.detailRC(renum);
		mav.addObject("vo", vo);
		ReviewVO rvo = reviewCommDaoInter.rcdetail(renum);
		String aaname = rvo.getComm().getAdmin().getAname(); // ����ۼ��� �������� �̸�
		String aname = (String) session.getAttribute("sessionname"); //�α����� �������� �̸�
		if (aname.equals(aaname)) {
			mav.setViewName("manager/comm/commUpdateForm");
		}
		else {
			mav.setViewName("manager/error/paramException");
			mav.addObject("emsg", "�ۼ��� �����ڸ� ������ �� �ֽ��ϴ�.");
		}
		return mav;
	}
	
	@PostMapping(value = "/commUpdate")
	public String commUpdate(ReviewCommVO rcvo) {
		reviewCommDaoInter.updateRC(rcvo);
		return "redirect:/manager/comm/rcview?renum="+rcvo.getRenum();
	}
	
	@GetMapping(value = "/commDel")
	public ModelAndView commDel(HttpSession session, @RequestParam("renum")int renum) {
		ModelAndView mav = new ModelAndView();
		ReviewVO vo = reviewCommDaoInter.rcdetail(renum);
		String aaname = vo.getComm().getAdmin().getAname(); // ����ۼ��� �������� �̸�
		String aname = (String) session.getAttribute("sessionname"); //�α����� �������� �̸�
		//System.out.println("aaname : "+aaname);
		//System.out.println("aname : "+aname);
		if (aname.equals(aaname)) {
			reviewCommDaoInter.delRC(renum);
			mav.setViewName("redirect:/manager/comm/rcommList");
		}else {
			mav.setViewName("manager/error/paramException");
			mav.addObject("emsg", "�ۼ��� �����ڸ� ������ �� �ֽ��ϴ�.");
		}
		return mav;
	}

	
	//��ģ �� ���� ����Ʈ�� ������!
	@GetMapping(value = "/rcview")
	public ModelAndView rcview(@RequestParam("renum")int renum) {
		ModelAndView mav = new ModelAndView("manager/comm/rcDetail");
		ReviewVO vo = reviewCommDaoInter.rcdetail(renum);
		mav.addObject("vo", vo);
		return mav; 
	}
	
	@RequestMapping(value = "/rcommList")
	public ModelAndView rcommlist() {
		ModelAndView mav = new ModelAndView();
		List<ReviewVO> rcommList = reviewCommDaoInter.rrcList();
		mav.addObject("rcommList", rcommList);
		mav.setViewName("manager/comm/rcommList");
		return mav;
	}
	
	@GetMapping("/commForm1")
	public ModelAndView commForm1(@RequestParam("renum")int renum) { //�̹� ����ۼ��Ѱ� �ִٸ� ��� �ۼ������� �� �Ѿ�� �ϴ� �޼���
		ModelAndView mav = new ModelAndView();
		mav.addObject("renum", renum);
		mav.setViewName("manager/comm/commForm");
		return mav;
	}
}