package CalcPack;

/**
 * Created by Allex on 18.05.2016.
 *
 * Класс для получения НОК и НОД
 *
 * gcd(int a,int b) - получение НОД
 *
 * lcm(int a,int b) - полуение НОК
 */
public class NOKNOD {


    public static int gcd(int a,int b){
        return b == 0 ? a : gcd(b,a % b);
    }

   public static int lcm(int a,int b){
        return a / gcd(a,b) * b;
    }


}
