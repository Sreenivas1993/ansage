<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="header.html" %>

<body>
    <!-- Navigation -->
    <%@ include file="nav.jsp" %>
	<script>
	$(document).ready(function(){
		$('title').text("Ansage | Page Not Found");
		
		
	});		
	</script>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <jsp:include page="sidebar.jsp" />

                
            <div class="col-md-9">
                <div class="row">
                    
                    <p class="lead" id="loginmsg">Sorry, Nothing Here.</p>
                    <p>The page you are looking for does not exist.</p>
                   
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
   

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
