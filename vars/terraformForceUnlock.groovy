import com.andres.terraform.commands.ForceUnlock

import static com.andres.terraform.helpers.CommandHelper.getScript

def call(Map options) {
    runScript(script: getScript(terraformVersion(), ForceUnlock.command, options))
}