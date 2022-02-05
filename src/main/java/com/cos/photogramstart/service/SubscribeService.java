package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	
	@Transactional
	public void subscribe(int fromUserId, int toUserId) {
		subscribeRepository.mSubscribe(fromUserId, toUserId);
	}
	@Transactional
	public void unSubscribe(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}
}
