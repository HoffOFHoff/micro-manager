///////////////////////////////////////////////////////////////////////////////
// AUTHOR:       Henry Pinkard, henry.pinkard@gmail.com
//
// COPYRIGHT:    University of California, San Francisco, 2015
//
// LICENSE:      This file is distributed under the BSD license.
//               License text is included with the source distribution.
//
//               This file is distributed in the hope that it will be useful,
//               but WITHOUT ANY WARRANTY; without even the implied warranty
//               of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
//
//               IN NO EVENT SHALL THE COPYRIGHT OWNER OR
//               CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
//               INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES.
//

package org.micromanager.magellan.internal.misc;

import ij.IJ;
import org.micromanager.magellan.internal.main.Magellan;

/**
 *
 * @author bidc
 */
public class Log {
   
   public static void log(String message) {
      Magellan.getStudio().logs().showError(message);
   }

   public static void log(String message, boolean show) {
      try {
         Magellan.getCore().logMessage(message);
      } catch (NoClassDefFoundError e) {
        //running outside of MM, ignore
      }
      if (show) {
         Magellan.getStudio().logs().showError(message);
      }
   }

   public static void log(Exception e) {
      Magellan.getStudio().logs().showError(e);
   }

   public static void log(Exception e, boolean show) {
      try {
         Magellan.getCore().logMessage(e.toString());
         Magellan.getCore().logMessage(getStackTraceAsString(e));
         Magellan.getCore().logMessage(e.getMessage() == null ? e.toString() : e.getMessage());
      } catch (NoClassDefFoundError ex) {
         //running outside of MM, ignore
      }
      if (show) {
         Magellan.getStudio().logs().showError(e);
      }
   }
   
   private static String getStackTraceAsString(Throwable aThrowable) {
      String result = "";
      for (StackTraceElement line : aThrowable.getStackTrace()) {
         result += "  at " + line.toString() + "\n";
      }
      Throwable cause = aThrowable.getCause();
      if (cause != null) {
         return result + "Caused by: " + cause.toString() + "\n" + getStackTraceAsString(cause);
      } else {
         return result;
      }
   }

}
