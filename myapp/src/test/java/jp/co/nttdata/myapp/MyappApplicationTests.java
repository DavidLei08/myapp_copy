package jp.co.nttdata.myapp;

import jp.co.nttdata.myapp.service.mail.MailService;
import jp.co.nttdata.myapp.utils.ip.IPAddrUtil;
import jp.co.nttdata.myapp.utils.ip.IpAddrFormatException;
import jp.co.nttdata.myapp.utils.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyappApplicationTests {

	//	@Test
//	public void contextLoads1() {
//		Jedis jedis = RedisUtil.getJedis();
//		jedis.set("a","abc");
//		String a =jedis.get("a");
//	}
//	@Test
//	public void contextLoads() throws IpAddrFormatException {
//		boolean isSecurity = IPAddrUtil.isSecurityIpAddr("192.168.0.10","192.168.0.55","192.168.0.20");
//	}
	@Autowired
	private MailService mailService;

	@Test
	public void contextLoads() {
		String to = "2686849744@qq.com";
		mailService.sendMail(to,
				"Mailbox authentication",
				UUID.randomUUID().toString().toUpperCase().
						replaceAll("-","").substring(0,4));
	}

}
