package core.service.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import core.service.cache.CacheLoader;
import core.service.cache.CacheProvider;

public abstract class TranslateService {
	@Autowired
	private CacheManager cacheManager;

	protected abstract Map<String, String> load();

	protected abstract String getServiceKey();

	public String translate(String key) {
		// Get cache data
		Map<String, String> maps = CacheProvider.getInstance(cacheManager).load("translation." + getServiceKey(),
				new CacheLoader<Map<String, String>>() {
					@Override
					public Map<String, String> load() {
						return TranslateService.this.load();
					}
				});

		// translation
		String str = maps.get(key);
		if (str == null) {
			return "";
		}
		return str;
	}
}