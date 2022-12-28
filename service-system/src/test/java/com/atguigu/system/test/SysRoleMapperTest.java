package com.atguigu.system.test;


import com.atguigu.model.system.SysRole;
import com.atguigu.system.mapper.SysRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Test
	public void testDelete(){
		QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
		wrapper.eq("role_name", "管理员");
		sysRoleMapper.delete(wrapper);
	}

	@Test
	public void testSelect(){
		QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
//		wrapper.eq("role_name","用户管理员");
		wrapper.like("role_name","管理员");
		System.out.println(sysRoleMapper.selectList(wrapper));
	}

	@Test
	public  void deleteId(){
		int i = sysRoleMapper.deleteById(2);
		System.out.println(i);
	}

	@Test
	public void update(){
		SysRole sysRole = sysRoleMapper.selectById(1);

		sysRole.setDescription("系统管理员尚硅谷");

		sysRoleMapper.updateById(sysRole);
	}

	@Test
	public void add(){
		SysRole sysRole = new SysRole();
		sysRole.setRoleName("测试角色2");
		sysRole.setRoleCode("testaManger");
		sysRole.setDescription("test");
		int rows = sysRoleMapper.insert(sysRole);
		System.out.println(rows);
	}

	@Test
	public void findAll(){
		List<SysRole> sysRoles = sysRoleMapper.selectList(null);
		System.out.println(sysRoles.toString());
	}
}
