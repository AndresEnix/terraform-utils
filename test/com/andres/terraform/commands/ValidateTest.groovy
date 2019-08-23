package com.andres.terraform.commands

import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript

class ValidateTest {

    @Test
    void validateCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true"

        when:
        def actualCommand = getScript(Validate.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true"

        when:
        def actualCommand = getScript(Validate.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true -var-file=my-var-file.ftvars"

        when:
        def actualCommand = getScript(Validate.command, ['-var-file': 'my-var-file.ftvars'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true"

        when:
        def actualCommand = getScript(Validate.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true -var-file=my-var-file-tfvars -no-color"

        when:
        def actualCommand = getScript(Validate.command, ['-var-file': 'my-var-file-tfvars', '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true my_path"

        when:
        def actualCommand = getScript(Validate.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true my_path"

        when:
        def actualCommand = getScript(Validate.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_varSeparatorIsWhitespace() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true -var 'my_var=test'"

        when:
        def actualCommand = getScript(Validate.command, ['-var': "'my_var=test'"])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true"

        when:
        def actualCommand = getScript(Validate.command, ['arguments': '-var "my_var=true" my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void validateCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform validate -check-variables=true my_path"

        when:
        def actualCommand = getScript(Validate.command, ['arguments': ['-var "my_var=true', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}