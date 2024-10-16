package com.xkcoding.helloworld;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>
 * SpringBoot启动类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-09-28 14:49
 */
@SpringBootApplication
@RestController
@Slf4j
public class SpringBootDemoHelloworldApplication {

    public static void main(String[] args) throws UnknownHostException {
      ConfigurableApplicationContext context = SpringApplication.run(SpringBootDemoHelloworldApplication.class, args);
      // 获取app的 访问地址
      Environment environment = context.getBean(Environment.class);
      //environment.getProperty("server.servlet.context-path") 应用的上下文路径，也可以称为项目路径
      String host = InetAddress.getLocalHost().getHostAddress();
      String port = environment.getProperty("server.port");
      String path = environment.getProperty("server.servlet.context-path");
      if (StringUtils.isEmpty(path)) {
        path = "";
      }
      log.info("\n访问地址：http://{}:{}{}", host, port, path);
    }

    /**
     * Hello，World
     *
     * @param who 参数，非必须
     * @return Hello, ${who}
     */
    @GetMapping("/hello")
    public String sayHello(@RequestParam(required = false, name = "who") String who) {
        if (StrUtil.isBlank(who)) {
            who = "World";
        }
        return StrUtil.format("Hello, {}!", who);
    }
}
