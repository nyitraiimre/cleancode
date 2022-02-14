public class WeatherItem {

    private int rowNumber;
    private double tempMaxValue;
    private double tempMinValue;
    private double tempDiff;

    public WeatherItem(int rowNumber, double tempMaxValue, double tempMinValue, double tempDiff) {
        this.rowNumber = rowNumber;
        this.tempMaxValue = tempMaxValue;
        this.tempMinValue = tempMinValue;
        this.tempDiff = tempDiff;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public double getTempMaxValue() {
        return tempMaxValue;
    }

    public void setTempMaxValue(double tempMaxValue) {
        this.tempMaxValue = tempMaxValue;
    }

    public double getTempMinValue() {
        return tempMinValue;
    }

    public void setTempMinValue(double tempMinValue) {
        this.tempMinValue = tempMinValue;
    }

    public double getTempDiff() {
        return tempDiff;
    }

    public void setTempDiff(double tempDiff) {
        this.tempDiff = tempDiff;
    }
}