package CalcPack;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Allex on 18.05.2016.
 * Основной класс программы ля взаимодействия с пользователем
 *
 * isFile(String inp) - определение уществования файла
 *
 * isUsualString(String inp) - определяем подходит ли входная строка под заданный шаблон
 *
 * tryCalcUsual(String str) - расчет одного выражения в обычном режиме
 *
 * tryCalcSpecial(String str) - расчет одного выражения в расширенном режиме
 *
 * CycleOfSpecialWork() - перевод программы в режим расширенной работы
 */



public class Calculator {
    public static void main(String[] args){
        if(args.length!=0){
            String cur=args[0];
            if(cur.equals("special")){
                CycleOfSpecialWork();
            }
            else {


            if(isFile(cur)){
                List<String> outt = new ArrayList<String>();
                List<String> mas=new ArrayList<String>();
                try{
                    Scanner in = new Scanner(new File(cur));
                    while (in.hasNextLine())
                        mas.add(in.nextLine());
                    in.close();
                }catch(Exception e){}
                for (String curent:mas) {
                   outt.add( tryCalcUsual(curent));
                }

                String path=System.getProperty("user.dir")+File.separatorChar+"result.xml";
                xmlClass.MakeXML(path,outt);
            }}
        }
        else{
            Scanner in = new Scanner(System.in);
            String input="";
            while(!input.equals("q")){
                System.out.println("\n\n\nВыбран обычный режим");
                System.out.println("Введи:\nстроку выражения или  \n'q' для выхода ");
                input=in.nextLine();
                if(!input.equals("q")){
                    System.out.println(tryCalcUsual(input));
                }
            }
        }
    }






    public static boolean isFile(String inp){
        try{File f=new File(inp);
            return Files.exists(f.toPath());}catch(Exception ex){return false;}

    }



    public static boolean isUsualString(String inp){
        return RegClass.checkWithRegExp(inp);
    }


    public static String tryCalcUsual(String str){
        str=new String(str).replace(" ","");
    if(isUsualString(str)){
       return PolskaClass.Calc(str);
    }
        return "eror";
}

    public static void tryCalcSpecial(String str){
        str=new String(str).replace(" ","");
        PolskaClass.Calc(str);
    }

    public static void CycleOfSpecialWork(){
        Scanner in = new Scanner(System.in);
        String input="";
        while(!input.equals("q")){
            System.out.println("Выбран спец режим");
            System.out.println("Введи:\nстроку выражения  или \n'q' для выхода");
            input=in.nextLine();
            if(!input.equals("q")){
                tryCalcSpecial(input);
            }
        }}


  //  public static List<String> getLines(String inp){
  //      File f= new File(inp);
  //      try{
  //          return Files.readAllLines(Paths.get(inp), StandardCharsets.UTF_8);
  //      }catch(Exception e){}
  //      finally{
  //          return null;
  //      }
//
  //  }

 //  public static void one_cycle_of_usual_work(){

 //      Scanner in = new Scanner(System.in);
 //      String input="";
 //      while(!input.equals("q")){
 //          System.out.println("\n\n\nВыбран обычный режим");
 //          System.out.println("Введи:\nстроку выражения или \nпуть к файлу или \n'q' для выхода в меню выбора режима");
 //          input=in.nextLine();
 //          if(!input.equals("q")){
 //              if(isFile(input)){
 //                  List<String> mas=getLines(input);
 //                  for (String curent:mas) {
 //                      tryCalcUsual(input);
 //                  }
 //              }
 //              else tryCalcUsual(input);
 //          }
 //      }
 //  }


 //  public void old_main(){
 //      Scanner in = new Scanner(System.in);
 //      String input="";
 //      while(!input.equals("close")){
 //          System.out.println("Выбери режим работы: \n 1-обычный\n 2-расширенный\n close - закрыть программу");
 //          input=in.nextLine();
 //          switch(input){
 //              case "1":{
 //                  one_cycle_of_usual_work();
 //                  break;
 //              }

 //              case "2":{
 //                  one_cycle_of_special_work();
 //                  break;
 //              }
 //              case "close":System.out.println("Программа сейчас закроется"); break;
 //              default:
 //                  System.out.println("Ошибка в команде выбора режима");
 //                  break;
 //          }
 //      }
 //  }
}


