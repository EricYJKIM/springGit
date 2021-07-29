package kh.spring.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dao.BoardDAO;
import kh.spring.dao.FilesDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.FilesDTO;
import kh.spring.vo.PagingVO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO dao;


	@Autowired
	private FilesDAO fdao;

	public int modify(BoardDTO dto) {
		return dao.modify(dto);
	}

	public int delete(int id) {
		return dao.delete(id);
	}


	@Transactional
	public void insert(String title,String contents,String realPath, MultipartFile[] file) throws Exception {


		dao.insert(title, contents);
		


		File filesPath = new File(realPath);
		if(!filesPath.exists()) {
			filesPath.mkdir();
		}


		for(MultipartFile tmp : file) {

			int seq = dao.getSeq();
			if(tmp.getSize() > 0) {
				String oriName = tmp.getOriginalFilename();
				String sysName = UUID.randomUUID().toString().replaceAll("-", "")+"_"+oriName;


				tmp.transferTo(new File(filesPath.getAbsolutePath()+"/"+sysName));

				FilesDTO fdto = new FilesDTO(0,oriName, sysName, null,seq);

				if(oriName!=null) {
					System.out.println("파일 이름" + oriName +"DB에 저장됨.");
					fdao.filesInsert(fdto);
				}


			}



		}
		
	}

	public int CountBoard() {
		return dao.CountBoard();
	}

	public int getSeq() {

		return dao.getSeq();
	}


	public BoardDTO detail(int board_seq) throws Exception {
		return dao.detail(board_seq);
	}

	public List<BoardDTO> SelectBoard(PagingVO vo) {
		return dao.SelectBoard(vo);

	}


	public void upload()throws Exception{



	}




	public void Paging(PagingVO vo, Model model, String nowPage, String cntPerPage) {

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
	}

}
