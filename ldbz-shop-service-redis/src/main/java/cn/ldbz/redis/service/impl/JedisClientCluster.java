package cn.ldbz.redis.service.impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import cn.ldbz.redis.service.JedisClient;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;


public class JedisClientCluster implements JedisClient {

//    @Autowired
    private JedisCluster jedisCluster;
    
    @Value("${redis.clusterNodes}")
    private String clusterNodes;

    public JedisClientCluster() {
        if (StringUtils.isEmpty(clusterNodes)) {
            throw new RuntimeException("clusterNodes不能为空!");
        }
        String[] nodes = clusterNodes.split(",");
        HashSet<HostAndPort> hostAndPorts = new HashSet<>();
        for (String node : nodes) {
            String[] split = node.split(":");
            HostAndPort hostAndPort = new HostAndPort(split[0], Integer.parseInt(split[1]));
            hostAndPorts.add(hostAndPort);
        }
        jedisCluster = new JedisCluster(hostAndPorts);
	}
    
    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String hget(String hkey, String key) {
        return jedisCluster.hget(key, key);
    }

    @Override
    public long hset(String hkey, String key, String value) {
        return jedisCluster.hset(hkey, key, value);
    }

    @Override
    public long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public long expire(String key, Integer second) {
        return jedisCluster.expire(key, second);
    }

    @Override
    public long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public long hdel(String hkey, String key) {
        return jedisCluster.hdel(hkey, key);
    }
    
}
