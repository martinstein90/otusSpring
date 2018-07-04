package domain;

public class Result {
    private final String result;
    private final int lowerLimitScore;
    private final int upperLimitScore;

    public Result(String result, int lowerLimitScore, int upperLimitScore) {
        System.out.println("Resutl(String result, int lowerLimitScore, int upperLimitScore) result: " + result);
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
