package com.hk.utils;

public class Util {

	// static : 클래스명.메서드,클래스명.맴버필드
	// non-static : 객체생성후 객체명.메서드, 객체명.멤버필드
	// static/non-static, return/void, 아규면트o/아규먼트x
	
	//&nbsp;&nbsp;<img src="img/img.jpg"/>
	private String arrowNbsp;
	
	
	
	public String getArrowNbsp() {
		return arrowNbsp;
	}


//	글목록에서 ${dto.depth}값을 setArrowNbsp(depth) 아규먼트로 전달
	public void setArrowNbsp(String depth) {
		String nbsp="";
		int depthInt=Integer.parseInt(depth);
		for (int i = 0; i < depthInt; i++) {//depth의 크기만큼&nbsp;를 만들어 준다
			nbsp+="&nbsp;&nbsp;&nbsp;&nbsp;";
			
		}
		this.arrowNbsp=(depthInt>0?nbsp+"<img src='img/arrow.png' alt='답글'/>":"");
	}



	public static String jsForWard(String msg,String url){
		String s="<script type='text/javascript'>"
					+"alert('"+msg+"');"
					+"location.href='"+url+"';"
					+"</script>";
		
		return s;
	}
	
	
}
