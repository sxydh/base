package cn.net.bhe.basics.keep.logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;


/**
 * 建议覆盖实现org.apache.log4j.jdbc.JDBCAppender
 * 原因详见http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/jdbc/JDBCAppender.html
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JDBCAppender extends org.apache.log4j.AppenderSkeleton
    implements org.apache.log4j.Appender {

  /**
   * URL of the DB for default connection handling
   */
  protected String databaseURL = "jdbc:odbc:myDB";

  /**
   * User to connect as for default connection handling
   */
  protected String databaseUser = "me";

  /**
   * User to use for default connection handling
   */
  protected String databasePassword = "mypassword";

  /**
   * Connection used by default.  The connection is opened the first time it
   * is needed and then held open until the appender is closed (usually at
   * garbage collection).  This behavior is best modified by creating a
   * sub-class and overriding the <code>getConnection</code> and
   * <code>closeConnection</code> methods.
   */
  protected Connection connection = null;

  /**
   * Stores the string given to the pattern layout for conversion into a SQL
   * statement, eg: insert into LogTable (Thread, Class, Message) values
   * ("%t", "%c", "%m").
   *
   * Be careful of quotes in your messages!
   *
   * Also see PatternLayout.
   */
  protected String sqlStatement = "";

  /**
   * size of LoggingEvent buffer before writting to the database.
   * Default is 1.
   */
  protected int bufferSize = 1;

  /**
   * ArrayList holding the buffer of Logging Events.
   */
protected ArrayList buffer;

  /**
   * Helper object for clearing out the buffer
   */
  protected ArrayList removes;
  
  private boolean locationInfo = false;

  public JDBCAppender() {
    super();
    buffer = new ArrayList(bufferSize);
    removes = new ArrayList(bufferSize);
  }

  /**
   * Gets whether the location of the logging request call
   * should be captured.
   *
   * @since 1.2.16
   * @return the current value of the <b>LocationInfo</b> option.
   */
  public boolean getLocationInfo() {
    return locationInfo;
  }
  
  /**
   * The <b>LocationInfo</b> option takes a boolean value. By default, it is
   * set to false which means there will be no effort to extract the location
   * information related to the event. As a result, the event that will be
   * ultimately logged will likely to contain the wrong location information
   * (if present in the log format).
   * <p/>
   * <p/>
   * Location information extraction is comparatively very slow and should be
   * avoided unless performance is not a concern.
   * </p>
   * @since 1.2.16
   * @param flag true if location information should be extracted.
   */
  public void setLocationInfo(final boolean flag) {
    locationInfo = flag;
  }
  

  /**
   * Adds the event to the buffer.  When full the buffer is flushed.
   */
@Override
public void append(LoggingEvent event) {
    event.getNDC();
    event.getThreadName();
    // Get a copy of this thread's MDC.
    event.getMDCCopy();
    if (locationInfo) {
      event.getLocationInformation();
    }
    event.getRenderedMessage();
    event.getThrowableStrRep();
    buffer.add(event);

    if (buffer.size() >= bufferSize)
      flushBuffer();
  }

  /**
   * By default getLogStatement sends the event to the required Layout object.
   * The layout will format the given pattern into a workable SQL string.
   *
   * Overriding this provides direct access to the LoggingEvent
   * when constructing the logging statement.
   *
   */
  protected String getLogStatement(LoggingEvent event) {
    return getLayout().format(event);
  }

  /**
   *
   * Override this to provide an alertnate method of getting
   * connections (such as caching).  One method to fix this is to open
   * connections at the start of flushBuffer() and close them at the
   * end.  I use a connection pool outside of JDBCAppender which is
   * accessed in an override of this method.
   * */
  protected void execute(String sql) throws SQLException {

    Connection con = null;
    Statement stmt = null;

    try {
        con = getConnection();

        stmt = con.createStatement();
        stmt.executeUpdate(sql);
    } finally {
        if(stmt != null) {
            stmt.close();
        }
        closeConnection(con);
    }

    //System.out.println("Execute: " + sql);
  }


  /**
   * Override this to return the connection to a pool, or to clean up the
   * resource.
   *
   * The default behavior holds a single connection open until the appender
   * is closed (typically when garbage collected).
   */
  protected void closeConnection(Connection con) {
  }

  /**
   * Override this to link with your connection pooling system.
   *
   * By default this creates a single connection which is held open
   * until the object is garbage collected.
   */
  protected Connection getConnection() throws SQLException {
      if (!DriverManager.getDrivers().hasMoreElements())
         setDriver("sun.jdbc.odbc.JdbcOdbcDriver");

      if (connection == null) {
        connection = DriverManager.getConnection(databaseURL, databaseUser,
                    databasePassword);
      }

      return connection;
  }

  /**
   * Closes the appender, flushing the buffer first then closing the default
   * connection if it is open.
   */
  @Override
public void close()
  {
    flushBuffer();

    try {
      if (connection != null && !connection.isClosed())
          connection.close();
    } catch (SQLException e) {
        errorHandler.error("Error closing connection", e, ErrorCode.GENERIC_FAILURE);
    }
    this.closed = true;
  }

  /**
   * loops through the buffer of LoggingEvents, gets a
   * sql string from getLogStatement() and sends it to execute().
   * Errors are sent to the errorHandler.
   *
   * If a statement fails the LoggingEvent stays in the buffer!
   */
  static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  
  public void flushBuffer() {
    //Do the actual logging
    removes.ensureCapacity(buffer.size());
    for (Iterator i = buffer.iterator(); i.hasNext();) {
      LoggingEvent logEvent = (LoggingEvent)i.next();
      try {
          String sql = "insert into t_log4j(logdate, priority, pos, throwable, message, thread) values(?, ?, ?, ?, ?, ?)";

          Connection con = null;
          PreparedStatement stmt = null;

          try {
              con = getConnection();

              stmt = con.prepareStatement(sql);
              stmt.setString(1, simpleDateFormat.format(new Date()));
              stmt.setString(2, logEvent.getLevel().toString());
              stmt.setString(3, logEvent.getLoggerName());
              ThrowableInformation throwableInformation = logEvent.getThrowableInformation();
              if (throwableInformation != null && throwableInformation.getThrowable() != null) {
                  stmt.setString(4, ExceptionUtils.getStackTrace(throwableInformation.getThrowable()));
              }
              stmt.setString(5, logEvent.getMessage().toString());
              stmt.setString(6, logEvent.getThreadName());
              stmt.execute();
          } finally {
              if(stmt != null) {
                  stmt.close();
              }
              closeConnection(con);
          }
      }
      catch (SQLException e) {
        errorHandler.error("Failed to excute sql", e,
               ErrorCode.FLUSH_FAILURE);
      } finally {
        removes.add(logEvent);
      }
    }
    
    // remove from the buffer any events that were reported
    buffer.removeAll(removes);
    
    // clear the buffer of reported events
    removes.clear();
  }


  /** closes the appender before disposal */
  @Override
public void finalize() {
    close();
  }


  /**
   * JDBCAppender requires a layout.
   * */
  @Override
public boolean requiresLayout() {
    return true;
  }


  /**
   *
   */
  public void setSql(String s) {
    sqlStatement = s;
    if (getLayout() == null) {
        this.setLayout(new PatternLayout(s));
    }
    else {
        ((PatternLayout)getLayout()).setConversionPattern(s);
    }
  }


  /**
   * Returns pre-formated statement eg: insert into LogTable (msg) values ("%m")
   */
  public String getSql() {
    return sqlStatement;
  }


  public void setUser(String user) {
    databaseUser = user;
  }


  public void setURL(String url) {
    databaseURL = url;
  }


  public void setPassword(String password) {
    databasePassword = password;
  }


  public void setBufferSize(int newBufferSize) {
    bufferSize = newBufferSize;
    buffer.ensureCapacity(bufferSize);
    removes.ensureCapacity(bufferSize);
  }


  public String getUser() {
    return databaseUser;
  }


  public String getURL() {
    return databaseURL;
  }


  public String getPassword() {
    return databasePassword;
  }


  public int getBufferSize() {
    return bufferSize;
  }


  /**
   * Ensures that the given driver class has been loaded for sql connection
   * creation.
   */
  public void setDriver(String driverClass) {
    try {
      Class.forName(driverClass);
    } catch (Exception e) {
      errorHandler.error("Failed to load driver", e,
             ErrorCode.GENERIC_FAILURE);
    }
  }
}

