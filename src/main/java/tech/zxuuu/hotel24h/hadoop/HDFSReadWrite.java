package tech.zxuuu.hotel24h.hadoop;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

/**
 * Java操作HDFS的功能类
 */
public class HDFSReadWrite {
  public static void readFromHDFS(String file) throws IOException {
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(URI.create(file), conf);
    Path path = new Path(file);
    FSDataInputStream in = fs.open(path);

    String finalContent = "";

    IOUtils.copyBytes(in, finalContent.st8, 4096, true);


  }

  public static void writeToHDFS(String file, String words) throws IOException, URISyntaxException {
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(URI.create(file), conf);
    Path path = new Path(file);
    FSDataOutputStream out = fs.create(path);   //创建文件
    out.write(words.getBytes("UTF-8"));
    out.close();
  }

  public static void uploadToHDFS(String src, String dst) throws IOException {
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(URI.create(dst), conf);
    Path pathDst = new Path(dst);
    Path pathSrc = new Path(src);
    fs.copyFromLocalFile(pathSrc, pathDst);
    fs.close();
  }

  public static void listDirOnHDFS(String dirPath) throws IOException {
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(URI.create(dirPath), conf);
    Path path = new Path(dirPath);
    FileStatus[] status = fs.listStatus(path);
    for (FileStatus f : status) {
      System.out.println(f.getPath().toString());
    }
  }

  public static void main(String[] args) throws IOException, URISyntaxException {
    String file = "hdfs://hadoop130:9000/user/hadoop/hotel24h/test_from_java.txt";
//    String words = "测试向HDFS里面写文件！";
//    writeToHDFS(file, words);
//    String path = "hdfs://192.168.13.20:9000/user/hadoop/";
    readFromHDFS(file);
  }
}