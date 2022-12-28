package com.atguigu.system.exception;

import com.atguigu.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice //捕获异常需要的注解
public class GlobalExceptionHandler {

	//全局异常捕获
	@ExceptionHandler(Exception.class) // 这个表示全部异常都捕获
	@ResponseBody//这个表示以json格式返回（默认为json)
	public Result error(Exception e){
		e.printStackTrace();
		return Result.fail().message("执行了全局异常处理");
	}
	@ExceptionHandler(ArithmeticException.class) //这个表示值捕获特定的异常
	@ResponseBody
	public Result error(ArithmeticException e){
		e.printStackTrace();
		return Result.fail().message("执行了全局异常处理");
	}

	//自定义异常
	@ExceptionHandler(GuiguException.class) //这个表示值捕获特定的异常
	@ResponseBody
	public Result error(GuiguException e){
		e.printStackTrace();
		return Result.fail().code(e.getCode()).message(e.getMsg());
	}

}
