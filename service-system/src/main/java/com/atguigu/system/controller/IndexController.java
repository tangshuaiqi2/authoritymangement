package com.atguigu.system.controller;


import com.atguigu.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

	@PostMapping("/login")
	public Result login(){
		Map<String, String> map = new HashMap<>();
		map.put("token", "admin-token");
		return Result.ok(map);
	}

	@GetMapping("/info")
	public Result info(){
		Map<String, String> map = new HashMap<>();
		map.put("roles", "[admin]");
		map.put("introduction", "i am a super ");
		map.put("avatar", "ss");
		map.put("name","ss");
		return Result.ok(map);
	}
}
