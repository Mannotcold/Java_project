package com.example.demo;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("quanlynhansu")
public interface QuanLyNhanSuClient {
	@GetMapping(value = "/employee")
	String LayThongTinNhanVien();
	
	@RequestMapping(value = "/add")
	void ThemNhanVien();
}