package com.andres.terraform.helpers

import org.junit.Test
import org.junit.Assert

import static ScriptHelper.DEFAULT_SCRIPT
import static ScriptHelper.DEFAULT_ENCODING
import static ScriptHelper.DEFAULT_LABEL
import static ScriptHelper.DEFAULT_RETURN_STATUS
import static ScriptHelper.DEFAULT_RETURN_STD_OUTS
import static ScriptHelper.LINUX_NODE
import static ScriptHelper.WINDOWS_NODE
import static ScriptHelper.fillMissingProps
import static ScriptHelper.isWindowsNode

class ScriptHelperTest {

    @Test
    void fillMissingProps_notNull() throws Exception {
        def result = fillMissingProps(null)
        Assert.assertNotNull(result)
    }

    @Test
    void fillMissingProps_useDefaultsWhenNoValuesProvided() throws Exception {
        setup:
        def expectedScript = DEFAULT_SCRIPT
        def expectedEncoding = DEFAULT_ENCODING
        def expectedLabel = DEFAULT_LABEL
        def expectedReturnStatus = DEFAULT_RETURN_STATUS
        def expectedReturnStdout = DEFAULT_RETURN_STD_OUTS
        def props = null

        when:
        def actualScript = fillMissingProps(props).script
        def actualEncoding = fillMissingProps(props).encoding
        def actualLabel = fillMissingProps(props).label
        def actualReturnStatus = fillMissingProps(props).returnStatus
        def actualReturnStdout = fillMissingProps(props).returnStdout

        then:
        Assert.assertEquals(expectedScript, actualScript)
        Assert.assertEquals(expectedEncoding, actualEncoding)
        Assert.assertEquals(expectedLabel, actualLabel)
        Assert.assertEquals(expectedReturnStatus, actualReturnStatus)
        Assert.assertEquals(expectedReturnStdout, actualReturnStdout)
    }

    @Test
    void fillMissingProps_useProvidedValues() throws Exception {
        setup:
        def expectedScript = 'ls -l'
        def expectedEncoding = 'ISO-8859-1'
        def expectedLabel = 'My label'
        def expectedReturnStatus = true
        def expectedReturnStdout = true
        def props = [
                script      : expectedScript,
                encoding    : expectedEncoding,
                label       : expectedLabel,
                returnStatus: expectedReturnStatus,
                returnStdout: expectedReturnStdout
        ]

        when:
        def actualScript = fillMissingProps(props).script
        def actualEncoding = fillMissingProps(props).encoding
        def actualLabel = fillMissingProps(props).label
        def actualReturnStatus = fillMissingProps(props).returnStatus
        def actualReturnStdout = fillMissingProps(props).returnStdout

        then:
        Assert.assertEquals(expectedScript, actualScript)
        Assert.assertEquals(expectedEncoding, actualEncoding)
        Assert.assertEquals(expectedLabel, actualLabel)
        Assert.assertEquals(expectedReturnStatus, actualReturnStatus)
        Assert.assertEquals(expectedReturnStdout, actualReturnStdout)
    }

    @Test
    void isWindowsNode_returnsTrueOnWindowsNode() throws Exception {
        Assert.assertTrue(isWindowsNode(WINDOWS_NODE))
    }

    @Test
    void isWindowsNode_returnsFalseWhenNodeIsNotWindows() throws Exception {
        Assert.assertFalse(isWindowsNode(LINUX_NODE))
    }
}