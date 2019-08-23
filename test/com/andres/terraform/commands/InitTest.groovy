package com.andres.terraform.commands

import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript

class InitTest {

    @Test
    void initCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform init -input=false -force-copy"

        when:
        def actualCommand = getScript(Init.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void initCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform init -input=false -force-copy"

        when:
        def actualCommand = getScript(Init.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void initCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform init -input=false -force-copy -from-module=my-module"

        when:
        def actualCommand = getScript(Init.command, ['-from-module': 'my-module'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void initCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform init -input=false -force-copy"

        when:
        def actualCommand = getScript(Init.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void initCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform init -input=false -force-copy -lock-timeout=5s -lock=false -no-color"

        when:
        def actualCommand = getScript(Init.command, ['-lock-timeout': '5s', '-lock': 'false', '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void initCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform init -input=false -force-copy my_path"

        when:
        def actualCommand = getScript(Init.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void initCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform init -input=false -force-copy my_path"

        when:
        def actualCommand = getScript(Init.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void initCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform init -input=false -force-copy"

        when:
        def actualCommand = getScript(Init.command, ['arguments': '-input="true" my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void initCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform init -input=false -force-copy my_path"

        when:
        def actualCommand = getScript(Init.command, ['arguments': ['-input="true', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}