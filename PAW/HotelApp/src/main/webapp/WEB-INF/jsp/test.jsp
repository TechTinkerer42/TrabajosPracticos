<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>Demo JSP</TITLE>
		<LINK REL=STYLESHEET HREF="css/style1.css" TYPE="text/css">
	</HEAD>
	<BODY>
		<H1>Hotel Manager</H1>
		<H2>Sign in:</H2>
		<form method='POST' action="<%= ar.edu.itba.it.paw.hotelapp.ServletName.LOGIN_SERVLET %>">
			<h3>Sign in</h3>
			<h4>Username:</h4>
			<input name="username" />
			<h4>Password:</h4>
			<input name="password" type="password" />
			<br />
			<input type="submit" value="Log in" />
		</form>
	</BODY>
</HTML>