package com.andres.terraform.commands

import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript

class PlanTest {

    @Test
    void planCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform plan -input=false -out=terraform.tfplan"

        when:
        def actualCommand = getScript(Plan.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void planCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform plan -input=false -out=terraform.tfplan"

        when:
        def actualCommand = getScript(Plan.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void planCommand_withSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform plan -input=false -out=terraform.tfplan -state=my-stafe.ftstate"

        when:
        def actualCommand = getScript(Plan.command, ['-state': 'my-stafe.ftstate'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void planCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform plan -input=false -out=terraform.tfplan"

        when:
        def actualCommand = getScript(Plan.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void planCommand_withMultipleSupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform plan -input=false -out=terraform.tfplan -lock-timeout=5s -lock=false -no-color"

        when:
        def actualCommand = getScript(Plan.command, ['-lock-timeout': '5s', '-lock': 'false', '-no-color': null])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void planCommand_withSpecifiedOut() throws Exception {
        setup:
        def expectedCommand = "terraform plan -input=false -out=my-custom-out.tfplan"

        when:
        def actualCommand = getScript(Plan.command, ['-out': 'my-custom-out.tfplan'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void planCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform plan -input=false -out=terraform.tfplan my_path"

        when:
        def actualCommand = getScript(Plan.command, ['arguments': 'my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void planCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform plan -input=false -out=terraform.tfplan my_path"

        when:
        def actualCommand = getScript(Plan.command, ['arguments': ['my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void planCommand_varSeparatorIsWhitespace() throws Exception {
        setup:
        def expectedCommand = "terraform plan -input=false -out=terraform.tfplan -var 'my_var=test'"

        when:
        def actualCommand = getScript(Plan.command, ['-var': "'my_var=test'"])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void planCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform plan -input=false -out=terraform.tfplan"

        when:
        def actualCommand = getScript(Plan.command, ['arguments': '-input="true" my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void planCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform plan -input=false -out=terraform.tfplan my_path"

        when:
        def actualCommand = getScript(Plan.command, ['arguments': ['-input="true', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}