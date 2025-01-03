// Name: HAMDI MOHAMMED YUNIS
// ID: UGR/8929/14
// Stream: Software

package com.itsc.OnineBookStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ServletComponentScan(basePackages = {"com.itc.OnlineBookStore", "com.ioc.QSevenOnwards"})
public class OnlineBookstore {

	public static void main(String[] args) {
		// LombokTestClass lombokTestClass = new LombokTestClass();
		// lombokTestClass.setEmail("hamdim801@gmail.com");
		// System.out.println(lombokTestClass.getEmail());

		SpringApplication.run(OnlineBookstore.class, args);
	}

}
