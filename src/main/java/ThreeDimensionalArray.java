public class ThreeDimensionalArray {

    public static void main(String[] args) {
        int[][][] array = new int[3][3][3];
        fillArray(array);
        printOutArrayFromBack(array);
    }

    private static void fillArray(int[][][] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                for (int k = 0; k < array[i][j].length; k++) {
                    count++;
                    array[i][j][k] = count;
                }
            }
        }
    }

    private static void printOutArrayFromBack(int[][][] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = array[i].length - 1; j >= 0; j--) {
                for (int k = array[i][j].length - 1; k >= 0; k--) {
                    System.out.print("[" + array[i][j][k] + "]");
                }
                System.out.println();
            }
        }
    }
}
