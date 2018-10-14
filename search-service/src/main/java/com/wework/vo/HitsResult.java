package com.wework.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * @author carl
 * @version since elastic search 6.4.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HitsResult<T> {

  private long total;
  private double max_score;
  private List<HitItem<T>> hits;

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public double getMax_score() {
    return max_score;
  }

  public void setMax_score(double max_score) {
    this.max_score = max_score;
  }

  public List<HitItem<T>> getHits() {
    return hits;
  }

  public void setHits(List<HitItem<T>> hits) {
    this.hits = hits;
  }
}
