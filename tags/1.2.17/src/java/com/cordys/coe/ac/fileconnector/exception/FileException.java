/**
 * Copyright 2005 Cordys R&D B.V. 
 * 
 * This file is part of the Cordys SAP Connector. 
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
 package com.cordys.coe.ac.fileconnector.exception;

/**
 * The exception class for FileConnector.
 *
 * @author  mpoyhone
 */
public class FileException extends Exception
{
    /**
     * Creates a new FileException object.
     */
    public FileException()
    {
        super();
    }

    /**
     * Creates a new exception object.
     *
     * @param  cause  The original exception
     */
    public FileException(Throwable cause)
    {
        super(cause);
    }

    /**
     * Creates a new exception object.
     *
     * @param  msg  Exception message
     */
    public FileException(String msg)
    {
        super(msg);
    }

    /**
     * Creates a new exception object.
     *
     * @param  message  Exception message
     * @param  cause    The original exception
     */
    public FileException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
