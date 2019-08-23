import com.andres.terraform.commands.Validate

import static com.andres.terraform.helpers.CommandHelper.getScript

def call(Map options) {
    runScript(script: getScript(Validate.command, options))
}