package com.example.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mp.mapper.UserMapper;
import com.example.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectAll() {
        List<User> userList = this.userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);



        }
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(20);
        user.setMail("test@bigdata.com");
        user.setName("曹操");
        user.setUserName("caocao");
        user.setPassword("123456");
        int result = this.userMapper.insert(user);
        System.out.println("result = " + result);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(6L);
        user.setAge(21);
        int result = this.userMapper.updateById(user);
        System.out.println("result = " + result);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setAge(22);
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", 6);
        int result = this.userMapper.update(user, queryWrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void testUpdate2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", 6).set("user_name", "mike");
        int result = this.userMapper.update(null, wrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void testDelete() {
        int result = this.userMapper.deleteById(5L);
        System.out.println("result = " + result);
    }

    @Test
    public void testDeleteByMap() {
        User user = new User();
        user.setAge(21);
        user.setName("赵六");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        int result = this.userMapper.delete(queryWrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void testDeleteBatchIds() {
        int result = this.userMapper.deleteBatchIds(Arrays.asList(3L, 5L));
        System.out.println("result = " + result);
    }

    @Test
    public void testSelectById() {
        User user = this.userMapper.selectById(2L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds() {
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(1L, 2L));
        System.out.println(users);
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", "zhangsan");
        User user = this.userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("age", "18");
        Integer integer = this.userMapper.selectCount(queryWrapper);
        System.out.println(integer);
    }

    @Test
    public void testSelectList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.gt("age", "17");
        List<User> users = this.userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void testSelectPage() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 20); //年龄大于20岁27
        Page<User> page = new Page<>(1, 2);
        //根据条件查询数据
        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数：" + iPage.getTotal());
        System.out.println("总页数：" + iPage.getPages());
        List<User> users = iPage.getRecords();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void testFindById() {
        User user = this.userMapper.findById(2L);
        System.out.println(user);
    }

    @Test
    public void testEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("password", "123456")
                .ge("age", 20)
                .in("name", "李张", "王五", "赵六");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLike() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "张");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOrderByAge() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("age");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOr() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "李张").or().eq("age", 24);
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelect() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "李张").or().eq("age", 24).select("id", "name", "age");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
