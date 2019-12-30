import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class JedisTest {

    /**
     * 单链接测试
     */
    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("192.168.10.131", 6379);
        jedis.set("mytest", "helloworld this my jedis client");
        String result = jedis.get("mytest");
        System.out.println(result);
        jedis.close();
    }

    /**
     * 连接池测试
     */
    @Test
    public void testJedisPool() {
        JedisPool jedisPool = new JedisPool("192.168.10.131", 6379);
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.get("mytest"));
        jedis.close();
        jedisPool.close();
    }

    @Test
    public void testJedisCluster() throws Exception{
        Set<HostAndPort> nodes=new HashSet<>();
        nodes.add(new HostAndPort("192.168.10.131",6379));
        nodes.add(new HostAndPort("192.168.10.132",6379));
        nodes.add(new HostAndPort("192.168.10.133",6379));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("cluster-test","myjedis cluster test");
        String result = cluster.get("cluster-test");
        System.out.println(result);
        cluster.close();
    }



}
