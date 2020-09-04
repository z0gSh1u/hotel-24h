package tech.zxuuu.hotel24h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zxuuu.hotel24h.entity.Comment;
import tech.zxuuu.hotel24h.hadoop.CommentCollector;
import tech.zxuuu.hotel24h.hadoop.HDFSReadWrite;
import tech.zxuuu.hotel24h.hadoop.RemoteShellExecutor;
import tech.zxuuu.hotel24h.mapper.CommentMapper;
import tech.zxuuu.hotel24h.service.CommentService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentMapper commentMapper;

  @Autowired
  private CommentCollector commentCollector;

  @Override
  /**
   * 状态码：0：评论成功；1：重复评论；2：错误信息
   */
  public Integer insertComment(Comment comment) {
    Comment commentCheck = commentMapper.selectCommentById(comment.getId());
    // TODO: 检查信息错误
    if (commentCheck != null) {
      return 1;
    }
    commentMapper.insertComment(comment);
    return 0;
  }

  @Override
  public List<Comment> getAllComment() {
    return commentMapper.selectAllComment();
  }

  /**
   * 状态码：0：成功；1：失败
   */
  @Override
  public Integer removeComment(String orderId) {
    return commentMapper.removeComment(orderId) ? 0 : 1;
  }

  @Override
  public Map<String, Object> analyzeComment() {
    // 第零步 - 读配置文件，删除旧文件
    final  String HADOOP_CONFIG_PATH = "/config/hadoop.properties";
    Properties properties = new Properties();
    try {
      properties.load(CommentServiceImpl.class.getResourceAsStream(HADOOP_CONFIG_PATH));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String hadoopHdfs = properties.getProperty("hadoop.hdfs");
    String hadoopNamenode = properties.getProperty("hadoop.namenode");
    String hadoopUsername = properties.getProperty("hadoop.namenode.username");
    String hadoopPassword = properties.getProperty("hadoop.namenode.password");
    String hadoopHome = properties.getProperty("hadoop.hadoophome");
    RemoteShellExecutor executor = new RemoteShellExecutor(hadoopNamenode, hadoopUsername, hadoopPassword);
    try {
      executor.exec("hadoop fs -rm -r /user/hadoop/hotel24h/output/");
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 第一步 - 向hdfs写评论文件
    String file = hadoopHdfs + "/user/hadoop/hotel24h/input/tokenized_comment.txt";
    try {
      String words = commentCollector.getTokenizedComment();
      HDFSReadWrite.writeToHDFS(file, words);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 第二步 - 执行mapreduce
    try {
      executor.exec(hadoopHome + "/bin/hadoop jar " + hadoopHome + "/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.2.jar "
        + "wordcount /user/hadoop/hotel24h/input/* /user/hadoop/hotel24h/output/");
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 第三步 - 从hdfs取数据
    String outputFile = hadoopHdfs + "/user/hadoop/hotel24h/output/part-r-00000";
    String outputContent = "";
    try {
      outputContent = HDFSReadWrite.readFromHDFS(outputFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(outputContent);
    Map map = new HashMap<>();
    return map;
  }
}
