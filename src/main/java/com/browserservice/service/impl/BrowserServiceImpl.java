package com.browserservice.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;

import com.browserservice.model.CurrentUrl;
import com.browserservice.service.BrowserService;

@Service
public class BrowserServiceImpl implements BrowserService{
	
	private final String username = "Acer";
	
	private String chromeCacheLocation = "C:\\Users\\"+username+"\\AppData\\Local\\Google\\Chrome\\User Data\\Default";
	private String firefoxCacheLocation = "C:\\Users\\"+username+"\\AppData\\Local\\Mozilla\\Firefox\\Profiles";
	
	private static String chromeCurrentTab = null;
	private static String firefoxCurrentTab = null;
	
	@Override
	public void startBrowser(String browser, String url) {
		if(browser.equals("chrome")){
			Runtime run = Runtime.getRuntime();
            try {
				run.exec(new String[]{"cmd", "/c","start chrome "+url});
				chromeCurrentTab = url;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			Runtime run = Runtime.getRuntime();
			try {
				run.exec(new String[]{"cmd", "/c","start firefox "+url});
				firefoxCurrentTab = url;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void stopBrowser(String browser) {
		if(browser.equals("chrome")) {
			Runtime run = Runtime.getRuntime();
			try {
				run.exec("taskkill /F /IM chrome.exe");
				chromeCurrentTab = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			Runtime run = Runtime.getRuntime();
			try {
				run.exec("taskkill /F /IM firefox.exe");
				firefoxCurrentTab = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void cleanBrowser(String browser) {
		if(browser.equals("chrome")) {
			try {
				FileUtils.deleteDirectory(new File(chromeCacheLocation));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		else {
			try {
				FileUtils.deleteDirectory(new File(firefoxCacheLocation));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public CurrentUrl getUrl(String browser) {
		if(browser.equals("chrome")) {
			if(chromeCurrentTab != null) {
				CurrentUrl url = new CurrentUrl();
				url.setCurrentTabUrl(chromeCurrentTab);
				return url;
			}
		}
		else if(browser.equals("firefox")) {
			if(firefoxCurrentTab != null) {
				CurrentUrl url = new CurrentUrl();
				url.setCurrentTabUrl(firefoxCurrentTab);
				return url;
			}
		}
		return null;
	}

}
