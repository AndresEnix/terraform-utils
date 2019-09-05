import com.andres.terraform.utils.TerraformVersion

import static com.andres.terraform.helpers.ScriptHelper.isWindowsNode

def call() {
    if (env.TERRAFORM_VERSION_FOR_UTILS) {
        return new TerraformVersion(env.TERRAFORM_VERSION_FOR_UTILS)
    }
    return getVersionFromScript()
}

def getVersionFromScript() {
    def script
    if (isWindowsNode(env.NODE_NAME)) {
        script = "terraform -version"
    } else {
        script = "terraform -version | egrep -o 'v([0-9]{1,}\\.)+[0-9]{1,}' | head -1"
    }
    def version = runScript(script: script, returnStdout: true)
    env.TERRAFORM_VERSION_FOR_UTILS = version
    return new TerraformVersion(version)
}