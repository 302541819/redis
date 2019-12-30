import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JedisSpringTest {

    @Autowired
    private JedisPool jedisPool;

    //@Autowired
    private JedisCluster jedisCluster;

    @Test
    public void testJedisPool() {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get("mytest");
        System.out.println(result);
        jedis.close();
    }

    @Test
    public void testJedisCluster() throws Exception {
        jedisCluster.set("cluster-test", "my jedis cluster test");
        System.out.println(jedisCluster.get("cluster-test"));
    }
}
