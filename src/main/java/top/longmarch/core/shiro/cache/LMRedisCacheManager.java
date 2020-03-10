package top.longmarch.core.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;


public class LMRedisCacheManager implements CacheManager{

	private RedisTemplate redisTemplate;
	private final ConcurrentMap<String, Cache> CACHES = new ConcurrentHashMap<String, Cache>();

	@Override
	public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
		Cache<K, V> cache = CACHES.get(cacheName);
        if (null == cache) {
        	cache = new RedisCache<K, V>(redisTemplate.opsForHash(),cacheName);
            CACHES.put(cacheName, cache);
        }
        return cache;
	}

	public void setRedisTimeout(String cacheName,long timeout) {
		this.redisTemplate.expire(cacheName, timeout, TimeUnit.SECONDS);
	}

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public ConcurrentMap<String, Cache> getCaches() {
		return CACHES;
	}

	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}


	public static class RedisCache<K,V> implements Cache<K,V>{

		private final HashOperations<String,K,V> redisTemplate;
		private final String cacheName;

		public RedisCache(HashOperations<String,K,V> redisTemplate,String cacheName){
			this.redisTemplate = redisTemplate;
			this.cacheName = cacheName;
		}

		@Override
		public void clear() throws CacheException {
			this.redisTemplate.delete(cacheName, keys());
		}

		@Override
		public V get(K key) throws CacheException {
			return this.redisTemplate.get(cacheName, key);
		}

		@Override
		public Set<K> keys() {
			return this.redisTemplate.keys(cacheName);
		}

		public V put(K key, V value) throws CacheException {
			this.redisTemplate.put(cacheName, key, value);
			return this.redisTemplate.get(cacheName, key);
		}

		@Override
		public V remove(K key) throws CacheException {
			V v = this.redisTemplate.get(cacheName, key);
			this.redisTemplate.delete(cacheName, key);
			return v;
		}

		@Override
		public int size() {
			return this.redisTemplate.size(cacheName).intValue();
		}

		@Override
		public Collection<V> values() {
			return this.redisTemplate.values(cacheName);
		}

		@Override
		public String toString() {
			return "cacheName:"+this.cacheName+",size:"+this.size();
		}
	}
}