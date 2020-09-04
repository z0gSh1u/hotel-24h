package tech.zxuuu.hotel24h.hadoop;

import java.io.*;
import java.nio.charset.Charset;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.apache.commons.io.IOUtils;

/**
 * 远程SSH连接执行工具
 */
public class RemoteShellExecutor {

  private Connection conn;
  private String ip;
  private String osUsername;
  private String password;
  private String charset = Charset.defaultCharset().toString();
  private static final int TIME_OUT = 1000 * 5 * 60;

  public RemoteShellExecutor(String ip, String username, String password) {
    this.ip = ip;
    this.osUsername = username;
    this.password = password;
  }

  /**
   * 登录
   */
  private boolean login() throws IOException {
    conn = new Connection(ip);
    conn.connect();
    return conn.authenticateWithPassword(osUsername, password);
  }

  /**
   * 执行
   */
  public int exec(String cmds) throws Exception {
    InputStream stdOut = null;
    InputStream stdErr = null;
    int ret = -1;
    try {
      if (login()) {
        Session session = conn.openSession();
        session.requestPTY("bash");
        session.startShell();
        stdOut = new StreamGobbler(session.getStdout());
        stdErr = new StreamGobbler(session.getStderr());
        BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdOut));
        PrintWriter out = new PrintWriter(session.getStdin());
        out.println(cmds);
        out.println("exit");
        out.close();
        session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS, 30000);
        System.out.println("Here is the output from stdout:");
        System.out.println("<br>");
        while (true) {
          String line = stdoutReader.readLine();
          if (line == null)
            break;
          System.out.println(line);
          System.out.println("<br>");
        }
        ret = session.getExitStatus();
        session.close();
        conn.close();
      } else {
        throw new Exception("SSH Remote connect failed for: " + ip);
      }
    } finally {
      if (conn != null) {
        conn.close();
      }
      IOUtils.closeQuietly(stdOut);
      IOUtils.closeQuietly(stdErr);
    }
    return ret;
  }

  public static void main(String args[]) throws Exception {
    RemoteShellExecutor executor = new RemoteShellExecutor("hadoop130", "root", "zhuoxu");
    executor.exec("ll");
  }
}
