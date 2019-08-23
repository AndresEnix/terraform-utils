import com.andres.terraform.commands.Taint

import static com.andres.terraform.helpers.CommandHelper.getScript

def call(Map options) {
    runScript(script: getScript(Taint.command, options))
}