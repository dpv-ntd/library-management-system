/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.Reader;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DPV
 */
public class ReaderDAO extends BaseDAO<Reader> {

    public ArrayList<Reader> getListReader() {
        ArrayList<Reader> listReader = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Book ORDER BY BookID";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Reader s = new Reader();
                s.setReaderID(rs.getString("ReaderID"));
                s.setReaderName(rs.getString("ReaderName"));
                s.setReaderDOB(rs.getDate("ReaderDOB"));
                s.setReaderAddress(rs.getString("ReaderAddress"));
                s.setReaderEmail(rs.getString("ReaderEmail"));
                s.setBeginDate(rs.getDate("BeginDate"));
                s.setExpirationDate(rs.getDate("ExpirationDate"));
                listReader.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReader;
    }

    public Reader getReaderToEdit(String ReaderID) {
        try {
            String sql = "SELECT * FROM Reader where ReaderID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ReaderID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Reader s = new Reader();
                s.setReaderID(rs.getString("ReaderID"));
                s.setReaderName(rs.getString("ReaderName"));
                s.setReaderDOB(rs.getDate("ReaderDOB"));
                s.setReaderAddress(rs.getString("ReaderAddress"));
                s.setReaderEmail(rs.getString("ReaderEmail"));
                s.setBeginDate(rs.getDate("BeginDate"));
                s.setExpirationDate(rs.getDate("ExpirationDate"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void createReader(String ReaderID, String ReaderName, String ReaderAddress, Date ReaderDOB, String ReaderEmail, Date BeginDate, Date ExpirationDate) {
        try {
            String sql = "INSERT INTO Reader (ReaderID, ReaderName, ReaderAddress, ReaderDOB, ReaderEmail, BeginDate, ExpirationDate) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ReaderID);
            statement.setString(2, ReaderName);
            statement.setString(3, ReaderAddress);
            statement.setDate(4, ReaderDOB);
            statement.setString(5, ReaderEmail);
            statement.setDate(6, BeginDate);
            statement.setDate(7, ExpirationDate);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteReader(String ReaderID) {
        try {
            String sql = "DELETE Reader WHERE ReaderID= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ReaderID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editReader(String ReaderID, String ReaderName, String ReaderAddress, Date ReaderDOB, String ReaderEmail, Date BeginDate, Date ExpirationDate) {
        try {
            String sql = "UPDATE Reader SET ReaderName = ?, ReaderAddress = ?, ReaderDOB = ?, ReaderEmail = ?, BeginDate = ?, ExpirationDate = ? WHERE BookID = ReaderID";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ReaderName);
            statement.setString(2, ReaderAddress);
            statement.setDate(3, ReaderDOB);
            statement.setString(4, ReaderEmail);
            statement.setDate(5, BeginDate);
            statement.setDate(6, ExpirationDate);
            statement.setString(7, ReaderID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
