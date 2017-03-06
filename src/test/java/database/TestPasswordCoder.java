package database;

import org.junit.Test;

/**
 * @author Pavel Kurachenko
 * @since 1/28/2017
 */
public class TestPasswordCoder {


    @Test
    public void code(){
        String password = "pasha123";
        String result = "";
        for (int i = 0; i < password.length(); i++){
            char temp = password.charAt(i);
            if (i % 2 == 0) {
                temp += 25;
            }else {
                temp += 32;
            }
            result += temp;
        }

        System.out.println(password);
        System.out.println(result);

        String result1 = "";
        for (int i = 0; i < result.length(); i++){
            char temp = result.charAt(i);
            if (i % 2 == 0) {
                temp -= 25;
            }else {
                temp -= 32;
            }
            result1 += temp;
        }

        System.out.println(password);
        System.out.println(result1);
    }
}
