package com.yi.seckill.dto;

import java.io.Serializable;

/**
 * 步长计数，用户订单号生成
 * @author YI
 * @date 2018-12-21 11:15:58
 */
public class SequenceInfo implements Serializable {

  /**
   * 所属表
   */
  private String name;
  /**
   * 当前值
   */
  private Integer currentValue;
  /**
   * 增加步长
   */
  private Integer step;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public Integer getCurrentValue() {
    return currentValue;
  }

  public void setCurrentValue(Integer currentValue) {
    this.currentValue = currentValue;
  }

  public Integer getStep() {
    return step;
  }

  public void setStep(Integer step) {
    this.step = step;
  }
}
