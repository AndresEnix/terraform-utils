import com.andres.terraform.commands.Output

import static com.andres.terraform.helpers.CommandHelper.getScript

def call(Map options) {
    runScript(script: getScript(Output.command, options))
}