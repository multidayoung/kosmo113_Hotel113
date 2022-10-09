package kr.co.hotel.mvc.aop;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hotel.mvc.dao.AdminDaoInter;
import kr.co.hotel.mvc.dao.MemberDaoInter;
import kr.co.hotel.vo.AdminVO;
import kr.co.hotel.vo.MyLoginLoggerDTO;

@Component
@Aspect
public class LoginAdvice {
   
   private Map<String,String> userAgent;
   
   @Autowired
   private MemberDaoInter memberList;
   @Autowired
   private AdminDaoInter adminDaoInter;
   
   LoginAdvice(){
      userAgent=new HashMap<String, String>();
   }
   
   @Around("execution(* kr.co.hotel.mvc.controller.login.LoginController.loginf*(..))")
   public ModelAndView loginLogger(ProceedingJoinPoint jp) {
      System.out.println("����!");
      Object[] fd = jp.getArgs(); //�޼����� ���ڰ�
      ModelAndView rpath = null;
      String methodName = jp.getSignature().getName();
      System.out.println("methodName : "+methodName);
      if (methodName.equals("loginfProcess")) { //loginó���� �Ǿ��� �� ����ǵ��� Advice�� ����
         //�α����� ���� �� ���� �޾Ƽ� ���� ������ ���̽��� ����
         MyLoginLoggerDTO vo = new MyLoginLoggerDTO(); //login����
         
         try {
            rpath = (ModelAndView) jp.proceed();
            if (fd[0] instanceof HttpSession && fd[1] instanceof HttpServletRequest) {
               HttpSession session = (HttpSession) fd[0];
               HttpServletRequest request = (HttpServletRequest) fd[1];
               String uid = (String) session.getAttribute("sessionid");
               userAgent.put(uid, (String) fd[3]);
               System.out.println();
               System.out.println("input uid : " + uid);
               System.out.println("input key : "+userAgent.containsKey(uid));
               System.out.println("input uagent : "+userAgent.get(uid));
               if (uid != null) {
                  vo.setIdn(uid);
                  vo.setStatus("login");
                  vo.setReip(request.getRemoteAddr());
                  vo.setUagent(userAgent.get(uid));
                  //dao ����
                  memberList.addLoginLogging(vo);
               }
            }
         } catch (Throwable e) {
            e.printStackTrace();
         }
      }else if (methodName.equals("loginfoutProcess")) { //logout
         MyLoginLoggerDTO vo = new MyLoginLoggerDTO(); //logout����
         if (fd[0] instanceof HttpSession && fd[1] instanceof HttpServletRequest) {
            HttpSession session = (HttpSession) fd[0];
            HttpServletRequest request = (HttpServletRequest) fd[1];
            String uid = (String) session.getAttribute("sessionid");
            System.out.println("uid : "+uid);
            boolean a = false;
            for(AdminVO d : adminDaoInter.getAllList()) {
               if(d.getAid()!=session.getAttribute("sessionid")) {
                  a=true;
               }
            }
            if(a==false) {
            if (uid != null) {
               vo.setIdn(uid);
               vo.setStatus("logout");
               vo.setReip(request.getRemoteAddr());
               System.out.println("userAgent : "+userAgent.get(uid));
               vo.setUagent(userAgent.get(uid));
               
               //dao ����
               memberList.addLoginLogging(vo);
            }
            }
         }
         try {
            rpath = (ModelAndView) jp.proceed();
         } catch (Throwable e) {
            e.printStackTrace();
         }
      }
      System.out.println("Test!");
      return rpath;
   }
   
}