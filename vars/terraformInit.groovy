import com.andres.terraform.commands.Init

import static com.andres.terraform.helpers.CommandHelper.getScript

def call(Map options) {
    runScript(script: getScript(Init.command, options))
}