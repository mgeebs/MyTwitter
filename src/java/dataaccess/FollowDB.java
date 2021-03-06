/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Follow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import murach.sql.ConnectionPool;
import murach.util.DBUtil;


public class FollowDB {

    public static int insert(Follow follow) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO twitterdb.follows (Email, followedEmail, followedDate) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, follow.getEmail());
            ps.setString(2, follow.getFollowedEmail());
            ps.setString(3, follow.getDate());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Follow> searchByEmail(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM twitterdb.follows WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            ArrayList<Follow> followList = new ArrayList<Follow>();

            Follow follow = null;

            while (rs.next()) {
                follow = new Follow();
                follow.setEmail(rs.getString("Email"));
                follow.setFollowedEmail(rs.getString("followedEmail"));
                follow.setDate(rs.getString("followedDate"));
                followList.add(follow);

            }
            return followList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static ArrayList<Follow> followers (String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM twitterdb.follows WHERE followedEmail = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            ArrayList<Follow> followList = new ArrayList<Follow>();

            Follow follow = null;

            while (rs.next()) {
                follow = new Follow();
                follow.setEmail(rs.getString("Email"));
                follow.setFollowedEmail(rs.getString("followedEmail"));
                follow.setDate(rs.getString("followedDate"));
                followList.add(follow);

            }
            return followList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static int unfollow(String email, String followedEmail) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM twitterdb.follows "
                + "WHERE Email = ? AND followedEmail = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, followedEmail);
            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

}
