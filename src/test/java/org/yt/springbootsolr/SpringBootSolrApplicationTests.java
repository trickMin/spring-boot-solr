package org.yt.springbootsolr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yt.springbootsolr.entity.User;
import org.yt.springbootsolr.entity.UserWithDpt;
import org.yt.springbootsolr.test.SolrTest;

import java.util.List;

@SpringBootTest
class SpringBootSolrApplicationTests {

    @Autowired
    private SolrTest solrTest;
    private List<User> userList = null;

    @Test
    void testFindById() {
        userList = solrTest.testFindByQueryCondition("id:2");
        if (userList != null){
            userList.forEach(user -> {
                System.out.println(user);
            });
        }else {
            System.out.println("userList还没有初始化成功...");
        }
    }

    @Test
    void findAll(){
        userList = solrTest.testFindAll();
        if (userList != null){
            userList.forEach(user -> {
                System.out.println(user);
            });
        }else {
            System.out.println("userList还没有初始化成功...");
        }
    }

    @Test
    void testFindUserWithDptByCondition() {
        List<UserWithDpt> userList = null;
        userList = solrTest.testFindUserWithDptByQueryCondition("*:*");
        if (userList != null){
            userList.forEach(user -> {
                System.out.println(user);
            });
        }else {
            System.out.println("userList还没有初始化成功...");
        }
    }

    @Test
    void testAdd(){
        User user = new User();
        user.setId(7);
        user.setAge(20);
        user.setUsername("tricker");
        user.setPassword("tricker123");
        user.setDptId(2);
        solrTest.add(user);
    }

    @Test
    void testDelete(){
        solrTest.deleteByCondition("username:tricker");
    }

    @Test
    void testUpdate(){
        List<User> userList = solrTest.testFindByQueryCondition("id:1");
        User user = userList.get(0);
        user.setUsername("修改过的admin");
        solrTest.update(user);
    }
}
