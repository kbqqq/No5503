package cn.edu.hbuas.audiocms.actions;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionContext;

public class UploadJSONAction {
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		System.out.println(obj.toJSONString());
		return obj.toJSONString();
	}
	public void execute() {
		/**
		 * KindEditor JSP
		 * 
		 * ��JSP��������ʾ���򣬽��鲻Ҫֱ����ʵ����Ŀ��ʹ�á�
		 * �����ȷ��ֱ��ʹ�ñ�����ʹ��֮ǰ����ϸȷ����ذ�ȫ���á�
		 * 
		 */
		HttpServletRequest request = ServletActionContext.getRequest();
		ActionContext pageContext = ServletActionContext.getContext();
		HttpServletResponse response = ServletActionContext.getResponse();
		//�ļ�����Ŀ¼·��
		String savePath = ((ServletRequest) pageContext.getContext()).getRealPath("/") + "attached/";

		//�ļ�����Ŀ¼URL
		String saveUrl  = request.getContextPath() + "/attached/";

		//���������ϴ����ļ���չ��
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,pptx,txt,zip,rar,gz,bz2");

		//����ļ���С
		long maxSize = 1000000;

		response.setContentType("text/html; charset=UTF-8");

		if(!ServletFileUpload.isMultipartContent(request)){
			System.out.println(getError("��ѡ���ļ���"));
			return;
		}
		//���Ŀ¼
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			System.out.println(getError("�ϴ�Ŀ¼�����ڡ�"));
			return;
		}
		//���Ŀ¼дȨ��
		if(!uploadDir.canWrite()){
			System.out.println(getError("�ϴ�Ŀ¼û��дȨ�ޡ�"));
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			System.out.println(getError("Ŀ¼������ȷ��"));
			return;
		}
		//�����ļ���
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		System.out.println("��ʼ�ϴ�");
		List items;
		try {
			items = upload.parseRequest(request);
		
			System.out.println(items);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();
				long fileSize = item.getSize();
				if (!item.isFormField()) {
					//����ļ���С
					if(item.getSize() > maxSize){
						System.out.println(getError("�ϴ��ļ���С�������ơ�"));
						return;
					}
					//�����չ��
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
						System.out.println(getError("�ϴ��ļ���չ���ǲ��������չ����\nֻ����" + extMap.get(dirName) + "��ʽ��"));
						return;
					}
	
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
					
					try{
						File uploadedFile = new File(savePath, newFileName);
						item.write(uploadedFile);
					}catch(Exception e){
						System.out.println(getError("�ϴ��ļ�ʧ�ܡ�"));
						e.printStackTrace();
						return;
					}
	
					JSONObject obj = new JSONObject();
					obj.put("error", 0);
					obj.put("url", saveUrl + newFileName);
					System.out.println(obj.toJSONString());
				}
			}
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
