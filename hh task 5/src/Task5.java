/**
 * Рассмотрим спираль, в которой, начиная с 1 в центре, последовательно расставим числа по часовой стрелке, 
 пока не получится спираль 5 на 5 

 21 22 23 24 25
 20  7  8  9 10
 19  6  1  2 11
 18  5  4  3 12
 17 16 15 14 13
 Можно проверить, что сумма всех чисел на диагоналях равна 101. 


 Чему будет равна сумма чисел на диагоналях, для спирали размером 1039 на 1039?
 */
public class Task5 {
   public static void main(String[] args) {
        long solution2016_5;
       //solution2016_5=Task5.calculateSumOnDiagonales(1039);
       int n;
       solution2016_5=1;
       int biggestPrevious=1;
       for(n=3;n<1040;n+=2) {
           solution2016_5+=biggestPrevious*4+(n-1)+(n-1)*2+(n-1)*3+(n-1)*4;
           biggestPrevious+=(n-1)*4;
       }
       System.out.print(solution2016_5);
   }

    private static long calculateSumOnDiagonales(int i){
        long thisAdds;
        if (i==1) {
            thisAdds=1;
        }
        else {
            thisAdds =(i-1)+(i-1)*2+(i-1)*3+(i-1)*4;
        }
        return calculateSumOnDiagonales(i-2)+thisAdds;
    }
}
