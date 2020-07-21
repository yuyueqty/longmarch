package top.longmarch.fwt;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class UpLoadPickture {

    private final String TAG = "UpLoadPickture";

    /* sumber server */
    public static void uploadPickture(final String piktrueRoute) {

        /*
         * visit server
         */
        // next line
        final String newLine = "\r\n";
        final String boundaryPrefix = "--";
        // data division
        final String BOUNDARY = UUID.randomUUID().toString();

        new Thread() {

            public void run() {
                String target = "http://39.106.182.255:8080/api/faceAi";
                URL url;
                try {
                    url = new URL(target);
                    HttpURLConnection urlConn = (HttpURLConnection) url
                            .openConnection(); // 创建一个HTTP连接
                    urlConn.setRequestMethod("POST"); // 指定使用POST请求方式
                    urlConn.setDoInput(true); // 向连接中写入数据
                    urlConn.setDoOutput(true); // 从连接中读取数据
                    urlConn.setUseCaches(false); // 禁止缓存
                    urlConn.setInstanceFollowRedirects(true); // 自动执行HTTP重定向
                    urlConn.setRequestProperty("connection", "Keep-Alive");
                    urlConn.setRequestProperty("Charset", "UTF-8");
                    urlConn.setRequestProperty("Content-Type",
                            "multipart/form-data; boundary=" + BOUNDARY); // 设置内容类型


                    // 构造传参，上传文件
                    File file = new File(piktrueRoute);
                    StringBuilder sb = new StringBuilder();
                    sb.append(boundaryPrefix);
                    sb.append(BOUNDARY);
                    sb.append(newLine);

                    /**
                     *文件参数,photo参数名可以随意修改
                     *photo 为服务器的key
                     *如果服务器设置了这个key就要改成响应的参数
                     */
                    sb.append("Content-Disposition: form-data;file=\""
                            + file.getName() + "\"" + newLine);
                    sb.append("Content-Type:multipart/form-data");
                    // 参数头设置完以后需要两个换行，然后才是参数内容
                    sb.append(newLine);
                    sb.append(newLine);

                    // 获取输出流
                    DataOutputStream out = new DataOutputStream(
                            urlConn.getOutputStream());
                    // 将参数头的数据写入到输出流中
                    out.write(sb.toString().getBytes());
                    // 数据输入流,用于读取文件数据
                    DataInputStream in = new DataInputStream(new FileInputStream(file));
                    byte[] bufferOut = new byte[1024];
                    int bytes = 0;
                    // 每次读1KB数据,并且将文件数据写入到输出流中
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    // 最后添加换行
                    out.write(newLine.getBytes());
                    in.close();

                    // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
                    byte[] end_data = (newLine + boundaryPrefix + BOUNDARY
                            + boundaryPrefix + newLine).getBytes();
                    // 写上结尾标识
                    out.write(end_data);
                    out.flush(); // 输出缓存
                    out.close(); // 关闭数据输出流
                    System.out.println(urlConn);
                    System.out.println("getResponseCode:" + urlConn.getResponseCode());

                    if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) { // 判断是否响应成功
                        InputStreamReader in1 = new InputStreamReader(
                                urlConn.getInputStream(), "utf-8"); // 获得读取的内容，utf-8获取内容的编码
                        BufferedReader buffer = new BufferedReader(in1); // 获取输入流对象
                        String inputLine = null;
                        System.out.println("inputLine:" + buffer.readLine());
                        while ((inputLine = buffer.readLine()) != null) {
                            System.out.println(inputLine + "\n");
                            try {
                                JSONObject reader = new JSONObject(inputLine);// 使用JSONObject解析
                                JSONObject reObjectData = reader
                                        .getJSONObject("data");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                        in1.close(); // 关闭字符输入流
                    }
                    urlConn.disconnect(); // 断开连接
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();

    }
}
 
