package com.atguigu.system.controller;


import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.SysUserQueryVo;
import com.atguigu.system.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-12-29
 */
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;

	@GetMapping("/{page}/{limit}")
	public Result list(@PathVariable Long page,
					   @PathVariable long limit,
					   SysUserQueryVo sysUserQueryVo){
		Page<SysUser> pageParam = new Page<>(page, limit);
		IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, sysUserQueryVo);
		return Result.ok(pageModel);
	}

	@PostMapping("save")
	public Result save(@RequestBody SysUser user){
		boolean save = sysUserService.save(user);
		if(save){
			return Result.ok();
		}
		return Result.fail();
	}

	@GetMapping("getUser/{id}")
	public Result getUser(@PathVariable String id){
		SysUser byId = sysUserService.getById(id);
		return Result.ok(byId);

	}
	@PostMapping("update")
	public Result update(@RequestBody SysUser user){
		boolean save = sysUserService.updateById(user);
		if(save){
			return Result.ok();
		}
		return Result.fail();
	}

	@DeleteMapping("remove/{id}")
	public Result remove(@PathVariable String id){
		boolean save = sysUserService.removeById(id);
		if(save){
			return Result.ok();
		}
		return Result.fail();
	}

}

