package com.bhc.startstop.webservice;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bhc.soa.web.contract.entity.user.v1.BadPassword;
import com.bhc.soa.web.contract.entity.user.v1.EmailNotFound;
import com.bhc.soa.web.contract.entity.user.v1.ProcessFailed;
import com.bhc.soa.web.contract.entity.user.v1.UserServicesV1;
import com.bhc.soa.web.contract.entity.user.v1.UsernameExists;
import com.bhc.soa.web.types.common.v1.RequestContextType;
import com.bhc.soa.web.types.messages.user.v1.AddCisAccountRequestType;
import com.bhc.soa.web.types.messages.user.v1.BhcUserType;
import com.bhc.soa.web.types.messages.user.v1.CreateRequestType;
import com.bhc.soa.web.types.messages.user.v1.CreateResponseType;
import com.bhc.soa.web.types.messages.user.v1.FindRequestType;
import com.bhc.soa.web.types.messages.user.v1.FindResponseType;
import com.bhc.soa.web.types.messages.user.v1.SendUsernameEmailRequestType;
import com.bhc.soa.web.types.messages.user.v1.UserAccountType;

import com.bhc.startstop.web.model.Person;
import com.bhc.startstop.web.model.PrimaryPerson;
import com.bhc.startstop.webservice.UserWebService;

@Service
public class UserWebServiceImpl implements UserWebService {

    //@Value("${application.enable.company.change}")
    //private String enableCompanyChange;
/*
	
    @Value("${auth.ldap.external.base.dn}")
    private String base;

    @Value("${auth.security.user.type}")
    private String userType;

    @Value("${user.service.ad.data.type}")
    private String adDataType;

    @Value("${user.service.dbad.data.type}")
    private String dbadDataType;

    @Value("${user.security.migration.status}")
    private String migrationStatus;

    @Value("${user.security.access.role}")
    private String role;
*/
    private String creatorId = "REGISTRATION";

    private UserServicesV1 userServicesV1;
    private static final Logger log = LoggerFactory.getLogger(UserWebServiceImpl.class);

    public UserWebServiceImpl( UserServicesV1 userServicesV1) {
        this.userServicesV1 = userServicesV1;
    }

    private FindRequestType generateFindRequestType(String userName) {

        FindRequestType request = new FindRequestType();
        request.setBase(base);
        request.setRequestContext(SoapHeaderInfo.createRequestContext());
        request.setType(dbadDataType);
        request.setUserName(userName);
        request.setUserType(userType);
        request.setLoginHistoryRequested(false);
        return request;
    }

    @Override
    public PrimaryPerson getLoggedInUserDetails(String userName) {

        PrimaryPerson profile = new PrimaryPerson();

        FindRequestType request = generateFindRequestType(userName);
        try {

            FindResponseType response = userServicesV1.find(request);
            if (StringUtils.isNotEmpty(response.getUser().getBusinessName())) {
                profile.setBusinessName(response.getUser().getBusinessName());
            } /* else {
                 profile.setBusinessName("");
              }*/
            profile.setEmail(response.getUser().getEmailAddress());
            // profile.setEnableCompanyChange(enableCompanyChange);
            profile.setFirstName(response.getUser().getFirstName());
            profile.setLastName(response.getUser().getLastName());
            profile.setPrimaryPhoneNumber(response.getUser().getPrimaryPhone());
            // set account numbers
            for (UserAccountType account : response.getUser().getAccountNbrs()) {
                profile.addCisAccountNumber(account.getCisAccountNbr());
            }

            /*if (response.getUser().getCompanyId() != null) {
                profile.setCompanyId(response.getUser().getCompanyId().toString());
            }*/

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
        return profile;

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
