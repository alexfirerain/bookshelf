public class Main {
    /**
     * Модель книжной полки, сортированной по числу страниц.
     * Книги пронумерованы, как ячейки массива.
     */
    static int[] bookShelf = { 14, 16, 19, 32, 32, 32, 56, 69, 72 };

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
         * Длина области поиска (количество книг в ней).
         * @return разность между правым и левым краями области.
         */
        int length() {
            return right - left;
        }

        /**
         * Выдаёт середину области поиска.
         * @return номер книги посередине области (по правилам целочисленного деления).
         */
        int median() {
            return left + length() / 2;
        }

        /**
         * Сокращает область поиска до её правой части,
         * устанавливая левый край по середине.
         */
        void takeRightHalf() {
            left = median();
        }

        /**
         * Сокращает область поиска до её левой части, устанавливая правый край по середине.
         */
        void takeLeftHalf() {
            right = median();
        }

        /**
         * Сообщает, является ли область поиска охватывающей всего одну книгу.
         * @return {@code true}, если длина области = 1.
         */
        boolean isSingularity() {
            return length() == 1;
        }

        Scope(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        int sample = 60;
        System.out.println(defineHowManyBooksBigger(bookShelf, sample));
    }


    /**
     * Функция поиска, сколько книг на полке содержат больше страниц, чем запрашивается.
     * @param shelf  нумерованная полка книг, сортированных по возрастанию числа страниц.
     * @param than   запрашиваемое число, с которым интересно сравнить книги на полке.
     * @return  количество книг на полке с количеством страниц толще запрошенного.
     */
    public static int defineHowManyBooksBigger(int[] shelf, int than) {
        Scope scope = new Scope(0, shelf.length);
        int leastBigger = 0;


        while (!scope.isSingularity()){
            leastBigger = scope.median();
            if (shelf[leastBigger] <= than) {
                scope.takeRightHalf();
            } else {
                scope.takeLeftHalf();
            }

        }

        if (shelf[leastBigger] <= than) {
            leastBigger++;
        } else {
            leastBigger--;
        }


        return shelf.length - leastBigger;
    }


}
