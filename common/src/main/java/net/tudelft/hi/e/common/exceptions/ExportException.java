/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tudelft.hi.e.common.exceptions;

import java.io.IOException;

/**
 * ExportException is an indication that an error occurred when exporting.
 */
public class ExportException extends IOException {

  /**
   * Default ExportException.
   */
  public ExportException() {
    super();
  }

  /**
   * ExportException with a message.
   * @param msg message of the exception.
   */
  public ExportException(String msg) {
    super(msg);
  }

  /**
   * ExportException with an exception.
   * @param ex
   */
  public ExportException(Exception ex) {
    super(ex);
  }
}
