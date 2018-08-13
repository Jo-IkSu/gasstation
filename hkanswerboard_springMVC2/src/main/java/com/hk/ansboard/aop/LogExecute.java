package com.hk.ansboard.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExecute {
	
	public void before(JoinPoint join) {
		Logger log=LoggerFactory.getLogger(join.getTarget()+"");
		log.info("시작");
	}
	
	public void afterReturning(JoinPoint join) {
		Logger log=LoggerFactory.getLogger(join.getTarget()+"");
		log.info("끝");
		for (int i = 0; i < join.getArgs().length; i++) {
			log.info(join.getArgs()[i]+"");
		}
	}
	public void daoError(JoinPoint join) {
		Logger log=LoggerFactory.getLogger(join.getTarget()+"");
		log.info("에러!!!"+join.getArgs());
		for (int i = 0; i < join.getArgs().length; i++) {
			log.info(join.getArgs()[i]+"");
		}
	}
}
