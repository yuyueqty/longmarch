package top.longmarch.core.shiro.cache;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;


public class MapCacheManager implements CacheManager{

	
	private static final ConcurrentMap<String, Cache> CACHES = new ConcurrentHashMap<String, Cache>();
	
	@Override
	public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
		Cache<K, V> cache = CACHES.get(cacheName);  
        if (null == cache) {  
        	cache = new MapCache<K, V>(cacheName);  
            CACHES.put(cacheName, cache);  
        }  
        return cache;  
	}

	public static class MapCache<K,V> implements Cache<K,V>{

		private final ConcurrentMap<K,V> storge = new ConcurrentHashMap<K,V>(); 
		private final String cacheName; 
		
		public MapCache(String cacheName){
			this.cacheName = cacheName;
		}
		
		@Override
		public void clear() throws CacheException {
			storge.clear();
		}

		@Override
		public V get(K key) throws CacheException {
			return storge.get(key);
		}

		@Override
		public Set<K> keys() {
			return storge.keySet();
		}

		@Override
		public V put(K key, V value) throws CacheException {
			return storge.put(key, value);
		}

		@Override
		public V remove(K key) throws CacheException {
			return storge.remove(key);
		}

		@Override
		public int size() {
			return storge.size();
		}

		@Override
		public Collection<V> values() {
			return storge.values();
		}

		@Override
		public String toString() {
			return "cacheName:"+this.cacheName+",size:"+this.size();
		}
	}
}