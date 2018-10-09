package com.wsy;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import com.wsy.iframe.BookLoginIFrame;
import com.wsy.util.CreatecdIcon;;

/**
 * 主窗体
 * 
 */
public class Library extends JFrame {
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();//虛拟桌面
	public static void main(String[] args) {
		
		try {
			//把外观设置成你所使用的平台的外观,也就是你这个程序在哪个平台运行,显示的窗口,对话框外观将是哪个平台的外观
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
			new BookLoginIFrame();//登录窗口
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
		DESKTOP_PANE.add(iframe);
	}
	
	
	/*
	 * 主窗口的构造方法
	 */
	public Library() {
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//java.awt.Window中的一个方法，设置在下次窗口可见时，窗口应显示位置
		setLocationByPlatform(true);
		setSize(800, 600);
		setTitle("湖北文理学院图书馆管理系统  BY 计科1511班图书馆管理系统研发小组 2017.11.03");
		JMenuBar menuBar = createMenu(); // 调用创建菜单栏的方法
		setJMenuBar(menuBar);
		JToolBar toolBar = createToolBar(); // 调用创建工具栏的方法
		//初始化得到ContentPane容器。并将工具栏添加到北部
	   getContentPane().add(toolBar, BorderLayout.NORTH);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); // 窗体背景
       
		  //给组件加一个监听，当组件的尺寸变化时，动态修改label的大小及缩放使用到的图片
		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();
				label.setSize(size);
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/背景.png")
						+ "'></html>");
			}
		});
		//表示label永远是DESKTOP_PANE这个容器的最后一个组件，也就是说,label永远在DESKTOP_PANE的最底层
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);//将虛拟桌面加入到ContentPane中
	}
	/**
	 * 创建工具栏
	 * 
	 * @return JToolBar
	 */
	private JToolBar createToolBar() { // 创建工具栏的方法
		JToolBar toolBar = new JToolBar();
		//设置工具栏不能移动
		toolBar.setFloatable(false);
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));//设置工具栏边框凸出来
		
		
		//在工具栏中添加 图书信息添加图标
		JButton bookAddButton=new JButton(MenuActions.BOOK_ADD);//创建按钮并绑定菜单事件
		ImageIcon bookaddicon=CreatecdIcon.add("bookAddtb.jpg");//创建图标方法
		//ImageIcon icon = new ImageIcon(Library.class.getResource("/bookAddtb.jpg"));
		
		//定义此组件将要显示的图标
		bookAddButton.setIcon(bookaddicon);
		//bookAddButton.setIcon(icon);
		//设置为隐藏掉事件文本 
		bookAddButton.setHideActionText(true);
		//将bookAddButton按钮加到工具栏中
		toolBar.add(bookAddButton);
		
		
		//在工具栏中添加图书修改图标
		JButton bookModiAndDelButton=new JButton(MenuActions.BOOK_MODIFY);
		ImageIcon bookmodiicon=CreatecdIcon.add("bookModiAndDeltb.jpg");//创建图标方法
		bookModiAndDelButton.setIcon(bookmodiicon);
		bookModiAndDelButton.setHideActionText(true);
		toolBar.add(bookModiAndDelButton);
		
		
		//在工具栏中添加图书类别添加图标
		JButton bookTypeAddButton=new JButton(MenuActions.BOOKTYPE_ADD);
		ImageIcon bookTypeAddicon=CreatecdIcon.add("bookTypeAddtb.jpg");//创建图标方法
		bookTypeAddButton.setIcon(bookTypeAddicon);
		bookTypeAddButton.setHideActionText(true);
		toolBar.add(bookTypeAddButton);
		
		
		//在工具栏中添加图书借阅图标
		JButton bookBorrowButton=new JButton(MenuActions.BORROW);
		ImageIcon bookBorrowicon=CreatecdIcon.add("bookBorrowtb.jpg");//创建图标方法
		bookBorrowButton.setIcon(bookBorrowicon);
		bookBorrowButton.setHideActionText(true);
		toolBar.add(bookBorrowButton);
		
		
		//在工具栏中添加新书订购图标
		JButton bookOrderButton=new JButton(MenuActions.NEWBOOK_ORDER);
		ImageIcon bookOrdericon=CreatecdIcon.add("bookOrdertb.jpg");//创建图标方法
		bookOrderButton.setIcon(bookOrdericon);
		bookOrderButton.setHideActionText(true);
		toolBar.add(bookOrderButton);
		
		
		//在工具栏中添加验收新书图标
		JButton bookCheckButton=new JButton(MenuActions.NEWBOOK_CHECK_ACCEPT);
		ImageIcon bookCheckicon=CreatecdIcon.add("newbookChecktb.jpg");//创建图标方法
		bookCheckButton.setIcon(bookCheckicon);
		bookCheckButton.setHideActionText(true);
		toolBar.add(bookCheckButton);
		
		
		//在工具栏中添加读者信息添加图标
		JButton readerAddButton=new JButton(MenuActions.READER_ADD);
		ImageIcon readerAddicon=CreatecdIcon.add("readerAddtb.jpg");//创建图标方法
		readerAddButton.setIcon(readerAddicon);
		readerAddButton.setHideActionText(true);
		toolBar.add(readerAddButton);
		
		
		//在工具栏中添加读者修改与删除图标
		JButton readerModiAndDelButton=new JButton(MenuActions.READER_MODIFY);
		ImageIcon readerModiAndDelicon=CreatecdIcon.add("readerModiAndDeltb.jpg");//创建图标方法
		readerModiAndDelButton.setIcon(readerModiAndDelicon);
		readerModiAndDelButton.setHideActionText(true);
		toolBar.add(readerModiAndDelButton);
		
		
		//在工具栏中添加退出系统图标
		JButton ExitButton=new JButton(MenuActions.EXIT);
		ImageIcon Exiticon=CreatecdIcon.add("exittb.jpg");//创建图标方法
		ExitButton.setIcon(Exiticon);
		ExitButton.setHideActionText(true);
		toolBar.add(ExitButton);
		return toolBar;
	}
	/**
	 * 创建菜单栏
	 */
	private JMenuBar createMenu() { // 创建菜单栏的方法
		JMenuBar menuBar = new JMenuBar();

		// 初始化新书订购管理菜单
		JMenu bookOrderMenu = new JMenu(); 
		bookOrderMenu.setIcon(CreatecdIcon.add("xsdgcd.jpg"));//创建菜单图标方法
		bookOrderMenu.add(MenuActions.NEWBOOK_ORDER);
		bookOrderMenu.add(MenuActions.NEWBOOK_CHECK_ACCEPT);
		
		
		
		

		// 初始化基础数据维护菜单
		JMenu baseMenu = new JMenu();
		baseMenu.setIcon(CreatecdIcon.add("jcsjcd.jpg"));//创建图标方法
		{
			
			JMenu readerManageMItem = new JMenu("读者信息管理");//创建菜单项
			readerManageMItem.add(MenuActions.READER_ADD);
			readerManageMItem.add(MenuActions.READER_MODIFY);

			JMenu bookTypeManageMItem = new JMenu("图书类别管理");
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);

			JMenu bookInffoManageMItem = new JMenu("图书信息管理");
			bookInffoManageMItem .add(MenuActions.BOOK_ADD);
			bookInffoManageMItem .add(MenuActions.BOOK_MODIFY);

			baseMenu.add(readerManageMItem);
			baseMenu.add(bookTypeManageMItem);
			baseMenu.add(bookInffoManageMItem);
			baseMenu.addSeparator();//追加下划线隔开
			baseMenu.add(MenuActions.EXIT);
		}
		
		// 初始化借阅管理菜单
		JMenu borrowManageMenu = new JMenu(); 
		borrowManageMenu.setIcon(CreatecdIcon.add("jyglcd.jpg"));
		borrowManageMenu.add(MenuActions.BORROW); // 借阅
		borrowManageMenu.add(MenuActions.GIVE_BACK); // 归还
		borrowManageMenu.add(MenuActions.BOOK_SEARCH); // 搜索

		
		//初始化系统维护菜单
		JMenu sysManageMenu = new JMenu(); 
		sysManageMenu.setIcon(CreatecdIcon.add("jcwhcd.jpg"));
		JMenu userManageMItem = new JMenu("用户管理"); // 用户管理菜单项
		userManageMItem.add(MenuActions.USER_ADD);
		userManageMItem.add(MenuActions.USER_MODIFY);
		
		sysManageMenu.add(MenuActions.MODIFY_PASSWORD);
		sysManageMenu.add(userManageMItem);

		menuBar.add(baseMenu); // 添加基础数据维护菜单到菜单栏
		menuBar.add(bookOrderMenu); // 添加新书订购管理菜单到菜单栏
		menuBar.add(borrowManageMenu); // 添加借阅管理菜单到菜单栏
		menuBar.add(sysManageMenu); // 添加系统维护菜单到菜单栏
		return menuBar;//返回菜单栏
	}
}
