// 下記サイトにて自動生成&toString()追記
// http://www.jsonschema2pojo.org/
package spring.persistence.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "url",
    "labels",
    "state"
})
public class Result {

    @JsonProperty("url")
    private String url;
    @JsonProperty("labels")
    private List<Label> labels = null;
    @JsonProperty("state")
    private int state;

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("labels")
    public List<Label> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    @JsonProperty("state")
    public int getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 犬と猫のどちらと認識したのか
     * @return "犬" or "猫"
     */
    public String judge() {
		return (getDogScore() > getCatScore()) ? "犬" : "猫";
	}

    public double getDogScore() {
		if (labels.get(0).getLabel().equals("犬")) {
			return labels.get(0).getScore();
		} else {
			return labels.get(1).getScore();
		}
	}

	public double getCatScore() {
		if (labels.get(0).getLabel().equals("猫")) {
			return labels.get(0).getScore();
		} else {
			return labels.get(1).getScore();
		}
	}

	@Override
	public String toString() {
		return "Result [url=" + url + ", labels=" + labels + ", state=" + state + "]";
	}

}
