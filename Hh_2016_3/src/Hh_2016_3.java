import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Рассмотрим все возможные числа a^b для 1<a<6 и 1<b<6: 
 22==4, 23=8, 24=16, 25=32 32=9, 33=27, 34=81, 35=243 42=16, 43=64, 44=256, 45=1024, 52=25, 53=125, 54=625, 55=3125 
 Если убрать повторения, то получим 15 различных чисел. 

 Сколько различных чисел ab для 2<a<149 и 2<b<106?

 */
public class Hh_2016_3 {
    public static void main (String[] args){

        //Set<BigInteger> setOfUnique=new HashSet<BigInteger>();
        Set<Double> setOfUnique=new HashSet<Double>();
       for(int a=2;a<149;a++) {
           for(int b=2;b<106;b++) {
               setOfUnique.add(Math.pow(a,b));
           }
       }
        System.out.println(setOfUnique.size());
    }
}
