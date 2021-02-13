// 下記サイトにて自動生成&toString()追記
// http://www.jsonschema2pojo.org/
package spring.persistence.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "score",
    "label"
})
public class Label {

    @JsonProperty("score")
    private double score;
    @JsonProperty("label")
    private String label;

    @JsonProperty("score")
    public double getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(double score) {
        this.score = score;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

	@Override
	public String toString() {
		return "Label [score=" + score + ", label=" + label + "]";
	}

}
