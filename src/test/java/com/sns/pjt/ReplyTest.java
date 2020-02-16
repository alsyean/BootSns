/*
 * package com.sns.pjt;
 * 
 * import java.util.List;
 * 
 * import org.junit.Ignore; import org.junit.Test; import
 * org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration; import
 * org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
 * import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
 * import org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.data.mongodb.core.MongoTemplate; import
 * org.springframework.data.mongodb.repository.MongoRepository; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * import com.sns.pjt.domain.MongoReply; import
 * com.sns.pjt.persistence.ReplyRepository;
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest
 * 
 * @EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class,
 * MongoDataAutoConfiguration.class }) public class ReplyTest {
 * 
 * @Autowired private ReplyRepository replyRepository;
 * 
 * @Autowired private MongoTemplate mongoTemplate;
 * 
 * @Test
 * 
 * @Ignore public void testReply() {
 * 
 * MongoReply reply = new MongoReply();
 * 
 * reply.setPostId(2); reply.setReplyContent("test");
 * 
 * System.out.println("@@@@@"); replyRepository.insert(reply);
 * 
 * System.out.println("end");
 * 
 * }
 * 
 * @Test
 * 
 * @Ignore public void testFind() {
 * 
 * MongoReply reply = new MongoReply();
 * 
 * System.out.println("aa : " + replyRepository.findAll().toString());
 * 
 * }
 * 
 * @Test
 * 
 * @Ignore public void testFindPostId() {
 * 
 * List<MongoReply> list = replyRepository.findByPostId(2);
 * 
 * System.out.println(list.toString());
 * 
 * }
 * 
 * @Test public void testDelete() {
 * 
 * 
 * }
 * 
 * 
 * 
 * }
 */