
  
<h3>Login Form</h3>  
<%  
String msg=(String)request.getAttribute("msg");  
if(msg!=null){  
out.print(msg);  
}  
 %>  
 <br/>  
<form action="UserServletController" method="post">  
Email:<input type="text" name="email"/><br/><br/>  
Password:<input type="password" name="password"/><br/><br/>  
<input type="submit" value="login"/>
</form>  
