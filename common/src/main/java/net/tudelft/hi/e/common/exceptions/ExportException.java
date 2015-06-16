/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tudelft.hi.e.common.exceptions;

import java.io.IOException;

/**
 *
 * @author mawdegroot
 */
public class ExportException extends IOException {

  public ExportException(String msg) {
    super(msg);
  }

  public ExportException(Exception ex) {
    super(ex);
  }

  public ExportException() {
    super();
  }
}
