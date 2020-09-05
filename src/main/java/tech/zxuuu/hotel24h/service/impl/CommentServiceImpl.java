package tech.zxuuu.hotel24h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.zxuuu.hotel24h.entity.Comment;
import tech.zxuuu.hotel24h.entity.Reserve;
import tech.zxuuu.hotel24h.hadoop.CommentCollector;
import tech.zxuuu.hotel24h.hadoop.HDFSReadWrite;
import tech.zxuuu.hotel24h.hadoop.RemoteShellExecutor;
import tech.zxuuu.hotel24h.mapper.CommentMapper;
import tech.zxuuu.hotel24h.mapper.ReserveMapper;
import tech.zxuuu.hotel24h.service.CommentService;

import java.io.*;
import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentMapper commentMapper;

  @Autowired
  private ReserveMapper reserveMapper;

  @Autowired
  private CommentCollector commentCollector;

  @Override
  /**
   * 状态码：0：评论成功；1：重复评论；2：错误信息
   */
  public Integer insertComment(Comment comment, String name, String phone) {
    // 信息错误
    Reserve reserve = reserveMapper.selectReserveById(comment.getId());
    if (reserve == null || !reserve.getReserverName().equals(name) || !reserve.getReserverPhone().equals(phone) || !reserve.getStatus().equals(2)) {
      return 2;
    }
    // 重复提交错误
    Comment commentCheck = commentMapper.selectCommentById(comment.getId());
    if (commentCheck != null) {
      return 1;
    }
    commentMapper.insertComment(comment);
    reserveMapper.updateStatus(3, comment.getId());
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
    // TODO: work-around
    File logFile = new File("F:\\hotel-24h\\src\\main\\resources\\static\\hadoop_log.html");
    try {
      FileWriter fileWriter = new FileWriter(logFile);
      fileWriter.write("");
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      System.setOut(new PrintStream(logFile));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    // 第零步 - 读配置文件，删除旧文件
    final String HADOOP_CONFIG_PATH = "/config/hadoop.properties";
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
    // 第四步 - 统计词频
    List<String> goodWords = new ArrayList<>();
    List<String> badWords = new ArrayList<>();
    final String GOODWORDS_PATH = "/config/GoodWords.txt";
    final String BADWORDS_PATH = "/config/BadWords.txt";
    BufferedReader br = null;
    String str = null;
    try {
      br = new BufferedReader(new InputStreamReader(CommentServiceImpl.class.getResourceAsStream(GOODWORDS_PATH)));
      while ((str = br.readLine()) != null) {
        goodWords.add(str);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      br = new BufferedReader(new InputStreamReader(CommentServiceImpl.class.getResourceAsStream(BADWORDS_PATH)));
      while ((str = br.readLine()) != null) {
        badWords.add(str);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    Set<String> goodWordSet = new HashSet<>();
    Set<String> badWordSet = new HashSet<>();
    Integer goodWordCount = 0;
    Integer badWordCount = 0;
    String[] words = outputContent.trim().split("\n");
    System.out.println(goodWords);
    System.out.println("<br>");
    System.out.println(badWords);
    System.out.println("<br>");
    for (String line : words) {
      String[] lineSplit = line.split("\t");
      String word = lineSplit[0];
      Integer encounter = Integer.parseInt(lineSplit[1]);
      System.out.println(word + " ");
      if (goodWords.contains(word)) {
        goodWordCount += encounter;
        goodWordSet.add(word);
      } else if (badWords.contains(word)) {
        badWordCount += encounter;
        badWordSet.add(word);
      }
    }
    // System.out.println(outputContent);
    System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    Map map = new HashMap<String, Object>();
    map.put("goodWordCount", goodWordCount);
    map.put("badWordCount", badWordCount);
    map.put("goodWordSet", goodWordSet.toArray());
    map.put("badWordSet", badWordSet.toArray());
    return map;
  }
}
