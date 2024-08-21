
package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@EnableFeignClients
@Controller
public class Udpt17ClcApplication{

		public static void main(String[] args) {
			SpringApplication.run(Udpt17ClcApplication.class, args);
		}
	@Autowired
	QuanLyNhanSuClient client;
	
	@RequestMapping(value = "/employee")
	public ModelAndView LayThongTinNhanVien() {
		String info = client.LayThongTinNhanVien();
		return new ModelAndView("nhanvien.html","info",info);
	}
}
