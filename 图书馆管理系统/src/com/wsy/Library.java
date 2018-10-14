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
 * ������
 * 
 */
public class Library extends JFrame {
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();//̓������
	public static void main(String[] args) {
		
		try {
			//��������ó�����ʹ�õ�ƽ̨�����,Ҳ����������������ĸ�ƽ̨����,��ʾ�Ĵ���,�Ի�����۽����ĸ�ƽ̨�����
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
			new BookLoginIFrame();//��¼����
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void addIFame(JInternalFrame iframe) { // ����Ӵ���ķ���
		DESKTOP_PANE.add(iframe);
	}
	
	
	/*
	 * �����ڵĹ��췽��
	 */
	public Library() {
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//java.awt.Window�е�һ���������������´δ��ڿɼ�ʱ������Ӧ��ʾλ��
		setLocationByPlatform(true);
		setSize(800, 600);
		setTitle("��������ѧԺͼ��ݹ���ϵͳ  BY �ƿ�1511��ͼ��ݹ���ϵͳ�з�С�� 2017.11.03");
		JMenuBar menuBar = createMenu(); // ���ô����˵����ķ���
		setJMenuBar(menuBar);
		JToolBar toolBar = createToolBar(); // ���ô����������ķ���
		//��ʼ���õ�ContentPane������������������ӵ�����
	   getContentPane().add(toolBar, BorderLayout.NORTH);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); // ���屳��
       
		  //�������һ��������������ĳߴ�仯ʱ����̬�޸�label�Ĵ�С������ʹ�õ���ͼƬ
		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();
				label.setSize(size);
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/����.png")
						+ "'></html>");
			}
		});
		//��ʾlabel��Զ��DESKTOP_PANE������������һ�������Ҳ����˵,label��Զ��DESKTOP_PANE����ײ�
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);//��̓��������뵽ContentPane��
	}
	/**
	 * ����������
	 * 
	 * @return JToolBar
	 */
	private JToolBar createToolBar() { // �����������ķ���
		JToolBar toolBar = new JToolBar();
		//���ù����������ƶ�
		toolBar.setFloatable(false);
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));//���ù������߿�͹����
		
		
		//�ڹ���������� ͼ����Ϣ���ͼ��
		JButton bookAddButton=new JButton(MenuActions.BOOK_ADD);//������ť���󶨲˵��¼�
		ImageIcon bookaddicon=CreatecdIcon.add("bookAddtb.jpg");//����ͼ�귽��
		//ImageIcon icon = new ImageIcon(Library.class.getResource("/bookAddtb.jpg"));
		
		//����������Ҫ��ʾ��ͼ��
		bookAddButton.setIcon(bookaddicon);
		//bookAddButton.setIcon(icon);
		//����Ϊ���ص��¼��ı� 
		bookAddButton.setHideActionText(true);
		//��bookAddButton��ť�ӵ���������
		toolBar.add(bookAddButton);
		
		
		//�ڹ����������ͼ���޸�ͼ��
		JButton bookModiAndDelButton=new JButton(MenuActions.BOOK_MODIFY);
		ImageIcon bookmodiicon=CreatecdIcon.add("bookModiAndDeltb.jpg");//����ͼ�귽��
		bookModiAndDelButton.setIcon(bookmodiicon);
		bookModiAndDelButton.setHideActionText(true);
		toolBar.add(bookModiAndDelButton);
		
		
		//�ڹ����������ͼ��������ͼ��
		JButton bookTypeAddButton=new JButton(MenuActions.BOOKTYPE_ADD);
		ImageIcon bookTypeAddicon=CreatecdIcon.add("bookTypeAddtb.jpg");//����ͼ�귽��
		bookTypeAddButton.setIcon(bookTypeAddicon);
		bookTypeAddButton.setHideActionText(true);
		toolBar.add(bookTypeAddButton);
		
		
		//�ڹ����������ͼ�����ͼ��
		JButton bookBorrowButton=new JButton(MenuActions.BORROW);
		ImageIcon bookBorrowicon=CreatecdIcon.add("bookBorrowtb.jpg");//����ͼ�귽��
		bookBorrowButton.setIcon(bookBorrowicon);
		bookBorrowButton.setHideActionText(true);
		toolBar.add(bookBorrowButton);
		
		
		//�ڹ�������������鶩��ͼ��
		JButton bookOrderButton=new JButton(MenuActions.NEWBOOK_ORDER);
		ImageIcon bookOrdericon=CreatecdIcon.add("bookOrdertb.jpg");//����ͼ�귽��
		bookOrderButton.setIcon(bookOrdericon);
		bookOrderButton.setHideActionText(true);
		toolBar.add(bookOrderButton);
		
		
		//�ڹ������������������ͼ��
		JButton bookCheckButton=new JButton(MenuActions.NEWBOOK_CHECK_ACCEPT);
		ImageIcon bookCheckicon=CreatecdIcon.add("newbookChecktb.jpg");//����ͼ�귽��
		bookCheckButton.setIcon(bookCheckicon);
		bookCheckButton.setHideActionText(true);
		toolBar.add(bookCheckButton);
		
		
		//�ڹ���������Ӷ�����Ϣ���ͼ��
		JButton readerAddButton=new JButton(MenuActions.READER_ADD);
		ImageIcon readerAddicon=CreatecdIcon.add("readerAddtb.jpg");//����ͼ�귽��
		readerAddButton.setIcon(readerAddicon);
		readerAddButton.setHideActionText(true);
		toolBar.add(readerAddButton);
		
		
		//�ڹ���������Ӷ����޸���ɾ��ͼ��
		JButton readerModiAndDelButton=new JButton(MenuActions.READER_MODIFY);
		ImageIcon readerModiAndDelicon=CreatecdIcon.add("readerModiAndDeltb.jpg");//����ͼ�귽��
		readerModiAndDelButton.setIcon(readerModiAndDelicon);
		readerModiAndDelButton.setHideActionText(true);
		toolBar.add(readerModiAndDelButton);
		
		
		//�ڹ�����������˳�ϵͳͼ��
		JButton ExitButton=new JButton(MenuActions.EXIT);
		ImageIcon Exiticon=CreatecdIcon.add("exittb.jpg");//����ͼ�귽��
		ExitButton.setIcon(Exiticon);
		ExitButton.setHideActionText(true);
		toolBar.add(ExitButton);
		return toolBar;
	}
	/**
	 * �����˵���
	 */
	private JMenuBar createMenu() { // �����˵����ķ���
		JMenuBar menuBar = new JMenuBar();

		// ��ʼ�����鶩������˵�
		JMenu bookOrderMenu = new JMenu(); 
		bookOrderMenu.setIcon(CreatecdIcon.add("xsdgcd.jpg"));//�����˵�ͼ�귽��
		bookOrderMenu.add(MenuActions.NEWBOOK_ORDER);
		bookOrderMenu.add(MenuActions.NEWBOOK_CHECK_ACCEPT);
		
		
		
		

		// ��ʼ����������ά���˵�
		JMenu baseMenu = new JMenu();
		baseMenu.setIcon(CreatecdIcon.add("jcsjcd.jpg"));//����ͼ�귽��
		{
			
			JMenu readerManageMItem = new JMenu("������Ϣ����");//�����˵���
			readerManageMItem.add(MenuActions.READER_ADD);
			readerManageMItem.add(MenuActions.READER_MODIFY);

			JMenu bookTypeManageMItem = new JMenu("ͼ��������");
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);

			JMenu bookInffoManageMItem = new JMenu("ͼ����Ϣ����");
			bookInffoManageMItem .add(MenuActions.BOOK_ADD);
			bookInffoManageMItem .add(MenuActions.BOOK_MODIFY);

			baseMenu.add(readerManageMItem);
			baseMenu.add(bookTypeManageMItem);
			baseMenu.add(bookInffoManageMItem);
			baseMenu.addSeparator();//׷���»��߸���
			baseMenu.add(MenuActions.EXIT);
		}
		
		// ��ʼ�����Ĺ���˵�
		JMenu borrowManageMenu = new JMenu(); 
		borrowManageMenu.setIcon(CreatecdIcon.add("jyglcd.jpg"));
		borrowManageMenu.add(MenuActions.BORROW); // ����
		borrowManageMenu.add(MenuActions.GIVE_BACK); // �黹
		borrowManageMenu.add(MenuActions.BOOK_SEARCH); // ����

		
		//��ʼ��ϵͳά���˵�
		JMenu sysManageMenu = new JMenu(); 
		sysManageMenu.setIcon(CreatecdIcon.add("jcwhcd.jpg"));
		JMenu userManageMItem = new JMenu("�û�����"); // �û�����˵���
		userManageMItem.add(MenuActions.USER_ADD);
		userManageMItem.add(MenuActions.USER_MODIFY);
		
		sysManageMenu.add(MenuActions.MODIFY_PASSWORD);
		sysManageMenu.add(userManageMItem);

		menuBar.add(baseMenu); // ��ӻ�������ά���˵����˵���
		menuBar.add(bookOrderMenu); // ������鶩������˵����˵���
		menuBar.add(borrowManageMenu); // ��ӽ��Ĺ���˵����˵���
		menuBar.add(sysManageMenu); // ���ϵͳά���˵����˵���
		return menuBar;//���ز˵���
	}
}
