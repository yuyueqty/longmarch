package top.longmarch.core.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordUtil {

    private static String ALGORITHM_NAME = "MD5";
    private static int HASH_ITERATIONS = 2;
    private static String DEFAULT_PASSWORD = "111111";

    public static String password(String password, String salt) {
        return new SimpleHash(ALGORITHM_NAME ,password ,salt ,HASH_ITERATIONS).toHex();
    }

    public static String password(String password) {
        return password(password, null);
    }

    public static String defaultPassword() {
        return password(DEFAULT_PASSWORD);
    }

    public static void main(String[] args) {
        System.out.println(defaultPassword());
    }

}
