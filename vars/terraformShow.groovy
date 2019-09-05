import com.andres.terraform.commands.Show

import static com.andres.terraform.helpers.CommandHelper.getScript

def call(Map options) {
    runScript(script: getScript(terraformVersion(), Show.command, options), returnStdout: shouldReturn(options))
}

def shouldReturn(options) {
    return options.return ? true : false
}