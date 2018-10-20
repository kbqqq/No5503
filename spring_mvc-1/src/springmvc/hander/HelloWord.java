package springmvc.hander;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWord {
	/**
	 * 
	 * @return
	 * 返回洗通过视图解析器解析为实际物理视图  对于 InternalResourceViewResolver视图解析器，会做如下操作
	 * prefix(前缀)+returnVal+suffix(后缀)
	 */

	@RequestMapping("/hellloword")
	public String  hello(){
		  
		  System.out.println("hello word");
		  return "success";
	  }

}
