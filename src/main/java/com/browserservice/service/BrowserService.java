package com.browserservice.service;

import com.browserservice.model.CurrentUrl;

public interface BrowserService {
	public void startBrowser(String browserName,String browserUrl);
	public void stopBrowser(String browserName);
	public void cleanBrowser(String browserName);
	public CurrentUrl getUrl(String browserName);

}
