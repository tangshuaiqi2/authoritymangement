package com.atguigu.system.test;


import com.atguigu.model.system.SysRole;
import com.atguigu.system.mapper.SysRoleMapper;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.javaws.IconUtil;
import com.sun.media.jfxmediaimpl.HostUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysRoleServiceImplTest {

	@Autowired
	private SysRoleService sysRoleService;


	@Test
	public void findAll(){
		List<SysRole> list = sysRoleService.list();
		System.out.println(list);
	}
}
