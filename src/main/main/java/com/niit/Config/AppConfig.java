package com.niit.Config;

import java.util.Properties;

import javax.sql.DataSource;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.niit.DAO.BlogDAOImpl;
import com.niit.DAO.CommentDAOImpl;
import com.niit.DAO.EventDAOImpl;
import com.niit.DAO.ForumDAOImpl;
import com.niit.DAO.FriendListDAOImpl;
import com.niit.DAO.JobsDAOImpl;
import com.niit.DAO.UserDaoImpl;
import com.niit.Model.Blog;
import com.niit.Model.Chat;
import com.niit.Model.Comment;
import com.niit.Model.Forum;
import com.niit.Model.FriendList;
import com.niit.Model.Jobs;
import com.niit.Model.User;



import antlr.debug.Event;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.niit")
@EnableTransactionManagement
public class AppConfig {
	@Autowired
	@Bean(name = "dataSource")
	public DataSource getH2DataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
		dataSource.setUsername("COLLOBORATION");
		dataSource.setPassword("vishnu");
		
		return dataSource;
		}
	
	private Properties getHibernateProperties(){
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.put("hibernate.format_sql", "true");
		
		return properties;
		}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.addAnnotatedClass(Comment.class);
		
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(FriendList.class);
		sessionBuilder.addAnnotatedClass(Jobs.class);
		
		
		return sessionBuilder.buildSessionFactory();
		}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
		
	}
	@Autowired(required=true)
	@Bean(name="userDao")
	public UserDaoImpl getUserDaoImpl(SessionFactory sessionFactory){
		return new UserDaoImpl(sessionFactory);
	}
	@Autowired(required=true)
	@Bean(name="blogDao")
	public BlogDAOImpl getBlogDAOImpl(SessionFactory sessionFactory){
		return new BlogDAOImpl(sessionFactory);
	} 
	
	@Autowired(required=true)
	@Bean(name="event")
	public EventDAOImpl getEventDAOImpl(SessionFactory sessionFactory){
		return new EventDAOImpl(sessionFactory);
	}

	@Autowired(required=true)
	@Bean(name="friendlist")
	public FriendListDAOImpl getFriendListDAOImpl(SessionFactory sessionFactory){
		return new FriendListDAOImpl(sessionFactory);
	}
	
	@Autowired(required=true)
	@Bean(name="jobs")
	public JobsDAOImpl getJobsDAOImpl(SessionFactory sessionFactory){
		return new JobsDAOImpl(sessionFactory);
	}
	@Autowired(required=true)
	@Bean(name="forumDao")
	public ForumDAOImpl getForumDAOImpl(SessionFactory sessionFactory){
		return new ForumDAOImpl(sessionFactory);
	}
	@Autowired(required=true)
	@Bean(name="commentDAO")
	public CommentDAOImpl getCommentDAOImpl(SessionFactory sessionFactory){
		return new CommentDAOImpl(sessionFactory);
	}

}
