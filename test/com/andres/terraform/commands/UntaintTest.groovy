package com.andres.terraform.commands

import com.andres.terraform.utils.TerraformVersion
import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript
import static com.andres.terraform.utils.TerraformVersion.MIN_VERSION_SUPPORTED

class UntaintTest {

    @Test
    void untaintCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform untaint"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Untaint.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void untaintCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform untaint"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Untaint.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void untaintCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform untaint -state=my-stafe.ftstate"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Untaint.command, ['-state': 'my-stafe.ftstate'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void untaintCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform untaint"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Untaint.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void untaintCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform untaint -lock-timeout=5s -lock=false -no-color"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Untaint.command, ['-lock-timeout': '5s', '-lock': 'false', '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void untaintCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform untaint my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Untaint.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void untaintCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform untaint my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Untaint.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void untaintCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform untaint"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Untaint.command, ['arguments': '-module="true" my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void untaintCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform untaint my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Untaint.command, ['arguments': ['-module="true', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}