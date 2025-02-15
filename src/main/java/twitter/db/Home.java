package twitter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Home { // User&FollowingPostViewer=timeline

    private Connection con;
    private String user_id;

    public Home(Connection con, String user_id) {
        this.con = con;
        this.user_id = user_id;
    }

    public void viewFollowingPosts() throws SQLException {
        String sql = "SELECT posts.*, user.nickname " +
                "FROM posts " +
                "JOIN following ON posts.user_id_writer = following.user_id_following " +
                "JOIN user ON posts.user_id_writer = user.user_id " +
                "WHERE following.user_id = ? OR posts.user_id_writer = ? " +
                "ORDER BY posts.update_date DESC";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, user_id);
            pstmt.setString(2, user_id);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("postid writer_id nickname image content update registration like");
                while (rs.next()) {
                    String postid = rs.getString(1);
                    if (rs.wasNull())
                        postid = "null";
                    String writer = rs.getString(2);
                    if (rs.wasNull())
                        writer = "null";
                    String image = rs.getString(3);
                    if (rs.wasNull())
                        image = "null";
                    String content = rs.getString(4);
                    if (rs.wasNull())
                        content = "null";
                    String update = rs.getString(5);
                    if (rs.wasNull())
                        update = "null";
                    String registration = rs.getString(6);
                    if (rs.wasNull())
                        registration = "null";
                    String like = rs.getString(7);
                    if (rs.wasNull())
                        like = "null";
                    String Nickname = rs.getString(9);
                    if (rs.wasNull())
                        Nickname = "null";
                    System.out.printf("%s %15s %15s %15s %15s %15s %15s %15s\n", postid, writer, Nickname, image,
                            content, update, registration, like);
                }
            }
        }
    }

    // Add other methods if necessary
}
