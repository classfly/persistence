
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.User;

/**
 * Created by fujianbo on 2018/4/22.
 *
 * @author fujianbo
 * @date 2018/04/22
 */
public class TestMybatis {
    @Test
    public void testQuery() {
        SqlSession sqlSession = buildMySqlEnv("config.xml");
        List<User> userList = sqlSession.selectList("com.classfly.mapper.UserMapper.query");
        for (User p : userList) {
            System.out.println(p);
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = buildMySqlEnv("config.xml");
        User user = new User();
        user.setUserId(124L);
        user.setAge(26);
        user.setPassword("test_123");
        user.setUserName("芸Rey");
        if (sqlSession.insert("com.classfly.mapper.UserMapper.insert", user) > 0) {
            sqlSession.commit();
        }
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = buildMySqlEnv("config.xml");
        User user = new User();
        user.setUserId(124L);
        user.setAge(26);
        user.setUserName("芸Rey");
        user.setPassword("test_modified");
        if (sqlSession.update("com.classfly.mapper.UserMapper.update", user) > 0) {
            sqlSession.commit();
        }
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = buildMySqlEnv("config.xml");
        User user = new User();
        user.setUserId(123L);
        if (sqlSession.update("com.classfly.mapper.UserMapper.delete", user) > 0) {
            sqlSession.commit();
        }
    }

    private static SqlSession buildMySqlEnv(String resource) {
        try {
            return new SqlSessionFactoryBuilder()
                .build(org.apache.ibatis.io.Resources.getResourceAsStream(resource))
                .openSession();
        } catch (IOException e) {
            System.out.printf("Failed to build mysql environment!");
            return null;
        }
    }
}
