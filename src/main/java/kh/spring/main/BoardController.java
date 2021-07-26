package kh.spring.main;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.vo.PagingVO;



@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private HttpSession session;

	//수정화면 이동
	@RequestMapping("modifyForm")
	public String modifyForm() {
		System.out.println("수정 화면 전환");
		return "board/writeModify";
	}
	
	//수정
	@RequestMapping("modifyProc")
	public String modify(BoardDTO dto) throws Exception {
		System.out.println("수정 요청 확인");
		int result = dao.modify(dto);
		return "home";
	}
	
	@RequestMapping("delete")
	public String delete(int board_seq) throws Exception {
		System.out.println("삭제 요청 확인");
		int result = dao.delete(board_seq);
		return "home";
	}

	private PagingVO vo;
	
	@RequestMapping("boardlist")
	public String boardList() {
		return "board/boardlist";
	}
	
	@RequestMapping("boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	@RequestMapping("writeProc")
	public String writeProc(String title, String contents) throws Exception{
//		String id = (String)session.getAttribute("loginID");
		dao.insert(title,contents);
		return "redirect:/";
	}
	
	@RequestMapping("list")
	public String list(PagingVO vo, Model model, String nowPage, String cntPerPage) throws Exception {
			
			int total = dao.CountBoard();
			if (nowPage == null && cntPerPage == null) {
				nowPage = "1";
				cntPerPage = "5";
				nowPage = "1";
			} else if (cntPerPage == null) { 
				cntPerPage = "5";
			}
			vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			model.addAttribute("paging", vo);
			model.addAttribute("viewAll", dao.SelectBoard(vo));
		return "board/boardlist";
	}	

	@RequestMapping(value="detail" ,method=RequestMethod.GET)
	public String detail(Model model,int board_seq) throws Exception{
		BoardDTO dto = dao.detail(board_seq);
		model.addAttribute("list",dto);
		return "board/detail";
	}

	@ExceptionHandler // 예외가 발생했을 때만,
	public String execeptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}