package com.hk.ansboard.service;

import java.util.List;

import com.hk.ansboard.dtos.AnsDto;

public interface IAnsService {
	public List<AnsDto> getAllList();
	public AnsDto getBoard(int seq);
	public boolean updateBoard(AnsDto dto);
	public boolean insertBoard(AnsDto dto);
	
	public boolean mulDelboard(String[] seq);
	
	//답글 추가
	public boolean replyBoard(AnsDto dto);
	
	public boolean readCount(int seq);
	public AnsDto getBoardAjax(int seq);
}
