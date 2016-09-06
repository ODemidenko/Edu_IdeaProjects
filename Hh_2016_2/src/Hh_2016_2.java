/**
 * Дано равенство, в котором цифры заменены на буквы: 
 wvz + zvv = rwru 
 123+322=4145
 Найдите сколько у него решений, если различным буквам соответствуют различные цифры.

 */
public class Hh_2016_2 {
    public static void main (String[] args){
        int[] digitArr=new int[10];
        int number1;
        int number2;
        int number3;
        int count=0;
        for(int w=1;w<=9;w++) {
            digitArr[0]=digitArr[7]=w;

            for(int v=1;v<=9;v++) {
                digitArr[1]=digitArr[4]=digitArr[5]=v;

                for(int z=1;z<=9;z++) {
                    digitArr[2] = digitArr[3] = z;

                    for (int r = 1; r <= 9; r++) {
                        digitArr[6] = digitArr[8] = r;

                        for(int u=1;u<=9;u++){
                            digitArr[9]=u;

                            number1=digitArr[0]*100+digitArr[1]*10+digitArr[2];
                            number2=digitArr[3]*100+digitArr[4]*10+digitArr[5];
                            number3=digitArr[6]*1000+digitArr[7]*100+digitArr[8]*10+digitArr[9];
                            if (number1+number2==number3) {
                               count++;
                                System.out.println(((Integer) number1).toString()+"+"+number2+"="+number3);
                            }
                        }
                    }
                }
            }
        }
        System.out.print(count);
    }
}
