<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<div class="col-md-3" id = "sidebar">

                <p class="lead">Actions</p>
                <% if(session.getAttribute("USER") == null) { %>  
                <div class="well">
                    <p id="indicate">Sign In.</p>
                    <br/>
                     <form action="LoginService" method="POST">
                        <div class="form-group">
                        <label for="LoginEmail">
                        Email address
                        </label>
                        <input type="email" name="emailid" class="form-control" id="emailid" aria-describedby="emailHelp" placeholder="Enter email" />
                       
                        </div>
						
                        <div class="form-group">
                        <label for="LoginPass">Password</label>
                        <input name="passwd" type="password" class="form-control" id="passwd" placeholder="Password" />
                        </div> 
                        <input id= "kht" type="hidden" name="locn" />
                        <button type="submit" class="btn btn-success">Submit</button>
                    </form>
                     <script>
						$(document).ready(function(){
							getLocation();
						});	
                     
						function getLocation() {
						    if (navigator.geolocation) {
						        navigator.geolocation.getCurrentPosition(showPosition);
						    } else {
						    	document.getElementById("kht").value = "Unknown";
						    }
						}
						function showPosition(position) {
						    var kht = position.coords.latitude + ","+position.coords.longitude;
						    document.getElementById("kht").value = kht;
						}
						</script>
                    <br/>
                    <p id="orreg">Or 
                    <a href="register.jsp"> Register</a></p>
                </div>
                <% } else { %>
                <!-- Else this -->
                <div class="well">
                Hey, 
				<%= session.getAttribute("NAME")%>
                </div>
                <div class="list-group">
                    <% out.println("<a class=\'list-group-item\' href=\'ViewProfile?profile=" + session.getAttribute("PROID") + "\'>"); %>
                    My Profile</a>
                    <a href="ask.jsp" class="list-group-item" id="askq">Post Question</a>
                    <a href="search.jsp?q=all" class="list-group-item">Answer Questions</a>
                    <a href="#" class="list-group-item" id="dispcoin">My Coins: 
                    <%= session.getAttribute("COINS")%>                   
                    </a>
                    <!--a href='profile.jsp?userid=12' class="list-group-item"-->
                </div>
                <script>
                $(document).ready(function(){
                	setInterval(function(){
                		$.get("CoinUpdate",function(respText){
    						$('#dispcoin').text("My Coins: " + respText);
    					});
                	},1000000);
					
				});	
                </script>
                <% } %>
            </div>
            
