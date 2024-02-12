package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);


        response.getWriter().write("ok");
    }

    @ResponseBody //이걸 안적어주면 return 값이 view 조회를 진행함
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username")String memberName,
            @RequestParam("age") int memberAge){

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody //이걸 안적어주면 return 값이 view 조회를 진행함
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, //변수 이름을 동일하게 설정하면 따로 설정 안해도됨.
            @RequestParam int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody //이걸 안적어주면 return 값이 view 조회를 진행함
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){ // 요청 파라미터 이름과 동일하면 가능
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody //이걸 안적어주면 return 값이 view 조회를 진행함
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, //(required = ture)면 파라미터 값이 다 있어야 오류 안남 없으면 오류
            @RequestParam(required = false) Integer age){ //Integer은 값이 null이여도 오류 안남 int는 오류

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody //이걸 안적어주면 return 값이 view 조회를 진행함
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, //값이 없으면 기본값을 guest로 하겠다. null 이거나 "" 이여도
            @RequestParam(required = false, defaultValue = "-1") int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody //이걸 안적어주면 return 값이 view 조회를 진행함
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeA1(@ModelAttribute HelloData helloData){ // @ModelAttribute를 사용하면 HelloData 객체가 생성되고 요청 파라미터 값도 모두 들어가 있다.
        log.info("username={}, age={}", helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeA2(HelloData helloData){ // @ModelAttribute 생략 가능 그치만 @RequestParam도 생략 가능하니 혼란 발생!!!
        log.info("username={}, age={}", helloData.getUsername(),helloData.getAge());
        return "ok";
    }
    /**
     *  스프링은 해당 생략 시 다음과 같은 규칙을 적용함
     *  String, int, Integer 같은 단순 타입 = @RequestParam
     *  나머지 = @ModelAttribute (argument resolver 로 지정해둔 타입 외 ex) HttpServletResponse ...)
     *  직접 만드는 객체들은 @ModelAttribute로 처리 가능
     */
}
