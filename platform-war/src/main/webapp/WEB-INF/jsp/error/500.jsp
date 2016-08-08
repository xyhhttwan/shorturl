<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML><HEAD><TITLE>500系统发生异常</TITLE>
    <link rel="bookmark" type="image/x-icon" href="<%=basePath%>images/front/logo_title.jpg"/>
    <link rel="shortcut icon" href="<%=basePath%>images/front/logo_title.jpg">
    <META http-equiv=Content-Type content="text/html; charset=utf-8">
    <STYLE type=text/css>
        BODY {
        FONT-SIZE: 12px; FONT-FAMILY: Tahoma
    }
    TD {
        FONT-SIZE: 12px; FONT-FAMILY: Tahoma
    }
    A:link {
        COLOR: #636363; TEXT-DECORATION: none
    }
    A:visited {
        COLOR: #838383; TEXT-DECORATION: none
    }
    A:hover {
        COLOR: #a3a3a3; TEXT-DECORATION: underline
    }
    BODY {
        BACKGROUND-COLOR: #cccccc
    }
    </STYLE>

    <META content="MSHTML 6.00.2900.2523" name=GENERATOR></HEAD>
<BODY style="TABLE-LAYOUT: fixed; WORD-BREAK: break-all" topMargin=10
      marginwidth="10" marginheight="10">
<TABLE height="95%" cellSpacing=0 cellPadding=0 width="100%" align=center
       border=0>
    <TBODY>
    <TR vAlign=center align=middle>
        <TD>
            <TABLE cellSpacing=0 cellPadding=0 width=468 bgColor=#ffffff border=0>
                <TBODY>
                <TR>
                    <TD width=20 background="<%=basePath%>images/front/500/rbox_1.gif"
                        height=20></TD>
                    <TD width=108 background="<%=basePath%>images/front/500/rbox_2.gif"
                        height=20></TD>
                    <TD width=56><IMG height=20 src="<%=basePath%>images/front/500/rbox_ring.gif"
                                      width=56></TD>
                    <TD width=100 background="<%=basePath%>images/front/500/rbox_2.gif"></TD>
                    <TD width=56><IMG height=20 src="<%=basePath%>images/front/500/rbox_ring.gif"
                                      width=56></TD>
                    <TD width=108 background="<%=basePath%>images/front/500/rbox_2.gif"></TD>
                    <TD width=20 background="<%=basePath%>images/front/500/rbox_3.gif"
                        height=20></TD></TR>
                <TR>
                    <TD align=left background="<%=basePath%>images/front/500/rbox_4.gif"
                        rowSpan=2></TD>
                    <TD align=middle bgColor=#eeeeee colSpan=5 height=50>
                        <P><strong>对不起,系统发生异常请稍后访问 <br>
                            <br>
                        </strong></P></TD>
                    <TD align=left background="<%=basePath%>images/front/500/rbox_6.gif"
                        rowSpan=2></TD></TR>
                <TR>
                    <TD align=left colSpan=5 height=80>
                        <P align=center>
                        <P id=LID2></P>
                        <UL>
                            <LI id=list1>确保您输入了一个正确的地址<BR>
                            <LI id=list2>单击<A
                                    href="<%=basePath%>html/index.html">首页</A>按钮，返回首页。</LI>
                            <LI id=list3>单击<A
                                    href="javascript:history.back(1)">后退</A>按钮，尝试其他链接。 </LI></UL>
                        <DIV align=right><BR>
                            platform </DIV></TD></TR>
                <TR>
                    <TD align=left background="<%=basePath%>images/front/500/rbox_7.gif"
                        height=20></TD>
                    <TD align=left background="<%=basePath%>images/front/500/rbox_8.gif" colSpan=5
                        height=20></TD>
                    <TD align=left background="<%=basePath%>images/front/500/rbox_9.gif"
                        height=20></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></BODY></HTML>
