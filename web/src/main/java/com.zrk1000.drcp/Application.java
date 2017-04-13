package com.zrk1000.drcp;


import com.zrk1000.drcp.model.User;
import com.zrk1000.drcp.serviceimpl.UserService;
import com.zrk1000.drpc.annotation.ServiceScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//@MapperScan("com.zrk1000.proxytest.mapper")
//@ImportResource("spring/spring-context.xml")
//@ComponentScan("")
@ServiceScan(basePackages = "com.zrk1000.drcp.serviceimpl",rpcHandleBeanRef="stormLocalDrpcHandle")
@RestController
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Autowired
	private UserService userService;


	@RequestMapping("/user")
	public User user(@RequestParam(required = false) String name){

		User result = null;
		try {
			result = userService.getUser(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
