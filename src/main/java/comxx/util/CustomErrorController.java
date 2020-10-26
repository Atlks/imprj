package comxx.util;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

//this is seconde to process ,first is exception handler
//@RestController 
//public class CustomErrorController  implements ErrorController {
// 
//    @GetMapping("/error")
//    public Object handleError(HttpServletRequest request) {
//        String errorPage = "error"; // default
//    //    RequestDispatcher.
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//         
//       Map  m=Maps.newHashMap();
//       m.put("RequestDispatcher", request);
//        
//        return m;
//    }
//     
//    public String getErrorPath() {
//        return "/error";
//    }
//}