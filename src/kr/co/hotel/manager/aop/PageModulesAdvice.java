package kr.co.hotel.manager.aop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import kr.co.hotel.manager.dao.PageListInter;
import kr.co.hotel.vo.SuperVO;

@Component
@Aspect
public class PageModulesAdvice {
	@Autowired
	private ApplicationContext applicationContext;

	private int nowPage = 1;// 현재 페이지 값
	private int nowBlock = 1;// 현재 블럭
	private int totalRecord = 0;// 총 게시물 수
	private int numPerPage = 10;// 한 페이지 당 보여질 게시물 수
	private int pagePerBlock = 10;// 한 블럭 당 보여질 페이지 수. 10 이상이면 다음 버튼 있음
	private int totalPage = 0; // 전체페이지 수 => totalRecord/numPerPage
	private int totalBlock = 0; // 전체 블럭 수
	private int beginPerPage = 0; // 각 페이지 별 시작 게시물의 index 값
	private int endPerPage = 0; // 각 페이지 별 마지막 게시물의 index 값
	
	// 강사님 코드를 참고하여 Around -> After로 리팩토링
	@After("execution(* kr.co.hotel.manager.controller.*.*Controller.*List(..))")
	public void pageModule(JoinPoint jp) {

		Object[] args = jp.getArgs();
		// 첫번째 인자: Model, 두번째 인자: 페이지값 String cPage
		Model m = (Model) args[0];
		String cPage = (String) args[1];
		
		// Model이 가지고 있는 클래스의 이름을 가져와서 Dao의 선택자로 사용
		// memberList => Dao의 Bean의 이름으로 선택
		String myDao = jp.getSignature().getName();
		System.out.println("myDao=>"+myDao);
		// 결과적으로 Dao는 무조건 이름이 xxList 로 설정되어 있어야 불러 올 수 있다.
		// @Repository("memberList")
		PageListInter pageListInter = applicationContext.getBean(myDao,PageListInter.class);

		try {
			// total값
			totalRecord = pageListInter.getCnt();
			// 총 게시물 수 가져오기
			totalPage = (int) Math.ceil(totalRecord / (double) numPerPage);
			totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
			// 현재 페이지를 요청할 때 파라미터로 현재 페이지 값을 받는다. 1~n
			String s_page = cPage;
			if (s_page != null) {
				nowPage = Integer.parseInt(s_page);
			}
			// begin ~ end 구하는 공식
			beginPerPage = (nowPage - 1) * numPerPage + 1;
			endPerPage = (beginPerPage - 1) + numPerPage;
			// 각 페이지 별 시작 게시물과 마지막 게시물의 index 값을 Map으로 넣어준다.
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("begin", beginPerPage);
			map.put("end", endPerPage);
			List<? extends SuperVO> list = pageListInter.getList(map);
			m.addAttribute("list",list);
			
			int startPage = (int)((nowPage-1)/pagePerBlock)*pagePerBlock+1;
			int endPage = startPage + pagePerBlock - 1;
			if(endPage > totalPage){
				endPage = totalPage;
		    }
			// 페이지값들을 넣어준다.
			m.addAttribute("startPage", startPage);
			m.addAttribute("endPage", endPage);
			m.addAttribute("nowPage", nowPage);
			m.addAttribute("pagePerBlock", pagePerBlock);
			m.addAttribute("totalPage",totalPage);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	
	}
}
