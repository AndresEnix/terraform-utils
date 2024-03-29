package com.andres.terraform.commands

import com.andres.terraform.utils.TerraformVersion
import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript
import static com.andres.terraform.utils.TerraformVersion.MIN_VERSION_SUPPORTED

class TaintTest {

    @Test
    void taintCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform taint"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Taint.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void taintCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform taint"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Taint.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void taintCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform taint -state=my-stafe.ftstate"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Taint.command, ['-state': 'my-stafe.ftstate'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void taintCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform taint"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Taint.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void taintCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform taint -lock-timeout=5s -lock=false -no-color"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Taint.command, ['-lock-timeout': '5s', '-lock': 'false', '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void taintCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform taint my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Taint.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void taintCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform taint my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Taint.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void taintCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform taint"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Taint.command, ['arguments': '-module="true" my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void taintCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform taint my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Taint.command, ['arguments': ['-module="true', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}