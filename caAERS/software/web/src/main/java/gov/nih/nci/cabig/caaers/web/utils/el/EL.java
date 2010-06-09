/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package gov.nih.nci.cabig.caaers.web.utils.el;

import junit.framework.TestCase;
import org.apache.commons.el.BinaryOperatorExpression;
import org.apache.commons.el.ExpressionString;
import org.apache.commons.el.parser.ELParser;
import org.apache.commons.el.parser.ParseException;

import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.Expression;
import java.io.StringReader;

public class EL {

    private MockVariableResolver resolver;
    private MockFunctionMapper mapper;

    public String evaluate(String input) {
        try {
            StringReader rdr = new StringReader(input);
            ELParser parser = new ELParser(rdr);
            Object result = parser.ExpressionString();

            if(result instanceof String) {
                return (String) result;
            } else if(result instanceof Expression) {
                Expression expr = (Expression) result;
                result = expr.evaluate(this.resolver);
                return result == null ? null : result.toString();
            } else if(result instanceof ExpressionString) {
                Expression expr = (Expression) result;
                result = expr.evaluate(this.resolver);
                return result == null ? null : result.toString();
            } else if (result instanceof BinaryOperatorExpression) {
                BinaryOperatorExpression expr = (BinaryOperatorExpression)result;
                result = expr.evaluate(this.resolver, this.mapper, null);
                return result.toString();
            } else {
                System.out.println("Incorrect type returned; not String, Expression or ExpressionString");
                return "";
            }
        } catch(ParseException pe) {
            throw new RuntimeException("ParseException thrown: " + pe.getMessage(), pe);
        } catch(ELException ele) {
            throw new RuntimeException("ELException thrown: " + ele.getMessage(), ele);
        }
    }

}
