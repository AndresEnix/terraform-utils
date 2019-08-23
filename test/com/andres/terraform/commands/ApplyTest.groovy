package com.andres.terraform.commands

import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript

class ApplyTest {

    @Test
    void applyCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform apply -input=false -auto-approve"

        when:
        def actualCommand = getScript(Apply.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void applyCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform apply -input=false -auto-approve"

        when:
        def actualCommand = getScript(Apply.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void applyCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform apply -input=false -auto-approve -state=my-stafe.ftstate"

        when:
        def actualCommand = getScript(Apply.command, ['-state': 'my-stafe.ftstate'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void applyCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform apply -input=false -auto-approve"

        when:
        def actualCommand = getScript(Apply.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void applyCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform apply -input=false -auto-approve -lock-timeout=5s -lock=false -no-color"

        when:
        def actualCommand = getScript(Apply.command, ['-lock-timeout': '5s', '-lock': 'false', '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void applyCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform apply -input=false -auto-approve my_path"

        when:
        def actualCommand = getScript(Apply.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void applyCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform apply -input=false -auto-approve my_path"

        when:
        def actualCommand = getScript(Apply.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void applyCommand_varSeparatorIsWhitespace() throws Exception {
        setup:
        def expectedCommand = "terraform apply -input=false -auto-approve -var 'my_var=test'"

        when:
        def actualCommand = getScript(Apply.command, ['-var': "'my_var=test'"])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void applyCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform apply -input=false -auto-approve"

        when:
        def actualCommand = getScript(Apply.command, ['arguments': '-input=truemy_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void applyCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform apply -input=false -auto-approve my_path"

        when:
        def actualCommand = getScript(Apply.command, ['arguments': ['-var="my_var=test"', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}