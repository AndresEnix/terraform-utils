import com.andres.terraform.commands.Destroy

import static com.andres.terraform.helpers.CommandHelper.getScript

def call(Map options) {
    runScript(script: getScript(Destroy.command, options))
}