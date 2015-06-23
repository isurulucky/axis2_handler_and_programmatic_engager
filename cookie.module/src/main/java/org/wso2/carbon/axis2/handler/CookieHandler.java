/*
 * Copyright (c) 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.axis2.handler;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.engine.Handler;
import org.apache.axis2.handlers.AbstractHandler;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.Header;

import java.util.*;

/**
 * This axis2 handler is used to query an external service to obtain custom Cookie(s).
 */
public class CookieHandler extends AbstractHandler implements Handler {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public InvocationResponse invoke(MessageContext msgContext) throws AxisFault {

        // TODO: invoke Cookie API. If success return InvocationResponse.CONTINUE, else InvocationResponse.ABORT

        // get OUT message context
        MessageContext outMessageContext = msgContext.getOperationContext().getMessageContext("Out");
        // here, a dummy cookie named MyCookie is set
        if (outMessageContext != null) {
            List<Header> headers = new ArrayList<Header>();
            headers.add(new Header(HTTPConstants.HEADER_SET_COOKIE, "MyCookie=DJFIASNB143VNJA453JKFS86FDSA"));
            outMessageContext.setProperty(HTTPConstants.HTTP_HEADERS, headers);
        }

        return InvocationResponse.CONTINUE;
    }
}
