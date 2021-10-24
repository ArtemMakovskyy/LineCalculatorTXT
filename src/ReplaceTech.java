

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ReplaceTech {
    private boolean flagHavePlusMinus;


    @Contract(mutates = "this")
    private boolean haveDoubleChar(char @NotNull [] firstTaskChar, char plus, char minus) {
        // наличие сдвоеных знаков везде кроме первого слота +- или -+
        for (int i = 0; i < firstTaskChar.length - 1; i++) {
            if (firstTaskChar[i] == plus & firstTaskChar[i + 1] == minus
                    || firstTaskChar[i] == minus & firstTaskChar[i + 1] == plus
                    || firstTaskChar[i] == minus & firstTaskChar[i + 1] == minus) {
                flagHavePlusMinus = true;
                break;
            } else flagHavePlusMinus = false;
        }
        return flagHavePlusMinus;
    }


    private boolean haveDoubleCharFirstSlot(char[] firstTaskChar, char plus, char minus) {
        // наличие сдвоеных знаков в первом слоте +- или -+
        if (firstTaskChar[0] == plus & firstTaskChar[1] == minus
                || firstTaskChar[0] == minus & firstTaskChar[1] == plus
                || firstTaskChar[0] == minus & firstTaskChar[1] == minus) {
            flagHavePlusMinus = true;
        } else flagHavePlusMinus = false;
        return flagHavePlusMinus;
    }

    @Contract(pure = true)
    private char[] replace_Double_Char_firstPosition(char  [] firstTaskChar, char plus, char minus, char replaseSymbol) {
        char[] test = new char[0];
        // если знаки первые
        if (firstTaskChar[0] == plus & firstTaskChar[1] == minus
                || firstTaskChar[0] == minus & firstTaskChar[1] == plus) {
            test = new char[firstTaskChar.length - 1];
            test[0] = replaseSymbol;
            for (int i = 1; i < test.length; i++) {
                test[i] = firstTaskChar[i + 1];
            }
        } else if (firstTaskChar[0] == minus & firstTaskChar[1] == minus) {
            test = new char[firstTaskChar.length - 1];
            test[0] = '+';
            for (int i = 1; i < test.length; i++) {
                test[i] = firstTaskChar[i + 1];
            }
        }
//         for (int i = 0; i < test.length; i++) {
//             System.out.println(test[i]);
//         }

        return test;
    }

    @Contract(pure = true)
    private char[] replace_Double_Char_BackPosition(char @NotNull [] firstTaskChar, char plus, char minus, char replaseSymbol) {
        char[] test = new char[0];
        int position_minus;
        int position_plus;
        for (int i = 0; i < firstTaskChar.length - 1; i++) {
            if (firstTaskChar[i] == plus & firstTaskChar[i + 1] == minus
                    || firstTaskChar[i] == minus & firstTaskChar[i + 1] == plus) {
                position_minus = i + 1;
                test = new char[firstTaskChar.length - 1];
                for (int j = 0; j < position_minus - 1; j++) test[j] = firstTaskChar[j];//вставка до
                test[position_minus - 1] = replaseSymbol;//вставка знака в нужное место
                for (int j = position_minus; j < test.length; j++) test[j] = firstTaskChar[j + 1];//вставка после
//                for (int j = 0; j < test.length; j++) System.out.print(test[j]);//печатаем результат
            } else if (firstTaskChar[i] == minus & firstTaskChar[i + 1] == minus) {
                position_plus = i + 1;
                test = new char[firstTaskChar.length - 1];
                for (int j = 0; j < position_plus - 1; j++) test[j] = firstTaskChar[j];//вставка до
                test[position_plus - 1] = '+';//вставка знака в нужное место
                for (int j = position_plus; j < test.length; j++) test[j] = firstTaskChar[j + 1];//вставка после
//                for (int j = 0; j < test.length; j++) System.out.print(test[j]);//печатаем результат
            }
        }
//         for (int i = 0; i < test.length; i++) {
//             System.out.println(test[i]);
//         }

        return test;
    }

    protected int[] findBrackets(ArrayList<Character> AL_withBrackets) {
        ArrayList<Character> arrayListBrackets = AL_withBrackets;

        int leftBrackets = 0;
        int rightBrackets = 0;
        int allLeftBrackets = 0;
        int firstMinusBeforeRightBracket = 0;
        for (int i = 0; i < arrayListBrackets.size(); i++) {
            if (arrayListBrackets.get(i) == '(') {
                leftBrackets = i;
                allLeftBrackets++;
            }
        }

        if (arrayListBrackets.get(0) == '-') firstMinusBeforeRightBracket = 1;

        for (int i = leftBrackets; i < arrayListBrackets.size(); i++) {
            if (arrayListBrackets.get(i) == ')') {
                rightBrackets = i;
                break;
            }
        }
        int[] brackets = new int[4];
        brackets[0] = leftBrackets;
        brackets[1] = rightBrackets;
        brackets[2] = allLeftBrackets * 2;
        brackets[3] = firstMinusBeforeRightBracket;

        return brackets;
    }

    protected ArrayList<Character> txt_toArray(@NotNull String str) {
        /*текст в  контейнер*/
        char arr[] = str.toCharArray();
        ArrayList<Character> arrayListBrackets = new ArrayList<>();
        for (char c : arr) arrayListBrackets.add(c);
        return arrayListBrackets;
    }

    protected boolean isBrackets(@NotNull ArrayList<Character> allList) {
        for (char c : allList) {
            if (c == '(' | c == ')') return true;
        }
        return false;
    }

}
