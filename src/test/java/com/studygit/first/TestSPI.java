package com.studygit.first;

import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.study.gitfirst.service.ISPIService;

/**
 * FileName: TestSPI
 * Author:   machao
 * Date:     2019/10/11 20:27
 * Description: Java SPI机制
 * History: 一定要在resources/META-INF/services/的配置文件里添加具体的实现类名
 * <author>          <time>          <version>          <desc>
 */

@SuppressWarnings("restriction")
public class TestSPI {
	public static void main(String[] args) {
		
		Iterator<ISPIService> providers = Service.providers(ISPIService.class);
		providers.forEachRemaining(spi -> {spi.execute();});
		
		ServiceLoader<ISPIService> load = ServiceLoader.load(ISPIService.class);
		load.forEach(spi -> {spi.execute();});
		
	}
}
