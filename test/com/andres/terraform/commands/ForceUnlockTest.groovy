package com.andres.terraform.commands

import org.junit.Assert
import org.junit.Test

import static com.andres.terraform.helpers.CommandHelper.getScript

class ForceUnlockTest {

    @Test
    void forceUnlockCommand_withDefaultOptions() throws Exception {
        setup:
        def expectedCommand = "terraform force-unlock -force"

        when:
        def actualCommand = getScript(ForceUnlock.command, null)

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void forceUnlockCommand_withUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform force-unlock -force"

        when:
        def actualCommand = getScript(ForceUnlock.command, ['-xxfg': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void forceUnlockCommand_withMultipleUnsupportedOption() throws Exception {
        setup:
        def expectedCommand = "terraform force-unlock -force"

        when:
        def actualCommand = getScript(ForceUnlock.command, ['-abc': 'true', '-def': 'true', '-ghi': 'true'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void forceUnlockCommand_withStringArguments() throws Exception {
        setup:
        def expectedCommand = "terraform force-unlock -force lock_id my_path"

        when:
        def actualCommand = getScript(ForceUnlock.command, ['arguments': 'lock_id my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void forceUnlockCommand_withCollectionArguments() throws Exception {
        setup:
        def expectedCommand = "terraform force-unlock -force lock_id my_path"

        when:
        def actualCommand = getScript(ForceUnlock.command, ['arguments': ['lock_id', 'my_path']])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }


    @Test
    void forceUnlockCommand_argumentDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform force-unlock -force"

        when:
        def actualCommand = getScript(ForceUnlock.command, ['arguments': '-force my_path'])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }

    @Test
    void forceUnlockCommand_argumentsDoNotContainsOptions() throws Exception {
        setup:
        def expectedCommand = "terraform force-unlock -force my_path"

        when:
        def actualCommand = getScript(ForceUnlock.command, ['arguments': ['-force', "my_path"]])

        then:
        Assert.assertEquals(expectedCommand, actualCommand)
    }
}