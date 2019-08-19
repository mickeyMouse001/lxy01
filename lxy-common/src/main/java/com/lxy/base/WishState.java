package com.lxy.base;

public interface WishState {

	//0-未开启，1-进行中，2-已结束
	int STATE_INIT=0;
	int STATE_RUNNING=1;
	int STATE_FINISHED=2;
}
