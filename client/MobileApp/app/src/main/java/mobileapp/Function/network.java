package mobileapp.Function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class network {
    static String url = "http://ipv4.dfen.xyz:5000/";

    public static String getDoc(String key) {
        String doc = sendGet(url + "get", "key=" + key);
        return doc;
    }

    public static String setDoc(String doc) {
        String key = sendPost(url + "store", "doc=" + doc);
        return key;
    }

    public static String addDocToList(String doc, String cookies) {
        String encodedUrl = "";
        try {
            encodedUrl = java.net.URLEncoder.encode(doc, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String key = sendPost(url + "storelist", "doc=" + encodedUrl + "&" + "cookies=" + cookies);
        return key;
    }

    public static String deleteDoc(String key, String cookies) {

        String result = sendPost(url + "delete", "key=" + key + "&" + "cookies=" + cookies);
        return result;
    }

    public static String ifNameExist(String userName) {
        String encodedUserName = "";
        try {
            encodedUserName = java.net.URLEncoder.encode(userName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = sendGet(url + "check", "username=" + encodedUserName);
        return result;
    }

    public static String signIn(String userName, String password) {
        String encodedUserName = "";
        try {
            encodedUserName = java.net.URLEncoder.encode(userName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = sendPost(url + "signin", "username=" + encodedUserName + "&" + "password=" + password);
        return result;
    }

    public static void print(Object str) {
        System.out.println(str);
    }

    private static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    private static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(getDoc("6c253648e0dfff00d6aa44566f34dae651f0d2191d5bf59dd74d8ce7314cb3e7"));
//        System.out.println(setDoc("23333"));
//        System.out.println(addDocToList("big 2","002e7e436441939758c41ed393257d2cea503d0dfb234201af1bfc21bfbc6d55"));
//        print(deleteDoc("d1e9052456c98c1b624b31def8aea3e3fdaf5e578825b13ac7035dda9cd9631c","002e7e436441939758c41ed393257d2cea503d0dfb234201af1bfc21bfbc6d55"));
//        print(ifNameExist("1234"));
        print(signIn("1234", "123"));
    }
}
