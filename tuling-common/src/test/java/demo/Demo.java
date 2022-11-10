package demo;

/**
 * @CreateTime: 2022-11-03  14:36
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        String str = "********************************************************";
        int length = str.length();
        System.out.println(length);
        int num = 0;
        for (int i = 0; ; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
                num++;
                if (num > length - 1) {
                    break;
                }
            }
            System.out.println();
            if (num > length - 1) {
                break;
            }
        }
    }
}
