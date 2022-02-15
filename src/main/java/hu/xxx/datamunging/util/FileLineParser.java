package hu.xxx.datamunging.util;

public class FileLineParser {

    public String[] parseDataLine(String dataline) {
        String[] ret = null;
        if (isNotBlank(dataline)) {
            String[] array = dataline.split(Constants.REGEXP_MULTI_SPACE);
            for (int i = 0; i < array.length; i++) {
                System.out.print("|[" + i + "]" + array[i]);
            }
            ret = array;
            System.out.println();
        }
        return ret;
    }

    private boolean isNotBlank(String dataline) {
        if (dataline == null || dataline.isBlank()) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return Constants.REGEXP_NUMBER_PATTERN.matcher(strNum).matches();
    }

}