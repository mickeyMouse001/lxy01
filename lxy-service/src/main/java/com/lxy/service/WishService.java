package com.lxy.service;

import java.util.List;

import com.lxy.entity.Wish;

public interface WishService {

	List<Wish> list(int state);

}
