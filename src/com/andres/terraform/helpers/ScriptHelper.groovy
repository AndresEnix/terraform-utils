package com.andres.terraform.helpers


class ScriptHelper {

    def static final DEFAULT_SCRIPT = 'echo "[WARNING] No script was provided"'
    def static final DEFAULT_ENCODING = 'UTF-8'
    def static final DEFAULT_LABEL = 'Executing custom script'
    def static final DEFAULT_RETURN_STATUS = false
    def static final DEFAULT_RETURN_STD_OUTS = false

    def static final LINUX_NODE = 'linux'
    def static final WINDOWS_NODE = 'windows'

    static boolean isWindowsNode(nodeName) {
        return WINDOWS_NODE.equalsIgnoreCase(nodeName)
    }

    def static fillMissingProps(Map props) {
        if (props == null) {
            props = [:]
        }
        if (props.script == null) {
            props.script = DEFAULT_SCRIPT
        }
        if (props.encoding == null) {
            props.encoding = DEFAULT_ENCODING
        }
        if (props.label == null) {
            props.label = DEFAULT_LABEL
        }
        if (props.returnStatus == null) {
            props.returnStatus = DEFAULT_RETURN_STATUS
        }
        if (props.returnStdout == null) {
            props.returnStdout = DEFAULT_RETURN_STD_OUTS
        }
        return props
    }
}