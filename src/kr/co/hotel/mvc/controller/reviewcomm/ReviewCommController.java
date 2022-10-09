package kr.co.hotel.mvc.controller.reviewcomm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hotel.mvc.dao.ReviewCommDaoInter;
import kr.co.hotel.vo.ReviewVO;

@Controller
@RequestMapping("comm")
public class ReviewCommController {

	@Autowired
	private ReviewCommDaoInter reviewCommDaoInter;
	
	@GetMapping("comm")
	public ModelAndView comm(@RequestParam("renum")int renum) {
		ModelAndView mav = new ModelAndView("member/review/comm");
		ReviewVO vo = reviewCommDaoInter.rcdetail(renum);
		mav.addObject("vo", vo);
		return mav;
	}
}
