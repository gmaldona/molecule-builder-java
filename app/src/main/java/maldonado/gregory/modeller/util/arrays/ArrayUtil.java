package maldonado.gregory.modeller.util.arrays;

public class ArrayUtil {

    public static String toStr(int[] array) {
        StringBuilder arrayStr = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            arrayStr.append(array[i]);
            if (i < array.length - 1) {
                arrayStr.append(" ");
            } else arrayStr.append("\n");
        }
        return arrayStr.toString();
    }

}
