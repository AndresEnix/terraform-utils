import com.andres.terraform.commands.Apply

import static com.andres.terraform.helpers.CommandHelper.getScript

def call(Map options) {
    runScript(script: getScript(Apply.command, options))
}