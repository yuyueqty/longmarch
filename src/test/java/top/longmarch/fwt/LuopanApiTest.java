package top.longmarch.fwt;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class LuopanApiTest {

    static String end = "\r\n";
    static String twoHyphens = "--";
    static String boundary = "abcdefghijk";
    static String file_path = "D:\\a.jpg";

    public static void main(String[] args) throws IOException {
        UpLoadPickture.uploadPickture(file_path);
//        File file = new File(file_path);

    }

    private static void aaa() {
        String file_path = "C:\\Users\\yuyue\\Pictures\\Camera Roll\\u=1894603084,3089086317&fm=26&gp=0.jpg";
        File file = new File(file_path);

        Map<String, String> params = new HashMap();
        params.put("file", file.getName());
        try {
            URL realUrl = new URL("http://39.106.182.255:8080/api/faceAi");
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=aaa");

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());

            StringBuilder request = new StringBuilder();

            for (String key : params.keySet()) {
                request.append(key + "=" + URLEncoder.encode(params.get(key), "UTF-8") + "&");
            }
            out.writeBytes("file="+file.getName());
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String result = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readContentFromPost() throws IOException {
        String file_path = "C:\\Users\\yuyue\\Pictures\\Camera Roll\\u=1894603084,3089086317&fm=26&gp=0.jpg";
        File file = new File(file_path);
        // Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL("http://39.106.182.255:8080/api/faceAi");
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        connection.setRequestMethod("POST");
        // Post 请求不能使用缓存
        connection.setUseCaches(false);
        //设置本次连接是否自动重定向
        connection.setInstanceFollowRedirects(true);
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数
        connection.setRequestProperty("Content-Type","multipart/form-data");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection
                .getOutputStream());
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
        String content = "file=" + URLEncoder.encode(file.getName(), "UTF-8");
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        out.writeBytes(content);
        //流用完记得关
        out.flush();
        out.close();
        //获取响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }
        reader.close();
        //该干的都干完了,记得把连接断了
        connection.disconnect();
    }


}
