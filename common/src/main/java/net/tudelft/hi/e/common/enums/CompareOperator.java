package net.tudelft.hi.e.common.enums;

/**
 * The possible compare operators.
 */
public enum CompareOperator {
  /**
   * equal.
   */
  EQ("==") {
    @Override
    public boolean matchesCompareResult(int result) {
      return result == 0;
    }
  },      /**
   * not equal.
   */
  NEQ("!=") {
    @Override
    public boolean matchesCompareResult(int result) {
      return result != 0;
    }
  },
  /**
   * greater than.
   */
  G(">") {
    @Override
    public boolean matchesCompareResult(int result) {
      return result > 0;
    }
  },
  /**
   * greater than or equal.
   */
  GEQ(">=") {
    @Override
    public boolean matchesCompareResult(int result) {
      return result >= 0;
    }
  },
  /**
   * less than.
   */
  L("<") {
    @Override
    public boolean matchesCompareResult(int result) {
      return result < 0;
    }
  },
  /**
   * less than or equal.
   */
  LEQ("<=") {
    @Override
    public boolean matchesCompareResult(int result) {
      return result <= 0;
    }
  };

  private String typeString;

  CompareOperator(String type) {
    typeString = type;
  }
  
  public abstract boolean matchesCompareResult(int result);

  @Override
  public String toString() {
    return typeString;
  }
}
