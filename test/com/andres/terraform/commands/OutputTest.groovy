package com.andres.terraform.commands

import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript

class OutputTest {

    @Test
    void outputCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform output"

        when:
        def actualCommand = getScript(Output.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform output"

        when:
        def actualCommand = getScript(Output.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform output -state=my-stafe.ftstate"

        when:
        def actualCommand = getScript(Output.command, ['-state': 'my-stafe.ftstate'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform output"

        when:
        def actualCommand = getScript(Output.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform output -module=my-module -json -no-color"

        when:
        def actualCommand = getScript(Output.command, ['-module': 'my-module', '-json': null, '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform output my_path"

        when:
        def actualCommand = getScript(Output.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform output my_path"

        when:
        def actualCommand = getScript(Output.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform output"

        when:
        def actualCommand = getScript(Output.command, ['arguments': '-state="true" my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform output my_path"

        when:
        def actualCommand = getScript(Output.command, ['arguments': ['-state="true', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}