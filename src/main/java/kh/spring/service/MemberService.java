package kh.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;


@Service
public class MemberService {

	
	
	
	
	@Autowired
	private MemberDAO dao;
	
	public int insert(MemberDTO dto) {
		return dao.insert(dto);
		
	}
	
	public int login(String id,String pw) {
		
		Map<String,String> param = new HashMap<>();
		param.put("id", id);
		param.put("pw", pw);
		
		return dao.login(param);
	}
	
	
	public int idDuplCheck(String id) {
		return dao.idDuplCheck(id);
	}
	
	
	public int memberOut(String id) {
		return dao.memberOut(id);
	}
	
	
}
