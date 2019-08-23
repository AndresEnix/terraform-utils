import static com.andres.terraform.helpers.ScriptHelper.isWindowsNode
import static com.andres.terraform.helpers.ConfigHelper.addMissingEnvironmentConfig
import static com.andres.terraform.helpers.ConfigHelper.addMissingEnvVars
import static com.andres.terraform.helpers.ConfigHelper.ENV_VARS_FILE_NAME

def call(Map userVars, Closure body) {
    def envVars = getAllEnvVars(env)
    env = addMissingEnvVars(env, envVars)
    env = addMissingEnvVars(env, userVars)
    env = addMissingEnvironmentConfig(env)
    body()
}

def getAllEnvVars(env) {
    def script = isWindowsNode(env.NODE_NAME) ? "set > ${ENV_VARS_FILE_NAME}" : "env > ${ENV_VARS_FILE_NAME}"
    runScript(script: script)
    def envVars = new Properties()
    envVars.load(new StringReader(readFile("${ENV_VARS_FILE_NAME}")))
    return envVars
}