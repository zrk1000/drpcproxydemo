package com.zrk1000.drpc;


import com.zrk1000.drpc.model.User;
import com.zrk1000.drpc.rpc.drpc.StormRemoteDrpcHandle;
import com.zrk1000.drpc.serviceimpl.UserService;
import com.zrk1000.drpc.annotation.ServiceScan;
import com.zrk1000.drpc.rpc.RpcHandle;
import com.zrk1000.drpc.rpc.drpc.StormLocalDrpcHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//@MapperScan("com.zrk1000.proxytest.mapper")
//@ImportResource("spring/spring-context.xml")
//@ComponentScan("")
@RestController
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		System.out.println(applicationContext.getBean("stormDrpcHandle"));
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
