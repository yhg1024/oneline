package com.controller;

import com.repositories.MemberRepository;

public class MemberController {
	
	private final MemberRepository memberRepository;
		
	}
	
	/**
     * 아이디 중복 여부 체크
     */
    @GetMapping("/userId_dup_check")
    public JSONData<Object> duplicateIdCheck(@RequestParam("userId") String userId) {
        boolean isExists = memberRepository.existsByUserId(userId);

        JSONData<Object> data = new JSONData<>() ;
        data.setSuccess(isExists);

        return data ;
    }
}
