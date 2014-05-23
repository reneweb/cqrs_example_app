package de.rweber.wishlist.logging;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class WebPageHandler 
{

  private static Logger logger = Logger.getLogger("WebPageLogger");

  private static List<LogRecord> webPageLogHolder = new ArrayList<LogRecord>();

  static {
    logger.addHandler(new Handler() {
      public void publish(LogRecord logRecord) 
      {
    	  if(webPageLogHolder.size() >= 30)
    		  webPageLogHolder.clear();
    	  
    	  webPageLogHolder.add(0, logRecord);
      }

      public void flush() {
    	  webPageLogHolder.clear();
      }

      public void close() {
      }
    });
  }
  
  public static List<LogRecord> getWebPageLog()
  {	  
	  return webPageLogHolder;
  }
}