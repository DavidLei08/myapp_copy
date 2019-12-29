package jp.co.nttdata.myapp;

import jp.co.nttdata.myapp.utils.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyappApplicationTests {

	@Test
	public void contextLoads() {
		Jedis jedis = RedisUtil.getJedis();
		jedis.set("a","abc");
		String a =jedis.get("a");
	}

}
