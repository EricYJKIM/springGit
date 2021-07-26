package kh.spring.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.BoardDAO;
import kh.spring.vo.PagingVO;


@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardDAO bdao;
	private PagingVO vo;
	
	@RequestMapping("list")
	public String list(PagingVO vo, Model model, String nowPage, String cntPerPage) throws Exception {
			
			int total = bdao.CountBoard();
			if (nowPage == null && cntPerPage == null) {
				nowPage = "1";
				cntPerPage = "5";
				nowPage = "1";
			} else if (cntPerPage == null) { 
				cntPerPage = "5";
			}
			vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			model.addAttribute("paging", vo);
			model.addAttribute("viewAll", bdao.SelectBoard(vo));
		return "board/boardlist";
	}
}

