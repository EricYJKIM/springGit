package kh.spring.main;


import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dao.BoardDAO;
import kh.spring.dao.FilesDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.FilesDTO;
import kh.spring.service.BoardService;
import kh.spring.vo.PagingVO;



@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;
		
	@Autowired
	private HttpSession session;
	
	@Autowired
	private FilesDAO fdao;

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
		int result = service.modify(dto);
		return "home";
	}
	
	@RequestMapping("delete")
	public String delete(int board_seq) throws Exception {
		System.out.println("삭제 요청 확인");
		int result = service.delete(board_seq);
		return "home";
	}




	
	@RequestMapping("boardlist")
	public String boardList() {
		return "board/boardlist";
	}
	
	@RequestMapping("boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	@RequestMapping("writeProc")
	public String writeProc(String title, String contents, MultipartFile[] file) throws Exception{

		String realPath = session.getServletContext().getRealPath("files");
		
		service.insert(title,contents,realPath,file); 
		
		
		return "redirect:/";
	}
	
	@RequestMapping("list")
	public String list(PagingVO vo, Model model, String nowPage, String cntPerPage) throws Exception {
			
			int total = service.CountBoard();
			service.Paging(vo, model, nowPage, cntPerPage);
			
			return "board/boardlist";
	}	

	@RequestMapping("detail")
	public String detail(Model model,int board_seq,String oriName, String sysName, HttpServletResponse resp) throws Exception{
		
		System.out.println(board_seq);
		BoardDTO dto = service.detail(board_seq);
		List<FilesDTO> flist = fdao.filesBySeq(board_seq);
		
		System.out.println(flist);
		
//		String filesPath = session.getServletContext().getRealPath("files");
//		File targetFile = new File(filesPath + "/" + sysName);
//
//		oriName = new String(oriName.getBytes(),"ISO_8859-1");
//		
//		
//		resp.setContentType("application/octet-stream; charset=utf8");
//		resp.setHeader("content-Disposition", "attachment;filename=\""+ oriName + "\"");
//
//		try(ServletOutputStream sos = resp.getOutputStream();){
//
//			FileUtils.copyFile(targetFile, sos);
//
//			sos.flush();
//
//		}
		
		model.addAttribute("list",dto);
		model.addAttribute("flist",flist);
		return "board/detail";
	}
	
	

	@ExceptionHandler // 예외가 발생했을 때만,
	public String execeptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}