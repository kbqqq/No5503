package com.wsy.iframe;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.wsy.dao.Dao;
import com.wsy.util.CreatecdIcon;
import com.wsy.util.MyDocument;

public class ReaderAddIFrame extends JInternalFrame {
    //定义声明组件
	private JTextField ISBN;
	private ButtonGroup buttonGroup = new ButtonGroup();//定义一个按钮组别，单选按钮JRadioButton常用到
	private JFormattedTextField keepmoney;//押金
	private JTextField tel;
	private JFormattedTextField date;
	private JFormattedTextField maxnumber;
	private JFormattedTextField bztime;
	private JTextField zjnumber;
	private JComboBox comboBox;
	private JTextField zy;
	private JTextField age;
	private JTextField readername;
	DefaultComboBoxModel comboBoxModel;
	String [] array;
	
	

	/**
	 * 创建窗口
	 */
	public ReaderAddIFrame() {
		super();
		setTitle("读者相关信息添加");// 设置窗体标题
		setIconifiable(true);//设置窗体可最小化
		setClosable(true);	// 设置窗体可关闭																		
		setBounds(100, 100, 500, 350);

		final JLabel logoLabel = new JLabel();//创建一个空标签对象
		ImageIcon readerAddIcon=CreatecdIcon.add("readerAdd.jpg");//创建图标
		logoLabel.setIcon(readerAddIcon);//标签对象logoLabel设置图片
		//设置logoLabel组件不透明
		logoLabel.setOpaque(true);
		logoLabel.setBackground(Color.CYAN);//设置背景颜色为蓝色
		//创建一个Dimension类封装logoLabel组件 的宽度和高度（只能精确到整数）
		logoLabel.setPreferredSize(new Dimension(400, 60));
		getContentPane().add(logoLabel, BorderLayout.NORTH);//将logoLabel添加到ContentPane容器的北部

		
		
		final JPanel panel = new JPanel();//创建panel对象
		panel.setLayout(new FlowLayout());//流布局
		getContentPane().add(panel);

		
		
		final JPanel panel_1 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);//网格布局
		//设置组件之间垂直间距，就是在这个容器内的两个组件间上下相隔的距离
		gridLayout.setVgap(15);
		//设置组件之间水平距离
		gridLayout.setHgap(10);
		panel_1.setLayout(gridLayout);//设置网格布局
		//创建Dimension类封装panel_1组件 的宽度和高度（只能精确到整数）
		panel_1.setPreferredSize(new Dimension(450, 200));
		panel.add(panel_1);
        /*
         * 姓名
         */
		
		//设置姓名标签
		final JLabel label_2 = new JLabel();
		label_2.setText("姓    名：");
		panel_1.add(label_2);
		
        //设置姓名文本框
		readername = new JTextField();
		readername.setDocument(new MyDocument(10));//设置姓名不大于10个字
		panel_1.add(readername);

		/*
		 * 性别
		 */
		 
		//设置性别标签
		final JLabel label_3 = new JLabel();
		label_3.setText("性    别：");
		panel_1.add(label_3);

		final JPanel label_13 = new JPanel();//创建一个panel_13对象
		final FlowLayout flowLayout = new FlowLayout();//流布局
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		label_13.setLayout(flowLayout);//设置流布局
		panel_1.add(label_13);
       //创建单选按钮“男”
		final JRadioButton radioButton1 = new JRadioButton();
		label_13.add(radioButton1);//将按钮加到标签label_13中
		radioButton1.setSelected(true);
		buttonGroup.add(radioButton1);//将按钮加到buttonGroup组件中
		radioButton1.setText("男");//单选按钮设置文本“男”
		//创建单选按钮“女”
		final JRadioButton radioButton2 = new JRadioButton();
		label_13.add(radioButton2);
		buttonGroup.add(radioButton2);
		radioButton2.setText("女");
		

		/*
		 * 年龄
		 */
		
		final JLabel label_4 = new JLabel();
		label_4.setText("年    龄：");
		panel_1.add(label_4);

		age = new JTextField();
		age.setDocument(new MyDocument(2));//不超过两位数
		age.addKeyListener(new NumberListener());
		panel_1.add(age);

		/*
		 * 职业
		 */
		final JLabel label_5 = new JLabel();
		label_5.setText("职    业：");
		panel_1.add(label_5);

		zy = new JTextField();
		zy.setDocument(new MyDocument(30));//不超过30个字
		panel_1.add(zy);

		/*
		 * 有效证件
		 */
		final JLabel label_6 = new JLabel();
		label_6.setText("有效证件：");
		panel_1.add(label_6);
 
		//创建下拉列表
		comboBox = new JComboBox();
		//用数组存储元素
		array=new String[]{"身份证","军人证","学生证","工作证"};
		comboBox.setModel(new DefaultComboBoxModel(array));
		//遍历
		for(int i=1;i<array.length;i++){
			comboBox.setSelectedIndex(i);
			comboBox.setSelectedItem(array[i]);
		}
		
		panel_1.add(comboBox);
		
		/*
		 * 证件号码
		 */

		final JLabel label_7 = new JLabel();
		label_7.setText("证件号码：");
		panel_1.add(label_7);

		zjnumber = new JTextField();
		zjnumber.setDocument(new MyDocument(13));//不超过13位数
		zjnumber.addKeyListener(new NumberListener());
		panel_1.add(zjnumber);



		/*
		 * 最大借书量
		 */
		final JLabel label_9 = new JLabel();
		label_9.setText("最大借书量：");
		panel_1.add(label_9);
		
		maxnumber = new JFormattedTextField();
		maxnumber.setDocument(new MyDocument(2));//不超过两位数
		maxnumber.addKeyListener(new NumberListener());
		panel_1.add(maxnumber);

		/*
		 * 会员证有效日期
		 */
		final JLabel label_10 = new JLabel();
		label_10.setText("会员证有效日期：");
		panel_1.add(label_10);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");//格式化日期
        //getDateInstance()是一个静态方法，获取时间并返回其DateFormat对象
		date = new JFormattedTextField(myfmt.getDateInstance());
		//在文本框中显示现在时间的后一整年
		java.util.Date date2 = new java.util.Date();
		date2.setDate(date2.getDate() + 365);
		date.setValue(date2);
		date.addKeyListener(new DateListener());
		panel_1.add(date);

		/*
		 * 电话
		 */
		final JLabel label_11 = new JLabel();
		label_11.setText("电    话：");
		panel_1.add(label_11);
		

		tel = new JTextField();
		tel.addKeyListener(new TelListener());
		tel.setDocument(new MyDocument(11));//不超过11位数

		panel_1.add(tel);

		/*
		 * 押金
		 */
		final JLabel label_12 = new JLabel();
		label_12.setText("押    金：");
		panel_1.add(label_12);
		
		keepmoney = new JFormattedTextField();
		keepmoney.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				String numStr="0123456789"+(char)8;//只允许输入数字与退格键   (char)8就是ASCII码的第8个char.也就是BS(退格键)
	            //e.getKeyChar()就是获得当前按键的对应的ASCII码的char形式.然后通过key.indexOf()方法来检查该按键是否在key列表里,
				//如果不在的话,返回的index是-1,则-1 < 0为true,执行e.consume();
				if(numStr.indexOf(e.getKeyChar())<0){
					e.consume();//e.consume() 是销毁对象，消耗事件
				}
				if(keepmoney.getText().length()>2||keepmoney.getText().length()<0){
					e.consume();
				}
			}
		});
		panel_1.add(keepmoney);
		
		/*
		 * 办证日期
		 */

		final JLabel label = new JLabel();
		label.setText("办证日期：");
		panel_1.add(label);

		
		bztime = new JFormattedTextField(myfmt.getDateInstance());//获取当前时间
		bztime.setValue(new java.util.Date());
		bztime.addKeyListener(new DateListener());
		panel_1.add(bztime);

		/*
		 * 读者编号
		 */
		final JLabel label_1 = new JLabel();
		label_1.setText("读者编号：");
		panel_1.add(label_1);

		ISBN = new JTextField();
		ISBN.setDocument(new MyDocument(13));//不超过13位数字
		panel_1.add(ISBN);

		/*
		 * 保存按钮
		 */
		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(450, 100));
		panel.add(panel_2);

		final JButton save = new JButton();
		panel_2.add(save);
		save.setText("保存");
		save.addActionListener(new ButtonAddListener(radioButton1));

		/*
		 * 返回按钮
		 */
		final JButton back = new JButton();
		panel_2.add(back);
		back.setText("返回");
		back.addActionListener(new CloseActionListener());
		setVisible(true);
		
	}
	
	/*
	 * 办证日期的事件事件
	 */
	class DateListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(bztime.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "时间格式请使用\"2007-05-10\"格式");
			}
		}
	}
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;//只允许输入数字键和退格键 
			//e.getKeyChar()就是获得当前按键的对应的ASCII码的char形式.然后通过key.indexOf()方法来检查该按键是否在key列表里,
			//如果不在的话,返回的index是-1,则-1 < 0为true,执行e.consume();
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();//销毁对象，
			}
		}
	}
	
	/*
	 * 事件
	 */
	
	class ButtonAddListener implements ActionListener {
		
		//表示完成button1按钮事件后才会去调用实现的actionPerformed方法
		private final JRadioButton button1;//声明保存按钮绑定的事件名button1
		
		ButtonAddListener(JRadioButton button1) {
			this.button1 = button1;
		}
		public void actionPerformed(final ActionEvent e) {
			//没有输入姓名
			if(readername.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者姓名文本框不可为空");//弹出标题为 消息的对话框并显示后面的消息文本
				return;
			}
			//没有输入年龄
			if(age.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者年龄文本框不可为空");
				return;
			}
			//没有输入号码
			if(zjnumber.getText().length()==0){
				JOptionPane.showMessageDialog(null, "证件号码文本框不可为空");
				return;
			}
			//证件号码不是13位
			if(zjnumber.getText().length()!=13){
				JOptionPane.showMessageDialog(null, "证件号码位数为13");
				return;
			}
			//没有输入押金
			if(keepmoney.getText().length()==0){
				JOptionPane.showMessageDialog(null, "押金文本框不可为空");
				return;
			}
			//没有输入职业
			if(zy.getText().length()==0){
				JOptionPane.showMessageDialog(null, "职业文本框不可为空");
				return;
			}
			//输入的职业框位数大于30
			if(zy.getText().length()>30){
				JOptionPane.showMessageDialog(null, "职业文本框位数为30");
				return;
			}
			//没有输入读者编号
			if(ISBN.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者条形码文本框不可为空");
				return;
			}
			//读者编号不是13位
			if(ISBN.getText().length()!=13){
				JOptionPane.showMessageDialog(null, "读者条形码文本框为13位");
				return;
			}
			//没有输入电话
			if(tel.getText().length()==0){
				JOptionPane.showMessageDialog(null, "电话号码文本框不可为空");
				return;
			}
			//电话号码大于11位或小于0位
			if(tel.getText().length()>11||tel.getText().length()<0){
				JOptionPane.showMessageDialog(null, "电话号码位数小于11位");
				return;
			}
			//没有输入最大借书量
			if(maxnumber.getText().length()==0){
				JOptionPane.showMessageDialog(null, "最大借书量文本框不可为空");
				return;
			}
			//最大借书量大于两位数或小于0
			if(maxnumber.getText().length()>2||tel.getText().length()<0){
				JOptionPane.showMessageDialog(null, "最大借书量为两位数字");
				return;
			}
			//没有输入办证事件或得到的时间为空
			if(bztime.getText().isEmpty()||date.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "时间格式请使用\"2007-05-10\"格式");
				return;
			}
		
			String sex="1";//男
			if(!button1.isSelected()){//男没有被选中
				sex="2";}//女
			String zj=String.valueOf(comboBox.getSelectedIndex());//证件类型通过选择值引索
			System.out.println(comboBox.getSelectedIndex());
			
			//getText().trim()的作用是:在获得的文本中除去空格
			//将得到的文本信息去空格通过Dao层插入保存到数据库中的读者信息表格中
			int i=Dao.InsertReader(readername.getText().trim(), sex.trim(), age.getText()
					.trim(),zjnumber.getText().trim(), 
					Date.valueOf(date.getText().trim()), maxnumber.getText().trim(),tel
					.getText().trim(),
					Double.valueOf(keepmoney.getText().trim()),zj,zy.getText().trim(),
					Date.valueOf(bztime.getText().trim()),
					ISBN.getText().trim());
			System.out.println(i);
			if(i==1){
				JOptionPane.showMessageDialog(null, "添加成功！");
				doDefaultCloseAction();//关闭
			}
			
		}
	}
	class TelListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789-"+(char)8;//只允许输入数字与退格键   (char)8就是ASCII码的第8个char.也就是BS(退格键)
            //e.getKeyChar()就是获得当前按键的对应的ASCII码的char形式.然后通过key.indexOf()方法来检查该按键是否在key列表里,
			//如果不在的话,返回的index是-1,则-1 < 0为true,执行e.consume();
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}

}