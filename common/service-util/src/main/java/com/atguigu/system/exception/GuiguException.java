package com.atguigu.system.exception;

import com.atguigu.common.result.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuiguException extends RuntimeException{
	Integer code;
	String msg;


}
