package hello.springmvc.basic;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //private final Logger log = LoggerFactory.getLogger(getClass()); 를 자동으로 해줌.
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass()); //클래스 지정해줌

    @RequestMapping("/log-test")
    public String LogTest(){

        String name = "moon";

        System.out.println("name = " + name);
        
//      log.trace("trace log={}" + name); 연산이 됨 과 파라미터만 넘기는것 과의 차이
        
        log.trace("trace log={}", name);
        log.debug("  debug log={}", name); // 개발 서버
        log.info("  info log={}", name); // 운영 서버
        log.warn("  warn log={}", name);
        log.error("  error log={}", name);

        return "ok";
    }
}
