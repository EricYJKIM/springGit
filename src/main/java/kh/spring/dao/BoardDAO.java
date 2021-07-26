package kh.spring.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;
import kh.spring.vo.PagingVO;

@Repository
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private HttpSession session;

	// 수정
	public int modify(BoardDTO dto) {
		String sql = "update board set title=?, contents=? where board_seq=?";
		return jdbc.update(sql, dto.getTitle(), dto.getContents(), dto.getBoard_seq());
	}
	// 삭제
	public int delete(int board_seq) {
		String sql = "delete from board where board_seq = ?";
		return jdbc.update(sql, board_seq);
	}



	@Autowired
	private BoardDAO dao;
	
	
	public int insert(String title, String contents) throws Exception{
		String writer = (String) session.getAttribute("loginID");
		String sql = "insert into board values(board_seq.nextval,?,?,?,sysdate,0)";
		return jdbc.update(sql,writer,title,contents);}

	// 새로 작성한 글에 대한 seq ----------------------------------------------------
	public int getSeq() {
		String sql ="select board_seq.nextval from dual";
		return jdbc.queryForObject(sql, Integer.class);
	}

	// 게시판 내용 화면에 뿌리기 -----------------------------------------------------
	public BoardDTO detail(int board_seq) throws Exception {
		String sql ="select * from board where board_seq=?";
		return jdbc.queryForObject(sql, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_seq(rs.getInt("board_seq"));
				dto.setId(rs.getNString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getDate("write_date"));
				dto.setView_count(rs.getInt("view_count"));
				return dto;
			}
		},board_seq);

	}

	// 조회수 출력 ---------------------------------------------------------------
	public int view_count(int board_seq) {
		String sql="update board set view_count = view_count+1 where board_seq=?";
		return jdbc.queryForObject(sql,Integer.class, board_seq);

	}

	// xss 공격방어 --------------------------------------------------------------
	public String XSSFilter(String target) {
		if(target!=null){
			target = target.replaceAll("<","&lt;");   
			target = target.replaceAll(">","&gt;");      
			target = target.replaceAll("&","&amp;");      
		}
		return target;
	}

	// XSSFilter 역으로 다시 해서 화면에 뿌리기 ---------------------------------------
	public String ReXSSFilter(String target) {
		if(target!=null){
			target = target.replaceAll("&lt;","<");   
			target = target.replaceAll("&gt;",">");      
			target = target.replaceAll("&amp;","&");      
		}
		return target;
	}



	// Board List 화면에 뿌리기 ---------------------------------------
	public List<BoardDTO> List() throws Exception{
		String sql ="select * from board";

		return jdbc.query(sql, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

				BoardDTO dto = new BoardDTO();
				dto.setBoard_seq(rs.getInt("board_seq"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setView_count(rs.getInt("view_count"));
				dto.setWrite_date(rs.getDate("write_date"));
				return dto;
			}
		});

	}

	//------------------ Paging --------------------------------------

	// 총 게시글 개수
	public int CountBoard() {
		String sql ="select count(*) from board";
		return jdbc.queryForObject(sql, Integer.class);
	}


	//페이징 처리 후 게시글 조회
	public List<BoardDTO> SelectBoard(PagingVO vo){
		String sql= "select * from"
				+ "(select"
				+ "    row_number() over(order by board_seq desc) rnum,"
				+ "    board_seq, title, id, write_date, view_count"
				+ "    from board)"
				+ "    where rnum between ? and ?"; //임의값 넣어 놨음 

		return jdbc.query(sql, new RowMapper<BoardDTO>() { //여기 처리 어떻게 해야 될지 모르겠음... ㅠ
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

				BoardDTO dto = new BoardDTO();
				dto.setBoard_seq(rs.getInt("board_seq"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setView_count(rs.getInt("view_count"));
				dto.setWrite_date(rs.getDate("write_date"));
				return dto;
			}
		},vo.getStart(), vo.getEnd());
	}
}