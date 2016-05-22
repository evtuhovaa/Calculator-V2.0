package CalcPack;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Allex on 18.05.2016.
 *
 * Класс предназначен для получения обратной польской записи передаваемого на его вход текстового выражения методом Бауэра-Замельтона
 *
 * getID() - получение кода символа для поиска команды в матрице команд
 *
 * getOperCode(char i,Stack st) - полученик кода операции из матрицы операций по двум индексам
 *
 * CalcWithoutExceptions(String s) - расчет результата без использования блоков try catch
 *
 * Calc(String s) - расчет результата с использованием блоков try catch
 */
public class PolskaClass {


    public static int[][] oper_mas={{5,1,1,1,1,0,1},
            {2,2,1,1,1,2,1},
            {2,2,2,1,1,2,1},
            {0,0,0,0,0,0,1},
            {0,1,1,1,1,3,1},
            {0,0,0,0,0,0,0},
            {4,4,4,0,4,4,1}};

    public static int getID(char a){
        switch (a){
            case '!':return 0;
            case '+':return 1;
            case '-':return 1;
            case '*':return 2;
            case '/':return 2;
            case '?':return 3;
            case '(':return 4;
            case ')':return 5;
            default: return 6;
        }
    }

    public static int getOperCode(char i,Stack st){
        char tmp=(char)st.pop();
        st.push(tmp);
        return oper_mas[getID(tmp)][getID(i)];
    }

    public static String CalcWithoutExceptions(String s) {
        String str =s; //"(((?1)*2)/3)+(1*4)/2";
String output_str="";
     //   System.out.println("Вас приветсвует польская запись");
     //   System.out.println("Введена строка:"+s);
output_str+="\n"+"Введена строка:"+s;
        Stack ish = new Stack();
        ArrayList outt = new ArrayList<>();
        ArrayList<Block> output=new ArrayList<Block>();// = new LinkedList<Block>();
        Stack stt = new Stack();


        ish.push('!');
        for (int i = 0; i < str.length(); i++) {
            ish.push(str.charAt(str.length() - 1 - i));
        }
        stt.push('!');





        char cur;
        while (!ish.isEmpty()) {
            cur = (char) ish.pop();
            ish.push(cur);


            int code = getOperCode(cur, stt);

            if (code == 5) {
                break;
            }
            if (code == 0) {
                System.out.print("errrrr");
                break;
            }

            if (code == 1) {
                cur = (char) ish.pop();
                stt.push(cur);
            }

            if (code == 2) {
                cur = (char) stt.pop();
                outt.add(cur);
                output.add(new Block(cur));
            }


            if (code == 3) {
                stt.pop();
                ish.pop();
            }


            if (code == 4) {
                int a = 10;
                int sum = 0;
                char c = (char) stt.pop();
                Stack<Integer> num=new Stack<Integer>();
                while (getID(c) == 6) {
                    //sum = sum * a + Integer.parseInt(c + "");
                    num.push(Integer.parseInt(c+""));
                    c = (char) stt.pop();
                    //a = a * 10;
                }

                while(!num.isEmpty()){
                    sum=sum*a+num.pop();

                }

                if (c == '?') sum = sum * (-1);
                else stt.push(c);

                outt.add(sum);
                output.add(new Block(sum));
            }


        }



        //System.out.println(outt);
        output_str+="\n"+"Польская запись:";
       // System.out.println("Польская запись:");
        for (Object current:outt) {
            //System.out.print(current.toString()+" ");
            output_str+=current.toString()+" ";
        }
       // System.out.println("Начинаем трансляцию!");

        Mathematics clas=new Mathematics(output);
        Block result=clas.calc();
       // System.out.print("Результат:"+result.chis+"/"+result.znam);
        //return result;
        if(result.znam!=0){
            output_str+="\nРезультат:"+result.chis+"/"+result.znam;
            return output_str;
        }else{return output_str+"  Ошибка! 0 в знаменателе!!!!!";}

    }

    public static String Calc(String s){
        try{return CalcWithoutExceptions(s);}catch(Exception ex){
            return ex.toString();
        }
    }

   // public static void main(String[] args) {
   //     String str = "(((?1)*2)/3)+(1*4)/2";
//
//
   //     Stack ish = new Stack();
   //     ArrayList outt = new ArrayList<>();
   //     ArrayList<Block> output=new ArrayList<Block>();// = new LinkedList<Block>();
   //     Stack stt = new Stack();
//
//
   //     ish.push('!');
   //     for (int i = 0; i < str.length(); i++) {
   //         ish.push(str.charAt(str.length() - 1 - i));
   //     }
   //     stt.push('!');
//
//
   //     System.out.println(ish);
   //     System.out.println(stt);
//
//
   //     char cur;
   //     while (!ish.isEmpty()) {
   //         cur = (char) ish.pop();
   //         ish.push(cur);
//
//
   //         int code = getOperCode(cur, stt);
//
   //         if (code == 5) {
   //             break;
   //         }
   //         if (code == 0) {
   //             System.out.print("errrrr");
   //             break;
   //         }
//
   //         if (code == 1) {
   //             cur = (char) ish.pop();
   //             stt.push(cur);
   //         }
//
   //         if (code == 2) {
   //             cur = (char) stt.pop();
   //             outt.add(cur);
   //             output.add(new Block(cur));
   //         }
//
//
   //         if (code == 3) {
   //             stt.pop();
   //             ish.pop();
   //         }
//
//
   //         if (code == 4) {
   //             int a = 10;
   //             int sum = 0;
   //             char c = (char) stt.pop();
   //             Stack<Integer> num=new Stack<Integer>();
   //             while (getID(c) == 6) {
   //                 //sum = sum * a + Integer.parseInt(c + "");
   //                 num.push(Integer.parseInt(c+""));
   //                 c = (char) stt.pop();
   //                 //a = a * 10;
   //             }
//
   //             while(!num.isEmpty()){
   //                 sum=sum*a+num.pop();
//
   //             }
//
   //             if (c == '?') sum = sum * (-1);
   //             else stt.push(c);
   //             //outt.add('.');
   //             outt.add(sum);
   //             output.add(new Block(sum));
   //         }
//
//
   //     }
//
//
   //     System.out.println(ish);
   //     System.out.println(stt);
   //     System.out.println(outt);
//
//
   //     Mathematics clas=new Mathematics(output);
   //     Block result=clas.calc();
   //     System.out.print(result.chis+"/"+result.znam);
//
//
//
//
//
   // }
}