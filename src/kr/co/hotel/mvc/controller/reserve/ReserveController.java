package kr.co.hotel.mvc.controller.reserve;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.velocity.tools.config.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.hotel.mvc.dao.ReserveDaoInter;
import kr.co.hotel.mvc.service.ReserveService;
import kr.co.hotel.vo.CannotReserveParaVO;
import kr.co.hotel.vo.CannotReserveVO;
import kr.co.hotel.vo.ReserveDetailParaVO;
import kr.co.hotel.vo.ReserveListParaVO;
import kr.co.hotel.vo.ReserveVO;
import kr.co.hotel.vo.RoomVO;


@Controller
@RequestMapping(value = "/reserve")
public class ReserveController {
	
	@Autowired
	private ReserveDaoInter reserveDaoInter;
		
	private int nowPage = 1; //���� ������ ��
	private int nowBlock = 1; //���� ��
	private int totalRecord = 0; //�� �Խù� ��
	private int numPerPage = 10; //�� ������ �� ������ �Խù� ��
	private int pagePerBlock = 5; // �� �� �� ������ ������ ��
	private int totalPage = 0; //��ü ������ �� => totalRecord ������ numPerPage
	private int totalBlock = 0; //��ü �� ��
	private int beginPerPage = 0; //�� ������ �� ���� �Խù��� index��
	private int endPerPage = 0; //�� ������ �� ������ �Խù��� index��	
	
	//ȸ�� ���� Ȯ�� �� �ش� ȸ���� �Խù� �޾ƿ��� ����¡ ó��
	@RequestMapping(value = "/reservelist")
	public String reservelistF(HttpServletRequest request, Model m, @RequestParam(defaultValue = "1") int cPage) {
		//session�� ���̵� ȹ��
		HttpSession session = request.getSession(false);
		String mid = (String) session.getAttribute("sessionid");
		if(mid != null) {
			ReserveListParaVO lvo = new ReserveListParaVO();
			lvo.setMid(mid);
			totalRecord = reserveDaoInter.reservecnt(lvo);
			//System.out.println(totalRecord);
			totalPage = (int) Math.ceil(totalRecord / (double)numPerPage);
			
			nowPage = cPage;
			
			beginPerPage = (nowPage-1) * numPerPage + 1;
			endPerPage = (beginPerPage-1) + numPerPage;
						
			lvo.setBegin(beginPerPage);
			lvo.setEnd(endPerPage);
			
			
			List<ReserveVO> list = reserveDaoInter.reservelist(lvo);
			
			
			m.addAttribute("rlist", list);
			
			int startPage = (int)((nowPage-1)/pagePerBlock)*pagePerBlock+1;
			int endPage = startPage + pagePerBlock -1;
			if(endPage > totalPage) {
				endPage = totalPage;
			}
			
			m.addAttribute("startPage", startPage);
			m.addAttribute("endPage", endPage);
			m.addAttribute("nowPage", nowPage);
			m.addAttribute("pagePerBlock", pagePerBlock);
			m.addAttribute("totalPage", totalPage);
			return "member/reserve/reserveList";
		}else {
			return "redirect:/member/";
		}
	}	
	
	//reserveForm���� �Ѿ�� ������ reserve���̺� insert�ϰ� �ش� ������ rstate�� ���� �Ұ������� ����
	@PostMapping(value = "/addreserve")
	public String name(ReserveVO vo) {
		reserveDaoInter.addreserve(vo);		
		return "redirect:/member/";
	}
	
	//���� ���� �󼼺��⿡�� �����ϱ� ��ư�� ������ ������ �޾Ƽ� reserveForm���� ����
	@RequestMapping(value = "/reservepage")
	public String reservepageF(Model m, int rnum, int rprice) {		
		m.addAttribute("rnum", rnum);
		m.addAttribute("rprice", rprice);
		return "member/reserve/reserveForm2";
	}			
	
	@ResponseBody
	@GetMapping(value = "/cannotreserve")
    public List<CannotReserveVO> cannotreservelist(String today, int rnum) {
        CannotReserveParaVO vo = new CannotReserveParaVO();
        vo.setToday(today);
        vo.setRnum(rnum);
        System.out.println(vo.getToday());
        System.out.println(vo.getRnum());
		List<CannotReserveVO> list = reserveDaoInter.cannotreserve(vo);
		//fullcalender�� event���� ������ ��¥�� ǥ�õǴ� ������ �Ϸ� ������ ǥ�õǾ
        //���Ƿ� ������ ��¥�� +1�Ͽ� ������
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for(CannotReserveVO e : list) {
            Date end2;
            String end3;
            try {
                end2 = df.parse(e.getEnd());
                cal.setTime(end2);
                cal.add(Calendar.DATE, 1);
                end3 = df.format(cal.getTime());
                e.setEnd(end3);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return list;
    }
	
	@GetMapping(value = "/reservecancel")
	public String reservecancel(HttpServletRequest request, Model m, int rvnum) {
		HttpSession session = request.getSession(false);
		String mid = (String) session.getAttribute("sessionid");
		int rvnum2 = rvnum;
		if(mid != null) {
			ReserveDetailParaVO vo = new ReserveDetailParaVO();
			vo.setMid(mid);
			vo.setRvnum(rvnum2);
			ReserveVO vo1 = reserveDaoInter.reservedetail(vo);
			m.addAttribute("dlist", vo1);			
			
			return "member/reserve/reserveCancel";
		}else {
			return "redirect:/member/";
		}		
	}
	
	@RequestMapping(value = "/reservercancelcom")
	public String reservecancelcom(ReserveVO vo) {
		reserveDaoInter.reservecancel(vo);		
		return "redirect:/member/";
	}
	
}