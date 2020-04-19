package top.longmarch.wx.entity;

public class FwTag {

    private String name;
    private String content;
    private String rank;
    private Integer score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "FwTag{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", rank='" + rank + '\'' +
                ", score=" + score +
                '}';
    }
}
