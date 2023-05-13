/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Castro_bank;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author L14Y09W37
 */
public class Connect {
    Connection connect = null;

    public Connect() {
        
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/castrobank","root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean registerUser(User user) {
        Statement stmt;
        String sql = null;
        ResultSet res = null;
        try {
            stmt = connect.createStatement();
            sql="select * from user where username='"+user.getUsername()+"'";
            res = stmt.executeQuery(sql);
            if(!res.next()) {
                sql = "insert into user values ('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getFirstname()+"', '"+user.getLastname()+"', 0)";
                stmt.executeUpdate(sql);
                return true;
            } else {
                throw new IllegalArgumentException("Username already existing");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connect.close();
            } catch(SQLException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public void addAccount(Account acc) {
        Statement stmt;
        String sql;
        ResultSet res;
        try {
            stmt = connect.createStatement();
            sql = "select * from account where accountNumber = '"+acc.getAccountNumber()+"'";
            res = stmt.executeQuery(sql);
            if(!res.next()) {
                sql = "insert into account values('"+acc.getAccountNumber()+"',"+acc.getBalance()+", '"+acc.getUsername()+"')";
                stmt.executeUpdate(sql);
            } else {
                throw new IllegalArgumentException("Account already exists!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int login(String username, String password){
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
            stmt=connect.createStatement();
            sql ="select * from user where username='"+username+"' and password='"+password+"'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                sql = "select userType from user where username = '"+username+"'";
                rs = stmt.executeQuery(sql);
                if(rs.next()) {
                    return Integer.parseInt(rs.getString("userType"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public ArrayList<Account> displayAccount(String username){
        ArrayList<Account> acc = new ArrayList<Account>();
        String sql ="select * from account where username='"+username+"'";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Account a = new Account(rs.getString(1),rs.getDouble(2)) ;
              acc.add(a);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return acc;
    }
    
    public boolean updateBalance(Account account) {
        Statement stmt;
        String sql = null;
        try {
            stmt = connect.createStatement();
            sql = "update account set balance='" + account.getBalance() +"' where accountNumber = '" + account.getAccountNumber() +"'";
            stmt.executeUpdate(sql);
            return true;
        } catch(SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void deleteAccount(Account account){
        Statement stmt;
        String sql = null;
        try {
            stmt = connect.createStatement();
            sql = "delete from account where accountNumber = '"+ account.getAccountNumber() +"'";
            stmt.executeUpdate(sql);
        } catch(SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void verify(Verification ver) {
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
            stmt = connect.createStatement();
            sql ="select * from verification where customerUsername='"+ver.getCustomerUsername()+"' and accountNumber='"+ver.getAccountNumber()+"' and amount='"+ver.getAmount()+"' and typeoftransaction='"+ver.getTypeoftransaction()+"' ";
            rs = stmt.executeQuery(sql);
            if(!rs.next()) {
                sql = "insert into verification values ('"+ver.getCustomerUsername()+"','"+ver.getAccountNumber()+"','"+ver.getAmount()+"', '"+ver.getTypeoftransaction()+"', 0)";
                stmt.executeUpdate(sql);
            } else {
                JOptionPane.showMessageDialog(null, "Process is already being initiated");
            }
        } catch(SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    
    public ArrayList<Verification> displayVerification(){
        ArrayList<Verification> verify = new ArrayList<Verification>();
        String sql ="select * from verification where status="+0+"";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Verification v = new Verification(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4));
              verify.add(v);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return verify;
    }
    
    public void updateStatus(Verification ver) {
        Statement stmt;
        String sql = null;
        try {
            stmt = connect.createStatement();
            sql = "update verification set status="+1+" where accountNumber ='"+ver.getAccountNumber()+"' and customerUsername='"+ver.getCustomerUsername()+"' ";
            stmt.executeUpdate(sql);
        } catch(SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int checkAccount(String accountNumber) {
        Statement stmt;
        String sql;
        ResultSet rs = null;
        try {
            stmt = connect.createStatement();
            sql = "select * from account where accountNumber = '"+accountNumber+"' ";
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public double getAccountBalance(String accountNumber) {
        Statement stmt;
        String sql;
        ResultSet rs = null;
        try {
            stmt = connect.createStatement();
            sql = "select * from account where accountNumber = '"+accountNumber+"' ";
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public String getAccountUsername(String accountNumber) {
        Statement stmt;
        String sql;
        ResultSet rs = null;
        try {
            stmt = connect.createStatement();
            sql = "select * from account where accountNumber = '"+accountNumber+"' ";
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return 0;
        return null;
    }
}
