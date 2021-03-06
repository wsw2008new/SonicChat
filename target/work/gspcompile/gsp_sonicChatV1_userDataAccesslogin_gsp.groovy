import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sonicChatV1_userDataAccesslogin_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/userDataAccess/login.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('resource','g',3,['dir':("css"),'file':("menuStyle.css")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',5,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(resource(dir:'images',file:'sonicLogo.png'))
printHtmlPart(5)
expressionOut.print(color)
printHtmlPart(6)
expressionOut.print(message)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
invokeTag('textField','g',52,['name':("username"),'type':("text"),'style':("width: 100%; margin: auto; margin-top: 3px;")],-1)
printHtmlPart(9)
invokeTag('textField','g',58,['name':("password"),'type':("password"),'style':("width: 100%; margin: auto; margin-top: 3px; ")],-1)
printHtmlPart(10)
invokeTag('actionSubmit','g',64,['value':("Login"),'action':("login"),'style':("margin-bottom: 15px; margin-top: 3px;"),'class':("MessageOK")],-1)
printHtmlPart(11)
})
invokeTag('form','g',67,['controller':("UserDataAccess"),'action':("dashboard")],2)
printHtmlPart(12)
})
invokeTag('captureBody','sitemesh',94,['style':("")],1)
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1438571278947L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
