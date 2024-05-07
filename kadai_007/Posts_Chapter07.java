package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		
		Connection con = null;
        Statement statement = null;
        
        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "Green_0713"
            );
            
            System.out.println("データベース接続成功");
            
            // SQLクエリの準備
            Statement post= con.createStatement();
            
            // レコードを追加するSQLクエリ
            String sql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES "
                    + "(1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13), "
                    + "(1002, '2023-02-08', 'お疲れ様です！', 12), "
                    + "(1003, '2023-02-09', '今日も頑張ります！', 18), "
                    + "(1001, '2023-02-09', '無理は禁物ですよ！', 17), "
                    + "(1002, '2023-02-10', '明日から連休ですね！', 20)";
            
            // レコードの追加を実行する
            System.out.println("レコード追加を実行します");
            int rowsInsart = post.executeUpdate(sql);
            System.out.println(rowsInsart + "件のレコードが追加されました");
            
            // レコードの検索
            Statement selectStatement = con.createStatement();
            String selectSql = "SELECT * FROM posts WHERE user_id = 1002;";
            ResultSet resultSet = selectStatement.executeQuery(selectSql);
            
            System.out.println("ユーザーIDが1002のレコードを検索しました");
            
            while(resultSet.next()) {
            	String posted_at = resultSet.getString("posted_at");
                String post_content = resultSet.getString("post_content");
                int likes = resultSet.getInt("likes");
                System.out.println(resultSet.getRow() + "件目：投稿日時=" + posted_at
                                   + "／投稿内容=" + post_content + "／いいね数=" + likes );
            	}
        }
            
            catch(SQLException e) {
                System.out.println("エラー発生：" + e.getMessage());
            } finally {
                // 使用したオブジェクトを解放
                if( statement != null ) {
                    try { statement.close(); } catch(SQLException ignore) {}
                }
                if( con != null ) {
                    try { con.close(); } catch(SQLException ignore) {}
                }
            }
        }
}
