package com.andres.terraform.commands

import com.andres.terraform.utils.TerraformVersion
import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript
import static com.andres.terraform.utils.TerraformVersion.MIN_VERSION_SUPPORTED

class ValidateTest {

    @Test
    void validateCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Validate.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Validate.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true -var-file=my_var_file.ftvars"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Validate.command, ['-var-file': 'my_var_file.ftvars'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Validate.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true -var-file=my_var_file.tfvars -no-color"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Validate.command, ['-var-file': 'my_var_file.tfvars', '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Validate.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Validate.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_varSeparatorIsWhitespace() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true -var 'my_var=test'"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Validate.command, ['-var': "'my_var=test'"])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Validate.command, ['arguments': '-var "my_var=true" my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Validate.command, ['arguments': ['-var "my_var=true', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}