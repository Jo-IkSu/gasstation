package com.hk.ansboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.ansboard.daos.IAnsDao;
import com.hk.ansboard.dtos.AnsDto;

@Service
public class AnsServiceImp implements IAnsService{
	
	@Autowired
	private IAnsDao ansDao;
	
	@Override
	public List<AnsDto> getAllList() {
		return ansDao.getAllList();
	}

	@Override
	@Transactional
	public AnsDto getBoard(int seq) {
		ansDao.readCount(seq);
		return ansDao.getBoard(seq);
	}

	@Override
	public boolean updateBoard(AnsDto dto) {
		return ansDao.updateBoard(dto);
	}

	@Override
	public boolean insertBoard(AnsDto dto) {
		return ansDao.insertBoard(dto);
	}

	@Override
	@Transactional
	public boolean mulDelboard(String[] seq) {
		return ansDao.mulDelboard(seq);
	}
	
	@Override
	@Transactional
	public boolean replyBoard(AnsDto dto) {
		int count=0;
		ansDao.replyBoardUpdate(dto);
		count=ansDao.replyBoardInsert(dto);
		return count>0?true:false;
	}

	@Override
	public boolean readCount(int seq) {
		return false;
	}

	@Override
	public AnsDto getBoardAjax(int seq) {
		return ansDao.getBoardAjax(seq);
	}

}
