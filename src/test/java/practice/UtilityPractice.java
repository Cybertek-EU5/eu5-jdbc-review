package practice;

import utilities.DBUtils;

public class UtilityPractice {

    public static void main(String[] args) {

        String jdbc_url = "jdbc:oracle:thin:@52.87.154.190:1521:XE" ;
        String username = "hr" ;
        String password = "hr" ;

        DBUtils.createConnection(jdbc_url,username,password);


    }

}
