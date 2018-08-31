package com.bhc.customerdashboard.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.ConnectionType;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bhc.customerdashboard.webservice.UserWebService;
import com.bhc.soa.web.contract.entity.user.v1.BadPassword;
import com.bhc.soa.web.contract.entity.user.v1.EmailNotFound;
import com.bhc.soa.web.contract.entity.user.v1.ProcessFailed;
import com.bhc.soa.web.contract.entity.user.v1.UserServicesV1;
import com.bhc.soa.web.contract.entity.user.v1.UserServiceV1;
import com.bhc.soa.web.contract.entity.user.v1.UsernameExists;
import com.bhc.soa.web.types.common.v1.RequestContextType;
import com.bhc.soa.web.types.common.v1.RequestorInfoType;
import com.bhc.soa.web.types.messages.user.v1.AddCisAccountRequestType;
import com.bhc.soa.web.types.messages.user.v1.BhcUserType;
import com.bhc.soa.web.types.messages.user.v1.CreateRequestType;
import com.bhc.soa.web.types.messages.user.v1.CreateResponseType;
import com.bhc.soa.web.types.messages.user.v1.FindRequestType;
import com.bhc.soa.web.types.messages.user.v1.FindResponseType;
import com.bhc.soa.web.types.messages.user.v1.SendUsernameEmailRequestType;
import com.bhc.soa.web.types.messages.user.v1.UserAccountType;
import  com.bhc.soa.web.types.common.v1.RequestContextType;

@Service
public class UserWebServiceRemote implements UserWebService {

    //@Value("${application.enable.company.change}")
    //private String enableCompanyChange;
	
    @Value("${auth.ldap.external.base.dn}")
    private String base = "OU=cis_customers, OU=Users, OU=Webapps, DC=Dev. DC=WebApps. DC=ad";

    @Value("${auth.security.user.type}")
    private String userType = "cis_user";

    @Value("${user.service.ad.data.type}")
    private String adDataType = "EXT-AD";

    @Value("${user.service.dbad.data.type}")
    private String dbadDataType = "DBAD";

    @Value("${user.security.migration.status}")
    private String migrationStatus = "Migrated";

    @Value("${user.security.access.role}")
    private String role = "cis_users";
    private String creatorId = "REGISTRATION";

    private String userServicesAddress = "https://wlsdevint1.bhcorp.ad:17003/userweb-service/services/user";
    private String userServicesWsdlAddress = "https://wlsdevint1.bhcorp.ad:17003/userweb-service/services/user?WSDL";
    
    private UserServicesV1 userServicesV1;
    private static final Logger log = LoggerFactory.getLogger(UserWebServiceRemote.class);

    protected static final String SCHEMA_VALIDATION_ENABLED = "schema-validation-enabled";
    
    protected void setInterceptors(JaxWsProxyFactoryBean factoryBean) {

        List<Interceptor<? extends Message>> inInteceptors = new ArrayList<Interceptor<? extends Message>>();
        inInteceptors.add(new org.apache.cxf.interceptor.LoggingInInterceptor());
        factoryBean.setInInterceptors(inInteceptors);

        List<Interceptor<? extends Message>> outInterceptors = new ArrayList<Interceptor<? extends Message>>();
        outInterceptors.add(new org.apache.cxf.interceptor.LoggingOutInterceptor());
        factoryBean.setOutInterceptors(outInterceptors);

        Map<String, Object> props = new HashMap<String, Object>();
        props.put(SCHEMA_VALIDATION_ENABLED, true);

        factoryBean.setProperties(props);
    }

    protected JaxWsProxyFactoryBean createJaxWsProxyFactoryBean(String address) {

        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setAddress(address);
        setInterceptors(factoryBean);
        return factoryBean;
    }
    
    protected <ProxyServiceType> ProxyServiceType addHttpPolicy(ProxyServiceType client) {

        Client cl = ClientProxy.getClient(client);
        HTTPConduit httpConduit = (HTTPConduit) cl.getConduit();

        HTTPClientPolicy policy = new HTTPClientPolicy();

        // setting only connection, others are not required, keeping here for reference.
        // policy.setConnectionTimeout(TIMEOUT);
        // policy.setReceiveTimeout(TIMEOUT);
        // policy.setAutoRedirect(true);
        // policy.setMaxRetransmits(2);

        policy.setAllowChunking(false);
        policy.setConnection(ConnectionType.CLOSE);

        httpConduit.setClient(policy);

        return client;
    }

    public UserWebServiceRemote( ) throws MalformedURLException {
    	URL wsdlUrl = new URL(userServicesWsdlAddress);
    	UserServiceV1 userServiceV1 = new UserServiceV1(wsdlUrl);
    	userServicesV1 = userServiceV1.getUserService();
        JaxWsProxyFactoryBean factoryBean = createJaxWsProxyFactoryBean(userServicesAddress);
        Map<String, Object> props = factoryBean.getProperties();
        props.put(SCHEMA_VALIDATION_ENABLED, false);

        this.userServicesV1 = addHttpPolicy(factoryBean.create(UserServicesV1.class));
        //this.userServicesV1 = userServicesV1;
    }

    private FindRequestType generateFindRequestType(String userName) throws DatatypeConfigurationException {
    	
    	RequestContextType rct = new RequestContextType();
    	rct.setRequestUuid("myuuid");
    	GregorianCalendar c = new GregorianCalendar();
    	c.setTime(new Date());
    	XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    	
    	rct.setRequestDt(date2);
    	
    	RequestorInfoType rit = new RequestorInfoType();
    	rit.setRequestorAppHost("App Host");
    	rit.setRequestorAppName("Customer Dashboard");
    	rct.setRequestor(rit);

        FindRequestType request = new FindRequestType();
        request.setBase(base);
        request.setRequestContext(rct);
        request.setType(dbadDataType);
        request.setUserName(userName);
        request.setUserType(userType);
        request.setLoginHistoryRequested(false);
        return request;
    }

    @Override
    public BhcUserType getLoggedInUserDetails(String userName) throws DatatypeConfigurationException {


        FindRequestType request = generateFindRequestType(userName);
        try {

            FindResponseType response = userServicesV1.find(request);
            
            return response.getUser();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }


    public void setBase(String base) {

        this.base = base;
    }

    public void setUserType(String userType) {

        this.userType = userType;
    }

    public void setAdDataType(String adDataType) {

        this.adDataType = adDataType;
    }

    public void setDbadDataType(String dbadDataType) {

        this.dbadDataType = dbadDataType;
    }

    public void setRole(String role) {
        this.role = role;
    }

       
}
