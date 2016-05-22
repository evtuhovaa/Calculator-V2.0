package CalcPack;

/**
 * Created by Allex on 18.05.2016.
 *
 * Обьект класса представляет собой элемент используемый при построении польской записи и вычислений и может олицетворть либо дробь (chis, znam, isNum=true)
 * либо оператор (funk - знак оператора, isNum=false)
 *
 * Block(char i) - конструктор для знака операции
 *
 * Block(int a) - конструктор для числа
 *
 * Block(int a,int b) - конструктор для дроби
 *
 * esierer() - функция упрощения дроби
 */
public class Block {

    public Block(char i){
        this.isNum=false;
        this.funk=i;
    }

    public Block(int a){
        this.isNum=true;

        this.chis=a;
        this.znam=1;
    }


    public Block(int a,int b){
        this.isNum=true;

        this.chis=a;
        this.znam=b;
        this.esierer();
    }
    public  void esierer(){
        int x = chis;
        int px=Math.abs(x);
        int y=znam;
        int nod=NOKNOD.gcd(px,y);
        chis=chis/nod;znam=znam/nod;
    }
    int chis;
    int znam;
    boolean isNum;
    char funk;

}
