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

	private int nowPage = 1;// ���� ������ ��
	private int nowBlock = 1;// ���� ��
	private int totalRecord = 0;// �� �Խù� ��
	private int numPerPage = 10;// �� ������ �� ������ �Խù� ��
	private int pagePerBlock = 10;// �� �� �� ������ ������ ��. 10 �̻��̸� ���� ��ư ����
	private int totalPage = 0; // ��ü������ �� => totalRecord/numPerPage
	private int totalBlock = 0; // ��ü �� ��
	private int beginPerPage = 0; // �� ������ �� ���� �Խù��� index ��
	private int endPerPage = 0; // �� ������ �� ������ �Խù��� index ��
	
	// ����� �ڵ带 �����Ͽ� Around -> After�� �����丵
	@After("execution(* kr.co.hotel.manager.controller.*.*Controller.*List(..))")
	public void pageModule(JoinPoint jp) {

		Object[] args = jp.getArgs();
		// ù��° ����: Model, �ι�° ����: �������� String cPage
		Model m = (Model) args[0];
		String cPage = (String) args[1];
		
		// Model�� ������ �ִ� Ŭ������ �̸��� �����ͼ� Dao�� �����ڷ� ���
		// memberList => Dao�� Bean�� �̸����� ����
		String myDao = jp.getSignature().getName();
		System.out.println("myDao=>"+myDao);
		// ��������� Dao�� ������ �̸��� xxList �� �����Ǿ� �־�� �ҷ� �� �� �ִ�.
		// @Repository("memberList")
		PageListInter pageListInter = applicationContext.getBean(myDao,PageListInter.class);

		try {
			// total��
			totalRecord = pageListInter.getCnt();
			// �� �Խù� �� ��������
			totalPage = (int) Math.ceil(totalRecord / (double) numPerPage);
			totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
			// ���� �������� ��û�� �� �Ķ���ͷ� ���� ������ ���� �޴´�. 1~n
			String s_page = cPage;
			if (s_page != null) {
				nowPage = Integer.parseInt(s_page);
			}
			// begin ~ end ���ϴ� ����
			beginPerPage = (nowPage - 1) * numPerPage + 1;
			endPerPage = (beginPerPage - 1) + numPerPage;
			// �� ������ �� ���� �Խù��� ������ �Խù��� index ���� Map���� �־��ش�.
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
			// ������������ �־��ش�.
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
