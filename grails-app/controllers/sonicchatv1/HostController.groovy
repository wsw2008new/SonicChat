package sonicchatv1

import session.Host
import session.MyWebSocketHandler

class HostController {

	// ****************** LIVE SERVER SOCKET CONTROL ******************
	
    def index() { 
		def hostData = HostData.get(1);
		if (hostData.systemActive) {
		render "Server is RUNNING at: " + session.Host.server.getURI().toString();
		} else {
		render "SYSTEM IS VOLUNTARY DOWN...";
		}
	}
	
	
	def toggleSocketServer() {
		//Must start socket!
		if (Host.server == null) {
			Host.startServer();
		} else if (!Host.server.isStarted()) {
			Host.startServer();
		} else if (Host.server.isStarted()) {
			Host.server.stop();
			Host.thread.interrupt();
		}
		Thread.sleep(3500);
		redirect(action: "adminControls")
		
	}
	
	
	def toggleAway() {
		def hostData = HostData.get(1);
		if (hostData.systemActive) {
			hostData.systemActive = false;
			hostData.totalActiveHost = 1;
		} else {
			hostData.systemActive = true;
			hostData.totalActiveHost = 1;
		}
		
		hostData.save(failOnError: true)
		
		Thread.sleep(3000);
		redirect(action: "adminControls")
	}
	
	
	def forceReset() {
		MyWebSocketHandler.clients.clear()
		MyWebSocketHandler.hosts.clear()
		if (Host.server != null) {
		Host.server.stop();
		Host.thread.interrupt();
		}
		redirect(action: "adminControls")
	}
		
	// Page for admin controls
	def adminControls() {	
		boolean serverStarted = false;
		boolean away = false;
		
		if (Host.server != null) {
			if (Host.server.isStarted()) { 
				serverStarted = true;	
			}
		}
		
		def hostData = HostData.get(1);
		if (hostData.systemActive) {
			away = true
		}
		
		render(view: "status", model: ["started": serverStarted.toString(), "away": away.toString()])
	}
	
	
	// ****************** Employee Systems ******************
	def loginEmployee() {	
		if (params.username == null && params.password == null){
			render(view: "login", model: ["message":"Welcome to the SonicChat dashboard.", "color": "#606060"])
		} else {
		def user = Users.findByUserNameAndPassword(params.username, params.password);
			if (user != null) {
				session["displayName"] = user.displayName
				session["siteID"] = user.siteID
				render (view: "ChatDashboardLive");
			} else {
				render(view: "login", model: ["message":"*Username or password not found. Please try again.", "color": "red"])	
			}
		
		}	
	}
	
	
	// ****************** TESTING ******************
	
	def StartHostLiveTest() {
		def hostData = HostData.get(1);
		if (hostData.systemActive) {
			def user = Users.findByUserName("Nick");
			session["displayName"] = user.displayName
			session["siteID"] = user.siteID
			render (view: "ChatDashboardLive");
		} else {
			render "Server is not running."
		}
	}
	
	def StartHostTest() {
		//Must start socket!
		if (Host.server == null) {
			Host.startServer();
		} else if (!Host.server.isStarted()) {
			Host.startServer();
		}

		Thread.sleep(3500);
		def hostData = HostData.get(1);
		if (hostData.systemActive) {
			def user = Users.findByUserName("Nick");
			session["displayName"] = user.displayName
			session["siteID"] = user.siteID
			render (view: "ChatDashboard");
		} else  {
			render "Server is not running."
		}
	}
	
	def startChatTest() {
		render (view: "TestChat" )
	}
	
}
