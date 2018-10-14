<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>文章详情</title>
<link rel="stylesheet" href="switch.css"/>
<link rel="stylesheet" href="manage_content.css"/>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<div class="manage_content">
<a href="news.action?topId=<s:property value="news.category.id"/>">返回类别：<s:property value="news.category.name"/></a><br/>
<a href="news!getNewsById.action?news.id=<s:property value="news.id"/>&signStr=update&topId=<s:property value="#session.topId"/>">编辑</a>
<table style="width:100%;"><tr>
<td style="width:50%;">
<b>文章标题：<s:property value="news.title"/></b><br/>
</td>
<td style="width:50%; text-align:right;">
<b>发布时间：</b><s:date name="news.createTime" format="yyyy-MM-dd HH:mm:ss"/><br/>
</td>
</tr></table>
<b>语音播报：</b><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="290" height="24">
              <param name="movie" value='Audios/dew_large.swf?soundFile=Audios/<s:property value="news.id"/>.mp3&amp;bg=0xCDDFF3&amp;leftbg=0x357DCE&amp;lefticon=0xF2F2F2&amp;rightbg=0x357DCE&amp;rightbghover=0x4499EE&amp;righticon=0xF2F2F2&amp;righticonhover=0xFFFFFF&amp;text=0x357DCE&amp;slider=0x357DCE&amp;track=0xFFFFFF&amp;border=0xFFFFFF&amp;loader=0x8EC2F4'>
              <param name="quality" value="high">
			  <param value="transparent" name="wmode">
              <embed src='Audios/dew_large.swf?soundFile=Audios/<s:property value="news.id"/>.mp3&amp;bg=0xCDDFF3&amp;leftbg=0x357DCE&amp;lefticon=0xF2F2F2&amp;rightbg=0x357DCE&amp;rightbghover=0x4499EE&amp;righticon=0xF2F2F2&amp;righticonhover=0xFFFFFF&amp;text=0x357DCE&amp;slider=0x357DCE&amp;track=0xFFFFFF&amp;border=0xFFFFFF&amp;loader=0x8EC2F4' quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="290" height="24">
  		</object><br/>
<b>文章内容：</b><br/>
<s:property value="%{news.content}" escape="false"/>
</div>
</body>
</html>