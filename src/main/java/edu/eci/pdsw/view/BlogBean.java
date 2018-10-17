/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
/*
import edu.eci.pdsw.entities.Blog;
import edu.eci.pdsw.services.BlogServices;
import edu.eci.pdsw.services.ServicesException;
*/
/**
 *
 * @author hcadavid
 * @author fchaves
 * @author salzate
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "blogBean")
@RequestScoped
public class BlogBean extends BasePageBean {
	
	@ManagedProperty(value = "#{param.user}")
	private String user;
        
        
      


	/**
	 * 
	 */
	private static final long serialVersionUID = 3594009161252782831L;

	//@Inject
	//private BlogServices blogServices;

	/*public List<Blog> getData() throws Exception {
		try {
			if (user == null) {
				return blogServices.listAll();
			} else {
				return blogServices.searchByUser(user);
			}
		} catch (ServicesException ex) {

			throw ex;
		}

	}
*/
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

}
