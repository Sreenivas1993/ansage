<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="header.html" %>

<body>

    <!-- Navigation -->
  	<%@ include file="nav.jsp" %>
	<script>
	$(document).ready(function(){
		$('head').append('<meta http-equiv="refresh" content="900">');
		
		/*window.setTimeout(
				$.get("Refresh", function(responseText) {
					window.location.href = responseText;
		            
				})
				, 15000);*/
			
		
			
		
		
	});		
	</script>
    <!-- Page Content -->
    <div class="container">
	
        <div class="row">

            <jsp:include page="sidebar.jsp" />

            
            <div class="col-md-9">
                
            	
                <div class="row">
                <p class="lead" id="txt">See our <a id="bttn">Questions</a></p>
                </div>

                </div>

            </div>

        </div>

    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

        <!-- Footer -->
        <%@ include file="footer.html" %>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
    
