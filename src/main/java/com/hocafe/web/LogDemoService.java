package com.hocafe.web;

import com.hocafe.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String testId) {
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log(testId);
    }
}
