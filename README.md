# axis2_handler_and_programmatic_engager
Contains a sample code for creating an axis2 handler and registering it programmatically for a particular axis2 service in a WSO2 carbon server.

This sample will:

1. Create a custom axis2 module, which will comprise of the handler
2. Engage this axis2 module to the AuthenticationAdmin service using a separate component

Testing the samples:

1. Clone
2. Build both sources using Maven, using the command mvn clean install
3. Shutdown the WSO2 IS (or any relevant carbon server) if already running
4. Copy the jar files (org.wso2.carbon.axis2.handler.custom.cookie-1.0.0.jar and org.wso2.carbon.axis2.module.engager-1.0.0.jar) built in step #2, to <IS_HOME>/repository/components/dropins
5. Restart the carbon server
6. Invoke the AuthenticationAdminService. I have attached the sample payload (payload.xml), which was used to invoke the service. Please find the sample curl command [1] used and the response [2] below. Note the custom cookie starting with 'MyCookie=' in the response.

[1]. curl -v -d @payload.xml -H "Content-Type: application/soap+xml action=login" -X POST -k https://localhost:9443/services/AuthenticationAdmin

[2].
> POST /services/AuthenticationAdmin HTTP/1.1
> User-Agent: curl/7.35.0
> Host: localhost:9443
> Accept: /
> Content-Type: application/soap+xml action=login
> Content-Length: 467
>

    upload completely sent off: 467 out of 467 bytes
    < HTTP/1.1 200 OK
    < Set-Cookie: JSESSIONID=498900C2E646306596DDA82BFA78E681; Path=/; Secure; HttpOnly
    < Set-Cookie: MyCookie=DJFIASNB143VNJA453JKFS86FDSA
    < Content-Type: text/xml;charset=UTF-8
    < Transfer-Encoding: chunked
    < Date: Thu, 04 Jun 2015 17:58:07 GMT
    Server WSO2 Carbon Server is not blacklisted
    < Server: WSO2 Carbon Server

