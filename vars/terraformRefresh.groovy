import com.andres.terraform.commands.Refresh

import static com.andres.terraform.helpers.CommandHelper.getScript

def call(Map options) {
    runScript(script: getScript(terraformVersion(), Refresh.command, options))
}