package com.bhc.customerdashboard.webservice;

import javax.xml.datatype.DatatypeConfigurationException;

import com.bhc.customerdashboard.web.model.BHUser;
import com.bhc.soa.web.types.messages.user.v1.BhcUserType;

public interface UserWebService {
    public BhcUserType getLoggedInUserDetails(String userName) throws DatatypeConfigurationException;
/*
    public void create(String username, String password, Person person)
            throws DuplicateUserNameException, BadPasswordException, WebProfileCreationException;
    public void addAccount(int companyId, String username, Long accountNumber) throws WebServiceException;
    
    // for user functions - can be removed when sso is implemented:
    public void sendUsernamesEmail(String email) throws EmailNotFoundException;
 */

}