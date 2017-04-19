package com.hospital.web.service;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.web.domain.Chart;
import com.hospital.web.mapper.Mapper;

@Service
public class ChartService {
	private static final Logger logger = LoggerFactory.getLogger(ChartService.class);
	@Autowired Mapper mapper;
	@Transactional //rollback 기능 포함 ( 결제할때 유용함 )
	@SuppressWarnings("unchecked")
	public List<Chart> chartList(Map<?,?> paramMap) throws Exception{
		IGetService service = (map)->mapper.findChart(map);
		List<Chart> list = (List<Chart>) service.execute(paramMap);
		logger.info("ChartService-chartList() {} !!", list);
		return list;
	}
}