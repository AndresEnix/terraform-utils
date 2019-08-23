package com.andres.terraform.helpers

class ConfigHelper {

    def static final ENV_VARS_FILE_NAME = 'env.txt'
    def static final TF_IS_CONFIGURED = 'true'
    def static final DEFAULT_TF_LOG = 'INFO'
    def static final DEFAULT_TF_LOG_PATH = 'terraform.log'
    def static final DEFAULT_TF_CLI_ARGS = ''
    def static final DEFAULT_TF_DATA_DIR = '.terraform'
    def static final DEFAULT_TF_INPUT = 'false'
    def static final DEFAULT_TF_IN_AUTOMATION = 'true'

    def static addMissingEnvironmentConfig(env) {
        if (env == null) {
            env = [:]
        }
        env.TF_INPUT = DEFAULT_TF_INPUT
        env.TF_IN_AUTOMATION = DEFAULT_TF_IN_AUTOMATION
        if (!env.TF_IS_CONFIGURED) {
            if (!env.TF_LOG) {
                env.TF_LOG = DEFAULT_TF_LOG
            }
            if (!env.TF_LOG_PATH) {
                env.TF_LOG_PATH = DEFAULT_TF_LOG_PATH
            }
            if (!env.TF_CLI_ARGS) {
                env.TF_CLI_ARGS = DEFAULT_TF_CLI_ARGS
            }
            if (!env.TF_DATA_DIR) {
                env.TF_DATA_DIR = DEFAULT_TF_DATA_DIR
            }
            env.TF_IS_CONFIGURED = TF_IS_CONFIGURED
        }
        return env
    }

    def static addMissingEnvVars(env, vars) {
        vars.each { key, val ->
            def varName = key.startsWith("TF_VAR_") ? key : "TF_VAR_${key}"
            if (!env."${varName}") {
                env."${varName}" = val
            }
        }
        return env
    }
}