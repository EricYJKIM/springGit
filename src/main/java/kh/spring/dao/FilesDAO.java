package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.FilesDTO;

@Repository
public class FilesDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	public int filesInsert(FilesDTO dto)throws Exception{
		String sql= "insert into files values(files_seq.nextval,?,?,sysdate,?)";
		return jdbc.update(sql, dto.getOriName(), dto.getSysName(), dto.getParent());
		
		}
	
	public List<FilesDTO> filesBySeq(int board_seq) throws Exception {

		String sql = "select * from files where parent = ?";
		
		return jdbc.query(sql, new RowMapper<FilesDTO>() {
			
			@Override
			public FilesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				FilesDTO dto = new FilesDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setOriName(rs.getString("oriName"));
				dto.setSysName(rs.getString("sysName"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setParent(rs.getInt("parent"));
				
				return dto;
			}
			
		},board_seq);


	}
	
}
