/*
 * 
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc. Use is
 * subject to license terms.
 *  
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import exception.BookNotFoundException;
import exception.BooksNotFoundException;
import exception.OrderException;

import javax.servlet.ServletContext;

public class BookDB {

    private ArrayList<BookDetails> books;

    Connection con;

    private boolean conFree = true;

    String dbDriver ="com.mysql.jdbc.Driver"; 
    String jdbcUrl;
    String dbUser;
    String dbPwd;

    public static void main(String[] args){

    }

    public BookDB(String pwd, String user, String url) throws Exception {
        this.dbPwd = pwd;
        this.dbUser = user;
        this.jdbcUrl = url;
        try {
        	Class.forName(dbDriver);
            con = DriverManager.getConnection(this.jdbcUrl,this.dbUser,this.dbPwd);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Couldn't open connection to database: "
                    + ex.getMessage());
        }
    }
    
    public void remove() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected synchronized Connection getConnection() {
        while (conFree == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        conFree = false;
        notify();
        return con;
    }

    protected synchronized void releaseConnection() {
        while (conFree == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        conFree = true;
        notify();
    }

    public int getNumberOfBooks() throws BooksNotFoundException {
        books = new ArrayList();
        try {
            String selectStatement = "select * " + "from books";
            getConnection();
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                BookDetails bd = new BookDetails(rs.getString(1), rs
                        .getString(2), rs.getString(3), rs.getString(4), rs
                        .getFloat(5), rs.getInt(6), rs.getString(7), rs
                        .getInt(8));
                if (rs.getInt(8) > 0)
                    books.add(bd);
            }
            prepStmt.close();
        } catch (SQLException ex) {
            throw new BooksNotFoundException(ex.getMessage());
        }
        releaseConnection();
        return books.size();
    }

    public Collection<BookDetails> getBooks() throws BooksNotFoundException {
        books = new ArrayList<>();
        try {
            String selectStatement = "select * " + "from books";
            getConnection();
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                BookDetails bd = new BookDetails(rs.getString(1), rs
                        .getString(2), rs.getString(3), rs.getString(4), rs
                        .getFloat(5), rs.getInt(6), rs.getString(7), rs
                        .getInt(8));
                if (rs.getInt(8) > 0)
                    books.add(bd);
            }
            prepStmt.close();
        } catch (SQLException ex) {
            throw new BooksNotFoundException(ex.getMessage());
        }

        releaseConnection();
        Collections.sort(books);
        return books;
    }

    public BookDetails getBookDetails(String bookId) throws BookNotFoundException {
        try {
            String selectStatement = "select * " + "from books where id = ? ";
            getConnection();
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, bookId);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                BookDetails bd = new BookDetails(rs.getString(1), rs
                        .getString(2), rs.getString(3), rs.getString(4), rs
                        .getFloat(5), rs.getInt(6), rs.getString(7), rs
                        .getInt(8));
                prepStmt.close();
                releaseConnection();
                return bd;
            } else {
                prepStmt.close();
                releaseConnection();
                throw new BookNotFoundException("Couldn't find book: " + bookId);
            }
        } catch (SQLException ex) {
            releaseConnection();
            throw new BookNotFoundException("Couldn't find book: " + bookId
                    + " " + ex.getMessage());
        }
    }

    public void buyBooks(String userId, ShoppingCart cart) throws OrderException {
        Collection items = cart.getItems();
        Iterator i = items.iterator();
        try {
            getConnection();
            con.setAutoCommit(false);
            while (i.hasNext()) {
                ShoppingCartItem sci = (ShoppingCartItem) i.next();
                BookDetails bd = (BookDetails) sci.getItem();
                String id = bd.getBookId();
                int quantity = sci.getQuantity();
                buyBook(id, quantity);
            }
            con.commit();
            con.setAutoCommit(true);
            releaseConnection();
        } catch (Exception ex) {
            try {
                con.rollback();
                releaseConnection();
                throw new OrderException("Transaction failed: "
                        + ex.getMessage());
            } catch (SQLException sqx) {
                releaseConnection();
                throw new OrderException("Rollback failed: " + sqx.getMessage());
            }
        }
    }

    public void buyBook(String bookId, int quantity) throws OrderException {
        try {
            decreaseInventory(bookId, quantity);

        } catch (Exception ex) {
            throw new OrderException("Couldn't purchase book: " + bookId + ex.getMessage());
        }
    }


    public void decreaseInventory(String bookId, int quantity){
        try {
            String selectStatement = "select * " + "from books where id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, bookId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                int inventory = rs.getInt(8);
                prepStmt.close();
                if ((inventory - quantity) >= 0) {
                    String updateStatement = "update books set inventory = inventory - ? where id = ?";
                    prepStmt = con.prepareStatement(updateStatement);
                    prepStmt.setInt(1, quantity);
                    prepStmt.setString(2, bookId);
                    prepStmt.executeUpdate();
                    prepStmt.close();
                } else
                    throw new OrderException("Not enough of " + bookId
                            + " in stock to complete order.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String checkUser(String userid, String pwd) throws Exception {
        if (userid == null && userid.length() == 0)
            throw new Exception("Please input the userid");
        String username = null;
        if (pwd == null)
            pwd = "";
        try {
            String selectStatement ="select * from users where userid = '" + userid+"'";  //sqlÓï¾ä×Ô¼º²¹³ä
            getConnection();
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                if (!pwd.equals(rs.getString("pwd")))
                    throw new Exception("Password not match");
                username = rs.getString("username");
            } else
                throw new Exception("User Not Exist");

            prepStmt.close();
        } catch (SQLException ex) {
            throw new BooksNotFoundException(ex.getMessage());
        }
        finally{
            releaseConnection();
        }
        return username;
    }

}
