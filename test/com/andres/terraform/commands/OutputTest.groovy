package com.andres.terraform.commands

import com.andres.terraform.utils.TerraformVersion
import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript
import static com.andres.terraform.utils.TerraformVersion.MIN_VERSION_SUPPORTED

class OutputTest {

    @Test
    void outputCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform output"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Output.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform output"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Output.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform output -state=my-stafe.ftstate"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Output.command, ['-state': 'my-stafe.ftstate'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform output"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Output.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform output -no-color -module=my_module -json"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Output.command, ['-module': 'my_module', '-json': null, '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform output my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Output.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform output my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Output.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform output"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Output.command, ['arguments': '-state="true" my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void outputCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform output my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Output.command, ['arguments': ['-state="true', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}