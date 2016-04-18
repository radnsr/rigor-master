
<%
	response.addHeader("Cache-Control",
			"no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
	response.addHeader("Pragma", "no-cache");
	response.addDateHeader("Expires", 0);
%>
<%@page import="com.rigor.model.User"%>

<md-sidenav layout="column" class="md-sidenav-left md-whiteframe-z2"
	md-component-id="left"> <md-toolbar
	class="md-tall md-hue-2"> <span flex></span>
<div layout="column" class="md-toolbar-tools-bottom inset">
	<user-avatar></user-avatar>
	<span></span>
	<div>

		<%
			User currentUser = (User) (session.getAttribute("user"));
			if (currentUser == null) {
				response.sendRedirect("error");
			} else {
		%>
		<%=currentUser.getName()%>
		<%
			}
		%>
	</div>
	<div>${user.email }</div>
</div>
</md-toolbar> <md-list> <md-item ng-repeat="item in menu"> <md-button
	href="{{item.link}}"> <md-item-content md-ink-ripple
	layout="row" layout-align="start center">
<div class="inset">
	<ng-md-icon icon="{{item.icon}}"></ng-md-icon>
</div>
<div class="inset">{{item.title}}</div>
</md-item-content> </md-button> </md-item> <md-divider></md-divider> <md-subheader>Management</md-subheader> <md-item
	ng-repeat="item in admin"> <a> <md-item-content
		md-ink-ripple layout="row" layout-align="start center">
	<div class="inset">
		<ng-md-icon icon="{{item.icon}}"></ng-md-icon>
	</div>
	<div class="inset">{{item.title}}</div>
	</md-item-content>
</a> </md-item> </md-list> </md-sidenav>
