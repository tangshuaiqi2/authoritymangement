package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理的接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;


	@GetMapping("/get/{id}")
	public Result get(@PathVariable Long id) {
		SysRole role = sysRoleService.getById(id);
		return Result.ok(role);
	}

	@DeleteMapping("batchRemove")
	public Result batchRemove(@RequestBody List<Long> ids){
		boolean save = sysRoleService.removeByIds(ids);
		if(save){
			return Result.ok();
		}
		return Result.fail();
	}

	@PostMapping("update")
	public Result updateRole(@RequestBody SysRole sysRole){
		boolean save = sysRoleService.updateById(sysRole);
		if(save){
			return Result.ok();
		}
		return Result.fail();
	}




	@PostMapping("findRoleById/{id}")
	public Result findRoleById(@PathVariable Long id){
		SysRole save = sysRoleService.getById(id);
		return Result.ok(save);
	}


	//@RequestBody不用使用get提交
	//传递json格式数据，吧json格式数据封装到对象里面
	@PostMapping("save")
	public Result saveRole(@RequestBody SysRole sysRole){
		boolean save = sysRoleService.save(sysRole);
		if(save){
			return Result.ok();
		}
		return Result.fail();

	}

	@GetMapping("{page}/{limit}")
	public Result findPageQueryRole(@PathVariable Long page,
									@PathVariable Long limit,
									SysRoleQueryVo sysRoleQueryVo){
		//创建page对象
		Page<SysRole> pageParam = new Page<>(page, limit);

		IPage<SysRole> pageMode = sysRoleService.selectPage(pageParam, sysRoleQueryVo);

		return Result.ok(pageMode);

	}

	@ApiOperation("逻辑删除的接口")
	@DeleteMapping("remove/{id}")
	public Result removeRole(@PathVariable Long id){
		boolean b = sysRoleService.removeById(id);
		if(b){
			return Result.ok();
		}
		return Result.fail();
	}
	@ApiOperation("查询所有记录的接口")
	@GetMapping("findAll")
	public Result findAllRole(){

		List<SysRole> list = sysRoleService.list();
		return Result.ok(list);
	}

}
