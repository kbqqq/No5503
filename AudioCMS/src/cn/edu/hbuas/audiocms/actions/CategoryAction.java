package cn.edu.hbuas.audiocms.actions;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import cn.edu.hbuas.audiocms.dao.CategoryDao;
import cn.edu.hbuas.audiocms.dao.NewsDao;
import cn.edu.hbuas.models.Category;
import cn.edu.hbuas.models.News;

public class CategoryAction extends ActionSupport {

	private Category category;
	private NewsDao nd = new NewsDao();
	private CategoryDao cd = new CategoryDao();
	private List<News> newsList;
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().removeAttribute("errMsg_category");
		//List<Category> cList = cd.selectListByTopId(category.getTopId());
		List<Category> cList = cd.selectListById();
		request.setAttribute("clist", cList);
		return "clist";
	}
	public String inputCategory() {
		return "addInput";
	}
	public String addCategory() {
		cd.insertCategory(category);
		return SUCCESS;
	}
	public String updateInput() {
		category = cd.selectCategory(category.getId());
		return "updateInput";
	}
	public String updateCategory() {
		cd.updateCategory(category);
		return SUCCESS;
	}
	public String delCategory() {
		HttpServletRequest request = ServletActionContext.getRequest();
		newsList = nd.selectListByTopId(category.getId(), 0, 0);
		if (newsList.isEmpty()) {
			cd.deleteCategory(category.getId());
			return SUCCESS;
		} else {
			request.getSession().setAttribute("errMsg_category", "��ѡ���Ϊ�գ�����ɾ����");
			List<Category> cList = cd.selectListById();
			request.setAttribute("clist", cList);
			return ERROR;
		}
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
