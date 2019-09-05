package com.andres.terraform.commands

import com.andres.terraform.utils.TerraformVersion
import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript
import static com.andres.terraform.utils.TerraformVersion.MAX_VERSION_SUPPORTED
import static com.andres.terraform.utils.TerraformVersion.MIN_VERSION_SUPPORTED

class ShowTest {

    @Test
    void showCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform show"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Show.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void showCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform show"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Show.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void showCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform show -no-color"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Show.command, ['-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void showCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform show"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Show.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void showCommand_jsonOptionInTerraformTwelve() throws Exception {
        setup:
        def expectedCommand = "terraform show -no-color"

        when:
        def actualCommand = getScript(new TerraformVersion(MAX_VERSION_SUPPORTED),Show.command, ['-no-color': null, '-json': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void showCommand_jsonOptionInTerraformEleven() throws Exception {
        setup:
        def expectedCommand = "terraform show -no-color -json"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Show.command, ['-no-color': null, '-json': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void showCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform show my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Show.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void showCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform show my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Show.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void showCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform show"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Show.command, ['arguments': '-json my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void showCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform show my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Show.command, ['arguments': ['-json', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}