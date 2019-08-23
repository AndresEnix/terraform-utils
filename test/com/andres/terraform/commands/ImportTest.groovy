package com.andres.terraform.commands

import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript

class ImportTest {

    @Test
    void importCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform import -input=false"

        when:
        def actualCommand = getScript(Import.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void importCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform import -input=false"

        when:
        def actualCommand = getScript(Import.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void importCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform import -input=false -state=my-stafe.ftstate"

        when:
        def actualCommand = getScript(Import.command, ['-state': 'my-stafe.ftstate'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void importCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform import -input=false"

        when:
        def actualCommand = getScript(Import.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void importCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform import -input=false -lock-timeout=5s -lock=false -no-color"

        when:
        def actualCommand = getScript(Import.command, ['-lock-timeout': '5s', '-lock': 'false', '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void importCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform import -input=false my_path"

        when:
        def actualCommand = getScript(Import.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void importCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform import -input=false my_path"

        when:
        def actualCommand = getScript(Import.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void importCommand_varSeparatorIsWhitespace() throws Exception {
        setup:
        def expectedCommand = "terraform import -input=false -var 'my_var=test'"

        when:
        def actualCommand = getScript(Import.command, ['-var': "'my_var=test'"])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void importCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform import -input=false"

        when:
        def actualCommand = getScript(Import.command, ['arguments': '-input="true" my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void importCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform import -input=false my_path"

        when:
        def actualCommand = getScript(Import.command, ['arguments': ['-input="true', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}