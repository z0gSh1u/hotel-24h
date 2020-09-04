package tech.zxuuu.hotel24h.hadoop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;
import tech.zxuuu.hotel24h.mapper.CommentMapper;

import java.io.*;
import java.util.List;

@Service
public class CommentCollector {

  @Autowired
  CommentMapper commentMapper;

  public String getTokenizedComment() throws IOException {
    // 收集评论
    String commentToTokenize = "";
    List<String> comments = commentMapper.concatRecentComments();
    for (String comment : comments) {
      commentToTokenize += comment;
      commentToTokenize += '\n';
    }
    InputStream in_withcode = new ByteArrayInputStream(commentToTokenize.getBytes("UTF-8"));
    Reader reader = new InputStreamReader(in_withcode);
    // 分词
    Analyzer analyzer = new IKAnalyzer();
    TokenStream tokenStream = analyzer.tokenStream("", reader);
    CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
    tokenStream.reset();
    String result = "";
    while (tokenStream.incrementToken()) {
      result += charTermAttribute.toString();
      result += " ";
    }
    tokenStream.close();
    return result;
  }


}
