/* 
 * Copyright 2005 Paul Hinds
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tp23.antinstaller.input;

/**
 *
 * <p>An InputField that should not have its property printed to the properties
 * file, probably for security reasons such as a PasswordTextInput </p>
 * <p>It is imperative that all classes that implement this interface extend
* InputField </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: SecretPropertyField.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public interface SecretPropertyField{
}
