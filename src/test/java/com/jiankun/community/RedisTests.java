package com.jiankun.community;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testStrings() {
        String redisKey = "test:count";

        redisTemplate.opsForValue().set(redisKey, 1);

        System.out.println(redisTemplate.opsForValue().get(redisKey));
        System.out.println(redisTemplate.opsForValue().increment(redisKey));
        System.out.println(redisTemplate.opsForValue().decrement(redisKey));
    }

    @Test
    public void testHashes() {
        String redisKey = "test:user";

        redisTemplate.opsForHash().put(redisKey, "id", "1");
        redisTemplate.opsForHash().put(redisKey, "username", "zhangsan");

        System.out.println(redisTemplate.opsForHash().get(redisKey, "id"));
        System.out.println(redisTemplate.opsForHash().get(redisKey, "username"));
    }

    @Test
    public void testLists() {
        String redisKey = "test:ids";

        redisTemplate.opsForList().leftPush(redisKey, 101);
        redisTemplate.opsForList().leftPush(redisKey, 102);
        redisTemplate.opsForList().leftPush(redisKey, 103);

        System.out.println(redisTemplate.opsForList().size(redisKey));
        System.out.println(redisTemplate.opsForList().index(redisKey, 0));
        System.out.println(redisTemplate.opsForList().range(redisKey, 0, 2));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
    }

    @Test
    public void testSets() {
        String redisKey = "test:teachers";

        redisTemplate.opsForSet().add(redisKey, "刘备", "qjk", "qyl", "dsy");

        System.out.println(redisTemplate.opsForSet().size(redisKey));
        System.out.println(redisTemplate.opsForSet().pop(redisKey));
        System.out.println(redisTemplate.opsForSet().members(redisKey));

    }

    @Test
    public void testSortedSets() {
        String redisKey = "test:students";

        redisTemplate.opsForZSet().add(redisKey, "Mark", 80);
        redisTemplate.opsForZSet().add(redisKey, "Mark 2", 90);
        redisTemplate.opsForZSet().add(redisKey, "Mark 3", 50);
        redisTemplate.opsForZSet().add(redisKey, "Mark 4", 60);

        System.out.println(redisTemplate.opsForZSet().zCard(redisKey));
        System.out.println(redisTemplate.opsForZSet().score(redisKey, "Mark 2"));
        System.out.println(redisTemplate.opsForZSet().score(redisKey, "Mark 1"));
        System.out.println(redisTemplate.opsForZSet().reverseRank(redisKey, "Mark 2")); // reverseRank 是统计排名， 从大到小
        System.out.println(redisTemplate.opsForZSet().rank(redisKey, "Mark 2")); // rank 是统计排名， 从小到大
        System.out.println(redisTemplate.opsForZSet().range(redisKey, 0, 2)); // range是从小到大
        System.out.println(redisTemplate.opsForZSet().reverseRange(redisKey, 0 , 2)); // reverseRange 是从大到小
    }


    @Test
    public void testKeys() {
        redisTemplate.delete("test:user");

        System.out.println((redisTemplate.hasKey("test:user"))); // 判断key是否存在

        redisTemplate.expire("test:students", 10, TimeUnit.SECONDS);
    }

    // 多次访问同一个key， 可以将key绑定到一个对象上，以减少代码重复
    @Test
    public void testBoundOperations() {
        String redisKey = "test:count";

        BoundValueOperations operations = redisTemplate.boundValueOps(redisKey);
        operations.increment();
        System.out.println(operations.get());
    }

    // 编程式事务
    @Test
    public void testTransactional() {
        Object obj = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String redisKey = "test:tx";
                operations.multi(); // 启用事务

                operations.opsForSet().add(redisKey, "zhangsan");
                operations.opsForSet().add(redisKey, "wangwu");
                operations.opsForSet().add(redisKey, "lisi");
                System.out.println(1);
                System.out.println(operations.opsForSet().members(redisKey));
                System.out.println(2);
                return operations.exec();  // 提交事务
            }
        });
        System.out.println(obj);
    }
}
