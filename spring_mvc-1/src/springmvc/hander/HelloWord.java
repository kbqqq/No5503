package springmvc.hander;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWord {
	/**
	 * 
	 * @return
	 * ����ϴͨ����ͼ����������Ϊʵ��������ͼ  ���� InternalResourceViewResolver��ͼ���������������²���
	 * prefix(ǰ׺)+returnVal+suffix(��׺)
	 */

	@RequestMapping("/hellloword")
	public String  hello(){
		  
		  System.out.println("hello word");
		  return "success";
	  }

}
