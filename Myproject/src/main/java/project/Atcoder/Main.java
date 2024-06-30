package project.Atcoder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n ---- running app... ---- \n");

        // 创建一个HttpClient会话
        var session = java.net.http.HttpClient.newHttpClient();

        try {
            // 调用Login类中的login方法进行登录操作
            Login.login(session);

            // 调用Check类中的checking方法进行检查操作
            Checking.checking(session);

            // 调用Submit类中的submission方法进行提交操作
            Submission.submission(session);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" ---- finished! ----");
    }
}
