package com.botree.bean;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.botree.dao.UserDao;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Component
@Scope("session")
public class UserBean {

	private String name;
	private String password;
	
	@Autowired
	private UserDao userDao;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void authenticate() throws IOException
	{
		var u=userDao.getUser(name);
		if(u!=null && u.getName().equals(name) && u.getPassword().equals(password))
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage("err", new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", "Invalid user name and password"));
		}
	}
}
