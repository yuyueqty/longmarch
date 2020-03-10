package top.longmarch.core.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SpringCacheManager implements CacheManager {

	private final org.springframework.cache.CacheManager delegator;
	
	private final ConcurrentMap<String, SpringCache> CACHES = new ConcurrentHashMap<String, SpringCache>();
	
	public SpringCacheManager(org.springframework.cache.CacheManager cacheManager){
		this.delegator = cacheManager;
	}
	
	@Override
	public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
		SpringCache<K,V> cache = this.CACHES.get(cacheName);
		if (cache != null) {
			return cache;
		}
		else {
			synchronized (this.CACHES) {
				cache = this.CACHES.get(cacheName);
				if (cache == null) {
					org.springframework.cache.Cache springCache = this.delegator.getCache(cacheName);
					cache = new SpringCache(cacheName,springCache);
					this.CACHES.put(cacheName, cache);
				}
				return cache;
			}
		}
	}

	public static class SpringCache<K,V> implements Cache<K,V>{

		private final String cacheName; 
		private final org.springframework.cache.Cache delegator;
		
		public SpringCache(String cacheName,org.springframework.cache.Cache cache){
			this.cacheName = cacheName;
			this.delegator = cache;
		}
		
		@Override
		public void clear() throws CacheException {
			this.delegator.clear();
		}

		@Override
		public V get(K key) throws CacheException {
			org.springframework.cache.Cache.ValueWrapper wrapper = this.delegator.get(key);
			return wrapper == null ? null : (V) wrapper.get();
		}

		@Override
		public V put(K key, V value) throws CacheException {
			this.delegator.put(key, value);
			return value;
		}

		@Override
		public V remove(K key) throws CacheException {
			V v = this.get(key);
			this.delegator.evict(key);
			return v;
		}

		@Override
		public Set<K> keys() {
			throw new UnsupportedOperationException(" not supported ");
		}
		
		@Override
		public int size() {
			throw new UnsupportedOperationException(" not supported ");
		}

		@Override
		public Collection<V> values() {
			throw new UnsupportedOperationException(" not supported ");
		}

		@Override
		public String toString() {
			return "cacheName:"+this.cacheName;
		}
	}
}