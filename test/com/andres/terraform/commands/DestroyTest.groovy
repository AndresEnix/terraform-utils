package com.andres.terraform.commands

import com.andres.terraform.utils.TerraformVersion
import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript
import static com.andres.terraform.utils.TerraformVersion.MIN_VERSION_SUPPORTED

class DestroyTest {

    @Test
    void destroyCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform destroy -auto-approve"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Destroy.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void destroyCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform destroy -auto-approve"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Destroy.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void destroyCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform destroy -auto-approve -state=my-stafe.ftstate"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Destroy.command, ['-state': 'my-stafe.ftstate'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void destroyCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform destroy -auto-approve"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Destroy.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void destroyCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform destroy -auto-approve -lock-timeout=5s -lock=false -no-color"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Destroy.command, ['-lock-timeout': '5s', '-lock': 'false', '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void destrroyCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform destroy -auto-approve my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Destroy.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void destroyCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform destroy -auto-approve my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Destroy.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void destroyCommand_varSeparatorIsWhitespace() throws Exception {
        setup:
        def expectedCommand = "terraform destroy -auto-approve -var 'my_var=test'"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Destroy.command, ['-var': "'my_var=test'"])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }


    @Test
    void destroyCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform destroy -auto-approve"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Destroy.command, ['arguments': '-state=false my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void destroyCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform destroy -auto-approve my_path"

        when:
        def actualCommand = getScript(new TerraformVersion(MIN_VERSION_SUPPORTED),Destroy.command, ['arguments': ['-state="my_var=test"', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}