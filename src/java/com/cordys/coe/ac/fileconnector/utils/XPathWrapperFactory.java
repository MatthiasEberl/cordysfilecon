/**
 * (c) 2008 Cordys R&D B.V. All rights reserved. The computer program(s) is the
 * proprietary information of Cordys B.V. and provided under the relevant
 * License Agreement containing restrictions on use and disclosure. Use is
 * subject to the License Agreement.
 */
package com.cordys.coe.ac.fileconnector.utils;

import com.cordys.coe.ac.fileconnector.exception.ConfigException;

import com.eibus.xml.xpath.XPath;
import com.eibus.xml.xpath.XPathMetaInfo;

/**
 * Factory class for XPathWrapper object. This class allows creation of XPathWrappers with different
 * configuration, e.g. namespace bindings.
 *
 * @author  mpoyhone
 */
public class XPathWrapperFactory
{
    /**
     * If true, this factory creates non-namespace XPaths (e.g. /a/b will be translated to
     * /*[local-name()='a']/*[local-name='b'] ).
     */
    private boolean useSimpleXPaths = false;
    /**
     * Contains the namespace bindnings.
     */
    private XPathMetaInfo xpathMetainfo;

    /**
     * Constructor for XPathWrapperFactory.
     */
    private XPathWrapperFactory()
    {
    }

    /**
     * Creates a factory which creates non-namespace XPaths (e.g. /a/b will be translated). to
     * /*[local-name()='a']/*[local-name='b'] ).
     *
     * @return  A new configured factory object.
     */
    public static XPathWrapperFactory createFactory()
    {
        XPathWrapperFactory res = new XPathWrapperFactory();

        res.xpathMetainfo = null;
        res.useSimpleXPaths = true;

        return res;
    }

    /**
     * Creates a factory which uses namespace bindings.
     *
     * @param   xpathMetainfo  XPath namespace bindings.
     *
     * @return  A new configured factory object.
     */
    public static XPathWrapperFactory createFactory(XPathMetaInfo xpathMetainfo)
    {
        XPathWrapperFactory res = new XPathWrapperFactory();

        res.xpathMetainfo = xpathMetainfo;
        res.useSimpleXPaths = false;

        return res;
    }

    /**
     * Creates a configured XPath wrapper.
     *
     * @param   xpath  XPath expression.
     *
     * @return  XPath wrapper.
     *
     * @throws  ConfigException
     */
    public XPathWrapper createWrapper(String xpath)
                               throws ConfigException
    {
        XPath xp = createXPath(xpath, true);

        return new XPathWrapper(xpath, xp, xpathMetainfo);
    }

    /**
     * Creates a configured XPath wrapper.
     *
     * @param   xpath              XPath expression.
     * @param   allowSimpleXPaths  If <code>false</code>, no the simple XPath form is not used, even
     *                             if it is configured for this factory.
     *
     * @return  XPath wrapper.
     *
     * @throws  ConfigException
     */
    public XPathWrapper createWrapper(String xpath, boolean allowSimpleXPaths)
                               throws ConfigException
    {
        XPath xp = createXPath(xpath, allowSimpleXPaths);

        return new XPathWrapper(xpath, xp, xpathMetainfo);
    }

    /**
     * Creates a configured XPath wrapper. This version replaces the first element with "." in the
     * beginning of the XPath, i.e. "a/b" becomes "./b".
     *
     * @param   xpath  XPath expression.
     *
     * @return  XPath wrapper.
     *
     * @throws  ConfigException
     */
    public XPathWrapper createWrapperForChildXPath(String xpath)
                                            throws ConfigException
    {
        if ((xpath.indexOf("/") < 0) && (xpath.indexOf(".") < 0))
        {
            xpath = ".";
        }
        else if (!xpath.startsWith("/") && !xpath.startsWith("."))
        {
            xpath = xpath.replaceFirst("^[^/]+/(.*)", "./$1");
        }

        XPath xp = createXPath(xpath, true);

        return new XPathWrapper(xpath, xp, xpathMetainfo);
    }

    /**
     * /** If set to true, this factory creates non-namespace XPaths (e.g. /a/b will be translated
     * to /*[local-name()='a']/*[local-name='b'] ).
     *
     * @param  useSimpleXPath  Value to be set.
     */
    public void setUseSimpleXPath(boolean useSimpleXPath)
    {
        this.useSimpleXPaths = useSimpleXPath;
    }

    /**
     * Creates a new XPath object based on the configuration.
     *
     * @param   xpath              XPath expression.
     * @param   allowSimpleXPaths  XPath expression.
     *
     * @return  Parsed XPath object.
     *
     * @throws  ConfigException
     */
    private XPath createXPath(String xpath, boolean allowSimpleXPaths)
                       throws ConfigException
    {
        if (useSimpleXPaths && allowSimpleXPaths)
        {
            xpath = xpath.replaceAll("([^/.]+)", "*[local-name()='$1']");
        }

        try
        {
            return new XPath(xpath);
        }
        catch (Exception e)
        {
            throw new ConfigException("Unable to create XPath: " + xpath, e);
        }
    }
}
