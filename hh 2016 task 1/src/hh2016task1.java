import java.util.ArrayList;

/**
 * Число 125874 и результат умножения его на 2 — 251748 можно получить друг из друга перестановкой цифр. 

 Найдите наименьшее положительное натуральное x такое, что числа 3*x, 6*x можно получить друг из друга перестановкой цифр.

 */
public class hh2016task1 {
    public static void main(String[] args) {

//        //for 5-digit number
//        for(int c1=1;c1<=4;c1++){
//            for(int c2=1;c2<=4;c2++) {
//                for(int c3=3;c3<=9&&c3%3==0;c3+=3) {
//                    //всего 48 комбинация
//                    // нужно умножить на 120 комбинаций в цикле, получим 5760, нормально
//                    composeAndCheckFiveDigitNumber(c1,c2,c3);
//                }
//            }
//        }

        //for 6-digit number
//        int count=0;
//        for(int c1=1;c1<=4;c1+=1){
//            for(int c2=1;c2<=4;c2+=1) {
//                for(int c3=0;c3<=9;c3+=1) {
//                    for(int c4=c3;c4<=9;c4+=1) {
//                        if ((c3+c4)%3==0) {
//                            //порядка 720 комбинация
//                            //нужно умножить на 720 комбинаций в цикле, получим 518400: на грани, но возможно
//                            //composeAndCheckSixDigitNumber(1,4,5,7);
//                            // System.out.println(++count);
//                            composeAndCheckSixDigitNumber(c1,c2,c3,c4);
//                        }
//                    }
//                }
//            }
//        }

       for(int digitsQty=4;digitsQty<7;digitsQty++) {
           int[] digitsCollection = new int[digitsQty];
           for (int c1 = 1; c1 <= 4; c1 += 1) {
               digitsCollection[0]=c1;
               recursiveDigitsGen(0,1,digitsCollection);
           }
       }
    }

    private static void recursiveDigitsGen(int startingDigit,int digitIndex, int[] digitsCollection){

       if (digitIndex<digitsCollection.length){
           for(int c = startingDigit; c <= 9; c += 1) {
               digitsCollection[digitIndex]=c;
               recursiveDigitsGen(c,digitIndex+1,digitsCollection);
           }
       }
       if (digitIndex==digitsCollection.length) {
        int sum=0;
           for(int i=0;i<digitsCollection.length;i++){
               sum+=digitsCollection[i];
           }
           if (sum%3==0) {
              composeAndCheckXDigitNumber(digitsCollection);
           }
       }
    }

    private static void composeAndCheckFiveDigitNumber(int c1,int c2, int c3) {
        //Составляем массив путем перебора доступных размещений для каждой следующей цифры: с2,с3,2*с1,2с2
        //Можем считать что c1 всегда на первом месте (в удвоенном числе он станет 2с1). Тогда размещать нужно на 1 цифру меньше
        //4! комбинаций (т.е. 24)
        int[] digits=new int[]{-1,-1,-1,-1,-1};
        int offset=0;
        int c2position=0;
        int c3position=0;
        int c4position=0;
        int c5position=0;


        digits[0]=c1;
        //todo попробовать: for(int pos1=1;pos1++<=5;) {
        //C2 positioning
        //we are strating from the second element as first is occupied with c1
        for(int pos2=1;pos2<=4;pos2+=1) {

            c2position=pos2;
            digits[c2position]=c2;

            //C3 positioning
            for(int pos3=1;pos3<=3;pos3+=1) {

              //получаем pos3-ий пустой элемент
                // (его реальная позиция будет смещена на найденное число непустых с меньшим индексом)
                offset=getOffset(digits,pos3);
                c3position=pos3+offset;
                digits[c3position]=c3;

                //2*C1 and 2*C2 positioning
                for(int pos4=1;pos4<=2;pos4+=1) {

                    offset=getOffset(digits,pos4);
                    c4position=pos4+offset;
                    digits[c4position]=2*c1;

                    for(int i=1;i<5;i++) {
                        if (digits[i]==-1){
                            c5position=i;
                            digits[c5position]=2*c2;
                            break;
                        }
                    }

                    int number=fixedArrayToNumber(digits);
                    if (doubledNumberHasSameDigits(number,digits)){
                        System.out.print(number);
                    }

                    //empty previously occupied cell
                    digits[c5position]=-1;
                    digits[c4position]=-1;

                }

                digits[c3position]=-1;

            }

            digits[c2position]=-1;
        }
    }

    private static void composeAndCheckSixDigitNumber(int c1,int c2, int c3, int c4) {
        //Составляем массив путем перебора доступных размещений для каждой следующей цифры: с2,с3,c4,2*с1,2с2
        //Можем считать что c1 всегда на первом месте (в удвоенном числе он станет 2с1). Тогда размещать нужно на 1 цифру меньше
        //5! комбинаций (т.е. 120)
        int[] digits=new int[]{-1,-1,-1,-1,-1,-1};

        digits[0]=c1;
        //todo попробовать: for(int pos1=1;pos1++<=5;) {
        //C2 positioning
        //we are strating from the second element as first is occupied with c1

        int[] digitsCollection=new int[] {2*c1,c2,2*c2,c3,c4};
        int recursionDepth=0;
        recursiveComposeAndCheck(digits,digitsCollection,recursionDepth);

    }

    private static void composeAndCheckXDigitNumber(int[] digitsCollectionBasic) {
        //Составляем массив путем перебора доступных размещений для каждой следующей цифры: с2,с3,c4,2*с1,2с2
        //Можем считать что c1 всегда на первом месте (в удвоенном числе он станет 2с1). Тогда размещать нужно на 1 цифру меньше
        //5! комбинаций (т.е. 120)
        int[] digits=new int[digitsCollectionBasic.length];
        int[] digitsCollectionFromSecondOne=new int[digitsCollectionBasic.length-1];
        for(int i=0;i<digitsCollectionBasic.length;i++){
            digits[i]=-1;
            if (i>0) {digitsCollectionFromSecondOne[i-1]=digitsCollectionBasic[i];}
        }
        digits[0]=digitsCollectionBasic[0];
        //we are strating from the second element as first is occupied with c1

        int recursionDepth=0;
        recursiveComposeAndCheck(digits,digitsCollectionFromSecondOne,recursionDepth);

    }


    private static void recursiveComposeAndCheck(int[] digits,int[] digitsCollection,int recursionDepth){

        int qtyOfEmptyCells=digits.length-1-recursionDepth;
        int offset;
        int position=0;

        if (qtyOfEmptyCells==1) {

            for(int i=1;i<digits.length;i++) {
                if (digits[i]==-1){
                    position=i;
                    digits[position]=digitsCollection[digitsCollection.length-1];
                }
            }

            int number=fixedArrayToNumber(digits);
            if (doubledNumberHasSameDigits(number,digits.clone())){
                System.out.println(number);
            }

            assert position!=0;
            digits[position]=-1;

        }
        else {

            for (int pos = 1; pos <= qtyOfEmptyCells; pos += 1) {

                offset = getOffset(digits, pos);
                position = pos + offset;
                digits[position] = digitsCollection[recursionDepth];

                recursiveComposeAndCheck(digits, digitsCollection, recursionDepth + 1);

                digits[position] = -1;
            }
        }
    }

    //checks for how much to offset for the non-empty array positions
    private static int getOffset(int[] digits, int pos) {
        int offset=0;
        //start searching from the second array element, as first (index=0) is always occupied by c1
        for (int ind=1;ind<=pos;ind+=1) {
            if (digits[ind+offset]!=-1) {
                //this is not empty element and we should offset for it;
                offset++;
                ind--;
            }
        }
        return offset;
    }

    //Comprise a number from left to right by passing the array from left to right
    private static int fixedArrayToNumber(int[] digits){
        int number=0;
        for(int pos=1;pos<=digits.length;pos+=1){
            number+=digits[pos-1]*Math.pow(10,digits.length-pos);
        }
        return number;
    }

    //double the original number and look up doubled number' digits in the original digits
    private static boolean doubledNumberHasSameDigits(int number, final int[] originalDigits) {
        int doubledNumber=number*2;
        int[] doubledDigits=new int[originalDigits.length];

        for(int i=0;i<doubledDigits.length;i++) {
            float dividedByTenPower= (float) (doubledNumber/Math.pow(10,i+1));
            int curDigit=(int)((dividedByTenPower - (int)dividedByTenPower)*10);
    //        doubledDigits[i]=curDigit;

            boolean digitFound=false;
            for(int pos=0;pos<originalDigits.length;pos+=1){
                if (originalDigits[pos]==curDigit) {
                    originalDigits[pos]=-1;
                    digitFound=true;
                    break;
                }
            }
            if (!digitFound) {
                return false;
            }
        }
        return true;
    }
}

/**
 * Created by Oleg on 05.09.2016.
 */
