<%-- 
    Document   : form
    Created on : Sep 30, 2018, 8:07:59 AM
    Author     : KALINDU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.0.3.min.js"></script>
        <script>
            var uri = "http://localhost:8080/CSRFSyncT/token";
            $(document).ready(function() {
		$.ajax({
			type : "GET",
			url : uri,
			dataType : "json",
			cache : false,
			crossDomain : true,
			processData : true,
			success : function(data) {
				$("#csrf").val(data['token']);
                                
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error");
			}
		});
	});
        </script>
    </head>
    <body>
        <h1>Registration Form</h1>
        <form method="post" action="submit">
            <table border="0" cellpadding="2" cellspacing="2">
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="fname" /></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lname" /></td>
                </tr>
                
                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" name="buttn" value="Submit" /></td>
                <input type="hidden" id="csrf" name="csrf" value="a"/>
                </tr>
            </table>
        </form>
    </body>
</html>
