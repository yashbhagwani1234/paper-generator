package com.mycompany.papergenerator;

import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    public Conn()
    {
        try
        {
          Class.forName("com.mysql.jdbc.Driver");
          c = DriverManager.getConnection("jdbc:mysql://localhost/createpaper","DBuser","12345");
          s = c.createStatement();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void main(String args[])
    {
        Conn obj = new Conn();
    }
}

