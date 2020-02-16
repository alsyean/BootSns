/*
 * package com.sns.pjt;
 * 
 * import java.util.Date; import java.util.Optional;
 * 
 * import javax.annotation.Resource;
 * 
 * import org.junit.Assert; import org.junit.Ignore; import org.junit.Test;
 * import org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.data.redis.core.HashOperations; import
 * org.springframework.data.redis.core.RedisTemplate; import
 * org.springframework.data.redis.core.ValueOperations; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * import com.sns.pjt.Service.PostService; import
 * com.sns.pjt.Service.UserService; import com.sns.pjt.domain.Post; import
 * com.sns.pjt.domain.RedisPost; import com.sns.pjt.domain.User; import
 * com.sns.pjt.persistence.PostRepository; import
 * com.sns.pjt.persistence.RedisPostRepository;
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest public class RedisTest {
 * 
 * @Autowired private RedisTemplate redisTemplate;
 * 
 * @Autowired private RedisPostRepository rpostRepository;
 * 
 * @Autowired private PostRepository postRepository;
 * 
 * @Autowired private UserService userService;
 * 
 * @Test
 * 
 * @Ignore public void testDataHandling() throws Exception {
 * 
 * String key = "key:springboot"; redisTemplate.opsForValue().set(key, "Hello");
 * String value = (String) redisTemplate.opsForValue().get(key);
 * 
 * Assert.assertEquals("Hello", value);
 * 
 * System.out.println("check"); }
 * 
 * @Test
 * 
 * @Ignore public void testPost() {
 * 
 * User user = userService.getUserById(1);
 * 
 * RedisPost rp = new RedisPost();
 * 
 * Post post = new Post();
 * 
 * post.setTitle("testTitle"); post.setContent("testContent");
 * post.setCreatedAt(new Date()); post.setUser(user);
 * 
 * Post insertPost = postRepository.save(post);
 * 
 * Post getPostById = postRepository.findById(insertPost.getId());
 * 
 * System.out.println("print : " + getPostById);
 * 
 * // postRepository.deleteById(getPostById.getId());
 * 
 * 
 * rp.setId(getPostById.getId()); rp.setViewCnt(0);
 * 
 * rpostRepository.save(rp);
 * 
 * }
 * 
 * @Test
 * 
 * @Ignore public void testFindPost() {
 * 
 * // System.out.println(rpostRepository.findById(50));
 * 
 * rpostRepository.deleteAll();
 * 
 * }
 * 
 * // @Resource(name = "redisTemplate")
 * 
 * @Test
 * 
 * @Ignore public void testIncr() {
 * 
 * HashOperations<String, String, String> hashOperation =
 * redisTemplate.opsForHash();
 * 
 * int cnt;
 * 
 * 
 * RedisPost rp = rpostRepository.findById(50);
 * 
 * System.out.println(rp);
 * 
 * 
 * hashOperation.increment("post:" + 17, "viewCnt", 1);
 * 
 * System.out.println("@@@@@@@");
 * 
 * cnt = Integer.valueOf((hashOperation.get("post:" + 17, "viewCnt")));
 * 
 * System.out.println(cnt);
 * 
 * }
 * 
 * @Test
 * 
 * @Ignore public void testViews() {
 * 
 * HashOperations<String, String, String> hashOperation =
 * redisTemplate.opsForHash();
 * 
 * int postId = 17;
 * 
 * int viewCount;
 * 
 * viewCount = Integer.valueOf((hashOperation.get("post:" + postId,
 * "viewCnt")));
 * 
 * System.out.println(viewCount);
 * 
 * RedisPost views = new RedisPost();
 * 
 * views = rpostRepository.findById(postId);
 * 
 * 
 * 
 * System.out.println(views);
 * 
 * }
 * 
 * @Test public void testDelete() {
 * 
 * rpostRepository.deleteById(15);
 * 
 * }
 * 
 * }
 */