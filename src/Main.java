import java.util.Arrays;

public class Main {
    /**
     * Модель книжной полки, сортированной по числу страниц.
     * Книги пронумерованы, как ячейки массива.
     */
    static final int[] bookShelf = { 14, 16, 19, 32, 32, 32, 56, 69, 72 };

    /**
     * Модель области поиска книги на полке.
      */
    static class Scope {
        /**
         * Левый край области поиска.
         * Соответствует номеру первой книги, которая входит в область.
         */
        int left;

        /**
         * Правый край области поиска. Соответствует следующему номеру,
         * который НЕ попадает в область поиска, или общему числу книг на полке,
         * если область поиска упирается в правый край.
         */
        int right;

        /**
         * Выдаёт середину области поиска.
         * @return номер книги посередине области (по правилам целочисленного деления).
         */
        int median() {
            return left + (right - left) / 2;
        }

        /**
         * Сокращает область поиска до её правой части,
         * устанавливая левый край справа от середины.
         */
        void takeRightHalf() {
            left = median() + 1;
        }

        /**
         * Сокращает область поиска до её левой части,
         * устанавливая правый край по середине.
         */
        void takeLeftHalf() {
            right = median();
        }

        /**
         * Сообщает, схлопнулась ли область поиска до пустой.
         * @return {@code true}, если длина области = 0.
         */
        boolean isSingularity() {
            return right == left;
        }

        /**
         * Новая область поиска с определёнными краями.
         * @param left  левый край, первая книга в области.
         * @param right правый край, следующий номер справа от области.
         */
        Scope(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        System.out.printf("Книги на полке: %s%n%n",
                Arrays.toString(bookShelf));

        int[] testCases = { 10, 22, 32, 60, 95 };

        Arrays.stream(testCases)
                .forEach(i -> checkHowManyBooksBigger(bookShelf, i));
    }


    /**
     * Функция поиска, сколько книг на полке содержат больше страниц, чем запрашивается.
     * @param shelf  нумерованная полка книг, сортированных по возрастанию числа страниц.
     * @param than   запрашиваемое число, с которым интересно сравнить книги на полке.
     * @return  количество книг на полке с количеством страниц толще запрошенного.
     */
    public static int defineHowManyBooksBigger(int[] shelf, int than) {
        Scope scope = new Scope(0, shelf.length);
        int allegedlyLeastBigger = 0;

        while (!scope.isSingularity()){
            allegedlyLeastBigger = scope.median();
            if (shelf[allegedlyLeastBigger] <= than) {
                scope.takeRightHalf();
                allegedlyLeastBigger++;
            } else {
                scope.takeLeftHalf();
            }
        }

        return shelf.length - allegedlyLeastBigger;
    }

    public static void checkHowManyBooksBigger(int[] shelf, int number) {
        System.out.printf("На полке %d книг с более чем %d страниц.%n",
                defineHowManyBooksBigger(shelf, number),
                number);
    }

}
