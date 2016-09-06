import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * Обычно перебор делителей заключается в переборе всех целых (как вариант: простых)
 * чисел от 2 до квадратного корня из факторизуемого числа n и в вычислении остатка от деления n на каждое из этих чисел.
 * Если остаток от деления на некоторое число i равен 0, то i является делителем n.
 * В этом случае либо n объявляется составным, и алгоритм заканчивает работу (если тестируется простота n),
 * либо n сокращается на i и процедура повторяется (если осуществляется факторизация n).
 * По достижении квадратного корня из n и невозможности сократить n ни на одно из меньших чисел n объявляется простым[1].

 Для ускорения перебора часто не проверяются чётные делители, кроме числа 2, а также делители, кратные трём, кроме числа 3. При этом тест ускоряется в три раза, так как из каждых шести последовательных потенциальных делителей необходимо проверить только два, а именно вида 6·k±1, где k — натуральное число.
 */
public class Hs_2016_4 {

    public static void main(String[] args){

        Hs_2016_4 calc=new Hs_2016_4();
        calc.calculate();

//        long dividedN;
//        long greatestDivisor=0;
//        long sumOfFactorials=0;
//        for(long n = 970000000; n<=980000000; n+=1) {
//            dividedN = n;
//            greatestDivisor = 0;
//
//            for (long c = (long) Math.sqrt(n); c > 3; c--) {
//                if (dividedN % c == 0) {
//                    dividedN /= c;
//                    greatestDivisor = c;
//
//                    break;
//                }
//            }
//
//            if (greatestDivisor == 0) {
//                greatestDivisor = n;
//
//            }
//            sumOfFactorials += greatestDivisor;
//        }
//        System.out.println(sumOfFactorials);
    }

    public void calculate() {

        ExecutorService es= Executors.newFixedThreadPool(2);
        Future<Long> result1=es.submit(new Calculator(970000000,975000000));
        Future<Long> result2=es.submit(new Calculator(975000001,980000000));

        try {
            System.out.println("Result is:"+result1.get()+result2.get());
        }
        catch (Exception e) {
        }

    }


    class Calculator implements Callable<Long> {
        long mStart=0;
        long mFinnish=0;

        @Override
        public Long call() {
            long dividedN;
            long greatestDivisor=0;
            long sumOfFactorials=0;
            for(long n = mStart; n<=mFinnish; n+=1){
                dividedN=n;
                greatestDivisor=0;

                for(long c=(long)Math.sqrt(n);c>3;c--) {
                    if (dividedN%c==0) {
                        dividedN/=c;
                        greatestDivisor=c;

                        break;
                    }
                }

                if (greatestDivisor==0) {
                    greatestDivisor=n;

                }
                sumOfFactorials+=greatestDivisor;
            }
            return sumOfFactorials;
        }

        public Calculator(long start,long finnish) {
            mStart=start;
            mFinnish=finnish;
        }
    }






    public static long factorial(long n) {
        long fact = 1; // this  will be the result
        for (long i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}

