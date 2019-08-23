package com.andres.terraform.commands

import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript

class GetTest {

    @Test
    void getCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform get"

        when:
        def actualCommand = getScript(Get.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void getCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform get"

        when:
        def actualCommand = getScript(Get.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void getCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform get -update=true"

        when:
        def actualCommand = getScript(Get.command, ['-update': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void getCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform get"

        when:
        def actualCommand = getScript(Get.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void getCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform get -update=true -no-color"

        when:
        def actualCommand = getScript(Get.command, ['-update': 'true', '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void getCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform get my_path"

        when:
        def actualCommand = getScript(Get.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void getCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform get my_path"

        when:
        def actualCommand = getScript(Get.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void getCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform get"

        when:
        def actualCommand = getScript(Get.command, ['arguments': '-update my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void getCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform get my_path"

        when:
        def actualCommand = getScript(Get.command, ['arguments': ['-update', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}