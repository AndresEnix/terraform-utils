package com.andres.terraform.helpers

import org.junit.Assert
import org.junit.Test

import static ConfigHelper.addMissingEnvironmentConfig
import static ConfigHelper.addMissingEnvVars

class ConfigHelperTest {

    @Test
    void addMissingEnvironmentConfig_notNull() throws Exception {
        def result = addMissingEnvironmentConfig(null)
        Assert.assertNotNull(result)
    }

    @Test
    void addMissingEnvironmentConfig_useDefaultsWhenNoValuesProvided() throws Exception {
        setup:
        def expectedEnv = [
                TF_INPUT        : 'false',
                TF_IN_AUTOMATION: 'true',
                TF_LOG          : 'INFO',
                TF_LOG_PATH     : 'terraform.log',
                TF_CLI_ARGS     : '',
                TF_DATA_DIR     : '.terraform',
                TF_IS_CONFIGURED: 'true'
        ]
        def env = null

        when:
        def actualEnv = addMissingEnvironmentConfig(env)

        then:
        Assert.assertEquals(expectedEnv, actualEnv)
    }

    @Test
    void addMissingEnvironmentConfig_tfInputAlwaysBecomeFalse() throws Exception {
        setup:
        def env = [
            TF_IS_CONFIGURED: 'true',
            TF_INPUT: 'true'
        ]

        when:
        def actualEnv = addMissingEnvironmentConfig(env)

        then:
        Assert.assertEquals('false', actualEnv.TF_INPUT)
    }

    @Test
    void addMissingEnvironmentConfig_tfInAutomationAlwaysBecomeTrue() throws Exception {
        setup:
        def env = [
                TF_IN_AUTOMATION: 'false'
        ]

        when:
        def actualEnv = addMissingEnvironmentConfig(env)

        then:
        Assert.assertEquals('true', actualEnv.TF_IN_AUTOMATION)
    }

    @Test
    void addMissingEnvironmentConfig_isAlreadyConfiguredAlwaysBecomeTrue() throws Exception {
        setup:
        def env = [
                TF_IS_CONFIGURED: 'true'
        ]

        when:
        def actualEnv = addMissingEnvironmentConfig(env)

        then:
        Assert.assertEquals('true', actualEnv.TF_IS_CONFIGURED)
    }

    @Test
    void addMissingEnvironmentConfig_isAddingVars() throws Exception {
        setup:
        def env = [:]
        def vars = [
                MY_VAR: 'true'
        ]

        when:
        def actualEnv = addMissingEnvVars(env, vars)

        then:
        Assert.assertEquals(true, actualEnv.containsKey('TF_VAR_MY_VAR'))
    }

    @Test
    void addMissingEnvironmentConfig_isNotOverridingExistingVars() throws Exception {
        setup:
        def env = [
                MY_VAR       : 'false',
                TF_VAR_MY_VAR: 'true'
        ]

        when:
        def actualEnv = addMissingEnvironmentConfig(env)

        then:
        Assert.assertEquals(env.TF_VAR_MY_VAR, actualEnv.TF_VAR_MY_VAR)
    }

    @Test
    void addMissingEnvVars_isOnlyAddingNonExistingVars() throws Exception {
        setup:
        def newExpectedVars = 2
        def env = [:]
        def vars = [
                MY_VAR       : false,
                MY_VAR1      : false,
                TF_VAR_MY_VAR: true
        ]

        when:
        def actualEnv = addMissingEnvVars(env, vars)

        then:
        Assert.assertEquals(newExpectedVars, actualEnv.size())
    }

    @Test
    void addMissingEnvVars_isNotAddingVariables() throws Exception {
        setup:
        def env = [:]
        def vars = [:]

        when:
        def actualEnv = addMissingEnvVars(env, vars)

        then:
        Assert.assertTrue(actualEnv.isEmpty())
    }
}