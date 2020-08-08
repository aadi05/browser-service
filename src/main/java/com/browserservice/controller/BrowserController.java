package com.browserservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.browserservice.model.CurrentUrl;
import com.browserservice.service.impl.BrowserServiceImpl;

@RestController
public class BrowserController {
	
	@Autowired
	BrowserServiceImpl service;
	
	@GetMapping("start")
	public void startBrowser(@RequestParam("browser") String browser, @RequestParam("url") String url) {
		
		service.startBrowser(browser, url);
	}
	
	@GetMapping("stop")
	public void stopBrowser(@RequestParam("browser") String browser) {
		
		service.stopBrowser(browser);
		
	}
	
	@GetMapping("cleanup")
	public void cleanBrowser(@RequestParam("browser") String browser) {
		
		service.cleanBrowser(browser);
	}
	
	@GetMapping("geturl")
	public CurrentUrl getUrlBrowser(@RequestParam("browser") String browser) {
		
		return service.getUrl(browser);
	}
	
	
}
