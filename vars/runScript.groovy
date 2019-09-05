import static com.andres.terraform.helpers.ScriptHelper.fillMissingProps
import static com.andres.terraform.helpers.ScriptHelper.isWindowsNode
import static com.andres.terraform.helpers.ScriptHelper.hideShellScript

def call(Map props) {

    def result

    props = fillMissingProps(props)
    ansiColor('xterm') {
        if (isWindowsNode(env.NODE_NAME)) {
            result = powershell(
                    script: props.script,
                    encoding: props.encoding,
                    label: props.label,
                    returnStatus: props.returnStatus,
                    returnStdout: props.returnStdout
            )
        } else {
            result = sh(
                    script: hideShellScript(props.script),
                    encoding: props.encoding,
                    label: props.label,
                    returnStatus: props.returnStatus,
                    returnStdout: props.returnStdout
            )
        }
    }
    if (props.returnStdout) {
        result = result == null ? '' : result.trim()
    }
    return result
}