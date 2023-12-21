package com.hocafe.web;


import com.hocafe.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoContoller {

    private final ObjectProvider<MyLogger> myLoggerProvicer;
    private final LogDemoService demoService;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        // ObjecyProvider
        MyLogger myLogger = myLoggerProvicer.getObject();
        // url 받기
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        // log 찍기
        myLogger.log("logDemoController test");

        // uuid 찍기
        demoService.logic("testId");
        return "OK";
    }

}
