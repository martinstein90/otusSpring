package domain;

public class Result {
    private String result;
    private int lowerLimitScore;
    private int upperLimitScore;

    public Result(String result, int lowerLimitScore, int upperLimitScore) {
        this.result = result;
        this.lowerLimitScore = lowerLimitScore;
        this.upperLimitScore = upperLimitScore;
    }

    public String getResult() {
        return result;
    }

    public int getLowerLimitScore() {
        return lowerLimitScore;
    }

    public int getUpperLimitScore() {
        return upperLimitScore;
    }
}
