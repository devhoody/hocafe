package com.hocafe.scan;

import com.hocafe.AutoAppConfig;
import com.hocafe.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService bean = ac.getBean(MemberService.class);
        System.out.println("bean = " + bean);
        Assertions.assertThat(bean).isInstanceOf(MemberService.class);
    }
}
