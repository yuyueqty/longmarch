//package top.longmarch.core.shiro.config;
//
//import org.apache.shiro.cache.Cache;
//import org.apache.shiro.cache.CacheException;
//import org.apache.shiro.cache.CacheManager;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.BoundHashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import top.longmarch.sys.entity.User;
//
//import java.util.Collection;
//import java.util.Set;
//
//@Component
//public class RedisCacheManager implements CacheManager {
//
//    private String cacheKeyPrefix = "shiro:";
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Override
//    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
//        return new ShiroRedisCache<K, V>(cacheKeyPrefix + name);
//    }
//
//    public class ShiroRedisCache<K, V> implements Cache<K, V> {
//
//        private String cacheKey;
//
//        public ShiroRedisCache(String cacheKey) {
//            this.cacheKey = cacheKey;
//        }
//
//        @Override
//        public V get(K key) throws CacheException {
//            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
//            Object k = hashKey(key);
//            return hash.get(k);
//        }
//
//        @Override
//        public V put(K key, V value) throws CacheException {
//            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
//            Object k = hashKey(key);
//            hash.put((K) k, value);
//            return value;
//        }
//
//        @Override
//        public V remove(K key) throws CacheException {
//            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
//
//            Object k = hashKey(key);
//            V value = hash.get(k);
//            hash.delete(k);
//            return value;
//        }
//
//        @Override
//        public void clear() throws CacheException {
//            redisTemplate.delete(cacheKey);
//        }
//
//        @Override
//        public int size() {
//            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
//            return hash.size().intValue();
//        }
//
//        @Override
//        public Set<K> keys() {
//            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
//            return hash.keys();
//        }
//
//        @Override
//        public Collection<V> values() {
//            BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
//            return hash.values();
//        }
//
//        protected Object hashKey(K key) {
//
//            if (key instanceof PrincipalCollection) {
//                PrincipalCollection pc = (PrincipalCollection) key;
//                User user = (User) pc.getPrimaryPrincipal();
//                return user.getUsername();
//            }
//            return key;
//        }
//    }
//
//}