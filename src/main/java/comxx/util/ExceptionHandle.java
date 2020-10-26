package comxx.util;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
 

@ControllerAdvice
public class ExceptionHandle {
@ExceptionHandler(value = Exception.class)
@ResponseBody
public Object handle(Exception e) {
	//ExceptionHandle
//    if (e instanceof GirlException) {
//        GirlException girlException = (GirlException) e;
//        return ResultUtil.error(girlException.getCode(), girlException.getMessage());
//    } else {
	
	  Map  m=Maps.newHashMap();
      m.put("e",  e);
        return  m;
        		//ResultUtil.error(-1,"未知错误");
    }
 
}
 
