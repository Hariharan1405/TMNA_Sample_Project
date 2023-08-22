package com.example.todo.app.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.example.todo.app.controller.User;
import com.example.todo.app.dao.UserDao;




@Repository
public class UserDaoImplementation extends JdbcDaoSupport implements UserDao{

	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	@Override
	public User getUserByName(String userName) {
		String sql= "select cast(aes_decrypt(unhex(`user_pswd`), 'secret') as char(50)) from user WHERE user_name=?";
		
		return getJdbcTemplate().queryForObject(sql, new Object[] { userName }, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserName(userName);
				user.setPassword(rs.getString(1));
				return user;
			}
			
		});
	}

	@Override
	public int createNewUser(User user) {
		String sql = "INSERT INTO USER(user_name, user_pswd) values(?, hex(aes_encrypt(?, 'secret')))";
		
		return getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, user.getUserName());
				ps.setString(2, user.getPassword());
				return ps;
			}
		});
	}

}
