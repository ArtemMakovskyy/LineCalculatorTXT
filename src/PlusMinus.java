

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PlusMinus extends ReplaceTech {

    private char[] quastion;

    private double collectAllPrintTest(@NotNull String quastion_txt) {
        quastion = quastion_txt.toCharArray();

//======================берет данные из обджекта===============
        ArrayList<Object> obj_dateAndSign = new ArrayList<>(getArrays(quastion));
        int dateNumber = (int) obj_dateAndSign.get(0);
        int signNumber = (int) obj_dateAndSign.get(1);

        for (int i = 0; i < 2; i++) obj_dateAndSign.remove(0);
        ArrayList<Double> digits = new ArrayList<>();
        ArrayList<Character> sign = new ArrayList<>();

        for (int i = 0; i < dateNumber; i++) {
            digits.add((Double) obj_dateAndSign.get(0));
            obj_dateAndSign.remove(0);
        }
        for (int i = 0; i < signNumber; i++) {
            sign.add((Character) obj_dateAndSign.get(0));
            obj_dateAndSign.remove(0);
        }
//======================конец берет данные из обжекта===============


        for (int i = 0; i < digits.size(); i++) digits.get(i);
        for (int i = 0; i < sign.size(); i++) sign.get(i);

        double d = calculate(digits, sign);
//        System.out.println(d);
//        System.out.println("END   double collectAllPrintTest(String quastion_txt)");
        return d;
    }

    protected ArrayList<Object> getArrays(char @NotNull [] array) {
        ArrayList<Character> quastion = new ArrayList<>();
        for (int i = 0; i < array.length; i++) quastion.add(array[i]);

        ArrayList<Double> digits = new ArrayList<>();//создали массив из цифр
        ArrayList<Character> signs = new ArrayList<>();//создали массив из знаков
//        System.out.println(quastion + " first quastion");
        String sa = "";
        String sb1;
        String sb2;


        //первые цифры
        if (quastion.get(0) == '+' || quastion.get(0) == '-') {
//            System.out.println("clear minus");
            sa = sa + quastion.get(0);
            quastion.remove(0);
        }

        while (true) {
            if (
                    (quastion.get(0) == '.' || quastion.get(0) == '1' || quastion.get(0) == '2'
                            || quastion.get(0) == '3' || quastion.get(0) == '4' || quastion.get(0) == '5'
                            || quastion.get(0) == '6' || quastion.get(0) == '7' || quastion.get(0) == '8'
                            || quastion.get(0) == '9' || quastion.get(0) == '0' || quastion.get(0) == ',')
            ) {
                sa = sa + quastion.get(0);
                quastion.remove(0);
            }
            if (quastion.size() == 0) {
                digits.add(Double.valueOf(sa));
                sa = "";
                break;
            }
            if (quastion.get(0) == '+' || quastion.get(0) == '-' || quastion.get(0) == '*' || quastion.get(0) == '/') {
                digits.add(Double.valueOf(sa));
                sa = "";
                break;
            }

        }
//
//        System.out.println("  *************second calculation / have digit***********");
        start:
        while (quastion.size() > 0) {
            //===========================================... *-1 *+1 /-1 /+1================================
            if (
                    (quastion.get(0) == '*' || quastion.get(0) == '/')
                            && (quastion.get(1) == '+' || quastion.get(1) == '-')
                            && (quastion.get(2) == '0' || quastion.get(2) == '1'
                            || quastion.get(2) == '2' || quastion.get(2) == '3'
                            || quastion.get(2) == '4' || quastion.get(2) == '5'
                            || quastion.get(2) == '6' || quastion.get(2) == '7'
                            || quastion.get(2) == '8' || quastion.get(2) == '9'
                            || quastion.get(2) == '.')) {
//                System.out.println("... *-1 *+1 /-1 /+1");
                signs.add(quastion.get(0));
                for (int i = 0; i < 2; i++) sa = sa + quastion.get(i + 1);
                for (int i = 0; i < 3; i++) quastion.remove(0);
                if (quastion.size() == 0) {//если закончилсы квест
                    digits.add(Double.valueOf(sa));
                    sa = "";
                    break start;
                }
                if (quastion.get(0) == '+' || quastion.get(0) == '-' || quastion.get(0) == '*' || quastion.get(0) == '/') {
                    digits.add(Double.valueOf(sa));
                    sa = "";
                    continue start;
                }
                while (quastion.get(0) == '0' || quastion.get(0) == '1'
                        || quastion.get(0) == '2' || quastion.get(0) == '3'
                        || quastion.get(0) == '4' || quastion.get(0) == '5'
                        || quastion.get(0) == '6' || quastion.get(0) == '7'
                        || quastion.get(0) == '8' || quastion.get(0) == '9'
                        || quastion.get(0) == '.') {

                    sa = sa + quastion.get(0);
                    quastion.remove(0);
                    if (quastion.size() == 0) {//если закончилсы квест
                        digits.add(Double.valueOf(sa));
                        sa = "";
                        break start;
                    }
                    if (quastion.get(0) == '+' || quastion.get(0) == '-' || quastion.get(0) == '*' || quastion.get(0) == '/') {
                        digits.add(Double.valueOf(sa));
                        sa = "";
                        continue start;
                    }
                }
            }

//===========================================END... *-1 *+1 /-1 /+1==========================================
// ===========================================...    -+1 +-1==================================================
            if (
                    (quastion.get(0) == '+' && quastion.get(1) == '-')
                            || (quastion.get(0) == '-' && quastion.get(1) == '+')
                            && (quastion.get(2) == '0' || quastion.get(2) == '1'
                            || quastion.get(2) == '2' || quastion.get(2) == '3'
                            || quastion.get(2) == '4' || quastion.get(2) == '5'
                            || quastion.get(2) == '6' || quastion.get(2) == '7'
                            || quastion.get(2) == '8' || quastion.get(2) == '9'
                            || quastion.get(2) == '.')) {
//                System.out.println("...    -+1 +-1");
                signs.add('-');
                sa = sa + quastion.get(2);
                for (int i = 0; i < 3; i++) quastion.remove(0);
                if (quastion.size() == 0) {//если закончилсы квест
                    digits.add(Double.valueOf(sa));
                    sa = "";
                    break start;
                }
                if (quastion.get(0) == '+' || quastion.get(0) == '-' || quastion.get(0) == '*' || quastion.get(0) == '/') {
                    digits.add(Double.valueOf(sa));
                    sa = "";
                    continue start;
                }
                while (quastion.get(0) == '0' || quastion.get(0) == '1'
                        || quastion.get(0) == '2' || quastion.get(0) == '3'
                        || quastion.get(0) == '4' || quastion.get(0) == '5'
                        || quastion.get(0) == '6' || quastion.get(0) == '7'
                        || quastion.get(0) == '8' || quastion.get(0) == '9'
                        || quastion.get(0) == '.') {
                    sa = sa + quastion.get(0);
                    quastion.remove(0);
                    if (quastion.size() == 0) {//если закончилсы квест
                        digits.add(Double.valueOf(sa));
                        sa = "";
                        ;
                        break start;
                    }
                    if (quastion.get(0) == '+' || quastion.get(0) == '-' || quastion.get(0) == '*' || quastion.get(0) == '/') {
                        digits.add(Double.valueOf(sa));
                        sa = "";
                        continue start;
                    }
                }
            }

//===========================================...    -+1 +-1===================================================================================
//================================================...    ++1 --1===========================================
            if (
                    (quastion.get(0) == '+' && quastion.get(1) == '+')
                            || (quastion.get(0) == '-' && quastion.get(1) == '-')
                            && (quastion.get(2) == '0' || quastion.get(2) == '1'
                            || quastion.get(2) == '2' || quastion.get(2) == '3'
                            || quastion.get(2) == '4' || quastion.get(2) == '5'
                            || quastion.get(2) == '6' || quastion.get(2) == '7'
                            || quastion.get(2) == '8' || quastion.get(2) == '9'
                            || quastion.get(2) == '.')) {
//                System.out.println("      ++1 --1");
                signs.add('+');
                sa = sa + quastion.get(2);
                for (int i = 0; i < 3; i++) quastion.remove(0);
                if (quastion.size() == 0) {//если закончилсы квест
                    digits.add(Double.valueOf(sa));
                    sa = "";
                    break start;
                }
                if (quastion.get(0) == '+' || quastion.get(0) == '-' || quastion.get(0) == '*' || quastion.get(0) == '/') {
                    digits.add(Double.valueOf(sa));
                    sa = "";
                    continue start;
                }
                while (quastion.get(0) == '0' || quastion.get(0) == '1'
                        || quastion.get(0) == '2' || quastion.get(0) == '3'
                        || quastion.get(0) == '4' || quastion.get(0) == '5'
                        || quastion.get(0) == '6' || quastion.get(0) == '7'
                        || quastion.get(0) == '8' || quastion.get(0) == '9'
                        || quastion.get(0) == '.') {
                    sa = sa + quastion.get(0);
                    quastion.remove(0);
                    if (quastion.size() == 0) {//если закончилсы квест
                        digits.add(Double.valueOf(sa));
                        sa = "";
                        break start;
                    }
                    if (quastion.get(0) == '+' || quastion.get(0) == '-' || quastion.get(0) == '*' || quastion.get(0) == '/') {
                        digits.add(Double.valueOf(sa));
                        sa = "";
                        continue start;
                    }
                }
            }

//================================================END...    ++1 --1===========================================
//===========================================... *1 /1 +1 -1===========================================
            if ((quastion.get(0) == '+' || quastion.get(0) == '-' || quastion.get(0) == '*' || quastion.get(0) == '/')
                    && (quastion.get(1) == '0' || quastion.get(1) == '1' || quastion.get(1) == '2' || quastion.get(1) == '3'
                    || quastion.get(1) == '4' || quastion.get(1) == '5' || quastion.get(1) == '6' || quastion.get(1) == '7'
                    || quastion.get(1) == '8' || quastion.get(1) == '9' || quastion.get(1) == '.')) {
//                System.out.println("... *1 /1 +1 -1");
                signs.add(quastion.get(0));
                sa = sa + quastion.get(1);
                for (int i = 0; i < 2; i++) quastion.remove(0);
                if (quastion.size() == 0) {//если закончилсы квест
                    digits.add(Double.valueOf(sa));
                    sa = "";
                    break start;
                }
                if (quastion.get(0) == '+' || quastion.get(0) == '-' || quastion.get(0) == '*' || quastion.get(0) == '/') {
                    digits.add(Double.valueOf(sa));
                    sa = "";
                    continue start;
                }
                while (quastion.get(0) == '0' || quastion.get(0) == '1'
                        || quastion.get(0) == '2' || quastion.get(0) == '3'
                        || quastion.get(0) == '4' || quastion.get(0) == '5'
                        || quastion.get(0) == '6' || quastion.get(0) == '7'
                        || quastion.get(0) == '8' || quastion.get(0) == '9'
                        || quastion.get(0) == '.') {
                    sa = sa + quastion.get(0);
                    quastion.remove(0);
                    if (quastion.size() == 0) {//если закончилсы квест
                        digits.add(Double.valueOf(sa));
                        sa = "";
                        break start;
                    }
                    if (quastion.get(0) == '+' || quastion.get(0) == '-' || quastion.get(0) == '*' || quastion.get(0) == '/') {
                        digits.add(Double.valueOf(sa));
                        sa = "";
                        continue start;
                    }
                }
            }
//===========================================... *1 /1 +1 -1===========================================
        }
//======================== transfer data========================
        ArrayList<Object> obj_dateAndSign = new ArrayList<>();
        int dateNumber = digits.size();//0
        int signNumber = signs.size();//1
        obj_dateAndSign.add((Object) dateNumber);
        obj_dateAndSign.add((Object) signNumber);
        for (int i = 0; i < digits.size(); i++) {
            obj_dateAndSign.add((Object) digits.get(i));//double}
        }
        for (int i = 0; i < signs.size(); i++) {
            obj_dateAndSign.add((Object) signs.get(i));//char}
        }
//        System.out.println("==================E N D ===============");

        return obj_dateAndSign;
    }

    private double calculate(ArrayList<Double> digits, ArrayList<Character> sign) {

        int rezult = 0;
        int a = 0, b = 1;

        start:
        while (true) {
            if (sign.size() == 0) {
//                System.out.print(" = ");
                break start;
            }

            if (sign.get(0) == '*' | sign.get(0) == '/') {
                if (sign.get(0) == '*') {
                    digits.set(1, digits.get(0) * digits.get(1));
                } else {
                    digits.set(1, digits.get(0) / digits.get(1));
                }
                digits.remove(0);
                sign.remove(0);
                continue start;
            }

            if (sign.size() > 1) {

                if (sign.get(0) == '+' & sign.get(1) == '*' ||
                        sign.get(0) == '+' & sign.get(1) == '/' ||
                        sign.get(0) == '-' & sign.get(1) == '*' ||
                        sign.get(0) == '-' & sign.get(1) == '/') {
                    if (sign.get(1) == '*') {
                        digits.set(2, digits.get(1) * digits.get(2));
                    } else {
                        digits.set(2, digits.get(1) / digits.get(2));
                    }
                    digits.remove(1);
                    sign.remove(1);
                    continue start;
                }

                if (sign.get(0) == '+' & sign.get(1) == '+' ||
                        sign.get(0) == '+' & sign.get(1) == '-' ||
                        sign.get(0) == '-' & sign.get(1) == '-' ||
                        sign.get(0) == '-' & sign.get(1) == '+') {
                    if (sign.get(0) == '+') {
                        digits.set(1, digits.get(0) + digits.get(1));
                    } else {
                        digits.set(1, digits.get(0) - digits.get(1));
                    }
                    digits.remove(0);
                    sign.remove(0);
                    continue start;
                }
            }

            if (sign.get(0) == '+' || sign.get(0) == '-') {
                if (sign.get(0) == '+') {
                    digits.set(1, digits.get(0) + digits.get(1));
                } else {
                    digits.set(1, digits.get(0) - digits.get(1));
                }
                digits.remove(0);
                sign.remove(0);
                continue start;
            }
        }
//        System.out.println(digits);
//        System.out.println(sign);
        return digits.get(0);
    }

    private @NotNull
    ArrayList<Character> getRezultArrChar(char @NotNull [] rez_txt) {
        String txt = "";
        for (int i = 0; i < rez_txt.length; i++) {
            txt = txt + rez_txt[i];
        }
        char[] c = Double.toString(collectAllPrintTest(txt)).toCharArray();
        ArrayList<Character> archar = new ArrayList<>();
        for (int i = 0; i < c.length; i++) {
            archar.add(c[i]);
        }
        return archar;
    }

    public double rezult(String str) {
        ///++++++++++++++++++++++++++++++++++++++++++++++++++++****************************
        System.out.print(str);
        ///++++++++++++++++++++++++++++++++++++++++++++++++++++****************************
        start:
        while (true) {
            ArrayList<Character> listWithBrackets = txt_toArray(str);// изначаьный стринг в эррей
            int[] brackets = findBrackets(listWithBrackets);//координаты двух скобок
            int countBrackets;
            ArrayList<Character> dellBracketsAll = new ArrayList<>(listWithBrackets);
            int length = 0;
            char[] temp;

            if (!isBrackets(listWithBrackets)) {
//                System.out.println("скобок нет");
                double rezult = collectAllPrintTest(str);
                ///++++++++++++++++++++++++++++++++++++++++++++++++++++****************************
                System.out.print(" = ");
                DecimalFormat f = new DecimalFormat("##.00");
                System.out.print(f.format(rezult));
                ///++++++++++++++++++++++++++++++++++++++++++++++++++++****************************
                System.out.println();

                break start;
            } else {
//                System.out.println("скобки есть");
                if (brackets[0] == 0 & brackets[2] == 2) {
//                    System.out.println("первая скобка");
                    /**длина массива для вычисления*/
                    length = brackets[1] - brackets[0] - 1;
                    temp = new char[length];
                    for (int i = 0; i < temp.length; i++) {
                        temp[i] = listWithBrackets.get(i + 1);
                    }
                    //вычисляем текст и добавляем результат в массив
                    ArrayList<Character> c1 = new ArrayList<>(getRezultArrChar(temp));
                    // вставлЯем в старый массив
                    for (int i = 0; i < brackets[1] + 1; i++) {
                        dellBracketsAll.remove(0);
                    }
                    for (int i = 0; i < dellBracketsAll.size(); i++) {
                        c1.add(dellBracketsAll.get(i));
                    }
                    String s = "";
                    for (char c : c1) s = s + c;
                    str = s;

                    listWithBrackets.clear();
//                    listWithBrackets = new ArrayList<>(txt_toArray(str));
//                    temp = new char[0];
                    continue start;
                }
                if (brackets[0] == 1 & brackets[2] == 2 & brackets[3] == 1) {
//                    System.out.println("сначала минус, потом первая скобка, кавычек две");
                    /////////удаляем первый минус, возможно смещщаем все данные на один влево

                    /**длина массива для вычисления*/
                    listWithBrackets.remove(0);//del minus
                    length = brackets[1] - brackets[0] - 1;
                    temp = new char[length];
                    for (int i = 0; i < temp.length; i++) {
                        temp[i] = listWithBrackets.get(i + 1);
                    }
//                    for (int i = 0; i < temp.length; i++) {
//                        System.out.println(temp[i]);
//                    }
                    //вычисляем текст и добавляем результат в массив
                    ArrayList<Character> c1 = new ArrayList<>(getRezultArrChar(temp));
                    // вставлЯем в старый массив
                    for (int i = 0; i < brackets[1] + 1; i++) {
                        dellBracketsAll.remove(0);
                    }
                    for (int i = 0; i < dellBracketsAll.size(); i++) {
                        c1.add(dellBracketsAll.get(i));
                    }
                    String s = "-";
                    for (char c : c1) s = s + c;
                    str = s;
                    listWithBrackets.clear();
//                    listWithBrackets = new ArrayList<>(txt_toArray(str));
//                    temp = new char[0];
                    continue start;
                }
                if (brackets[0] != 0 & brackets[1] == listWithBrackets.size() - 1 & brackets[2] >= 2) {
//                    System.out.println("скобка не на первой позиции и на последней");
                    length = brackets[1] - brackets[0] - 1;
//                    System.out.println(length + " длина внутри скобок");
                    temp = new char[length];
                    //создали темп с вычислением
                    for (int i = 0; i < temp.length; i++) {
                        temp[i] = listWithBrackets.get(i + brackets[0] + 1);
                    }
                    //вычисляем текст и добавляем результат в массив
                    ArrayList<Character> c1 = new ArrayList<>(getRezultArrChar(temp));
                    //удаляем все что в скобках
                    for (int i = brackets[0]; i < brackets[1] + 1; i++) {
                        dellBracketsAll.remove(brackets[0]);
                    }
                    dellBracketsAll.addAll(c1);
                    str = "";
//                    temp = new char[0];
                    for (int i = 0; i < dellBracketsAll.size(); i++) {
                        str = str + dellBracketsAll.get(i);
                    }
//                    System.out.println(str);
                    continue start;
                } else if (brackets[0] != 0 & brackets[1] != listWithBrackets.size() - 1) {
//                    System.out.println("скобка не на первой позиции и не на последней");

                    length = brackets[1] - brackets[0] - 1;
//                    System.out.println(length + " длина внутри скобок");
                    temp = new char[length];

                    //создали темп с вычислением
                    for (int i = 0; i < temp.length; i++) {
                        temp[i] = listWithBrackets.get(i + brackets[0] + 1);
                    }
                    //вычисляем текст и добавляем результат в массив
                    ArrayList<Character> c1 = new ArrayList<>(getRezultArrChar(temp));

                    //удаляем все что в скобках
                    ArrayList<Character> first = new ArrayList<>();
                    ArrayList<Character> last = new ArrayList<>();
                    for (int i = 0; i < brackets[0]; i++) {
                        first.add(dellBracketsAll.get(i));
                    }
                    for (int i = brackets[1] + 1; i < dellBracketsAll.size(); i++) {
                        last.add(dellBracketsAll.get(i));
                    }
                    first.addAll(c1);
                    first.addAll(last);
                    str = "";
                    for (int i = 0; i < first.size(); i++) {
                        str = str + first.get(i);
                    }
//                    System.out.println(str);
                    dellBracketsAll.clear();
                    dellBracketsAll.addAll(first);
                    first.clear();
                    c1.clear();
                    last.clear();
                    continue start;
                }
                System.err.println("err");
                break start;
            }
        }
//        System.out.println("finish");
        return 0;
    }
}