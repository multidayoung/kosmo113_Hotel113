package kr.co.hotel.mvc.controller.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hotel.mvc.dao.ReviewInter;
import kr.co.hotel.vo.ReviewVO;

@Controller
@RequestMapping(value = "/review")
public class ReviewController {       // 수정
	
	@Autowired
	private ReviewInter reviewInter;	

	/*@RequestMapping("/reviewlist")
	public ModelAndView list() {
		List<ReviewVO> list = reviewInter.list();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/relist");
		mav.addObject("list", list);
		return mav;
	}*/

	@GetMapping("/wr")
	public String writeForm(Model model, @RequestParam("rvnum")int rvnum) {
		model.addAttribute("rvnum", rvnum);
		return "member/review/readd";
	}
	
	@RequestMapping("/write")
	public String writee(ReviewVO rvo) {
		System.out.println(rvo.getRetitle());
		System.out.println(rvo.getRewriter());
		System.out.println(rvo.getRecontent());
		reviewInter.write(rvo);
		return "redirect:/member/review/reviewlist";
	}
	
	
	@RequestMapping(value ="/del")
	public String deletee(@RequestParam("renum") int renum) {
		reviewInter.delete(renum);
		return "redirect:/member/review/reviewlist";
	}

	@RequestMapping(value = "/upde")   //////////////
	public String updatee(ReviewVO rvo) {
		System.out.println(rvo.getRecontent());
		System.out.println(rvo.getRewriter());
		reviewInter.update(rvo);
		return "redirect:/member/review/reviewlist";
	}
	
	@RequestMapping(value = "/upform")
	public ModelAndView reviewUpform(@RequestParam("renum") int num) {
		ModelAndView mav = new ModelAndView();
		ReviewVO rvo = reviewInter.detailView(num);
		mav.addObject("rvo",rvo);
		mav.setViewName("member/review/reupdate");
		return mav;
	}
	
	@GetMapping(value = "/view")
	public String vieww(Model model ,@RequestParam("renum") int renum) {
		model.addAttribute("review", reviewInter.detailView(renum));	
		return "member/review/redeatail"; // 디테일 
	}

	// 검색 결과 리스트 출력 ( AOP 페이징 처리 사용 안한 것)
		@GetMapping(value = "/reviewlist")
		public String reviewlist(Model m,@RequestParam(defaultValue = "1")int cPage) {
			   int nowPage = 1;//현재 페이지 값
			   int nowBlock = 1;//현재 블럭
			   int totalRecord = 0;//총 게시물 수
			   int numPerPage = 10;//한 페이지 당 보여질 게시물 수
			   int pagePerBlock = 10;//한 블럭 당 보여질 페이지 수. 5 이상이면 다음 버튼 있음
			   int totalPage = 0; //전체페이지 수 => totalRecord/numPerPage
			   int beginPerPage = 0; //각 페이지 별 시작 게시물의 index 값
			   int endPerPage = 0; //각 페이지 별 마지막 게시물의 index 값
			    totalRecord = reviewInter.getCnt();
			   System.out.println(totalRecord);
			      System.out.println("totalRecord: "+totalRecord);
			      totalPage = (int) Math.ceil(totalRecord/(double)numPerPage);
			      System.out.println("cPage: "+cPage);
			      if(cPage != 0) {
			         nowPage = cPage;
			      }
			      m.addAttribute("nowPage", nowPage);
			      beginPerPage = (nowPage -1) * numPerPage + 1;
			      endPerPage = (beginPerPage -1) + numPerPage;
			      Map<String, Integer> map = new HashMap<String, Integer>();
			      map.put("begin", beginPerPage);
			      map.put("end", endPerPage);
			      List<ReviewVO> list = reviewInter.list(map);
				  m.addAttribute("list",list);
				  
			      int startPage = (int) ((nowPage-1)/pagePerBlock)*pagePerBlock+1;
			      int endPage = startPage + pagePerBlock - 1;

			      if(endPage > totalPage){
			         endPage = totalPage;
			      }
			      m.addAttribute("startPage",startPage );
			      m.addAttribute("totalPage", totalPage);
			      m.addAttribute("endPage", endPage);
			      m.addAttribute("nowPage", nowPage);
			      m.addAttribute("pagePerBlock", pagePerBlock);
			   
			
			return "member/review/relist";		
		}
		
		
}
