package com.digital.util;

import org.springframework.util.Assert;

import java.sql.*;


public class DBUtils {
    private Connection objConnection;
    private int count;
    private final ExtentReportUtil reportUtil;

    public DBUtils()	{
        reportUtil = ExtentReportUtil.getExtentReportUtil();
    }

    public Connection getOracleDBConnection( String connString, String strUser, String strPass ) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            reportUtil.reportExecutionStatus("Info", "Oracle Driver jar is not included in the build path.");
        }

        // connString should not be null
        Assert.notNull(connString, "Connection String cannot be null");

        try {
            objConnection = DriverManager.getConnection(connString,strUser,strPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(objConnection == null)
            reportUtil.reportExecutionStatus("Info", " DB connection failure");

        return objConnection;

    }

    public  String getData( String strQuery,Connection objConnection) throws SQLException {

        StringBuilder strResultSet = new StringBuilder();
        ResultSet resultSet = null;
        Statement objStmt = null;
        String separator = "";
        try {
            objStmt = objConnection.createStatement();
            reportUtil.reportExecutionStatus("Info", "Select Query : "+ strQuery + " used to fetch the data");
            resultSet = objStmt.executeQuery(strQuery);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while(resultSet.next()){
                for (int i = 1; i <= columnsNumber; i++) {
                    strResultSet.append(separator);
                    separator = ",";
                    strResultSet.append(resultSet.getString(i));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (objStmt != null) {
                objStmt.close();
                if (resultSet != null) {
                    resultSet.close();
                }
            }
        }
        return strResultSet.toString();
    }

    public int dataDelete( String strQuery, Connection objConnection) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = objConnection.prepareStatement(strQuery);
            count = stmt.executeUpdate();
            if (count > 0)
                reportUtil.reportExecutionStatus("Info","DB record deleted sucessfully with Query : " + strQuery );
            else
                reportUtil.reportExecutionStatus("Info","There is no record in DB to Delete with Query : "+strQuery );
        }
        catch (SQLException e) {
            reportUtil.reportExecutionStatus("Info","Exception during deletion"+e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return count;
    }

    public int dataUpdate( String strQuery, Connection objConnection) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = objConnection.prepareStatement(strQuery);
            count = stmt.executeUpdate();
            if (count > 0)
                reportUtil.reportExecutionStatus("Info","DB record Updated sucessfully with query :" + strQuery);
            else
                reportUtil.reportExecutionStatus("Info","There is no record in DB to update with matching query criteria" + strQuery);
        }
        catch (SQLException e) {
            reportUtil.reportExecutionStatus("Error","Exception during DB update"+e.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
        return count;
    }


    public int getRecordCount(String strQuery,Connection objConnection ) throws Exception
    {
        int intRCount = 0;
        ResultSet resultSet = null;
        Statement objStmt = null;
        try {
            objStmt = objConnection.createStatement();
            resultSet = objStmt.executeQuery(strQuery);
            if(resultSet.next() && resultSet.last())
                intRCount = resultSet.getRow();
            else
                intRCount = 0;
        }
        catch (SQLException e) {
            reportUtil.reportExecutionStatus("Error","Exception during DB update"+e.getMessage()); }
        finally {
            if (objStmt != null) {
                objStmt.close();
                if (resultSet != null) {
                    resultSet.close();
                }
            }
        }
        reportUtil.reportExecutionStatus("Info", "Select Query"+ strQuery + " record count " +intRCount);
        return intRCount;


    }
    public void closeConnection(Connection objConnection) {
        try{
            if(objConnection!=null)
                objConnection.close(); }
        catch(SQLException se){
            se.printStackTrace(); }
    }
}
