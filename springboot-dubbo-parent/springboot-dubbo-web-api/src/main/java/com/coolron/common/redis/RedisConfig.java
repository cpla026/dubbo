package com.coolron.common.redis;

import com.coolron.redis.MessageReceiver;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

/**
 * @Auther: xf
 * @Date: 2018/11/20 23:10
 * @Description:  redis 配置类
 */
@Slf4j
@Configuration
@EnableCaching//(缓存支持)
//继承CachingConfigurerSupport，重写CacheManager方法。
public class RedisConfig extends CachingConfigurerSupport {

	/**
	 * 注入 RedisConnectionFactory
	 */
	@Autowired
    RedisConnectionFactory redisConnectionFactory;

	/**
	 * 指定key的生成策略
	 * @return KeyGenerator
	 */
	@Bean
    public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				String[] value = new String[1];
				// sb.append(target.getClass().getName());
				// sb.append(":" + method.getName());
				Cacheable cacheable = method.getAnnotation(Cacheable.class);
				if (cacheable != null) {
					value = cacheable.value();
				}
				CachePut cachePut = method.getAnnotation(CachePut.class);
				if (cachePut != null) {
					value = cachePut.value();
				}
				CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
				if (cacheEvict != null) {
					value = cacheEvict.value();
				}
				sb.append(value[0]);
				//获取参数值
				for (Object obj : params) {
					sb.append(":" + obj.toString());
				}
				return sb.toString();
			}
		};
	}

	/**
	 * 实例化 CacheManager 对象，指定使用RedisCacheManager作缓存管理
	 *
	 * @return CacheManager
	 */
	@Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
		// 设置缓存过期时间（单位:秒），60秒
		//rcm.setDefaultExpiration(600);
		rcm.setUsePrefix(true);
		return rcm;
	}

	/**
	 * 实例化 RedisTemplate 对象
	 * @return RedisTemplate
	 */
	@Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		initDomainRedisTemplate(redisTemplate);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	/**
	 * 设置数据存入 redis 的序列化方式
	 * </br>redisTemplate 序列化默认使用的jdkSerializeable, 存储二进制字节码, 所以自定义序列化类
	 *
	 * @param redisTemplate
	 */
	private void initDomainRedisTemplate(
			RedisTemplate<String, Object> redisTemplate) {
		//key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
		//所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
		//或者JdkSerializationRedisSerializer序列化方式;

		// 使用Jackson2JsonRedisSerialize 替换默认序列化
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		//// string结构的数据，设置value的序列化规则和 key的序列化规则
		//StringRedisSerializer解决key中午乱码问题。//Long类型不可以会出现异常信息;
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		//value乱码问题：Jackson2JsonRedisSerializer
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

		//设置Hash结构的key和value的序列化方式
		//redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
		//redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
	}

	/**
	 * redis数据操作异常处理
	 * 这里的处理：在日志中打印出错误信息，但是放行
	 * 保证redis服务器出现连接等问题的时候不影响程序的正常运行，使得能够出问题时不用缓存
	 * @return
	 */
	@Bean
    @Override public CacheErrorHandler errorHandler() {
		CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
			@Override public void handleCacheGetError(RuntimeException e,
                                                      Cache cache, Object key) {
				log.error("redis异常：key=[{}]", key, e);
			}

			@Override public void handleCachePutError(RuntimeException e,
                                                      Cache cache, Object key, Object value) {
				log.error("redis异常：key=[{}]", key, e);
			}

			@Override public void handleCacheEvictError(RuntimeException e,
                                                        Cache cache, Object key) {
				log.error("redis异常：key=[{}]", key, e);
			}

			@Override public void handleCacheClearError(RuntimeException e,
					Cache cache) {
				log.error("redis异常：", e);
			}
		};
		return cacheErrorHandler;
	}

	/**
	 * 实例化 ValueOperations 对象,可以使用 String 操作
	 * @param redisTemplate
	 * @return
	 */
	@Bean
    public ValueOperations<String, Object> valueOperations(
			RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForValue();
	}

	/**
	 * 实例化 HashOperations 对象,可以使用 Hash 类型操作
	 * @param redisTemplate
	 * @return
	 */
	@Bean
    public HashOperations<String, String, Object> hashOperations(
			RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForHash();
	}

	/**
	 * 实例化 ListOperations 对象,可以使用 List 操作
	 * @param redisTemplate
	 * @return
	 */
	@Bean
    public ListOperations<String, Object> listOperations(
			RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForList();
	}

	/**
	 * 实例化 SetOperations 对象,可以使用 Set 操作
	 * @param redisTemplate
	 * @return
	 */
	@Bean
    public SetOperations<String, Object> setOperations(
			RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForSet();
	}

	/**
	 * 实例化 ZSetOperations 对象,可以使用 ZSet 操作
	 * @param redisTemplate
	 * @return
	 */
	@Bean
    public ZSetOperations<String, Object> zSetOperations(
			RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForZSet();
	}

	/**
	 * redis消息监听器容器
	 * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
	 * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
	 * @param connectionFactory
	 * @param listenerAdapter
	 * @return
	 */
	@Bean
	//相当于xml中的bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		//订阅了一个叫chat 的通道
		container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
		//这个container 可以添加多个 messageListener
		return container;
	}

	/**
	 * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
	 * @param receiver
	 * @return
	 */
	@Bean
    MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
		//这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
		//也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	/**redis 读取内容的template */
	@Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}
}
