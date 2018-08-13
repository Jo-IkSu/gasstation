package com.hk.ansboard;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.ansboard.dtos.AnsDto;
import com.hk.ansboard.service.IAnsService;

import jdk.nashorn.internal.ir.RuntimeNode.Request;


@Controller
public class HomeController {
	
	@Autowired
	private IAnsService ansService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value = "/boardlist.do", method = RequestMethod.GET)
	public String getAllBoard(Locale locale, Model model) {
		logger.info("글목록보기 {}.", locale);
		List<AnsDto> list=ansService.getAllList();
		model.addAttribute("lists", list );
		return "boardlist";
	}
	
	@RequestMapping(value = "/insertForm.do", method = RequestMethod.GET)
	public String insertForm(Locale locale, Model model) {
		logger.info("글쓰기폼이동 {}.", locale);
		return "insertform";
	}
	
	@RequestMapping(value = "/insertboard.do", method = RequestMethod.POST)
	public String insertBoard(AnsDto dto, Locale locale, Model model) {
		logger.info("글추가하기 {}.", locale);
		boolean isS=ansService.insertBoard(dto);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg","글추가실패");
			return "error";
		}
	}
	@RequestMapping(value = "/boarddetail.do", method = RequestMethod.GET)
	public String boardDetail(AnsDto dto, Locale locale, Model model) {
		logger.info("글상세보기 {}.", locale);
		AnsDto ddto=ansService.getBoard(dto.getSeq());
		model.addAttribute("dto",ddto);
		return "boarddetail";
		}
	
	@RequestMapping(value = "/updateform.do", method = RequestMethod.GET)
	public String updateForm(AnsDto dto, Locale locale, Model model) {
		logger.info("글수정폼이동 {}.", locale);
		AnsDto ddto=ansService.getBoard(dto.getSeq());
		model.addAttribute("dto",ddto);
		return "updateform";
		}
	
	@RequestMapping(value = "/muldel.do")
	public String updateForm(String[]chk, Locale locale, Model model) {
		//파라미터정의 : 배열로 정의하면 여러개의 파라미터를 받을 수 있다
		System.out.println(chk[0]);
		logger.info("글삭제하기 {}.", locale);
		boolean isS=ansService.mulDelboard(chk);
		if(isS) {
			return "redirect:boardlist.do";
		}else {	
			model.addAttribute("msg","글삭제실패");
			return "error";
		}
		
	}
	@RequestMapping(value = "/updateboard.do", method = RequestMethod.POST)
	public String updateBoard(AnsDto dto, Locale locale, Model model) {
		logger.info("글수정하기{}.", locale);
		boolean isS=ansService.updateBoard(dto);
		if(isS) {
			return "redirect:boarddetail.do?seq="+dto.getSeq();
		}else {
			model.addAttribute("msg","글수정실패");
			return "error";
		}	
	}
	@RequestMapping(value = "/replyboard.do", method = RequestMethod.POST)
	public String replyBoard(AnsDto dto, Locale locale, Model model) {
		logger.info("답글달기{}.", locale);
		boolean isS=ansService.replyBoard(dto);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg","답글추가실패");
			return "error";
		}	
	}
	@ResponseBody
	@RequestMapping(value = "/contentAjax.do", method = RequestMethod.POST)
	public Map<String, AnsDto> contentAjax(AnsDto dto, Locale locale, Model model) {
		logger.info("ajax처리:글내용보기{}.", locale);
		AnsDto ddto=ansService.getBoardAjax(dto.getSeq());
		Map<String, AnsDto>map=new HashMap<>();
		map.put("dto", ddto);		
		return map;
	}
	 @RequestMapping(value = "/test.do",method = RequestMethod.GET)
	   public String test(Model model, AnsDto dto) {//전달되는 파라미터를 이름만 일치하면 받아짐
	   
	         return "test";      
	   }
}

