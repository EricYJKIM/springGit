
package kh.spring.main;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;



@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberDAO dao;

	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("signup")
	public String signup() {
		return "member/signup";
	}
	
	
	@RequestMapping("signupProc")
	public String signupProc(MemberDTO dto) {
		
		
		dao.insert(dto);
		
		
		return "redirect:/";
	}
	
	
	@RequestMapping("loginProc")
	public String loginProc(String id, String pw) {
		
		int result= dao.login(id,pw);
		
		if(result>0) {
			session.setAttribute("loginID",id);
		}
		return "redirect:/";
	}
	
	
	//로그아웃
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();

		return "redirect:/";
	}
	
	

	@ResponseBody
	@RequestMapping(value="duplCheck",produces="text/html;charset=utf8")
	public String duplCheck(String id) {
		
		int result = dao.idDuplCheck(id);
		
		return String.valueOf(result);
	}
	
	

	//회원탈퇴
	@RequestMapping("memberOut")
	public String memberOut() {
		String id = (String)session.getAttribute("loginID");
		dao.memberOut(id);
		session.invalidate();
		return "redirect:/";
		
	}
}