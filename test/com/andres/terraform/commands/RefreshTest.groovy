package com.andres.terraform.commands

import com.andres.terraform.utils.TerraformVersion
import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript
import static com.andres.terraform.utils.TerraformVersion.MIN_VERSION_SUPPORTED

class RefreshTest {

    @Test
    void refreshCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform refresh -input=false"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Refresh.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void refreshCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform refresh -input=false"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Refresh.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void refreshCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform refresh -input=false -state=my-stafe.ftstate"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Refresh.command, ['-state': 'my-stafe.ftstate'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void refreshCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform refresh -input=false"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Refresh.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void refreshCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform refresh -input=false -lock-timeout=5s -lock=false -no-color"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Refresh.command, ['-lock-timeout': '5s', '-lock': 'false', '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void refreshCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform refresh -input=false my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Refresh.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void refreshCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform refresh -input=false my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Refresh.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void refreshCommand_varSeparatorIsWhitespace() throws Exception {
        setup:
        def expectedCommand = "terraform refresh -input=false -var 'my_var=test'"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Refresh.command, ['-var': "'my_var=test'"])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void refreshCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform refresh -input=false"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Refresh.command, ['arguments': '-input="true" my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void refreshCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform refresh -input=false my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Refresh.command, ['arguments': ['-input="true', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}