/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpa;

import java.sql.*;

/**
 *
 * @author hammadashraf2083
 */
public class DAO {

    Connection con = null;

    void Connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/GPAPROJECT";
            con = DriverManager.getConnection(url, "root", "");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    int insert(REG s) {
        int res = 0;
        try {
            Connection();
            String qry = "insert into log   values (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(1, s.rollno);
            pst.setString(2, s.name);

            pst.setString(3, s.degree);
            pst.setString(4, s.username);
            pst.setString(5, s.password);

            res = pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return res;
    }

    ResultSet log(REG s) {
        ResultSet rs = null;
        try {
            Connection();
            String query = "Select * FROM log where username=? AND password=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, s.username);
            pst.setString(2, s.password);
            rs = pst.executeQuery();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        ;
        return rs;
    }

    int insertgpa(sgpa s) {
        int res = 0;
        try {
            Connection();
            String qry = "insert into sgpa values (?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(4, s.marks);
            pst.setInt(3, s.cr);
            pst.setString(5, s.subject);
            pst.setInt(1, s.rollno);
            pst.setInt(7, s.semester);
            pst.setString(6, s.gradepoints);
            pst.setDouble(2, s.sgpa);

            res = pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return res;

    }





    int insertcgpa(sgpa s) {
        int res = 0;
        try {
            Connection();
            String qry = "insert into cgpa  values (?,?,?)";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(2, s.rollno);
            pst.setDouble(1, s.cgpa);
            pst.setInt(3, s.semester);

            res = pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return res;
    }

    ResultSet cgpa(sgpa s) {
        ResultSet rs = null;
        try {
            Connection();

            String query = "Select AVG(gpa) from cgpa where rollno=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, s.rollno);
            rs = pst.executeQuery();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        ;
        return rs;
    }
        ResultSet show(sgpa s) {
        ResultSet rs = null;
        try {
            Connection();

            String query = "Select * from cgpa where rollno=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, s.rollno);
            rs = pst.executeQuery();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        ;
        return rs;
    }
    
    int update(sgpa s) {
        int res = 0;
        try {
            Connection();
            String qry = "update  cgpa set gpa=? where rollno=? AND semester=?";
            PreparedStatement pst = con.prepareStatement(qry);
           
            pst.setDouble(1, s.cgpa);
            //  pst.setString(3, s.type);
            pst.setInt(2, s.rollno);
            pst.setInt(3, s.semester);
            //pst.setString(6, s.served);
            res = pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return res;
    }

    int delete(sgpa s) {
        int res = 0;
        try {
            Connection();
            String qry = "delete from cgpa where semester=?";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(1, s.semester);

            res = pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return res;
    }
       ResultSet selectall() {
        ResultSet rs = null;
        try {
            Connection();
            String qry = "select * from Formula";
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return rs;

    }
}
