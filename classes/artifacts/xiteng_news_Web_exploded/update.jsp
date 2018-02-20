<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新新闻</title>
<script src="js/jquery.min.js"></script>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<script src="css/bootstrap/js/bootstrap.js"></script>
<link rel=stylesheet href="css/index.css">
<script type="text/javascript">
function addPic(){
	var pic=document.getElementById("addPicutre");
	var cont=document.getElementById("content");
	var start="<img src=\"";
	var end="\">";
	cont.value+=start+pic.value+end;
	}
</script>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="row login-box">
  <!--定义窗口横跨  col-md-offset-4使得窗口右移，形成居中显示效果-->
     <div  class="col-md-10 col-md-offset-1">
     <!--使用panel定义面板 panel-primary规定面板的皮肤颜色(也可以设为panel-info等)-->
         <div  class="panel panel-primary">
            <!--设定头部标题栏-->
            <div class="panel-heading">发布新闻</div>
            <!--设定面板主体，我们的表单元素要全部放在这里面-->
            <div class="panel-body">
               <form name="formen" action="NewsManageServlet?type=edit_end&id=${CURRENT_READ.getId()}" method="post">
               <!--form-group对每个表单域进行分组-->
                  <div class="form-group">
                      <label>标题:</label>
                      <!--给表单域添加form-control样式，能够美化文本框-->
                      <input type="text" class="form-control" name="title" placeholder="必填" value="${CURRENT_READ.getTitle() }" required>
                  </div>
                  <div class="form-group">
                      <label>简介:</label>
                      <!--给表单域添加form-control样式，能够美化文本框-->
                      <input type="textarea" class="form-control" name="brief" placeholder="必填" value="${CURRENT_READ.getBrief() }"  required>
                  </div>
                   <div class="form-group">
                      <label>作者</label>
                      <!--给表单域添加form-control样式，能够美化文本框-->
                      <input type="text" class="form-control" name="author" placeholder="必填" value="${CURRENT_READ.getAuthor() }"  required>
                  </div>
              	 <div class="form-group">
                      <label>内容:</label>
<input class="media-object" name="addPicutre" id="addPicutre" type="file" style="display:none" onChange="addPic()">

<button type="button"  class="glyphicon glyphicon-picture " aria-hidden="true" onclick="document.formen.addPicutre.click()"></button>
    
                      <!--给表单域添加form-control样式，能够美化文本框-->
                  	  <textarea class="form-control" rows="4" id="content" name="content" placeholder="必填"  required>${CURRENT_READ.getContent() }</textarea>
                  </div>
                  <div class="col-md-4 col-md-offset-4">
              	<button style="width:90px" class="btn  btn-primary" type="submit">发布</button>
				<a href="index.jsp" style="width:90px" class="btn  btn-primary">首页</a>
                		</div>
               </form>
            </div>
         </div>

     </div>
  </div>

</body>
</html>