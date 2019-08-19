package com.lxy.service;

import java.util.List;

import com.lxy.dto.AdPositionDto;
import com.lxy.entity.AdPosition;

public interface AdService {

	List<AdPositionDto> listByPage(String pageName);

}
