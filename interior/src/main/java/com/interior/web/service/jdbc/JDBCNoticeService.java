package com.interior.web.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interior.web.entity.Notice;
import com.interior.web.service.NoticeService;

@Service
public class JDBCNoticeService implements NoticeService{

	@Autowired
	private DataSource dataSource;

	public List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException{

		int start = 1 + (page-1)*10;     // 1, 11, 21, 31, ..
		int end = 10*page; // 10, 20, 30, 40...

		String sql = "SELECT * FROM NOTICE_VIEW WHERE "+field+" LIKE ? AND NUM BETWEEN ? AND ?";

		Connection con = dataSource.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2, start);
		st.setInt(3, end);
		ResultSet rs = st.executeQuery();

		List<Notice> list = new ArrayList<Notice>();

		while(rs.next()){
		    int id = rs.getInt("ID");
		    String title = rs.getString("TITLE");
		    String writerId = rs.getString("WRITER_ID");
		    Date regDate = rs.getDate("REGDATE");
		    String content = rs.getString("CONTENT");
		    int hit = rs.getInt("hit");
		    String files = rs.getString("FILES");

		    Notice notice = new Notice(
		    					id,
		    					title,
		    					writerId,
		    					regDate,
		    					content,
		    					hit,
		    					files
		    				);

		    list.add(notice);

		}


		rs.close();
		st.close();
		con.close();

		return list;
	}

	// Scalar
	public int getCount() throws ClassNotFoundException, SQLException {
		int count = 0;

		String sql = "SELECT COUNT(ID) COUNT FROM NOTICE";

//		Class.forName(driver);
//		Connection con = DriverManager.getConnection(url,uid, pwd);
		Connection con = dataSource.getConnection();
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(sql);

		if(rs.next())
			count = rs.getInt("COUNT");

		rs.close();
		st.close();
		con.close();

		return count;
	}

	public int insert(Notice notice) throws SQLException, ClassNotFoundException {
		String title = notice.getTitle();
		String writerId = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();

		String sql = "INSERT INTO notice (    " +
				"    title," +
				"    writer_id," +
				"    content," +
				"    files" +
				") VALUES (?,?,?,?)";

//		Class.forName(driver);
		Connection con = dataSource.getConnection();
		//Statement st = con.createStatement();
		//st.ex....(sql)
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);

		int result = st.executeUpdate();


		st.close();
		con.close();

		return result;
	}

	public int update(Notice notice) throws SQLException, ClassNotFoundException {
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();

		String sql = "UPDATE NOTICE " +
				"SET" +
				"    TITLE=?," +
				"    CONTENT=?," +
				"    FILES=?" +
				"WHERE ID=?";

//		Class.forName(driver);
		Connection con = dataSource.getConnection();
		//Statement st = con.createStatement();
		//st.ex....(sql)
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);

		int result = st.executeUpdate();

		st.close();
		con.close();

		return result;
	}

	public int delete(int id) throws ClassNotFoundException, SQLException {

		String sql = "DELETE NOTICE WHERE ID=?";

		Connection con = dataSource.getConnection();
		//Statement st = con.createStatement();
		//st.ex....(sql)
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);

		int result = st.executeUpdate();

		st.close();
		con.close();

		return result;
	}


}
