package core.service.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class CacheProvider {
	public static final String CACHE_NAME = "globalCache";
	private CacheManager cacheManager;

	private CacheProvider(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public static CacheProvider getInstance(CacheManager cacheManager) {
		return new CacheProvider(cacheManager);
	}

	@SuppressWarnings("unchecked")
	public <V> V load(String key, CacheLoader<V> cacheLoader) {
		Cache cache = getCache();
		if (cache.get(key) != null) {
			return (V) cache.get(key).get();
		}

		V value = cacheLoader.load();
		cache.put(key, value);
		return value;
	}

	public Cache getCache() {
		return this.cacheManager.getCache(CACHE_NAME);
	}
	
}
