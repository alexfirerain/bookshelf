public class Main {
    static int[] bookShelf = { 23, 32, 41, 41, 41, 50, 70 };
    static class Scope {
        int left, right;

        int length() {
            return right - left;
        }

        int median() {
            return left + length() / 2;
        }

        void takeRightHalf() {
            left = median();
        }

        void takeLeftHalf() {
            right = median();
        }

        boolean isSingularity() {
            return length() == 1;
        }

        Scope(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        int sample = 42;
        System.out.println(defineHowManyBooksBigger(bookShelf, sample));
    }


    public static int defineHowManyBooksBigger(int[] shelf, int than) {
        Scope scope = new Scope(0, shelf.length);
        int leastBigger = 0;


        while (!scope.isSingularity()){
            leastBigger = scope.median();
            if (than >= shelf[leastBigger]) {
                scope.takeRightHalf();
            } else {
                scope.takeLeftHalf();
            }

        }

        if (than >= shelf[leastBigger]) {
            leastBigger++;
        } else {
            leastBigger--;
        }


        return shelf.length - leastBigger;
    }


}
